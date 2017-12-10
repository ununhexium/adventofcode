package p10

import com.google.common.collect.Lists
import more.formattedHexadecimal

class CircularList<T>(c: Collection<T>) : ArrayList<T>(c)
{
  override fun get(index: Int) =
      super.get(index % this.size)

  override fun set(index: Int, element: T) =
      super.set(index % size, element)

  override fun subList(fromIndex: Int, toIndex: Int) =
      (fromIndex until toIndex).map { get(it) }.toMutableList()

  fun swap(start: Int, length: Int) =
      subList(start, start + length).reversed().forEachIndexed { index, t ->
        set(start + index, t)
      }
}

class Assembly(val lengths: List<Int>, val chainLength: Int, val rounds: Int)

val inputs = listOf(
    Assembly(listOf(3, 4, 1, 5), 5, 1),

    Assembly(
        listOf(
            102,
            255,
            99,
            252,
            200,
            24,
            219,
            57,
            103,
            2,
            226,
            254,
            1,
            0,
            69,
            216
        ),
        256,
        1
    )
)

fun asciiToInt(input: String) = input.map { it.toInt() }

private fun knotHash(assembly: Assembly): CircularList<Int>
{
  val list = CircularList((0 until assembly.chainLength).toList())
  var current = 0
  var skipSize = 0

  (1..assembly.rounds).forEach {
    assembly.lengths.forEach { length ->
      list.swap(current, length)
      current += length + skipSize
      skipSize++
    }
  }
  return list
}

fun denseHash(list: List<Int>) =
    Lists.partition(list, 16).map { it.reduce { a, b -> a xor b }!! }

fun addTail(list: List<Int>) =
    list.toMutableList().also { it.addAll(listOf(17, 31, 73, 47, 23)) }

fun hexHash(input: String): String
{
  return denseHash(
      knotHash(
          Assembly(
              addTail(input.map { it.toInt() }),
              256,
              64
          )
      )
  ).joinToString(separator = "") { it.formattedHexadecimal(2) }
}

fun main(args: Array<String>)
{
  inputs.forEach { input ->
    val list = knotHash(input)

    println(list)
    println(list[0] * list[1])
  }

  println(hexHash(more.Input.getFor("p10")))
}
