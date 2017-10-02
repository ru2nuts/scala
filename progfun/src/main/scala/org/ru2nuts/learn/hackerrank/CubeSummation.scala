package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/cube-summation
  */
object CubeSummation {

  type cubeType = mutable.HashMap[Int, mutable.HashMap[Int, mutable.HashMap[Int, Long]]]

  def setCubeVal(sparseCube: cubeType, x: Int, y: Int, z: Int, v: Long) = {
    if (!sparseCube.contains(x))
      sparseCube(x) = mutable.HashMap[Int, mutable.HashMap[Int, Long]]()
    if (!sparseCube(x).contains(y))
      sparseCube(x)(y) = mutable.HashMap[Int, Long]()
    sparseCube(x)(y)(z) = v
  }

  def blockSum(sparseCube: cubeType, x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int): Long = {
    var sum = 0L
    sparseCube.keys.foreach(x => {
      if (x1 <= x && x <= x2) {
        val sx = sparseCube(x)
        sx.keys.foreach(y => {
          if (y1 <= y && y <= y2) {
            val sxy = sx(y)
            sxy.keys.foreach(z => {
              if (z1 <= z && z <= z2) {
                sum += sxy(z)
              }
            })
          }
        })
      }
    })
    sum
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);

    val res = scala.collection.mutable.MutableList[Long]()
    val t = sc.nextInt
    (1 to t).foreach(l => {
      val n = sc.nextInt
      val m = sc.nextInt

      val sparseCube: cubeType = mutable.HashMap[Int, mutable.HashMap[Int, mutable.HashMap[Int, Long]]]()

      (1 to m).foreach(_ => {
        sc.next() match {
          case "UPDATE" => {
            val x = sc.nextInt
            val y = sc.nextInt
            val z = sc.nextInt
            val W = sc.nextLong
            setCubeVal(sparseCube, x, y, z, W)
          }
          case "QUERY" => {
            val (x1, y1, z1, x2, y2, z2) = (sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt())
            res += blockSum(sparseCube, x1, y1, z1, x2, y2, z2)
          }
        }
      })
    })
    res.foreach(println(_))

  }
}
