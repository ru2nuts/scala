package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/string-mingling/problem
  */
object MinglingStrings {

  def main(args: Array[String]) {
    val s1 = scala.io.StdIn.readLine()
    val s2 = scala.io.StdIn.readLine()

    println(s1.zip(s2).map(cc => cc._1 + "" + cc._2).mkString)
  }

}
