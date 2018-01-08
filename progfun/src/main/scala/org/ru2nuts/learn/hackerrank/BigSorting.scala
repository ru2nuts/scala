package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/big-sorting/problem
  */
object BigSorting {

  def bigSorting(arr: Array[String]): Array[String] = {
    arr.sortWith((a, b) => {
      if (a.length == b.length) {
        a < b
      } else
        a.length < b.length
    })
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[String](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.next();
    }
    val result = bigSorting(arr);
    println(result.mkString("\n"))
  }

}
