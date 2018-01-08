package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/stat-warmup/problem
  */
object StatsWarmup {

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution */

    val n = scala.io.StdIn.readInt()
    val strA = scala.io.StdIn.readLine()
    val elems = strA.split(' ').map(_.toInt).sorted

    val mean = elems.sum.toDouble / n
    println(mean) //mean

    // median
    val med = if (n > 0 && n % 2 == 0) {
      (elems(n / 2 - 1) + elems(n / 2)).toDouble / 2
    } else {
      elems(n / 2 - 1)
    }

    // med
    println(med)

    // mode
    println(elems.groupBy(e => e).toSeq.sortBy(g => (-g._2.size, g._1)).head._1)

    //std dev
    val stdDev = Math.sqrt(elems.map(e => {
      val d = e.toDouble - mean
      d * d
    }).sum / n)

    println(stdDev)

    val ciLow = mean - 1.96 * (stdDev / Math.sqrt(n))
    val ciHi = mean + 1.96 * (stdDev / Math.sqrt(n))

    println(s"$ciLow $ciHi")
  }

}
