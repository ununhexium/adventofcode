package p3

import com.google.common.collect.Lists
import more.Input

fun main(args: Array<String>)
{
  val sides = Input
      .getFor("p3")
      .split("\n")
      .map { it.trim().replace(Regex(" +"), " ").split(" ") }
      .map { it.map { it.toInt() } }

  readByLine(sides)
  readByColumn(sides)
}

fun readByColumn(sides: List<List<Int>>)
{
  val columns = sides.map { it[0] } + sides.map { it[1] } + sides.map { it[2] }
  val realTriangles = Lists
      .partition(columns, 3)
      .sumBy { if (isTriangle(it)) 1 else 0 }

  println("By column: " + realTriangles)
}

private fun readByLine(sides: List<List<Int>>)
{
  val realTriangles = sides.sumBy { if (isTriangle(it)) 1 else 0 }
  println("By line: " + realTriangles)
}

fun isTriangle(sides: List<Int>): Boolean
{
  val sortedSides = sides.sorted()
  return sortedSides[2] < (sortedSides[1] + sortedSides[0])
}
