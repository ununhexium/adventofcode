package p5

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

fun main(args: Array<String>)
{
  val input = "ojvtpuvg"
  val password: MutableList<Char?> = (0..7).map { null }.toMutableList()
  var index = 0
  val hasher = MessageDigest.getInstance("MD5")
  while (password.filter { it != null }.size < 8)
  {
    hasher.reset()
    val candidate = input + index
    hasher.update(candidate.toByteArray(Charsets.UTF_8))
    val byteHash = hasher.digest()
    val md5sum = byteHash.let { DatatypeConverter.printHexBinary(it)!! }

    if (md5sum.startsWith("00000"))
    {
      println("Found $md5sum")
      val position = md5sum[5].toInt() - '0'.toInt()
      val digit = md5sum[6]
      if (position < password.size && password[position] == null)
      {
        password[position] = digit
        println("Found " + password.joinToString{ it?.toString() ?: "_" })
      }
    }
    if (index % 1_000_000 == 0)
    {
      println("To the Moon ! Almost very success :D $index")
    }
    index++
  }

  println(password.joinToString("").toLowerCase())
}