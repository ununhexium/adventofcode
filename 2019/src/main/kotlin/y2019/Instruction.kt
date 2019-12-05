package y2019

import y2019.memory.ImmediateMemoryAccessor
import y2019.memory.MemoryAccessor
import y2019.memory.PositionalMemoryAccessor

open class Instruction(
    val parameterCount: Int,
    modesCode: Int,
    /**
     * @param 1 Instruction Pointer
     * @param 2 Program
     * @param 3 How to access the memory
     *
     * @return nextPointer
     */
    val execute: (Int, MutableList<Int>, Array<MemoryAccessor>) -> Unit
) {
  private val modes = (0 until parameterCount).map {
    when (
      val accessMode = String
          .format("%0${parameterCount}d", modesCode)
          .reversed()[it]
      ) {
      '0' -> PositionalMemoryAccessor
      '1' -> ImmediateMemoryAccessor
      else -> throw IllegalStateException("No memory access more for $accessMode")
    }
  }.toTypedArray()

  /**
   * Executes 1 instruction and returns the next instruction pointer value
   */
  fun tick(ptr: Int, program: MutableList<Int>): Int {
    execute(ptr, program, modes)
    return ptr + parameterCount + 1
  }

  override fun toString(): String {
    return "Instruction(parameterCount=$parameterCount, mode=${modes.contentToString()}, execute=$execute)"
  }

}

