fun day1a(input: List<Int>): Pair<Int, Int> {
  input.forEach { a ->
    input.forEach { b ->
      if (a + b == 2020) return a to b
    }
  }

  throw IllegalStateException("Must not happen")
}

fun day1b(input: List<Int>): Triple<Int, Int, Int> {
  input.forEach { a ->
    input.forEach { b ->
      input.forEach { c ->
        if (a + b + c == 2020) return Triple(a, b, c)
      }
    }
  }

  throw IllegalStateException("Must not happen")
}
