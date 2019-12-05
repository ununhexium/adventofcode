package y2019

import y2019.instruction.AddInstruction
import y2019.instruction.Equals
import y2019.instruction.Halt
import y2019.instruction.Instruction
import y2019.instruction.JumpIfFalse
import y2019.instruction.JumpIfTrue
import y2019.instruction.LessThan
import y2019.instruction.MulInstruction
import y2019.instruction.Peek
import y2019.instruction.Poke
import y2019.io.DefaultBuffer
import y2019.io.DelegatedInputOutput
import y2019.io.InputOutput

class IntCodeComputer(
    input: List<Int>,
    val noun: Int? = null,
    val verb: Int? = null,
    val initialIo: Map<String, Int>? = null,
    val defaultIO: InputOutput = DefaultBuffer
) {
  constructor(
      input: String,
      noun: Int? = null,
      verb: Int? = null,
      initialIo: Map<String, Int>? = null
  ) : this(
      input.split(',').map { it.toInt() },
      noun,
      verb,
      initialIo
  )

  private val internalProgram: MutableList<Int> = input.toMutableList()

  val program: List<Int>
    get() =
      internalProgram

  private val internalIO = listOf(
      DelegatedInputOutput("default", defaultIO, defaultIO)
  )
      .map { it.name to it }
      .toMap()

  fun io(name: String? = null): InputOutput =
      if (name == null) {
        internalIO["default"]
            ?: error("No port named $name. Available ports are: ${internalIO.keys}")
      } else {
        internalIO[name]
            ?: error("No port named $name. Available ports are: ${internalIO.keys}")
      }

  private fun getInstruction(raw: Int): Instruction {
    // TODO: cache if perf problems
    return when (raw % 100) {
      1 -> AddInstruction(raw)
      2 -> MulInstruction(raw)
      3 -> Peek(raw, io())
      4 -> Poke(raw, io())
      5 -> JumpIfTrue(raw)
      6 -> JumpIfFalse(raw)
      7 -> LessThan(raw)
      8 -> Equals(raw)

      99 -> Halt()

      else -> throw IllegalStateException("No instruction for opcode $raw")
    }
  }

  fun compute(): List<Int> {
    var ptr = 0

    try {
      while (true) {
        val op = getInstruction(internalProgram[ptr])
        ptr = op.execute(ptr, internalProgram)
      }
    } catch (e: HaltException) {
      return internalProgram
    }
  }

  fun init(): IntCodeComputer {
    internalProgram[1] = noun ?: internalProgram[1]
    internalProgram[2] = verb ?: internalProgram[2]
    initialIo?.let {
      it.entries.forEach { (key, value) ->
        io(key).set(value)
      }
    }

    return this
  }

  fun run(): Int {
    init()
    compute()
    return internalProgram[0]
  }
}