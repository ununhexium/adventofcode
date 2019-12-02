package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D1BTest {
  @Test
  fun foo() {
    assertThat(D1B.solve1(14)).isEqualTo(2)
    assertThat(D1B.solve1(1969)).isEqualTo(966)
    assertThat(D1B.solve1(100756)).isEqualTo(50346)

    println(
        D1B.solve(
            Resources
                .getResource("1.input.txt")
                .readText()
                .split("\n")
                .map { it.toLong() }
        )
    )
  }
}