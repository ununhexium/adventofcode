package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D6BTest {
  companion object {
    val input =
        """
          |COM)B
          |B)C
          |C)D
          |D)E
          |E)F
          |B)G
          |G)H
          |D)I
          |E)J
          |J)K
          |K)L
          |K)YOU
          |I)SAN
        """.trimMargin()
  }

  @Test
  fun `solve example`() {
    val parents = D6.parse(input)
    assertThat(D6B.solve(parents)).isEqualTo(4)
  }

  @Test
  fun `solve part B`() {
    println(
        D6B.solve(
            D6.parse(
                Resources.getResource(
                    "6b.input.txt"
                ).readText()
            )
        )
    )
  }
}