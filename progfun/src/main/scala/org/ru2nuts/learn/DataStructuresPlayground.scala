package org.ru2nuts.learn

import java.util

object DataStructuresPlayground {

  def main(args: Array[String]) = {

    // reverse string in place
    val z = "abcdefg".toCharArray
    for (i <- 0 to z.length / 2 - 1) {
      var bi = z.length - 1 - i
      val c = z(i)
      z(i) = z(bi)
      z(bi) = c
    }
    println(new String(z))

    // reverse list (with stack)
    var l = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
    var s = new scala.collection.mutable.Stack[Int]
    l.foreach(s.push(_))
    s.foreach(print)
    println()

    // reverse list
    var res: List[Any] = List()
    val n: Int = l.length - 1
    for (i <- 0 to n) {
      res = res ::: l(n - i) :: Nil
    }
    res.foreach(print)

    println()
    println(itoa(12435))
    println(itoa(21))
    println(itoa(1))
    println(itoa(0))
    println(itoa(-1))
    println(itoa(-123))

    playTicTacToe
  }


  def itoa(v: Int) = {
    val dc = Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

    def recur(n: Int, base: Int, pos: Int): String = {
      if (n >= Math.pow(base, (pos - 1)).toInt) {
        val digit: Int = (n % Math.pow(base, pos).toInt) / Math.pow(base, (pos - 1)).toInt
        recur(n, base, pos + 1) + dc(digit)
      }
      else if (n == 0)
        "0"
      else
        ""
    }

    (if (v < 0) "-" else "") + recur(Math.abs(v), 10, 1)
  }

  // x,y - first move
  def playTicTacToe() = {

    val matrix: Array[Array[Int]] = Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))
    val n = 2
    val sc = new java.util.Scanner(System.in)

    var getOut = false
    while (!getOut) {
      println("Enter x, y")
      var (x, y) = (sc.nextInt(), sc.nextInt())
      matrix(x)(y) = 1
      printM(matrix)
      val nxt = nextMove(matrix)
      if (nxt.isEmpty) {
        println("No more moves")
        getOut = true
      } else {
        var (x, y) = nxt.get
        matrix(x)(y) = 2
        printM(matrix)
        getOut = isDone(matrix)
      }
    }

    def printM(m: Array[Array[Int]]) =
      for (i <- 0 to n) {
        for (j <- 0 to n) {
          print(m(i)(j))
          print(' ')
        }
        println()
      }

    def isDone(m: Array[Array[Int]]): Boolean = {
      (for (i <- 0 to n) yield {
        val m1 = (m(i)(0), m(i)(1), m(i)(2))
        val m2 = (m(0)(i), m(1)(i), m(2)(i))
        crossMatch(m1) || crossMatch(m2)
      }).reduce(_ || _) ||
        crossMatch(m(0)(0), m(1)(1), m(2)(2)) ||
        crossMatch(m(2)(0), m(1)(1), m(0)(2))
    }

    def crossMatch(v: (Int, Int, Int)) =
      v == (1, 1, 1) || v == (2, 2, 2)

    def nextMove(m: Array[Array[Int]]): Option[(Int, Int)] = {

      val zz =
        (for (i <- 0 to n) yield {
          val z =
            (for (j <- 0 to n) yield {
              if (m(i)(j) == 0)
                Some((i, j))
              else
                None
            }
              ).filter(_.nonEmpty)
          z
        }).flatMap(r => r)

      val avalSpots = zz.filter(_.nonEmpty).map(_.get)

      if (avalSpots.nonEmpty) {
        val nextI = (avalSpots.size * Math.random()).toInt
        Some(avalSpots(nextI))
      }
      else
        None
    }
  }


}
