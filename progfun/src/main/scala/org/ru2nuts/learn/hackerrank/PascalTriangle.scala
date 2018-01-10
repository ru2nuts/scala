package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/pascals-triangle/problem
  */
object PascalTriangle {

  def main(args: Array[String]) {
    val k = scala.io.StdIn.readInt()

    def fact(n: Int): Int = {
      if (n == 0)
        1
      else
        (1 to n).product
    }

    for (r <- 0 until k) {
      for (c <- 0 to r) {
        print(fact(r) / (fact(c) * fact(r - c)))
        print(' ')
      }
      println()
    }
  }
}
