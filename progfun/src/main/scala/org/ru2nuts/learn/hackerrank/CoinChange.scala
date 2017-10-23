package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/coin-change
  */
object CoinChange {

  def combinationsCount(sum: Long, denominations: mutable.SortedSet[Long]): Int = {
    if (denominations.size == 0 || sum == 0)
      0
    else if (denominations.size == 1) {
      val den = denominations.head
      if (sum % den == 0)
        1
      else
        0
    } else {
      var resultCount = 0
      val largestDemon = denominations.head
      val largestDemonCount = sum / largestDemon

      (1L to largestDemonCount).foreach(ldm => {
        val rem = sum - largestDemon * ldm
        if (rem == 0)
          resultCount += 1
        else
          resultCount += combinationsCount(rem, denominations.tail)
      })

      resultCount += combinationsCount(sum, denominations.tail)
      resultCount
    }
  }


  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    val m = sc.nextInt()
    val c = collection.mutable.SortedSet[Long]()
    for (c_i <- 0 to m - 1) {
      c.add(sc.nextLong())
    }
    println(combinationsCount(n, c))
  }
}