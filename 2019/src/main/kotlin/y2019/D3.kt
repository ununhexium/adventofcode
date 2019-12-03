package y2019

import kotlin.math.abs


object D3A {
  fun solve(
      path1: List<ManhattanMovement>,
      path2: List<ManhattanMovement>
  ): Int {
    val pos1 = getPositions(path1)
    val pos2 = getPositions(path2)
    return pos1
        .intersect(pos2)
        .map { it to it.distanceTo(ManhattanPosition.ORIGIN) }
        .also { println(it) }
        .minBy { it.second }!!
        .second
  }

  fun getPositions(path: List<ManhattanMovement>): List<ManhattanPosition> {
    var current = ManhattanPosition(0, 0)
    val positions = mutableListOf<ManhattanPosition>()

    path.forEach { step ->
      val (x, y) = step

      positions.addAll(
          (1..abs(x)).map {
            current = current.copy(x = sign(x) + current.x)
            current
          }
      )

      positions.addAll(
          (1..abs(y)).map {
            current = current.copy(y = sign(y) + current.y)
            current
          }
      )
    }

    return positions
  }

  private fun sign(y: Int): Int =
      if (y < 0) -1 else 1
}

object D3B {
  fun solve(
      path1: List<ManhattanMovement>,
      path2: List<ManhattanMovement>
  ): Int {
    val pos1 = D3A.getPositions(path1)
    val pos2 = D3A.getPositions(path2)
    val positionToReach = pos1
        .intersect(pos2)
        .minBy { intersection ->
          pos1.indexOfFirst { it == intersection } +
              pos2.indexOfFirst { it == intersection }
        }!!

    return pos1.indexOfFirst { it == positionToReach } +
        pos2.indexOfFirst { it == positionToReach } +
        2 // steps to move from the origin to the first position
  }
}