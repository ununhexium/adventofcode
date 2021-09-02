import Tools.load
import org.junit.jupiter.api.Test

internal class D01KtTest {
  @Test
  fun easy() {
    // given
    val input = load("D01").map { it.toInt() }

    // when
    val (a, b) = day1a(input)

    // then
    println(a * b)
  }

  @Test
  fun hard() {
    // given
    val input = load("D01").map { it.toInt() }

    // when
    val (a, b, c) = day1b(input)

    // then
    println(a * b * c)
  }

}
