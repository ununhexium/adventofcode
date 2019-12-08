package y2019


object D6 {
  fun parse(input: String): Map<String, String> {
    val parents = mutableMapOf<String, String>()
    input.split("\n").forEach { line ->
      // B orbits A
      val (A, B) = line.split(')')
      parents[B] = A
    }
    return parents
  }
}

object D6A {

  fun solve(parents: Map<String, String>): Int {
    return parents.keys.sumBy { countOrbits(parents, it) }
  }

  private fun countOrbits(parents: Map<String, String>, it: String): Int {
    var count = 0;
    var current = it
    while (current != "COM") {
      count++
      current = parents[current] ?: error("No parent for $current")
    }
    return count
  }
}

object D6B {
  fun solve(parents: Map<String, String>): Int {
    val parentChain1 = getParentChain(parents, "YOU")
    val parentChain2 = getParentChain(parents, "SAN")

    val common = parentChain1.intersect(parentChain2)
    val toCommon1 = parentChain1.dropLastWhile { it in common }
    val toCommon2 = parentChain2.dropLastWhile { it in common }

    return toCommon1.size + toCommon2.size + 1 /* the common orbit */ -2 /* YOU and SAN */ -1 /* Count the jumps between nodes, not the nodes */
  }

  private fun getParentChain(
      parents: Map<String, String>,
      what: String
  ): List<String> {
    val chain = mutableListOf<String>()
    var current = what
    while (current != "COM") {
      chain.add(current)
      current = parents[current] ?: error("No parent for $current")
    }
    return chain
  }
}