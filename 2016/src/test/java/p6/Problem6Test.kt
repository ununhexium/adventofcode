package p6

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class Problem6Test{
  @Test
  fun shouldUnjam(){
    val input="""eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar"""

    Assert.assertEquals("easter", unjam(input.split("\n")))
  }
}