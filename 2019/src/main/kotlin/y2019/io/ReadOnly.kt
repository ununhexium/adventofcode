package y2019.io

object ReadOnly: Output {
  override val name = "Read Only"

  override fun set(value: Int) {
    throw IllegalStateException("Read only port")
  }
}