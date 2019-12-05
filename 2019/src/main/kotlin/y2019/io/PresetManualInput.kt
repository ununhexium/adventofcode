package y2019.io

import java.io.Console
import java.util.Scanner

class PresetManualInput(vararg inputs: Int): Input {
  override val name = "ManualInput"

  private val iterator = inputs.iterator()

  override fun get(index: Int?): Int {
    if(!iterator.hasNext()) {
      throw IllegalStateException("End of manual inputs")
    }
    return iterator.next()
  }
}
