package y2019.instruction

class JumpIfFalse(raw: Int) : Instruction(
    2,
    raw / 100
) {
  override fun execute(ptr: Int, program: MutableList<Int>): Int {
    val (param0, param1) = getParameters(ptr, program)

    return if (param0.get() == 0) {
      param1.get()
    } else {
      nextInstruction(ptr)
    }
  }
}
