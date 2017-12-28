package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
  */
object MakingAnagrams {

  def main(args: Array[String]) {
    var a = scala.io.StdIn.readLine()
    var b = scala.io.StdIn.readLine()

    val h = new scala.collection.mutable.HashMap[Char, Int]()

    a.foreach(c => {
      val count = h.getOrElse(c, 0) + 1
      h.put(c, count)
    })
    b.foreach(c => {
      val count = h.getOrElse(c, 0) - 1
      h.put(c, count)
    })

    val sum = h.values.reduce { (a: Int, b: Int) =>
      Math.abs(a) + Math.abs(b)
    }

    println(sum)
  }

}
