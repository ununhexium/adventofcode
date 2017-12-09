package p4

import com.google.common.io.Resources


fun main(args: Array<String>)
{
  val input = Resources.toString(
      Resources.getResource("p4/input.txt"),
      Charsets.UTF_8
  )

  println(
      input
          .split("\n")
          .map {
            it
                .split(" ")
                .map { it.map { it }.sorted().joinToString(separator = "") }
                .groupBy { it }
                .map { it.value.size }
                .max()!! < 2
          }
          .map { if (it) 1 else 0 }
          .sum()
  )
}