package org.ru2nuts.learn.hackerrank

import scala.collection.immutable.IndexedSeq

/**
  * https://www.hackerrank.com/challenges/crush
  */
object ArrayManipulation {

  def modifyA(a: Int, b: Int, k: Int, inA: IndexedSeq[Int]): IndexedSeq[Int] = {
    for (i <- 0 to inA.length - 1) yield
      if (i >= a && i <= b)
        inA(i) + k
      else
        inA(i)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt();
    val m = sc.nextInt();
    var inA = (1 to n).map(i => 0)

    (1 to m).foreach(q => {
      val a = sc.nextInt() - 1;
      val b = sc.nextInt() - 1;
      val k = sc.nextInt();
      inA = modifyA(a, b, k, inA)
    })
    var max = Integer.MIN_VALUE
    inA.foreach(e => if (max < e) max = e)
    println(max)
  }

}
