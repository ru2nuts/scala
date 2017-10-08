package org.ru2nuts.learn.hackerrank

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/unique-colors
  */
object UniqueColors {

  class Node(val index: Int, val color: Int) {
    val adjacent: mutable.HashSet[Node] = mutable.HashSet[Node]()

    override def toString: String = "" + index + ":" + color
  }

  def getPath(startNode: Node, currentNode: Node, prevNode: Node, colors: mutable.HashSet[Int]): Int = {

    colors += currentNode.color

    val frontier = currentNode.adjacent.filter(_ != prevNode)

    val cs = colors.size

    val fs = frontier.size
    val result =
      if (fs == 0)
        cs
      else if (fs == 1)
        cs + getPath(startNode, frontier.head, currentNode, colors)
      else {
        var ss = 0
        frontier.foreach(e => {
          ss += getPath(startNode, e, currentNode, colors.clone())
        })
        cs + ss
      }
    result
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt

    val nodes = mutable.ArrayBuffer[Node]()
    (1 to n).foreach(index => {
      nodes += (new Node(index, sc.nextInt))
    })

    val edges = (1 to n - 1).foreach(_ => {
      val n1 = nodes(sc.nextInt - 1)
      val n2 = nodes(sc.nextInt - 1)
      n1.adjacent.add(n2)
      n2.adjacent.add(n1)
    })

    (0 to n - 1).foreach(i => {
      val node = nodes(i)
      val colCount: Int = getPath(node, node, node, scala.collection.mutable.HashSet[Int]())
      println(colCount)
    })
  }
}