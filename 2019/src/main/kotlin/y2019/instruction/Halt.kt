package y2019.instruction

import y2019.HaltException

class Halt : Instruction(0, 0) {
  override fun execute(ptr: Int, program: MutableList<Int>): Int {
    throw HaltException()
  }
}
