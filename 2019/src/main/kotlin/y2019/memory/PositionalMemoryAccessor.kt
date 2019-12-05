package y2019.memory

object PositionalMemoryAccessor : MemoryAccessor {
  override fun get(program: List<Int>, address: Int) =
      program[program[address]]

  override fun set(program: MutableList<Int>, address: Int, value:Int) {
    program[program[address]] = value
  }

  override fun toString() = "P"
}