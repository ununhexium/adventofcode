package y2019.memory

object ImmediateMemoryAccessor : MemoryAccessor {
  override fun get(address: Int, program: List<Int>) =
      program[address]

  override fun set(address: Int, program: MutableList<Int>, value: Int) {
    program[address] = value
  }

  override fun toString() = "I"
}