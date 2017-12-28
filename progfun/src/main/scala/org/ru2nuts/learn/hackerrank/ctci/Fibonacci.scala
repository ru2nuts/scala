package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem
  */
object Fibonacci {

  def fibonacci(x: Int): Int = {
    x match {
      case 0 => 0
      case 1 => 1
      case n => fibonacci(n - 1) + fibonacci(n - 2)
    }
  }


  def main(args: Array[String]) {
    /** This will handle the input and output **/
    println(fibonacci(scala.io.StdIn.readInt()))
  }

}
