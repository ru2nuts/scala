package org.ru2nuts.learn.hackerrank

import scala.collection.mutable.ListBuffer

/**
  * https://www.hackerrank.com/challenges/dynamic-array
  */
object DynamicArray {

  def main(args: Array[String]): Unit = {

    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt
    val q = sc.nextInt
    val qs = for (i <- 0 to q - 1) yield {
      (sc.nextInt, sc.nextInt, sc.nextInt)
    }

    val seqList = (0 to n - 1).map(e => ListBuffer[Int]())
    var lastAnswer = 0

    qs.foreach(q => {
      if (q._1 == 1) {
        val x = q._2
        val y = q._3
        val seq = seqList((x ^ lastAnswer) % n)
        seq += y
      } else if (q._1 == 2) {
        val x = q._2
        val y = q._3
        val seq = seqList((x ^ lastAnswer) % n)
        val size = seq.size
        lastAnswer = seq(y % size)
        println(lastAnswer)
      }
    })
  }

}
