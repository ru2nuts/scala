package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/crush
  */
object ArrayManipulation {

  var max = 0L

  def modifyA(a: Int, b: Int, k: Long, inA: scala.collection.mutable.ArraySeq[Long]) = {
    for (i <- a to b) {
      val l = inA(i) + k
      if (l > max)
        max = l
      inA(i) = l
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt
    val m = sc.nextInt
    var inA = new scala.collection.mutable.ArraySeq[Long](n)

    (1 to m).foreach(q => {
      val a = sc.nextInt - 1
      val b = sc.nextInt - 1
      val k = sc.nextLong
      modifyA(a, b, k, inA)
    })
    println(max)
  }

}
