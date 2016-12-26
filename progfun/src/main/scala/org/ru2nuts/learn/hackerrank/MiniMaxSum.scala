package org.ru2nuts.learn.hackerrank

object MiniMaxSum {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)

    val n = 5
    val l = for (i <- 0 to n - 1) yield sc.nextLong

    var max = 0L
    var min = Long.MaxValue

    val sum = l.reduce(_+_)

    l.foreach(v => {
      val curSum4 = sum - v

      if (max < curSum4)
        max = curSum4

      if (min > curSum4)
        min = curSum4
    })

    print(s"${min} ${max}")
  }
}
