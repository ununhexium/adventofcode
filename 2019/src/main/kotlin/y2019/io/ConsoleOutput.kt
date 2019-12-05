package y2019.io

object ConsoleOutput : Output {
  override val name = "console"

  override fun set(value: Int) {
    println(value)
  }
}
