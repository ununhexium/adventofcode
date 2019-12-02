package y2019

class IntCodeComputer(
    input: List<Int>,
    val noun: Int? = null,
    val verb: Int? = null
) {
  val program: MutableList<Int> = input.toMutableList()

  fun compute(): List<Int> {
    var ptr = 0

    val operations = instructions

    try {
      while (true) {
        val opCode = program[ptr]
        val op = operations[opCode] ?: error("Unknown operation code $opCode")
        ptr = op.tick(ptr, program)
      }
    } catch (e: HaltException) {
      return program
    }
  }

  fun init() {
    program[1] = noun ?: program[1]
    program[2] = verb ?: program[2]
  }

  fun run(): Int {
    init()
    compute()
    return program[0]
  }
}