package y2019

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D4ATest {
  @Test
  fun `part 1`() {
    println(
        D4A.solve(273025..767253).size
    )
  }
}