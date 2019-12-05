package y2019.io

object DefaultBuffer: InputOutput {
  override val name = "default"

  private var value: Int? = null

  override fun get(index: Int?): Int {
    return value!!
  }

  override fun set(value: Int) {
    println("Set $name to $value")
    this.value = value
  }
}