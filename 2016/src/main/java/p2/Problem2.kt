package p2

import com.google.common.io.Resources
import more.Input
import p1.Direction
import p1.Direction.DOWN
import p1.Direction.LEFT
import p1.Direction.RIGHT
import p1.Direction.UP
import kotlin.math.max
import kotlin.math.min

val keypad = listOf(
    listOf(1, 2, 3),
    listOf(4, 5, 6),
    listOf(7, 8, 9)
).map { it.map { ('0'.toInt() + it).toChar() } }

val stupidPad = listOf(
    listOf(' ', ' ', '1', ' ', ' '),
    listOf(' ', '2', '3', '4', ' '),
    listOf('5', '6', '7', '8', '9'),
    listOf(' ', 'A', 'B', 'C', ' '),
    listOf(' ', ' ', 'D', ' ', ' ')
)

data class Position(
    private val x: Int,
    private val y: Int,
    private val pad: List<List<Char>>
)
{
  fun move(
      direction: Direction
  ): Position
  {
    val xMax = pad[0].size - 1
    val yMax = pad.size - 1
    return conditionalMove(
        when (direction)
        {
          UP    -> Position(x, max(0, y - 1), pad)
          DOWN  -> Position(x, min(yMax, y + 1), pad)
          LEFT  -> Position(max(0, x - 1), y, pad)
          RIGHT -> Position(min(xMax, x + 1), y, pad)
        }
    )
  }

  private fun conditionalMove(position: Position) =
      if (position.asKeyCode() == ' ') this else position

  fun asKeyCode() = pad[y][x]
}


fun main(args: Array<String>)
{
  val steps = Input.getFor("p2")
      .split("\n")
      .map { it.map { Direction.from(it) } }

  val code = findCode(steps, keypad, '5')
  println(code)

  println(findCode(steps, stupidPad, '5'))
}

fun findCode(
    steps: List<List<Direction>>,
    pad: List<List<Char>>,
    startingKey: Char
): String
{
  return steps
      .fold(listOf<Position>()) { code, line ->
        val startingButton = code.lastOrNull() ?: findKey(startingKey, pad)
        val digit = line.fold(startingButton) { position, direction ->
          position.move(direction)
        }
        code + digit
      }
      .map { it.asKeyCode() }
      .joinToString(separator = "")
}

fun findKey(key: Char, pad: List<List<Char>>): Position
{
  val button = pad.
      mapIndexed { y, list ->
        list.mapIndexed { x, c ->
          Triple(x, y, c)
        }
      }
      .flatten()
      .first { it.third == key }

  return Position(button.first, button.second, pad)
}
