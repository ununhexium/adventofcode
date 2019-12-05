package y2019.io

interface Input {
  /**
   * The name of this port
   */
  val name: String

  /**
   * Get the value at this port. If no index is given, returns the latest value
   */
  fun get(index: Int? = null): Int
}