package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/crush
  */
object ArrayManipulation {

  def modifyA(a: Int, b: Int, k: Long, inA: scala.collection.mutable.ArraySeq[Long]) = {
    inA(a) += k
    val afterB = b + 1
    if (afterB < inA.length)
      inA(afterB) -= k
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
    var max = 0L
    var runningSum = 0L
    inA.foreach(e => {
      runningSum += e
      if (max < runningSum)
        max = runningSum
    })
    println(max)
  }

}
