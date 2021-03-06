package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D7ATest {
  @Test
  fun `solve example1`() {
    val intCode = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"

    assertThat(D7A.evaluatePhaseSettings(intCode, listOf(4, 3, 2, 1, 0)))
        .isEqualTo(43210)
    assertThat(D7A.solve(intCode)).isEqualTo(listOf(4, 3, 2, 1, 0))
  }

  @Test
  fun `solve example 2`() {
    val intCode = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"

    assertThat(D7A.evaluatePhaseSettings(intCode, listOf(0, 1, 2, 3, 4)))
        .isEqualTo(54321)
    assertThat(D7A.solve(intCode)).isEqualTo(listOf(0, 1, 2, 3, 4))
  }

  @Test
  fun `solve example 3`() {
    val intCode = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"

    assertThat(D7A.evaluatePhaseSettings(intCode, listOf(1, 0, 4, 3, 2)))
        .isEqualTo(65210)
    assertThat(D7A.solve(intCode)).isEqualTo(listOf(1, 0, 4, 3, 2))
  }

  @Test
  fun `part A`() {
    val intCode = Resources.getResource("7.input.txt").readText()
    println(
        D7A.evaluatePhaseSettings(
            intCode,
            D7A.solve(intCode)
        )
    )
  }

  @Test
  fun `solve example 4`() {

  }

}