package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D3ATest {
  @Test
  fun `solve examples`() {
    assertThat(
        D3A.solve(
            "R8,U5,L5,D3".parsed(),
            "U7,R6,D4,L4".parsed()
        )
    ).isEqualTo(6)

    assertThat(
        D3A.solve(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72".parsed(),
            "U62,R66,U55,R34,D71,R55,D58,R83".parsed()
        )
    ).isEqualTo(159)

    assertThat(
        D3A.solve(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".parsed(),
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".parsed()
        )
    ).isEqualTo(135)
  }

  @Test
  fun `solve part 1`() {
    val input = Resources
        .getResource("3.input.txt")
        .readText()
        .split("\n")
        .map { it.parsed() }
        .toTypedArray()

    println(D3A.solve(input[0], input[1]))
  }

  @Test
  fun `solve example as B`() {
    assertThat(
        D3B.solve(
            "R8,U5,L5,D3".parsed(),
            "U7,R6,D4,L4".parsed()
        )
    ).isEqualTo(15+15)
  }

  @Test
  fun `solve part 2`() {
    val input = Resources
        .getResource("3.input.txt")
        .readText()
        .split("\n")
        .map { it.parsed() }
        .toTypedArray()

    println(D3B.solve(input[0], input[1]))
  }

  private fun String.parsed(): List<ManhattanMovement> =
      this.split(",").map {
        when (it[0]) {
          'U' -> ManhattanMovement(0, +it.substring(1).toInt())
          'D' -> ManhattanMovement(0, -it.substring(1).toInt())
          'R' -> ManhattanMovement(+it.substring(1).toInt(), 0)
          'L' -> ManhattanMovement(-it.substring(1).toInt(), 0)
          else -> throw IllegalArgumentException("What kind of direction is $it?")
        }
      }
}

