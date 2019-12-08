package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class D6ATest {
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
        """.trimMargin()
  }

  @Test
  fun `parse example`() {
    val parents = D6.parse(input)
    assertThat(parents).containsExactlyEntriesOf(
        mapOf(
            "B" to "COM",
            "C" to "B",
            "D" to "C",
            "E" to "D",
            "F" to "E",
            "G" to "B",
            "H" to "G",
            "I" to "D",
            "J" to "E",
            "K" to "J",
            "L" to "K"
        )
    )
  }

  @Test
  fun `solve example`() {
    val parents = D6.parse(input)
    assertThat(D6A.solve(parents)).isEqualTo(42)
  }

  @Test
  fun `solve part A`() {
      println(
          D6A.solve(
              D6.parse(
                  Resources.getResource("6.input.txt").readText()
              )
          )
      )
  }
}
