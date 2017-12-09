package p5

import com.google.common.io.Resources

fun main(args: Array<String>)
{
  val values = Resources.toString(
      Resources.getResource("p5/input.txt"),
      Charsets.UTF_8
  )
  val input = values.split("\n").map { it.toInt() }

  println(execute(input.toMutableList()))
}

fun execute(input: MutableList<Int>): Int
{
  var index = 0
  var instructions = 0
  while (index >= 0 && index < input.size)
  {
    val current = input[index]
    val tmp = index + current
    input[index] = if(current >= 3) current - 1 else current + 1
    index = tmp
    instructions++
  }

  return instructions
}
