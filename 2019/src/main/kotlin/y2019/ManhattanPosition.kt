package y2019

import kotlin.math.abs

data class ManhattanPosition(val x: Int, val y: Int) {
  fun distanceTo(origin: ManhattanPosition): Int =
      abs(this.x - origin.x) + abs(this.y - origin.y)

  companion object {
    val ORIGIN = ManhattanPosition(0, 0)
  }
}
