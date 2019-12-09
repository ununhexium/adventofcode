package y2019

import com.google.common.io.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D8ATest {
  @Test
  fun `part A`() {
      println(
          D8A.solve(
              Resources.getResource("8.input.txt").readText(),
              25,
              6
          )
      )
  }

  @Test
  fun `decode`() {
    println(
        D8B.decode(
            Resources.getResource("8.input.txt").readText(),
            25,
            6
        )
    )
  }
}