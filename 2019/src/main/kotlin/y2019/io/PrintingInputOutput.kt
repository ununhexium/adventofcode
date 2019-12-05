package y2019.io

class PrintingInputOutput(override val name: String) : InputOutput {
  var value: Int? = null

  override fun get(index: Int?): Int {
    val now = value ?: throw IllegalStateException("Can't read $name: no value")
    return now
  }

  override fun set(value: Int) {
    println("Set $name to $value")
    this.value = value
  }
}