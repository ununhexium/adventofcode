package p6

import more.Input

fun main(args: Array<String>)
{
  val words = Input
      .getFor("p6")
      .split("\n")

  println(unjam(words))
}

fun unjam(input: List<String>): String
{
  return (0..input.first().lastIndex)
      .map { index ->
        input
            .map { it[index] }
            .groupBy { it }
            .mapValues { it.value.size }
            .toList()
            .sortedBy { it.second }
//            .reversed()
            .first()
            .first
      }
      .joinToString("")
}
