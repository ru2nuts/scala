package org.ru2nuts.learn.hackerrank

object ArrayDS {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.nextInt();
    }

    for (i <- 1 to n) {
      print(arr(n-i))
      print(' ')
    }
  }
}
