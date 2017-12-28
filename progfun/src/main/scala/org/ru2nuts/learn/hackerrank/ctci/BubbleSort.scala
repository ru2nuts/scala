package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
  */
object BubbleSort {


  def swap(a: Array[Int], j1: Int, j2: Int) = {
    val z = a(j1)
    a(j1) = a(j2)
    a(j2) = z
  }

  def bs(a: Array[Int]) = {
    var swapCount = 0
    val n = a.length
    for (i <- 0 until n) {
      for (j <- 0 until n - 1) {
        // Swap adjacent elements if they are in decreasing order
        if (a(j) > a(j + 1)) {
          swap(a, j, j + 1)
          swapCount += 1
        }
      }
    }
    swapCount
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var a = new Array[Int](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }

    val swapCount = bs(a)

    println(s"Array is sorted in $swapCount swaps.")
    println(s"First Element: ${a.head}")
    println(s"Last Element: ${a.last}")
  }

}
