package y2019.instruction

import y2019.memory.ImmediateMemoryAccessor
import y2019.memory.MemoryAccessor
import y2019.memory.Parameter
import y2019.memory.PositionalMemoryAccessor

abstract class Instruction(
    val parameterCount: Int,
    modesCode: Int
) {
  protected val accessors = (0 until parameterCount).map {
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

  override fun toString(): String {
    return "Instruction(parameterCount=$parameterCount, mode=${accessors.contentToString()})"
  }

  fun nextInstruction(ptr: Int): Int =
      ptr + parameterCount + 1

  fun getParameters(ptr: Int, program: MutableList<Int>): List<Parameter> {
    // TODO: PERF could be cached
    return (0 until parameterCount).map {
      object : Parameter {
        override fun get(): Int =
            accessors[it][ptr + it + 1, program]

        override fun set(value: Int) {
          accessors[it][ptr + it + 1, program] = value
        }
      }
    }
  }

  /**
   * @param ptr Instruction Pointer
   * @param program Memory for both instructions and data
   *
   * @return next pointer value
   */
  abstract fun execute(ptr: Int, program: MutableList<Int>): Int
}
