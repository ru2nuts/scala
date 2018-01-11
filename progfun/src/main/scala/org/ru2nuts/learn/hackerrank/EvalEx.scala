package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/eval-ex/problem
  */
object EvalEx {

  def fact(n: Int): Double = {
    (1 to n).reduce(_ * _).toDouble
  }

  def ex(x: Double): Double = {
    1 + x + (2 to 9).map(i => {
      val z = Math.pow(x, i) / fact(i)
      z
    }).sum
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    (0 until n).foreach(e => {
      val x = sc.nextDouble()

      println(ex(x))

    })
  }
}
