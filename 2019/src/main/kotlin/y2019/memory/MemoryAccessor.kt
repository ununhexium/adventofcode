package y2019.memory

interface MemoryAccessor {
  operator fun get(address: Int, program: List<Int>): Int
  operator fun set(address: Int, program: MutableList<Int>, value: Int)
}