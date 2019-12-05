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

    // STORE
//    3 to Instruction(1) { ptr, program ->
//      program[program[ptr + 1]]
//    }

    // HALT
    99 to Instruction(0) { _, _ -> throw HaltException() }
)
