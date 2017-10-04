package org.ru2nuts.learn.hackerrank

import scala.collection.Seq

/**
  * https://www.hackerrank.com/challenges/unique-colors
  */
object UniqueColors {

  class Node(val index: Int, val color: Int, var unexplored: Boolean) {
    override def toString: String = "" + index + ":" + color + ":" + unexplored
  }

  class Edge(val from: Node, val to: Node) {
    override def toString: String = "" + from + "--" + to
  }

  val cache = scala.collection.mutable.Map[(Int, Int), Option[List[Node]]]()

  def getPath(start: Node, end: Node, edges: Seq[Edge]): Option[List[Node]] = {
    start.unexplored = false
    if (start == end)
      return Some(start :: Nil)

    var pathIndex = (start.index, end.index)
    val cachedVal = cache.get(pathIndex)
    if (cachedVal.nonEmpty)
      return cachedVal.get
    else {
      val frontier = edges.
        filter(_.to.unexplored).
        filter(_.from == start).
        map(_.to).
        union(edges.
          filter(_.from.unexplored).
          filter(_.to == start).
          map(_.from))
      if (frontier.nonEmpty) {
        frontier.foreach(e => {
          val p = getPath(e, end, edges)
          if (p.nonEmpty) {
            val result = Some(start :: p.get)
            cache(pathIndex) = result
            return result
          }
        })
      }
      //cache(pathIndex) = None
      return None
    }
  }


  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt

    val nodes = (1 to n).map(index => new Node(index, sc.nextInt, true)).toList

    val edges = (1 to n - 1).map(_ => (new Edge(nodes(sc.nextInt - 1), nodes(sc.nextInt - 1))))

    nodes.foreach(from => {
      val colCount = nodes.map(to => {

        nodes.foreach(n => n.unexplored = true)
        val p = getPath(from, to, edges)
        if (p.nonEmpty)
          p.get.map(_.color).distinct.size
        else
          0
      }).sum
      println(colCount)
    })
  }
}