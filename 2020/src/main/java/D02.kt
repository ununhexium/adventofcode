// counts valid passwords
fun day2a(input: List<String>): Int {
  return input.count { l ->
    val (policy, password) = l.split(':')
    val (low, tail) = policy.split('-')
    val (high, char) = tail.split(' ')
    val freq = password.groupingBy { it }.eachCount()
    val charFreq = (freq[char.first()] ?: 0)

    (low.toInt() <= charFreq) && (charFreq <= high.toInt())
  }
}

fun day2b(input: List<String>): Int {
  return input.count { l ->
    val (policy, password) = l.split(':')
    val (low, tail) = policy.split('-')
    val (high, char) = tail.split(' ')

    val indexLow = low.toInt()
    val indexHigh = high.toInt()

    val pos1 = password.lastIndex >= indexLow && password[indexLow] == char.first()
    val pos2 = password.lastIndex >= indexHigh && password[indexHigh] == char.first()

    pos1 xor pos2
  }
}
