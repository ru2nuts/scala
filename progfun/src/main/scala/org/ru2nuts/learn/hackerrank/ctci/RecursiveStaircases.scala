package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
  */
object RecursiveStaircases {

  val memo = scala.collection.mutable.HashMap[Int, Long]()

  def countCombinations(n: Int): Long = {
    if (n < 0)
      return 0

    if (n == 0)
      return 1

    if (n == 1)
      return 1

    if (memo.contains(n))
      return memo.get(n).get

    val res = countCombinations(n - 1) + countCombinations(n - 2) + countCombinations(n - 3)
    memo.put(n, res)
    res
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var s = sc.nextInt();
    var a0 = 0;
    while (a0 < s) {
      var n = sc.nextInt();
      println(countCombinations(n))
      a0 += 1
    }
  }
}
