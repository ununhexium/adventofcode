package p2

import more.Input

val input0 = """5 9 2 8
9 4 7 3
3 8 6 5"""

val inputs = listOf(
    input0,
    Input.getFor("p2")
)

fun main(args: Array<String>)
{
  inputs.forEach {
    println(checksum(it))
    println(checksum2(it))
  }
}

fun checksum2(input: String): Int
{
  val grid = inputToGrid(input)
  return grid.map { it -> findIntDiv(it) }.sum()
}

private fun findIntDiv(line: List<Int>): Int
{
  line.map { a ->
    line.map { b ->
      if (a!=b && a % b == 0)
      {
        println("$a and $b")
        return Math.max(a, b) / Math.min(a, b)
      }
    }
  }
  throw RuntimeException("Data is wrong")
}

private fun checksum(input: String): Int
{
  val grid = inputToGrid(input)
  return grid.map { it.max()!! - it.min()!! }.sum()
}

private fun inputToGrid(input: String): List<List<Int>> =
    input.split("\n").map { it.split(" ").map { it.toInt() } }