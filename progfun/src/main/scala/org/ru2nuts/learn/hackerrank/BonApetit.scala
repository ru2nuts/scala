package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/bon-appetit
  */
object BonApetit {

  def main(args: Array[String]): Unit = {

    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt
    val k = sc.nextInt
    val c = for (i <- 0 to n - 1) yield sc.nextInt
    val b = sc.nextInt

    val s = b - ((c.sum - c(k)) / 2)

    if (s == 0)
      println("Bon Appetit")
    else
      println(s)
  }
}
