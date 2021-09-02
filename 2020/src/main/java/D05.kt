import java.math.BigInteger

fun day05(lines: List<String>): Long {
  return lines.joinToString(separator = "\n").split("\n\n").fold(0L) { acc, g ->
    val people = g.split("\n").filter { it.trim().isNotEmpty() }.size
    val count = g.groupBy { it }.filterKeys { it in 'a' .. 'z' }.filterValues { it.size == people }.size
    acc + count
  }
}
