package org.ru2nuts.learn.hackerrank

import scala.collection._

/**
  * https://www.hackerrank.com/challenges/counting-on-a-tree
  */
object CountingOnTree {

  class Node(val index: Int, val value: Long, var unexplored: Boolean)

  class Edge(val from: Node, val to: Node)

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
      cache(pathIndex) = None
      return None
    }
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt
    val q = sc.nextInt

    val nodes = (1 to n).map(index => (index, new Node(index, sc.nextLong, true))).toMap

    val edges = (1 to n - 1).map(_ => (new Edge(nodes(sc.nextInt), nodes(sc.nextInt))))

    val queries = (1 to q).map(_ => (nodes(sc.nextInt), nodes(sc.nextInt), nodes(sc.nextInt), nodes(sc.nextInt)))

    queries.foreach(q => {
      val w = q._1
      val x = q._2
      val y = q._3
      val z = q._4

      //nodeValues
      nodes.foreach(n => n._2.unexplored = true)
      val pathWX: List[Node] = getPath(w, x, edges).getOrElse(Nil)
      nodes.foreach(n => n._2.unexplored = true)
      val pathYZ: List[Node] = getPath(y, z, edges).getOrElse(Nil)

      println(pathWX.filter(v1 => pathYZ.filter(v2 => v1.index != v2.index && v1.value == v2.value).size > 0).size)
    })
  }
}