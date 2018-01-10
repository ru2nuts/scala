package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/huge-gcd-fp/problem
  */
object HugeGCD {

  def gcd(a: BigInt, b: BigInt): BigInt = {
    if (a >= b)
      gcdR(a, b)
    else
      gcdR(b, a)
  }

  def gcdR(a: BigInt, b: BigInt): BigInt = {
    if (b == 0)
      a
    else
      gcdR(b, a % b)
  }

  def productOnNums(ns: Array[Int]): BigInt = {
    ns.aggregate(BigInt(1))((b: BigInt, a: Int) => {
      b * a
    }, (b1: BigInt, b2: BigInt) => {
      b1 * b2
    })
  }

  def main(args: Array[String]): Unit = {
    val n = scala.io.StdIn.readInt()
    val ns = scala.io.StdIn.readLine().split(' ').map(_.toInt)
    val m = scala.io.StdIn.readInt()
    val ms = scala.io.StdIn.readLine().split(' ').map(_.toInt)

    val bigN = productOnNums(ns)
    val bigM = productOnNums(ms)

    val g = gcd(bigN, bigM)

    val modn = (1 to 9).map(r => 10L).reduce(_*_) + 7

    println(g % modn)
  }
}
