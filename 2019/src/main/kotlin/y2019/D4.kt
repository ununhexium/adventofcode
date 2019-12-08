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

object D4B {
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
      }.filter {
        // the two adjacent matching digits are not part of a larger group of matching digits
        it
            .toString()
            .groupBy { it }
            .values
            .groupBy { it.size }[2]
            ?.size
            ?: 0 >= 1
      }
}
