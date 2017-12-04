package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/birthday-cake-candles
  */
object BirthdayCakeCandles {


  def birthdayCakeCandles(n: Int, ar: Array[Int]): Int =  {
    var max = Int.MinValue
    var maxCount = 1
    for (e <- ar) {
      if (max < e) {
        max = e
        maxCount = 1
      } else if (max == e) {
        maxCount += 1
      }
    }
    maxCount
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var ar = new Array[Int](n);
    for(ar_i <- 0 to n-1) {
      ar(ar_i) = sc.nextInt();
    }
    val result = birthdayCakeCandles(n, ar);
    println(result)
  }


}
