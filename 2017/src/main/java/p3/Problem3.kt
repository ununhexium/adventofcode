package p3

import more.format
import more.next
import p3.Position.x
import p3.Position.y
import kotlin.math.ceil
import kotlin.math.log10

// https://adventofcode.com/2017/day/3

val inputs = listOf(
    1,
    1024,
    289326
)

fun main(args: Array<String>)
{
  inputs.forEach {
    println(buildSpiral(it))
  }
}

private fun distance(input: Int): Int
{
  if (input == 1) return 0

  val MAX = 1000
  /**
   * ring / max value
   * 0 / 1
   * 1 / 9
   * 2 / 25
   * ... / ...
   */
  val ring =
      (0..MAX)
          .asSequence()
          .first { it -> ringToSquare(it) >= input }

  // ring 2 has length 2, ring 3 has length 4
  val ringSideLength = ringToSide(ring)
  // the surface of the previous ring
  val previousSquareRing = ringToSquare(ring - 1)
  // 1, 9, 25, 49, 81 etc. have index 0
  // then it goes +1 in the same direction as the original spiral
  val ringIndex = input - previousSquareRing - ring
  val edgeIndex = ringIndex % (ringSideLength - 1)
  return ring + edgeIndex
}

fun square(i: Int) = i * i

fun ringToSide(i: Int) = i * 2 + 1

fun ringToSquare(i: Int) = square(ringToSide(i))

object Position
{
  var x: Int = 0
  var y: Int = 0
}

enum class Direction
{
  UP,
  LEFT,
  DOWN,
  RIGHT
}

fun moveThere(direction: Direction, distance: Int)
{
  when (direction)
  {
    Direction.UP    -> Position.y += distance
    Direction.DOWN  -> Position.y -= distance
    Direction.LEFT  -> Position.x -= distance
    Direction.RIGHT -> Position.x += distance
  }
}

fun buildSpiral(stopAfter: Int): Int
{
  val side = 20
  val grid = Array(side, { Array(side, { 0 }) })
  Position.x = side / 2
  Position.y = side / 2
  grid[x][y] = 1

  // 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, ....
  var direction = Direction.RIGHT
  val increments = (1..side).map { listOf(it, it) }.flatten()
  val increment = increments.iterator()

  while (x > 0
      && x < side - 1
      && y > 0
      && y < side - 1
      )
  {
    (0 until increment.next()).forEach {
      /**
       * a b c
       * d e f
       * g h i
       *
       * sum a to i, screen coordinates
       */
      grid[x][y] = (-1..1).map { yOffset ->
        (-1..1).map { xOffset ->
          grid[x + xOffset][y + yOffset]
        }.sum()
      }.sum()

      println("[$x;$y] = " + grid[x][y])
      if (grid[x][y] > stopAfter)
      {
        printFormatedGrid(grid)
        return grid[x][y]
      }

      moveThere(direction, 1)
    }
    direction = direction.next()
  }
  printFormatedGrid(grid)
  return -1
}

private fun printFormatedGrid(grid: Array<Array<Int>>)
{
  val length = ceil(log10(grid.flatten().max()!!.toDouble() + 1.0)).toInt()
  println(
      grid.joinToString(separator = "") {
        "\n" + it.joinToString(separator = " ") {
          it.format(length)
        }
      }
  )
}