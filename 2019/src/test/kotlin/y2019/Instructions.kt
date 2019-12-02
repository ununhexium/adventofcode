package y2019

val instructions = mapOf(
    // ADD
    1 to Instruction(3) { ptr, program ->
      program[program[ptr + 3]] = program[program[ptr + 1]] + program[program[ptr + 2]]
    },

    // MUL
    2 to Instruction(3) { ptr, program ->
      program[program[ptr + 3]] = program[program[ptr + 1]] * program[program[ptr + 2]]
    },

    // HALT
    99 to Instruction(0) { _, _ -> throw HaltException() }
)
