package y2019

object D4A {
  fun solve(range: IntRange) =
      // in range
      range.filter {
        // has length 6
        it.toString().length == 6
      }.filter {
        // has 2 identical elements 1 next to the other
        it.toString().zipWithNext().any { it.first == it.second }
      }.filter {
        // never decreases
        it.toString().zipWithNext().all { it.first <= it.second }
      }
}