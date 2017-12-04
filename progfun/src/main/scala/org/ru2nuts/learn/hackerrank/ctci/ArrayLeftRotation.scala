package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
  */
object ArrayLeftRotation {

  def convertIndex(i: Int, k: Int, n: Int): Int = {
    val newI = i + k
    if (newI >= n)
      newI - n
    else
      newI
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var k = sc.nextInt();
    var a = new Array[Int](n);
    for (a_i <- 0 to n - 1) {
      a(a_i) = sc.nextInt();
    }

    (0 until n).foreach(i => {
      print(a(convertIndex(i, k, n)))
      print(' ')
    })
  }

}
