package p1

import p1.Direction.DOWN
import p1.Direction.LEFT
import p1.Direction.RIGHT
import p1.Direction.UP

// The problem comes from http://adventofcode.com/2016/day/1

// TODO: extract

/**
 * Generic way to list the values of an Enum
 */
inline fun <reified T> Enum<T>.listValues() where T : Enum<T> =
    this.javaClass.enumConstants.toList()

/**
 * Generic way to go to the X next or previous item in an enum.
 * If the index overflows, it loops.
 *
 * @return The enum constant [offset] indexes away.
 */
inline fun <reified T> Enum<T>.offset(offset: Int) where T : Enum<T> =
    Direction.values()[
        Math.floorMod(
            this.ordinal + offset,
            this.listValues().size
        )
        ]

/**
 * @return The next Enum entry
 */
inline fun <reified T> Enum<T>.previous() where T : Enum<T> =
    this.offset(-1)

/**
 * @return The previous Enum entry
 */
inline fun <reified T> Enum<T>.next() where T : Enum<T> =
    this.offset(+1)


val input = """R3, L5, R2, L2, R1, L3, R1, R3, L4, R3, L1, L1, R1, L3, R2, L3, L2, R1, R1, L1, R4, L1, L4, R3, L2, L2, R1, L1, R5, R4, R2, L5, L2, R5, R5, L2, R3, R1, R1, L3, R1, L4, L4, L190, L5, L2, R4, L5, R4, R5, L4, R1, R2, L5, R50, L2, R1, R73, R1, L2, R191, R2, L4, R1, L5, L5, R5, L3, L5, L4, R4, R5, L4, R4, R4, R5, L2, L5, R3, L4, L4, L5, R2, R2, R2, R4, L3, R4, R5, L3, R5, L2, R3, L1, R2, R2, L3, L1, R5, L3, L5, R2, R4, R1, L1, L5, R3, R2, L3, L4, L5, L1, R3, L5, L2, R2, L3, L4, L1, R1, R4, R2, R2, R4, R2, R2, L3, L3, L4, R4, L4, L4, R1, L4, L4, R1, L2, R5, R2, R3, R3, L2, L5, R3, L3, R5, L2, R3, R2, L4, L3, L1, R2, L2, L3, L5, R3, L1, L3, L4, L3"""

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
  DOWN
}

fun moveThere(direction: Direction, distance: Int)
{
  when (direction)
  {
    UP    -> Position.y += distance
    DOWN  -> Position.y -= distance
    LEFT  -> Position.x -= distance
    RIGHT -> Position.x += distance
  }
}

private fun walk(input: String): Int
{
  var direction = UP
  input.split(", ").map { movement ->
    direction = when (movement[0])
    {
      'R'  -> direction.next()
      'L'  -> direction.previous()
      else -> throw RuntimeException("Dafuq?")
    }
    moveThere(direction, movement.substring(1).toInt())
  }
  return Position.x + Position.y
}

fun main(args: Array<String>)
{
  println(walk(input))
}
