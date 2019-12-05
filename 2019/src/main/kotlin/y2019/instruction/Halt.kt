package y2019.instruction

import y2019.HaltException
import y2019.Instruction

class Halt : Instruction(0, 0, { _, _, _ -> throw HaltException() })
