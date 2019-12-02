package y2019

object D2A {
  fun compute(input: List<Int>): List<Int> {
    val program = input.toMutableList()

    var cursor = 0

    out@ while (true) {
      when (program[cursor]) {
        1 -> program[program[cursor + 3]] = program[program[cursor + 1]] + program[program[cursor + 2]]
        2 -> program[program[cursor + 3]] = program[program[cursor + 1]] * program[program[cursor + 2]]
        99 -> break@out
      }

      cursor += 4
    }

    return program
  }

  fun solve(input: List<Int>): Int {
    val altered = input.toMutableList()
    altered[1]=12
    altered[2]=2
    val computed = compute(altered)
    return computed[0]
  }
}