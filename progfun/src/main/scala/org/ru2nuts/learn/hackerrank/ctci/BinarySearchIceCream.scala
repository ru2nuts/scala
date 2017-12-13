package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
  */
object BinarySearchIceCream {

  def getVals(n: Int, m: Int, a: Array[Int]): (Int, Int) = {

    (0 to n - 1).foreach(i => {
      val v1 = a(i)
      (i + 1 to n - 1).foreach(j => {
        val v2 = a(j)
        if (v1 + v2 == m)
          return (i, j)
      })
    })
    throw new Exception("no matching pair")
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var t = sc.nextInt();
    var a0 = 0;
    while (a0 < t) {
      var m = sc.nextInt();
      var n = sc.nextInt();
      var a = new Array[Int](n);
      for (a_i <- 0 to n - 1) {
        a(a_i) = sc.nextInt();
      }
      a0 += 1;
      val (i: Int, j: Int) = getVals(n, m, a)
      println((i + 1) + " " + (j + 1))
    }
  }
}
