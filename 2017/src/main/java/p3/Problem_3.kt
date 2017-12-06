package p3

val inputs = listOf(
    1,
    2,
    9,
    12,
    23,
    1024,
    289326
)

fun main(args: Array<String>)
{
  inputs.forEach {
    println(distance(it))
  }
}

private fun distance(input: Int): Int
{
  if (input == 1) return 0

  val MAX = 1000
  /**
   * ring / max value
   * 0 / 1
   * 1 / 9
   * 2 / 25
   * ... / ...
   */
  val ring =
      (0..MAX)
          .asSequence()
          .first { it -> ringToSquare(it) >= input }

  // ring 2 has length 2, ring 3 has length 4
  val ringSideLength = ringToSide(ring)
  // the surface of the previous ring
  val previousSquareRing = ringToSquare(ring - 1)
  // 1, 9, 25, 49, 81 etc. have index 0
  // then it goes +1 in the same direction as the original spiral
  val ringIndex = input - previousSquareRing - ring
  val edgeIndex = ringIndex % (ringSideLength - 1)
  return ring + edgeIndex
}

fun square(i: Int) = i * i

fun ringToSide(i: Int) = i * 2 + 1

fun ringToSquare(i: Int) = square(ringToSide(i))
