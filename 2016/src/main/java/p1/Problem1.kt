package p1

import more.Input
import more.format
import more.next
import more.previous
import p1.Direction.DOWN
import p1.Direction.LEFT
import p1.Direction.RIGHT
import p1.Direction.UP
import p1.Position.x
import p1.Position.y
import java.io.FileWriter
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.log10

// The problem comes from http://adventofcode.com/2016/day/1

object Position
{
  var x: Int = 0
  var y: Int = 0
}

enum class Direction
{
  //order is important for turning
  RIGHT,
  UP,
  LEFT,
  DOWN,
  //
  ;


  companion object
  {
    fun from(c: Char) : Direction
    {
      return when (c)
      {
        'U'  -> UP
        'D'  -> DOWN
        'L'  -> LEFT
        'R'  -> RIGHT
        else -> throw RuntimeException("Nope, fix that: " + c)
      }
    }
  }
}

fun moveThere(
    direction: Direction,
    distance: Int,
    path: MutableList<Pair<Int, Int>>
)
{
  (1..distance).forEach { step ->
    when (direction)
    {
      UP    -> Position.y += 1
      DOWN  -> Position.y -= 1
      LEFT  -> Position.x -= 1
      RIGHT -> Position.x += 1
    }
    path.add(x to y)
  }
}

private fun walk(input: String): Pair<Int, Int>
{
  val memory = mutableListOf(x to y)
  var direction = UP
  input.split(", ").mapIndexed { index, movement ->
    val turn = movement[0]
    direction = when (turn)
    {
      'R'  -> direction.next()
      'L'  -> direction.previous()
      else -> throw RuntimeException("Dafuq?")
    }
    val distance = movement.substring(1).toInt()
    moveThere(direction, distance, memory)
  }

  val duplicatePositions = memory
      .mapIndexed { index, content ->
        index to content
      }
      // group by position
      .groupBy { it.second }
      // to find duplicated positions
      .filterValues { it.size > 1 }
      // and among the duplicated ones
      .values
      .flatten()
      // order them by index (of memory list)
      .sortedBy { it.first }

  println(duplicatePositions)
  println(duplicatePositions.first())

  val firstCross = duplicatePositions
      // then keep only the first duplicated position
      .first()
      // of which we only want to actual position
      .second

  // to view the path in Office
  FileWriter("/tmp/out.csv").use {
    it.write(show(memory))
  }

  return (abs(x) + abs(y)) to (abs(firstCross.first) + abs(firstCross.second))
}

fun show(memory: MutableList<Pair<Int, Int>>): String
{
  val xs = memory.map { it.first }
  val ys = memory.map { it.second }
  val xMax = xs.max()!!
  val xMin = xs.min()!!
  val yMax = ys.max()!!
  val yMin = ys.min()!!

  val grid = Array(xMax - xMin + 1, { Array(yMax - yMin + 1, { 0 }) })
  memory.forEachIndexed { index, step ->
    grid[step.first - xMin][step.second - yMin] = index + 116
  }

  val digits = ceil(
      log10(
          grid.map { it.max()!! }.max()!! + 1.0
      )
  ).toInt()

  return grid.joinToString(separator = "\n") {
    it.joinToString(separator = ",") {
      it.format(digits)
    }
  }
}

fun main(args: Array<String>)
{
  println(walk(Input.getFor("p1")))
}
