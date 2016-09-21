package org.ru2nuts.learn.hackerrank

import scala.collection.{Seq, mutable}

object BFSShortestSearch {

  //https://www.hackerrank.com/challenges/bfsshortreach
  def bfsDistances(startN: Int, nodeCount: Int, edges: Array[(Int, Int)]): Array[Int] = {

    val distances: Array[Int] = new Array[Int](nodeCount)
    var visited: mutable.Set[Int] = new mutable.HashSet[Int]
    val q = new mutable.Queue[Int]()
    q.enqueue(startN)
    while (q.nonEmpty) {
      val node = q.dequeue()
      if (!visited.contains(node)) {
        visited.add(node)
        //TODO: inefficient - repeated looks in "edges" and "visited"
        // adgacent nodes should be avaialble from the current node, e.g. "neighbors" collection
        val nextNodes = edges.filter(_._1 == node).map(_._2).
          union(edges.filter(_._2 == node).map(_._1)).
          distinct.filterNot(visited.contains(_))

        if (nextNodes.nonEmpty)
          nextNodes.foreach(n => {
            if (distances(n - 1) == 0)
              distances(n - 1) = distances(node - 1) + 1
            else
              distances(n - 1) = Math.min(distances(n - 1), distances(node - 1) + 1)
            q.enqueue(n)
          })
      }
    }

    distances.map(d => if (d == 0) -1 else d * 6)
  }


  def main(args: Array[String]) = {
    //    The first line contains an integer, , denoting the number of queries. The subsequent lines describe each query in the following format:
    //
    //      The first line contains two space-separated integers describing the respective values of (the number of nodes) and (the number of edges) in the graph.
    //    Each line of the subsequent lines contains two space-separated integers, and , describing an edge connecting node to node .
    //      The last line contains a single integer, , denoting the index of the starting node.
    val sc = new java.util.Scanner(System.in)

    val qn = sc.nextInt()

    case class Q(n: Int, edges: Array[(Int, Int)], s: Int)

    val qs = new Array[Q](qn)
    for (i <- 0 to qn - 1) {
      val n = sc.nextInt()
      val m = sc.nextInt()
      val edges = new Array[(Int, Int)](m)
      for (j <- 0 to m - 1) {
        edges(j) = (sc.nextInt(), sc.nextInt())
      }
      val s = sc.nextInt()
      qs(i) = Q(n, edges, s)
    }

    qs.foreach(q => {
      val res = bfsDistances(q.s, q.n, q.edges)
      println(res.take(q.s - 1).union(res.takeRight(res.length - q.s)).mkString(" "))
    })
  }
}
