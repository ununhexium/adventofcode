package y2019

import com.google.common.io.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import y2019.io.ConsoleOutput
import y2019.io.DelegatedInputOutput
import y2019.io.ManualInput
import y2019.io.PresetManualInput

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
  fun `D5A`() {
    val computer = IntCodeComputer(
        Resources.getResource("5.input.txt").readText(),
        initialIo = mapOf("default" to 1)
    )

    println(computer.run())
  }

  @Test
  fun `jump if true`() {
    val computer = IntCodeComputer(
        listOf(104, 116, 1105, 1, 7, 104, 117, 99)
    ).init()

    computer.compute()

    assertThat(computer.io().get()).isEqualTo(116)
  }

  @Test
  fun `dont jump if not true`() {
    val computer = IntCodeComputer(
        listOf(105, 0, 0, 104, 116, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(116)
  }

  @Test
  fun `jump if false`() {
    val computer = IntCodeComputer(
        listOf(104, 116, 1106, 0, 7, 104, 117, 99)
    ).init()

    computer.compute()

    assertThat(computer.io().get()).isEqualTo(116)
  }

  @Test
  fun `dont jump if not false`() {
    val computer = IntCodeComputer(
        listOf(106, 1, 0, 104, 116, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(116)
  }

  @Test
  fun `less than`() {
    val computer = IntCodeComputer(
        listOf(1107, 1, 2, 0, 4, 0, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `not less than`() {
    val computer = IntCodeComputer(
        listOf(1107, 2, 1, 0, 4, 0, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `equals`() {
    val computer = IntCodeComputer(
        listOf(1108, 1, 1, 0, 4, 0, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `not equals`() {
    val computer = IntCodeComputer(
        listOf(1108, 2, 1, 0, 4, 0, 99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input equals 8 - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8),
        initialIo = mapOf("default" to 8)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input not equals 8 - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8),
        initialIo = mapOf("default" to 116)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input less than 8 - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8),
        initialIo = mapOf("default" to -99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input not less than 8 - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8),
        initialIo = mapOf("default" to 8)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input equals 8 - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99),
        initialIo = mapOf("default" to 8)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input not equals 8 - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99),
        initialIo = mapOf("default" to 116)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input less than 8 - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99),
        initialIo = mapOf("default" to -99)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input not less than 8 - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99),
        initialIo = mapOf("default" to 8)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input is non zero - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9),
        initialIo = mapOf("default" to 116)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input is not non zero - position mode`() {
    val computer = IntCodeComputer(
        listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9),
        initialIo = mapOf("default" to 0)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Test
  fun `input is non zero - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1),
        initialIo = mapOf("default" to 116)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(1)
  }

  @Test
  fun `input is not non zero - immediate mode`() {
    val computer = IntCodeComputer(
        listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1),
        initialIo = mapOf("default" to 0)
    ).init()

    computer.run()

    assertThat(computer.io().get()).isEqualTo(0)
  }

  @Disabled
  @Test
  fun `user io`() {
    val computer = IntCodeComputer(
        listOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        ),
        defaultIO = DelegatedInputOutput("default", PresetManualInput(7), ConsoleOutput)
    ).init()

    computer.run()
  }
}
