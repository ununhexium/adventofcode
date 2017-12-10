package p7

import com.google.common.io.Resources

fun main(args: Array<String>)
{
  val input = Resources.toString(
      Resources.getResource("p7/input.txt"),
      Charsets.UTF_8
  )

  val tree = buildTree(input)
  println("The root is $tree")
  println(tree.toTree())
  println(tree.filter({ !it.isBalanced() }))
}

val arrow = " -> "
val naming = Regex("(?<name>\\p{Lower}+) \\((?<weight>\\p{Digit}+)\\)(?<children> \\-> .*)?")

class Program(val name: String, val weight: Int, val children: List<Program>)
{
  fun getTotalWeight(): Int = weight + children.map { it.getTotalWeight() }.sum()

  fun toTree(depth: Int = 1): String
  {
    return this.run {
      depth.toString() + "  ".repeat(depth) + "$name ($weight ${getTotalWeight()})\n" + children.map {
        it.toTree(depth + 1)
      }.joinToString(separator = "") { it }
    }
  }

  fun isBalanced(): Boolean
  {
    println("Children " + children.joinToString())
    val byWeight = children.groupBy { it.getTotalWeight() }
    // there can only be 1 unbalanced weight
    val unbalanced = byWeight.filterValues { it.size == 1 }.flatMap { it.value }
    println(unbalanced)

    if (unbalanced.isNotEmpty())
    {
      println("Problem at " + unbalanced)
      /*
       * There may be at most either 1 or 2 "wrong".
       * If there are only 2 process, either may be wrong.
       */
      if (unbalanced.size > 1)
      {
        /**
         * Here we may guess which one is actually wrong if it's
         * not possible to balance the weight of 1 of them.
         * But the exercise doesn't give any information for that case.
         */
        throw RuntimeException("How am I supposed to guess which one to change? " + unbalanced)
      }
      // this only works because there is the assumption that only 1 program is off balance
      // https://adventofcode.com/2017/day/7
      val wrong = unbalanced.first()

      return !wrong.isBalanced()
    }
    else
    {
      return true
    }
  }

  override fun toString() = "$name ($weight / ${getTotalWeight()})"

  fun filter(filter: (Program) -> Boolean): List<Program>
  {
    val result = children
        .flatMap { it.filter(filter) }
        .toMutableList()

    if (filter(this)) result.add(this)

    return result
  }
}

data class OrphanProgram(
    val name: String,
    val weight: Int,
    val children: List<String>
)
{
  /**
   * Converts an orphan program to a real program, given a complete program tree
   */
  fun asProgram(bag: List<OrphanProgram>): Program
  {
    return Program(
        this.name,
        this.weight,
        this.children.map { childName ->
          bag.find { it.name == childName }!!.asProgram(bag)
        }
    )
  }

  companion object
  {
    /**
     * Converts a string to an orphan program
     */
    fun from(input: String): OrphanProgram
    {
      val matcher = naming.toPattern().matcher(input)
      if (!matcher.matches())
      {
        throw  RuntimeException("broken input for " + input)
      }
      val name = matcher.group("name")
      val weight = matcher.group("weight").toInt()

      val potentialChildren = matcher.group("children") ?: ""
      val children: List<String> = if (potentialChildren.isNotEmpty())
      {
        potentialChildren
            .substring(arrow.length) // remove the arrow
            .split(", ")
      }
      else listOf()

      return OrphanProgram(name, weight, children)
    }
  }
}

fun buildTree(input: String): Program
{
  val bag = input
      .split("\n")
      .sortedBy { if (it.contains(arrow)) 1 else 0 }
      .map { OrphanProgram.from(it) }
      .toMutableList()

  /*
   * Get the root node.
   * This is the node which has children but is the child of no other node.
   */
  val children = bag.flatMap { it.children }
  val root = bag.first { !children.contains(it.name) }

  return root.asProgram(bag)
}
