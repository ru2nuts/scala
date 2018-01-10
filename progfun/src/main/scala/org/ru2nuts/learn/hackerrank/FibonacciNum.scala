package org.ru2nuts.learn.hackerrank

object FibonacciNum {

  def fibonacci(z: Int): Int = {

    if (z == 0)
      return 0
    if (z == 1)
      return 0
    if (z == 2)
      return 1

    var (v1, v2) = (0, 1)

    var i = 0

    var res = 0

    for (i <- 3 to z) {
      res = v1 + v2

      v1 = v2
      v2 = res

    }
    return res
  }


  def main(args: Array[String]) {
    /** This will handle the input and output **/
    println(fibonacci(scala.io.StdIn.readInt()))
  }

}
