package org.ru2nuts.learn.hackerrank

import scala.collection.{BitSet, mutable}

/**
  * https://www.hackerrank.com/challenges/unique-colors
  */
object UniqueColors {

  class Node(val index: Int, val color: Int) {
    val adjacent: mutable.MutableList[Node] = mutable.MutableList[Node]()

    override def toString: String = "" + index + ":" + color
  }

  val cache: mutable.HashMap[(Node, Node), Int] = mutable.HashMap[(Node, Node), Int]()

  def getPath(from: Node, to: Node, prevNode: Node, colors: BitSet): Int = {

    val newColors = colors + from.color
    if (from == to) {
      val size = newColors.size
      return size
    }
    val frontier = from.adjacent
    if (frontier.size == 0)
      return 0
    else {
      val size = frontier.map(e => { //foreach with var, because frontier is a hashset
        if (e == prevNode)
          0
        else
          getPath(e, to, from, newColors)
      }).reduce(_ + _)
      return size
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt

    var maxColors = 0

    val nodes = Array.ofDim[Node](n)
    (1 to n).foreach(index => {
      val colorNum = sc.nextInt
      if (colorNum > maxColors)
        maxColors = colorNum
      nodes(index - 1) = new Node(index, colorNum)
    })

    (1 until n).foreach(zu => {
      val n1 = nodes(sc.nextInt - 1)
      val n2 = nodes(sc.nextInt - 1)
      n1.adjacent += n2
      n2.adjacent += n1
    })

    val res = new java.lang.StringBuilder()

    (0 until n).foreach(i => {
      val node1 = nodes(i)
      val colCount: Int = (0 until n).map(j => {
        val node2 = nodes(j)
        val possibleVal2 = cache.get(node2, node1)
        if (possibleVal2.nonEmpty) {
          possibleVal2.get
        } else {
          val b = BitSet.newBuilder
          b.sizeHint(maxColors)
          val size = getPath(node1, node2, node1, b.result())
          cache.put((node1, node2), size)
          size
        }

      }).reduce(_ + _)
      //println(colCount)
      res.append(colCount).append('\n')
    })
    println(res.toString())
  }
}