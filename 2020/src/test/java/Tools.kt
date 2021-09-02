object Tools {
  fun load(s: String): List<String> {
    return Tools::class.java.getResource("$s.txt")!!.readText().lines().dropLast(1)
  }
}
