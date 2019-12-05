package y2019.memory

object ImmediateMemoryAccessor : MemoryAccessor {
  override fun get(program: List<Int>, address: Int) =
      program[address]

  override fun set(program: MutableList<Int>, address: Int, value: Int) {
    program[address] = value
  }

  override fun toString() = "I"
}