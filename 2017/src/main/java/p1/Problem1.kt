package p1

import more.Input

val inputs = listOf(
    "1122",
    "1111",
    "1234",
    "91212129",
    Input.getFor("p1")
)

fun main(args: Array<String>)
{
  val offsets = listOf(
      { index: Int, size: Int -> (index + 1) % size },
      { index: Int, size: Int -> (index + size / 2) % size }
  )
  offsets.forEach { offsetter ->
    inputs.forEach { input ->
      println(
          "$input -> " + computeSum(
              input,
              offsetter
          )
      )
    }
  }
}


fun computeSum(input: String, offset: (Int, Int) -> Int): Int
{
  val ints = input.map { it.toInt() - '0'.toInt() }

  return (0 until ints.size - 1)
      .filter { ints[it] == ints[offset(it, input.length)] }
      .sumBy { ints[it] }
}
