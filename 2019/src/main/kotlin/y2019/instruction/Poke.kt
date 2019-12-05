package y2019.instruction

import y2019.io.InputOutput

class Poke(raw: Int, val io: InputOutput) : Instruction(
    1,
    raw / 100
) {
  override fun execute(ptr: Int, program: MutableList<Int>): Int {
    val (param0) = getParameters(ptr, program)

    io.set(param0.get())

    return nextInstruction(ptr)
  }
}
