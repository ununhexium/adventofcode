package y2019

import y2019.io.DefaultBuffer
import y2019.io.DelegatedInputOutput
import y2019.io.PresetInput
import java.lang.IllegalStateException

object D7A {

  fun solve(intCode: String): List<Int> {
    val phaseSettings = (0..4).toList().permutations().map { it.toList() }

    val max = phaseSettings.sortedBy {
      val thrust = evaluatePhaseSettings(intCode, it)
      thrust
    }!!

    return max.last()
  }

  fun evaluatePhaseSettings(
      intCode: String,
      phaseSettings: List<Int>,
      previousOutput: Int = 0
  ): Int {
    return if (phaseSettings.isEmpty()) {
      previousOutput
    } else {
      val input = PresetInput(phaseSettings.first(), previousOutput)
      val output = DefaultBuffer()
      val io = DelegatedInputOutput(
          "default",
          input,
          output
      )
      IntCodeComputer(intCode, defaultIO = io).run()
      evaluatePhaseSettings(intCode, phaseSettings.drop(1), output.get())
    }
  }
}

object D7B {
  fun solve(intCode: String): List<Int> {
    val phaseSettings = (5..9).toList().permutations().map { it.toList() }

    val max = phaseSettings.sortedBy {
      val thrust = D7B.evaluateLoopPhaseSettings(intCode, it)
      thrust
    }!!

    return max.last()
  }

  fun evaluateLoopPhaseSettings(intCode: String, it: List<Int>): Int {
    TODO("Impl")
  }
}
