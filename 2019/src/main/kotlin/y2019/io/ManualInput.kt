package y2019.io

import java.io.Console
import java.util.Scanner

object ManualInput: Input {
  override val name = "ManualInput"

  override fun get(index: Int?): Int {
    System.err.print("In> ")
    return Scanner(System.`in`).nextInt()
  }
}
