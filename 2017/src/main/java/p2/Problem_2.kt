package p2

import javax.management.RuntimeErrorException
import kotlin.math.max
import kotlin.math.min

val input0 = """5 9 2 8
9 4 7 3
3 8 6 5"""

val input1 = """3458 3471 163 1299 170 4200 2425 167 3636 4001 4162 115 2859 130 4075 4269
2777 2712 120 2569 2530 3035 1818 32 491 872 113 92 2526 477 138 1360
2316 35 168 174 1404 1437 2631 1863 1127 640 1745 171 2391 2587 214 193
197 2013 551 1661 121 206 203 174 2289 843 732 2117 360 1193 999 2088
3925 3389 218 1134 220 171 1972 348 3919 3706 494 3577 3320 239 120 2508
239 947 1029 2024 733 242 217 1781 2904 2156 1500 3100 497 2498 3312 211
188 3806 3901 261 235 3733 3747 3721 267 3794 3814 3995 3004 915 4062 3400
918 63 2854 2799 178 176 1037 487 206 157 2212 2539 2816 2501 927 3147
186 194 307 672 208 351 243 180 619 749 590 745 671 707 334 224
1854 3180 1345 3421 478 214 198 194 4942 5564 2469 242 5248 5786 5260 4127
3780 2880 236 330 3227 1252 3540 218 213 458 201 408 3240 249 1968 2066
1188 696 241 57 151 609 199 765 1078 976 1194 177 238 658 860 1228
903 612 188 766 196 900 62 869 892 123 226 57 940 168 165 103
710 3784 83 2087 2582 3941 97 1412 2859 117 3880 411 102 3691 4366 4104
3178 219 253 1297 3661 1552 8248 678 245 7042 260 581 7350 431 8281 8117
837 80 95 281 652 822 1028 1295 101 1140 88 452 85 444 649 1247"""

val inputs = listOf(
    input0,
    input1
)

fun main(args: Array<String>)
{
  inputs.forEach {
    println(checksum(it))
    println(checksum2(it))
  }
}

fun checksum2(input: String): Int
{
  val grid = inputToGrid(input)
  return grid.map { it -> findIntDiv(it) }.sum()
}

private fun findIntDiv(line: List<Int>): Int
{
  line.map { a ->
    line.map { b ->
      if (a!=b && a % b == 0)
      {
        println("$a and $b")
        return max(a, b) / min(a, b)
      }
    }
  }
  throw RuntimeException("Data is wrong")
}

private fun checksum(input: String): Int
{
  val grid = inputToGrid(input)
  return grid.map { it.max()!! - it.min()!! }.sum()
}

private fun inputToGrid(input: String): List<List<Int>>
{
  val grid = input.split("\n").map { it.split(" ").map { it.toInt() } }
  return grid
}