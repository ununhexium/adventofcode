package p10

import org.junit.Assert.assertEquals
import org.junit.Test
import p6.input
import kotlin.test.asserter


class Problem10Test
{
  @Test
  fun checkAsciiToInt()
  {
    assertEquals(listOf(49, 44, 50, 44, 51), asciiToInt("1,2,3"))
  }

  @Test
  fun checkTail()
  {
    assertEquals(listOf(999, 17, 31, 73, 47, 23), addTail(listOf(999)))
  }

  @Test
  fun checkDenseHash()
  {
    assertEquals(
        listOf(64),
        denseHash(listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22))
    )
  }

  @Test
  fun checkCompleteHash()
  {
    mapOf(
        "" to "a2582a3a0e66e6e86e3812dcb672a272",
        "AoC 2017" to "33efeb34ea91902bb2f59c9920caa6cd",
        "1,2,3" to "3efbe78a8d82f29979031a4aa0b16a9d",
        "1,2,4" to "63960835bcdc130f0b66d7ff4f6a5a8e"
    ).forEach { input, output ->
      assertEquals(output, hexHash(input))
    }


  }

}