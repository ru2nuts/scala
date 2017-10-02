package org.ru2nuts.learn.hackerrank

/**
  * https://www.hackerrank.com/challenges/cube-summation
  */
object CubeSummation {

  def blockSum(cube: Array[Array[Array[Long]]], x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int): Long = {
    cube.slice(x1 - 1, x2).map(l2 => l2.slice(y1 - 1, y2).map(l3 => l3.slice(z1 - 1, z2).sum).sum).sum
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);

    val res = scala.collection.mutable.MutableList[Long]()
    val t = sc.nextInt
    (1 to t).foreach(l => {
      val n = sc.nextInt
      val m = sc.nextInt

      val cube: Array[Array[Array[Long]]] = Array.ofDim[Long](n, n, n)

      (1 to m).foreach(_ => {
        sc.next() match {
          case "UPDATE" => {
            val x = sc.nextInt
            val y = sc.nextInt
            val z = sc.nextInt
            val W = sc.nextLong
            cube(x - 1)(y - 1)(z - 1) = W
          }
          case "QUERY" => {
            val (x1, y1, z1, x2, y2, z2) = (sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt())
            res += blockSum(cube, x1, y1, z1, x2, y2, z2)
          }
        }
      })
    })
    res.foreach(println(_))

  }
}
