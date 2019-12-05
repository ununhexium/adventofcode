package y2019.instruction

class Equals(raw: Int) : Instruction(
    3,
    raw / 100
) {
  override fun execute(ptr: Int, program: MutableList<Int>): Int {
    val (param0, param1, param2) = getParameters(ptr, program)

    if (param0.get() == param1.get()) {
      param2.set(1)
    } else {
      param2.set(0)
    }

    return nextInstruction(ptr)
  }
}
