package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/maximum-perimeter-triangle
  */
object MaximumPerimeterTriangle {


  def isValid(triangle: Seq[Long]): Boolean = {
    val t = triangle.sorted
    triangle.filterNot(_ == 0).size == 3 &&
      t(2) < t(0) + t(1)
  }

  def perim(t: Seq[Long]) =
    t(0) + t(1) + t(2)

  def longSide(t: Seq[Long]): Long = {
    val s = t.sorted
    s(2)
  }

  def midSide(t: Seq[Long]): Long = {
    val s = t.sorted
    s(1)
  }

  def shortSide(t: Seq[Long]): Long = {
    val s = t.sorted
    s(0)
  }

  def main(args: Array[String]): Unit = {

    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt
    val l = for (i <- 0 to n - 1) yield sc.nextLong

    var (maxPerim, maxLongSide, maxMidSide, maxShortSide) = (-1L, -1L, -1L, -1L)

    for (i <- 0 to n - 1) {
      for (j <- i + 1 to n - 1) {
        for (k <- j + 1 to n - 1) {
          val triangle = Seq(l(i), l(j), l(k))

          if (isValid(triangle)) {
            val curPerim = perim(triangle)
            val curLongSide = longSide(triangle)
            val curMidSide = midSide(triangle)
            val curShortSide = shortSide(triangle)

            if (curPerim > maxPerim) {
              maxPerim = curPerim
              maxLongSide = curLongSide
              maxMidSide = curMidSide
              maxShortSide = curShortSide
            } else if (curPerim == maxPerim && curLongSide > maxLongSide) {
              maxLongSide = curLongSide
              maxMidSide = curMidSide
              maxShortSide = curShortSide
            } else if (curPerim == maxPerim && curLongSide == maxLongSide && curShortSide > maxShortSide) {
              maxLongSide = curLongSide
              maxMidSide = curMidSide
              maxShortSide = curShortSide
            }
          }
        }
      }
    }

    if (maxPerim > -1)
      println(s"${maxShortSide} ${maxMidSide} ${maxLongSide}")
    else
      println(-1)
  }

}
