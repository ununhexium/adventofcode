package y2019.memory

interface MemoryAccessor {
  operator fun get(program: List<Int>, address: Int): Int
  operator fun set(program: MutableList<Int>, address: Int, value: Int)
}