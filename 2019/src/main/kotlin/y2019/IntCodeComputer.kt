package y2019

import y2019.instruction.AddInstruction
import y2019.instruction.Halt
import y2019.instruction.Load
import y2019.instruction.MulInstruction
import y2019.instruction.Store
import y2019.io.InputOutput
import y2019.io.PrintingInputOutput
import java.util.concurrent.atomic.AtomicReference

class IntCodeComputer(
    input: List<Int>,
    val noun: Int? = null,
    val verb: Int? = null,
    val initialIo: Map<String, Int>? = null
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
      PrintingInputOutput("default")
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
      3 -> Store(raw, io())
      4 -> Load(raw, io())

      99 -> Halt()

      else -> throw IllegalStateException("No instruction for opcode $raw")
    }
  }

  fun compute(): List<Int> {
    var ptr = 0

    try {
      out@ while (true) {
        val op = getInstruction(internalProgram[ptr])
        ptr = op.tick(ptr, internalProgram)
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