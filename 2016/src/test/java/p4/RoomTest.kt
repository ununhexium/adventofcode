package p4

import org.junit.Assert.assertEquals
import org.junit.Test

class RoomTest
{
  @Test
  fun isThereAGhostInTheMachine()
  {
    val examples = mapOf(
        "aaaaa-bbb-z-y-x-123[abxyz]" to true,
        "a-b-c-d-e-f-g-h-987[abcde]" to true,
        "not-a-real-room-404[oarel]" to true,
        "totally-real-room-200[decoy]" to false
    )

    examples.forEach { string, real ->
      val room = Room.from(string)
      assertEquals(string + " " + room.hashIt(), real, room.isReal())
    }
  }

  @Test
  fun decipher()
  {
    val room = Room.from("qzmt-zixmtkozy-ivhz-343[xxx]")
    assertEquals("very encrypted name", room.decipher())
  }
}