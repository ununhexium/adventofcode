package y2019.instruction

import y2019.memory.ImmediateMemoryAccessor
import y2019.Instruction
import y2019.memory.PositionalMemoryAccessor

class MulInstruction(raw: Int) : Instruction(
    3,
    raw / 100,
    { ptr, program, accessors ->
      val param0 = accessors[0]
      val param1 = accessors[1]
      val param2 = accessors[2]
      param2[program, ptr + 3] = param0[program, ptr + 1] * param1[program, ptr + 2]
    }
)
