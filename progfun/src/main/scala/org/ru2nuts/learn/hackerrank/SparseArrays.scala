package org.ru2nuts.learn.hackerrank

import java.util.Scanner

/**
  * https://www.hackerrank.com/challenges/sparse-arrays
  */
object SparseArrays {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt
    sc.nextLine
    val ss = for (i <- 0 to n - 1) yield sc.nextLine
    val nq = sc.nextInt
    sc.nextLine
    val qq = for (i <- 0 to nq - 1) yield sc.nextLine
    qq.foreach(q => {
      println(ss.count(_ == q))
    })
  }
}
