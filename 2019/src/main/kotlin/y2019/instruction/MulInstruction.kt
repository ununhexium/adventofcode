package y2019.instruction

class MulInstruction(raw: Int) : Instruction(
    3,
    raw / 100
) {
  override fun execute(ptr: Int, program: MutableList<Int>): Int {
    val (param0,param1,param2) = getParameters(ptr, program)

    param2.set(param0.get() * param1.get())

    return nextInstruction(ptr)
  }
}
