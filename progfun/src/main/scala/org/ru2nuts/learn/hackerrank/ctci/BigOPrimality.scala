package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-big-o/problem
  */
object BigOPrimality {

  def isPrime(n: Long): Boolean = {
    if (n == 0 || n == 1)
      return false
    (2L to Math.sqrt(n).floor.toLong).foreach(i => {
      if (n % i == 0)
        return false
    })
    return true
  }


  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var p = sc.nextInt()
    for (zz <- 1 to p) {
      var n = sc.nextLong()
      println(if (isPrime(n)) "Prime " else "Not prime")
    }
  }

}
