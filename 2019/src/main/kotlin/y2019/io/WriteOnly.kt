package y2019.io

object WriteOnly: Input {
  override val name = "WriteOnly"

  override fun get(index: Int?): Int {
    throw IllegalStateException("Write only")
  }
}
