package y2019.io

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import y2019.IntCodeComputer

internal class PresetInputTest {
  @Test
  fun `can read when there is a single input value`() {
    val i = PresetInput(116)
    val o = DefaultBufferFactory.create()
    IntCodeComputer(
        listOf(3, 1, 4, 1, 99),
        defaultIO = DelegatedInputOutput(i, o)
    ).run()

    Assertions.assertThat(o.get()).isEqualTo(116)
  }

  @Test
  fun `can read the next input value`() {
    val i = PresetInput(116, 117)
    val o = DefaultBufferFactory.create()
    IntCodeComputer(
        listOf(3, 1, 3, 1, 4, 1, 99),
        defaultIO = DelegatedInputOutput(i, o)
    ).run()

    Assertions.assertThat(o.get()).isEqualTo(117)
  }
}
