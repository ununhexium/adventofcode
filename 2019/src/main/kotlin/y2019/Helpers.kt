package y2019


fun <T> List<T>.permutations(): Sequence<List<T>> = sequence {
  val copy = this@permutations.toMutableList()
  val fakeStack = IntArray(copy.size) { 0 }

  /**
   * Swaps a and b and returns the list itself
   */

  /**
   * Swaps a and b and returns the list itself
   */
  /**
   * Swaps a and b and returns the list itself
   */
  /**
   * Swaps a and b and returns the list itself
   */
  fun <E> MutableList<E>.swap(a: Int, b: Int): MutableList<E> =
      this.also { this[a] = this[b].also { this[b] = this[a] } }

  yield(copy)

  var stackPointer = 0
  while (stackPointer < copy.size) {
    if (fakeStack[stackPointer] < stackPointer) {
      yield(
          if (stackPointer % 2 == 0) copy.swap(0, stackPointer)
          else copy.swap(fakeStack[stackPointer], stackPointer)
      )
      fakeStack[stackPointer] += 1
      stackPointer = 0
    } else {
      fakeStack[stackPointer] = 0
      stackPointer++
    }
  }
}
