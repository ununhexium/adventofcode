package y2019.io

interface Output {
  /**
   * The name of this port
   */
  val name: String

  /**
   * Sets the value at this port
   */
  fun set(value: Int)
}