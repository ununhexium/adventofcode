package y2019.io

class PrintingInputOutput(override val name: String) : InputOutput {
  private val history = mutableListOf<Int>()

  override fun get(index: Int?): Int {
    if (history.isEmpty()) {
      throw IllegalStateException("Can't read $name: no value")
    }
    return history.last()
  }

  override fun set(value: Int) {
    println("Set $name to $value")
    history.add(value)
  }
}