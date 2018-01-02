package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
  */
object MergeSortInversions {

  def countInversions(arr: Array[Int]): Long = {
    val counter = new SwapsCountStruct()
    mergeSortRecur(arr, counter)
    counter.count
  }

  class SwapsCountStruct(var count: Long = 0L) {}

  def mergeSortRecur(a: Array[Int], swapsCount: SwapsCountStruct): Array[Int] = {

    if (a.length <= 1)
      return a

    val (a1, a2) = a.splitAt(a.length / 2)

    val sortedA1 = mergeSortRecur(a1, swapsCount)
    val sortedA2 = mergeSortRecur(a2, swapsCount)

    val res = new scala.collection.mutable.ArrayBuffer[Int]()

    var i, j = 0

    while (i < sortedA1.length && j < sortedA2.length) {
      val e1 = sortedA1(i)
      val e2 = sortedA2(j)

      if (e1 <= e2) {
        res.append(e1)
        i += 1
      } else {
        res.append(e2)
        j += 1
        swapsCount.count += (sortedA1.length - i)
      }
    }
    while (i < sortedA1.length) {
      val e1 = sortedA1(i)
      res.append(e1)
      i += 1
    }
    while (j < sortedA2.length) {
      val e2 = sortedA2(j)
      res.append(e2)
      j += 1
    }

    res.toArray
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var t = sc.nextInt();
    var a0 = 0;
    while (a0 < t) {
      var n = sc.nextInt();
      var arr = new Array[Int](n);
      for (arr_i <- 0 to n - 1) {
        arr(arr_i) = sc.nextInt();
      }
      val result = countInversions(arr);
      println(result)
      a0 += 1;
    }
  }

}
