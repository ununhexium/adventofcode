package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IntCodeComputerTest {
  @Test
  fun `can run in computer`() {
    assertThat(IntCodeComputer(listOf(1, 0, 0, 0, 99)).compute())
        .isEqualTo(listOf(2, 0, 0, 0, 99))
    assertThat(IntCodeComputer(listOf(2, 3, 0, 3, 99)).compute())
        .isEqualTo(listOf(2, 3, 0, 6, 99))
    assertThat(IntCodeComputer(listOf(2, 4, 4, 5, 99, 0)).compute())
        .isEqualTo(listOf(2, 4, 4, 5, 99, 9801))
    assertThat(IntCodeComputer(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)).compute())
        .isEqualTo(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
  }

  @Test
  fun `can run in different operational modes`() {
    assertThat(IntCodeComputer(listOf(1002, 4, 3, 4, 33)).compute())
        .isEqualTo(listOf(1002, 4, 3, 4, 99))
  }

  @Test
  fun `can use io`() {
    val computer = IntCodeComputer(
        listOf(3, 0, 4, 0, 99),
        initialIo = mapOf(
            "default" to 116
        )
    ).init()

    assertThat(computer.io().get()).isEqualTo(116)

    computer.run()

    assertThat(computer.program).isEqualTo(listOf(116, 0, 4, 0, 99))
    assertThat(computer.io().get()).isEqualTo(116)
  }

  @Test
  fun `part A`() {
    val computer = IntCodeComputer(
        Resources.getResource("5.input.txt").readText(),
        initialIo = mapOf("default" to 1)
    )

    println(computer.run())
  }
}
