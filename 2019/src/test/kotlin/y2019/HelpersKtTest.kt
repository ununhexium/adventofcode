package y2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HelpersKtTest {
  @Test
  fun `can do permutations`() {
    val permutations = listOf(1,2,3)
        .permutations()
        // make a copy, otherwise it's always the same element that is returned and in the end, all the permutation will be a single instance
        .map { it.toList() }

    assertThat(permutations.toList()).containsExactlyInAnyOrder(
        listOf(1,2,3),
        listOf(1,3,2),
        listOf(2,1,3),
        listOf(2,3,1),
        listOf(3,1,2),
        listOf(3,2,1)
    )
  }
}