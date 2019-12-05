package y2019.memory

object PositionalMemoryAccessor : MemoryAccessor {
  override fun get(address: Int, program: List<Int>) =
      program[program[address]]

  override fun set(address: Int, program: MutableList<Int>, value: Int) {
    program[program[address]] = value
  }

  override fun toString() = "P"
}