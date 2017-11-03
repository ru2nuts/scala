package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/unique-colors
  */
object UniqueColors {

  class Node(val index: Int, val color: Int) {
    val adjacent: mutable.MutableList[Node] = mutable.MutableList[Node]()

    override def toString: String = "" + index + ":" + color
  }

  val cache: mutable.HashMap[(Node, Node), Int] = mutable.HashMap[(Node, Node), Int]()

  def getPath(from: Node, to: Node, prevNode: Node, colors: mutable.HashSet[Int]): Int = {

    colors += from.color
    if (from == to) {
      val size = colors.size
      return size
    }
    val frontier = from.adjacent.filter(_ != prevNode)
    if (frontier.size == 0)
      return 0
    else {
      val size = frontier.map(e => { //foreach with var, because frontier is a hashset
        getPath(e, to, from, getColorsClone(colors))
      }).reduce(_ + _)
      return size
    }
  }

  private def getColorsClone(colors: mutable.HashSet[Int]) = {
    colors.clone()
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt

    val nodes = Array.ofDim[Node](n)
    (1 to n).foreach(index => {
      nodes(index - 1) = new Node(index, sc.nextInt)
    })

    (1 until n).foreach(zu => {
      val n1 = nodes(sc.nextInt - 1)
      val n2 = nodes(sc.nextInt - 1)
      n1.adjacent += n2
      n2.adjacent += n1
    })

    (0 until n).foreach(i => {
      val node1 = nodes(i)
      val colCount: Int = (0 until n).map(j => {
        val node2 = nodes(j)

        val possibleVal1 = cache.get(node1, node2)
        if (possibleVal1.nonEmpty) {
          possibleVal1.get
        } else {
          val possibleVal2 = cache.get(node2, node1)
          if (possibleVal2.nonEmpty) {
            possibleVal2.get
          } else {
            val size = getPath(node1, node2, node1, mutable.HashSet[Int]())
            cache.put((node1, node2), size)
            size
          }
        }
      }).reduce(_ + _)
      println(colCount)
    })
  }
}