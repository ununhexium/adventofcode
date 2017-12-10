package p9

import com.google.common.io.Resources
import more.format
import p9.State.IGNORE_NEXT_OK
import p9.State.IGNORE_NEXT_TRASH
import p9.State.OK
import p9.State.TRASH

enum class State
{
  OK,
  TRASH,
  IGNORE_NEXT_OK,
  IGNORE_NEXT_TRASH
}

fun main(args: Array<String>)
{
  val input = Resources.toString(
      Resources.getResource("p9/input.txt"),
      Charsets.UTF_8
  )

  val tests = mapOf(
      "{}" to 1,
      "{{{}}}" to 6,
      "{{},{}}" to 5,
      "{{{},{},{{}}}}" to 16,
      "{<a>,<a>,<a>,<a>}" to 1,
      "{{<ab>},{<ab>},{<ab>},{<ab>}}" to 9,
      "{{<!!>},{<!!>},{<!!>},{<!!>}}" to 9,
      "{{<a!>},{<a!>},{<a!>},{<ab>}}" to 3
  )

  tests.forEach { k, v ->
    val score = consume(k)
    if (score.first != v)
    {
      println("Problem " + k)
      throw RuntimeException("Expected $v was $score")
    }
  }

  println(consume(input))
}

private fun consume(input: String): Pair<Int, Int>
{
  var mode = OK
  var depth = 0
  var score = 0
  var petitScarabeeBousier = 0
  input.forEach { char ->
    mode = when (mode)
    {
      IGNORE_NEXT_OK    -> OK
      IGNORE_NEXT_TRASH -> TRASH
      OK                -> when (char)
      {
        '{'  ->
        {
          depth += 1
          score += depth
          OK
        }
        '}'  ->
        {
          depth -= 1
          OK
        }
        '<'  -> TRASH
        '!'  -> IGNORE_NEXT_OK
        else -> OK
      }
      TRASH             ->
      {
        when (char)
        {
          '>'  -> OK
          '!'  -> IGNORE_NEXT_TRASH
          else -> {
            petitScarabeeBousier++
            TRASH
          }
        }
      }
    }
  }

  return score to petitScarabeeBousier
}