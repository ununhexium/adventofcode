fun day04(file: List<String>): Int {

  val fields = listOf<Pair<String, (String) -> Boolean>>(
    "byr" to { it.toInt() in 1920 .. 2002 },
    "iyr" to { it.toInt() in 2010 .. 2020 },
    "eyr" to { it.toInt() in 2020 .. 2030 },
    "hgt" to {
      when (it.takeLast(2)) {
        "cm" -> it.dropLast(2).toInt() in 150 .. 193
        "in" -> it.dropLast(2).toInt() in 59 .. 76
        else -> false
      }
    },
    "hcl" to { it.matches(Regex("#[0-9a-f]{6}")) },
    "ecl" to { it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
    "pid" to { it.matches(Regex("[0-9]{9}")) },
//    "cid", now 100% optional ^_^
  )

  val passports = file.joinToString(separator = " ").split("  ")

  return passports.count { p ->
    p.split(" ").map { it.split(":")[0] }.containsAll(fields.map { it.first }) &&
        p.split(" ").map { it.split(":").map { it.strip() } }.all { (k, v) ->
          k == "cid" || fields.first { it.first == k }.second.invoke(v) ?: false
        }
  }
}
