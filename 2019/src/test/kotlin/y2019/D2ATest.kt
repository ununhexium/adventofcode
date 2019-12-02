package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D2ATest {
  @Test
  fun `can compute`() {
    assertThat(D2A.compute("1,0,0,0,99".parse()))
        .isEqualTo("2,0,0,0,99".parse())
    assertThat(D2A.compute("2,3,0,3,99".parse()))
        .isEqualTo("2,3,0,6,99".parse())
    assertThat(D2A.compute("2,4,4,5,99,0".parse()))
        .isEqualTo("2,4,4,5,99,9801".parse())
    assertThat(D2A.compute("1,1,1,4,99,5,6,0,99".parse()))
        .isEqualTo("30,1,1,4,2,5,6,0,99".parse())
  }

  @Test
  fun `can run in computer`() {
    assertThat(IntCodeComputer("1,0,0,0,99".parse()).compute())
        .isEqualTo("2,0,0,0,99".parse())
    assertThat(IntCodeComputer("2,3,0,3,99".parse()).compute())
        .isEqualTo("2,3,0,6,99".parse())
    assertThat(IntCodeComputer("2,4,4,5,99,0".parse()).compute())
        .isEqualTo("2,4,4,5,99,9801".parse())
    assertThat(IntCodeComputer("1,1,1,4,99,5,6,0,99".parse()).compute())
        .isEqualTo("30,1,1,4,2,5,6,0,99".parse())
  }

  @Test
  fun solve() {
    println(
        D2A.solve(
            Resources
                .getResource("2.input.txt")
                .readText()
                .split(',')
                .map { it.toInt() }
        )
    )
  }

  @Test
  fun `same output`() {
    val original = D2A.solve(
        Resources
            .getResource("2.input.txt")
            .readText()
            .split(',')
            .map { it.toInt() }
    )

    val fancy = IntCodeComputer(
        Resources
            .getResource("2.input.txt")
            .readText()
            .split(',')
            .map { it.toInt() },
        12, 2
    ).run()

    assertThat(original).isEqualTo(fancy)
  }

  @Test
  fun `output 19690720`() {
    val input = Resources
        .getResource("2.input.txt")
        .readText()
        .split(',')
        .map { it.toInt() }

    val nounAndVerb = (0..99).flatMap { err1 ->
      (0..99).map { err2 ->
        err1 to err2
      }
    }

    println(
        nounAndVerb.first {
          IntCodeComputer(input, it.first, it.second).run() == 19690720
        }
    )
  }

  private fun String.parse() =
      this.split(',').map { it.toInt() }
}

