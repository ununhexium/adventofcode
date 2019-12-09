package y2019

object D8A {
  fun solve(input: String, width: Int, height: Int): Int {
    val surface = width * height
    val layersCount = input.length / surface
    val layers = (0 until layersCount).map {
      input.subSequence(surface * it, surface * (it + 1))
    }

    val minZero = layers.minBy { it.count { it == '0' } }!!
    val ones = minZero.count { it == '1' }
    val twos = minZero.count { it == '2' }

    return ones * twos
  }
}

object D8B {
  fun decode(input: String, width: Int, height: Int): String {
    val surface = width * height

    val layersCount = input.length / surface
    val layers = (0 until layersCount).map {
      input.subSequence(surface * it, surface * (it + 1))
    }

    return (0 until height).map { y ->
      (0 until width).map { x ->
        val cursor = x + width * y
        val layer = layers.first { it[cursor] != '2' }
        val color = layer[cursor]
        when (color) {
          '0' -> ' '
          '1' -> 'X'
          else -> throw IllegalStateException("What does $color mean?")
        }
      }
    }.joinToString("\n") {
      it.joinToString("")
    }
  }
}