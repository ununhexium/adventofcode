fun day3a(trees: List<String>): Long {
  val slopes = listOf(
    1 to 1,
    3 to 1,
    5 to 1,
    7 to 1,
    1 to 2,
  )

  return slopes.fold(1L) { acc, e ->
    val count = trees.indices.filter { it % e.second == 0 }.count { row ->
      trees[row][(row * e.first) % trees.first().length] == '#'
    }

    acc * count
  }
}
