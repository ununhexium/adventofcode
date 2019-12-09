package y2019.io

class DefaultBuffer(
    private var value: Int? = null
) : InputOutput {
  override val name = "default"

  override fun get(index: Int?): Int {
    return value!!
  }

  override fun set(value: Int) {
    this.value = value
  }
}