package pack

import com.google.common.collect.Lists.cartesianProduct
import com.google.common.io.Resources.getResource
import com.google.common.io.Resources.toString
import org.funktionale.currying.curried
import java.nio.charset.StandardCharsets.UTF_8

fun main(args: Array<String>) {
  val txt = toString(getResource("2.input.txt"), UTF_8).split("\n")

  /*
   * Keeps words which have at least 1 repetition of exactly n letters
   */
  val dispatcher = { n: Int, txt: List<String> ->
    txt.filter { word ->
      word.groupBy {
        it
      }.any {
        it.value.size == n
      }
    }
  }.curried()

  val couples = dispatcher(2)(txt)
  val triples = dispatcher(3)(txt)

  println(couples.size * triples.size)

  // PART2
  val diff = { s: String, t: String ->
    if (s.length != t.length) -1
    else (s zip t).count {
      it.first != it.second
    }
  }

  val oneCharDiff = cartesianProduct(txt, txt).filter {
    diff(it[0],it[1]) == 1
  }

  val pair = oneCharDiff.first() // assuming there is only 1 instance of such a combination
  val glued = pair[0] zip pair[1]
  val code = glued.joinToString(separator = "") {
    if(it.first == it.second) it.first.toString() else ""
  }

  println(code)
}