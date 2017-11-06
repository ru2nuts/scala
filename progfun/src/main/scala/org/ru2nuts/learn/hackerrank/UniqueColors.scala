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

  def getPathBFS(from: Node, to: Node): Int = {
    val possibleVal2 = cache.get(to, from)
    if (possibleVal2.nonEmpty) {
      return possibleVal2.get
    } else {
      val q = new mutable.Queue[(Node, BitSet, Node)]()
      q.enqueue((from, BitSet(from.color), from))
      while (q.nonEmpty) {
        val (node, currentBS, prevNode) = q.dequeue()
        val nextNodes = node.adjacent.filterNot(_ == prevNode)
        if (nextNodes.nonEmpty)
          nextNodes.foreach(n => {
            val nextBS = currentBS + n.color
            if (n == to) {
              val resSize = nextBS.size
              cache.put((from, to), resSize)
              return resSize
            }
            else
              q.enqueue((n, nextBS, node))
          })
      }
      1
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
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
      val from = nodes(i)
      val colCount: Int = (0 until n).map(j => {
        if (i == j)
          1
        else {
          val to = nodes(j)
          val size = getPathBFS(from, to)
          size
        }
      }).reduce(_ + _)
      println(colCount)
      //res.append(colCount).append('\n')
    })
    println(res.toString())
  }
}