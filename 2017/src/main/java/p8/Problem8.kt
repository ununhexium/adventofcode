package p8

import com.google.common.io.Resources
import more.Input
import javax.management.MXBean

val R1 = "register1"
val R2 = "register2"
val OPCODE = "opcode"
val V1 = "value1"
val V2 = "value2"
val COMP = "comparator"
val MAX = "SenatusPopuluQueRomanus"

val registers = HashMap<String, Int>()

val parser = Regex("(?<$R1>\\p{Lower}+) (?<$OPCODE>\\p{Lower}+) (?<$V1>-?\\p{Digit}+) if (?<$R2>\\p{Lower}+) (?<$COMP>[!<>=]+) (?<$V2>-?\\p{Digit}+)")

val operations = mapOf(
    "inc" to { register: Int, value: Int -> register + value },
    "dec" to { register: Int, value: Int -> register - value }
)

val comparisons = mapOf(
    "<=" to { left: Int, right: Int -> left <= right },
    ">=" to { left: Int, right: Int -> left >= right },
    "<" to { left: Int, right: Int -> left < right },
    ">" to { left: Int, right: Int -> left > right },
    "!=" to { left: Int, right: Int -> left != right },
    "==" to { left: Int, right: Int -> left == right }
)

fun main(args: Array<String>)
{
  println("Parsing with " + parser.pattern)

  val tests = listOf(
      "a inc 1 if b == 0",
      "abc inc 123 if ber == 120"
  )

  tests.forEach {
    parser.matchEntire(it) ?: throw RuntimeException("Grr")
    println("Ok " + it)
  }

  val code = Input.getFor("p8")

  execute(code)
  println(registers.values.max())
}

private fun execute(code: String)
{
  code.split("\n").forEach { line ->
    val matcher = parser.matchEntire(line) ?: throw RuntimeException("Broken assembly code \n" + line)

    fun get(groupName: String) = matcher.groups[groupName]!!.value

    val register1 = registers[get(R1)] ?: 0
    val opcodeString = get(OPCODE)
    val opcode = operations[opcodeString] ?: throw RuntimeException("Missing opcode " + opcodeString)
    val operand = get(V1).toInt()
    val register2 = registers[get(R2)] ?: 0
    val comparatorString = get(COMP)
    val comparator = comparisons[comparatorString] ?: throw RuntimeException("Missing operator " + comparatorString)
    val value = get(V2).toInt()

    if (comparator(register2, value)) registers[get(R1)] = opcode(
        register1,
        operand
    )

    val max = registers[MAX] ?: 0
    val candidate = registers.values.max() ?: 0
    registers[MAX] = if(candidate > max) candidate else max
  }
}
