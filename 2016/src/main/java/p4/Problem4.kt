package p4

import more.CircularList
import more.Input

val NAME = "name"
val ID = "id"
val CHECKSUM = "checksum"

val pattern = Regex("(?<$NAME>[\\p{Lower}-]+)-(?<$ID>\\p{Digit}+)\\[(?<$CHECKSUM>\\p{Lower}+)]")

val alphabet = CircularList(('a'..'z').toList())

fun shift(c: Char, amount: Int) = alphabet[c.toInt() - 'a'.toInt() + amount]

data class Room(val name: String, val id: Int, val checksum: String)
{
  companion object
  {
    fun from(input: String): Room
    {
      val matcher = pattern.matchEntire(input) ?: throw RuntimeException("Broken input: $input")

      fun get(key: String) =
          matcher.groups[key]?.value ?: throw  RuntimeException("No value for $key in $input")

      return Room(
          get(NAME),
          get(ID).toInt(),
          get(CHECKSUM)
      )
    }
  }

  fun isReal(): Boolean
  {
    return checksum == hashIt()
  }

  fun hashIt(): String
  {
    return name
        // dashes don't count
        .replace("-", "")
        // count each letter
        .groupBy { it }
        .mapValues { it.value.size }
        // sort by occurrence
        .toList()
        /*
         * Sort by letter first.
         * So if there is a tie, we get the lowest letter.
         */
        .sortedBy { it.first.toInt() }
        /*
         * Descending order.
         * Don't use reversed() as it would mess up the alphabetical order
         */
        .sortedBy { -it.second }
        // only the 5 biggest count
        .take(5)
        // recreate the hash
        .map { it.first }
        .joinToString(separator = "")
  }

  fun decipher() =
      name
          .map {
            if (it == '-') ' ' else shift(it, id)
          }
          .joinToString(separator = "")
}

fun main(args: Array<String>)
{
  val rooms = Input
      .getFor("p4")
      .split("\n")
      .map { Room.from(it) }

  val realOnes = rooms
      .sumBy { if (it.isReal()) it.id else 0 }

  println(realOnes)

  val roomName = "north"
  val northPoleRoom = rooms.filter { it.isReal() }.filter { it.decipher().contains(roomName) }
  println(northPoleRoom.map { it.toString() + " " + it.decipher() })
}