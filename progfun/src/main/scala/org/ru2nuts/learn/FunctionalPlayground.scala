package org.ru2nuts.learn

import scala.collection._

object FunctionalPlayground {


  def main(args: Array[String]) = {
    val f1 = fibonacciSeq(1000)

    f1.foreach(println)
  }

  def fibonacciSeq(upperLimit: Int): Seq[Int] = {

    def recur(a1: Int, a2: Int, lim: Int): List[Int] = {
      val a3: Int = a1 + a2
      if (a3 > lim) Nil else a3 :: recur(a2, a3, lim)
    }

    1 :: 1 :: Nil ::: recur(1,1, upperLimit)
  }


}
