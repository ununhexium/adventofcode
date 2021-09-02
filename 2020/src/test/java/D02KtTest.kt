import Tools.load
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D02KtTest {
  @Test
  fun a() {
    val input = load("D02a")
    val valid = day2a(input)
    println(valid)
  }

  @Test
  fun b() {
    val input = load("D02a")
    val valid = day2b(input)
    println(valid)
  }
}
