package y2019.instruction

import y2019.Instruction
import y2019.io.InputOutput
import java.util.concurrent.atomic.AtomicReference

class Load(raw: Int, val io: InputOutput) : Instruction(
    1,
    raw / 100,
    { ptr, program, modes ->
      val param0 = modes[0]
      io.set(param0[program, ptr + 1])
    }
)
