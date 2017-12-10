package p2

import org.junit.Test
import p1.Direction
import kotlin.test.assertEquals

class Problem2Test
{
  @Test
  fun findKey(){
    assertEquals(Position(1,1, keypad), p2.findKey('5', keypad))
    assertEquals(Position(2,2, keypad), p2.findKey('9', keypad))

    assertEquals(Position(0,2, stupidPad), p2.findKey('5', stupidPad))
    assertEquals(Position(3,3, stupidPad), p2.findKey('C', stupidPad))
  }

  @Test
  fun example()
  {
    val input = """ULL
      |RRDDD
      |LURDL
      |UUUUD"""

    val instructions = input.trimMargin().split("\n").map { it.map { Direction.from(it) } }

    assertEquals("1985", findCode(instructions, keypad, '5'))
    assertEquals("5DB3", findCode(instructions, stupidPad, '5'))

  }
}