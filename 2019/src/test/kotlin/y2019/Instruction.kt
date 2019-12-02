package y2019

data class Instruction(
    val parameterCount: Int,
    /**
     * @param 1 Instruction Pointer
     * @param 2 Program
     */
    val execute: (Int, MutableList<Int>) -> Unit
) {
  /**
   * Executes 1 instruction
   *
   * @return the next instruction pointer value
   */
  fun tick(ptr: Int, program: MutableList<Int>): Int {
    execute(ptr, program)
    return ptr + parameterCount + 1
  }
}

