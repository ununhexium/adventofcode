package y2019.io

import kotlin.random.Random

class DelegatedInputOutput(
    override val name: String,
    private val input: Input,
    private val output: Output
) : InputOutput, Input by input, Output by output {
  constructor(
      input: Input,
      output: Output
  ) : this("IO" + Random.nextInt(65536), input, output)
}
