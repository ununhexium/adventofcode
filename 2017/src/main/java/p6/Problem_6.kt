package p6

val input = """14 0 15 12 11 11 3 5 1 6 8 4 9 1 8 4"""

fun main(args: Array<String>)
{
  val ints = input.split(" ").map { it.toInt() }

  println(cycle(mutableListOf(0, 2, 7, 0)))
  println(cycle(ints.toMutableList()))
}

fun cycle(currentList: MutableList<Int>): Int
{
  val previous = HashSet<List<Int>>()
  var cycles = 0

  while (true)
  {
    val max = currentList.withIndex().maxBy { it.value }!!
    currentList[max.index] = 0
    (1..max.value).forEach { currentList[(max.index + it) % currentList.size]++ }

    cycles++
    if (previous.contains(currentList)) break

    previous.add(currentList.toList())
  }

  return cycles
}


