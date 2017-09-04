package org.ru2nuts.learn.hackerrank

import java.util.Scanner

object ArrayLeftRotation {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt
    val d = sc.nextInt
    val a = for (i <- 0 to n - 1) yield sc.nextInt
    for (i <- 0 to n - 1) yield {
      val newI = i + d
      if (newI < n) {
        print(a(newI))
      } else {
        print(a(newI % n))
      }
      print(' ')
    }
  }
}
