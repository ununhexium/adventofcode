package y2019.io

class DelegatedInputOutput(
    override val name: String,
    private val input: Input,
    private val output: Output
) : InputOutput, Input by input, Output by output
