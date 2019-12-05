package y2019

import com.google.common.io.Resources
import java.lang.Long.max

object D1A {
  fun solve(): Long {
    val txt = Resources.getResource("1.input.txt").readText().split("\n")
    return txt.fold(0L) { acc, e ->
      acc + e.toLong() / 3L - 2L
    }
  }
}

object D1B {
  fun solve(input: List<Long>): Long {
    return input.fold(0L) { acc, e ->
      acc + solve1(e)
    }
  }

  fun solve1(input: Long): Long {
    return if (input <= 0L) {
      0L
    } else {
      val current = max(0L, input / 3L - 2L)
      return current + solve1(current)
    }
  }
}