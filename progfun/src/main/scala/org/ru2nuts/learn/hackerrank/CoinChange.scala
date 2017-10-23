package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/coin-change
  */
object CoinChange {

  val cachedCounts = mutable.HashMap[(Long, Int), Long]()

  def combinationsCount(sum: Long, denominations: Seq[Long], denomStartPos: Int): Long = {
    if (denominations.size == denomStartPos || sum == 0)
      0
    else if (denominations.size == denomStartPos + 1) {
      val den = denominations(denomStartPos)
      if (sum % den == 0)
        1
      else
        0
    } else {
      val cachedRes = cachedCounts.get((sum, denomStartPos))

      if (cachedRes.nonEmpty)
        cachedRes.get
      else {
        var resultCount = 0L
        val largestDemon = denominations(denomStartPos)
        val largestDemonCount = sum / largestDemon

        (1L to largestDemonCount).foreach(ldm => {
          val rem = sum - largestDemon * ldm
          if (rem == 0)
            resultCount += 1
          else
            resultCount += combinationsCount(rem, denominations, denomStartPos + 1)
        })

        resultCount += combinationsCount(sum, denominations, denomStartPos + 1)

        cachedCounts.put((sum, denomStartPos), resultCount)
        resultCount
      }
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
    println(combinationsCount(n, c.toSeq, 0))
  }
}