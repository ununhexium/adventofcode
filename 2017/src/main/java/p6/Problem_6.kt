package p6

val input = """14 0 15 12 11 11 3 5 1 6 8 4 9 1 8 4"""

fun main(args: Array<String>)
{
  val ints = input.split(" ").map { it.toInt() }

  println(cycle(mutableListOf(0, 2, 7, 0)))
  println(cycle(ints.toMutableList()))
}

fun cycle(currentList: MutableList<Int>): Pair<Int, Int>
{
  val lookup = HashSet<List<Int>>()
  val previous = ArrayList<List<Int>>()
  var cycles = 0

  while (true)
  {
    val max = currentList.withIndex().maxBy { it.value }!!
    currentList[max.index] = 0
    (1..max.value).forEach { currentList[(max.index + it) % currentList.size]++ }

    cycles++
    if (lookup.contains(currentList)) break

    val copy = currentList.toList()
    lookup.add(copy)
    previous.add(copy)
  }

  val index = previous
      .mapIndexed { index, list -> index to list }
      .first { it.second == currentList }
      .first

  val interval = previous.size - index
  return cycles to interval
}


