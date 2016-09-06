package org.ru2nuts.learn.hackerrank

/**
  * Created by ru2nuts on 9/3/16.
  */
object SyncShop {

  def main(args: Array[String]) {

    val (n, m, k) = (6, 10, 3)

    // shopping center type: number of fish types, array of fish types
    val shoppingCenters: Array[(Int, Array[Int])] = Array[(Int, Array[Int])](
      (2, Array[Int](1, 2)),
      (1, Array[Int](3)),
      (0, Array[Int]()),
      (2, Array[Int](1, 3)),
      (1, Array[Int](2)),
      (1, Array[Int](3)))

    // road type: node 1, node 2, edge weight
    val road: Array[(Int, Int, Int)] = Array(
      (1, 2, 572),
      (4, 2, 913),
      (2, 6, 220),
      (1, 3, 579),
      (2, 3, 808),
      (5, 3, 298),
      (6, 1, 927),
      (4, 5, 171),
      (1, 5, 671),
      (2, 5, 463))

    //input
    //    6 10 3
    //    2 1 2
    //    1 3
    //    0
    //    2 1 3
    //    1 2
    //    1 3
    //    1 2 572
    //    4 2 913
    //    2 6 220
    //    1 3 579
    //    2 3 808
    //    5 3 298
    //    6 1 927
    //    4 5 171
    //    1 5 671
    //    2 5 463

    //result
    //    792

    val res = proc(n, m, k, shoppingCenters, road)

  }


  def proc(n: Int, m: Int, k: Int, shoppingCenters: Array[(Int, Array[Int])], road: Array[(Int, Int, Int)]) = {

    // all paths from 1 -> N
    // all combinations of two paths (different or same)
    // choose only combinations, that collect all fish types
    // find combo with min total weight

    case class Edge(n1: Int, n2: Int, weight: Int)

    case class Path(edges: Seq[Edge], allEdges: Seq[Edge]) {

      def connects(n1: Int, n2: Int): Boolean =
        nodes.contains(n1) && nodes.contains(n2)

      def weight: Int =
        edges.map(_.weight).reduce(_ + _)

      def nodes: Seq[Int] = edges.map(_.n1).union(edges.map(_.n2)).distinct

      def append(newN: Int): Path = {
        val newEdge = allEdges.filter(e => e.n1 == nodes.last && e.n2 == newN)
        Path(edges ++ newEdge, allEdges)
      }
    }


    def FindAllPaths(startN: Int, endN: Int, allEdges: Seq[Edge]): Seq[Path] = {

      def advance(p: Path, allEdges: Seq[Edge], endN: Int): Seq[Path] = {
        val n = p.nodes.last
        if (n == endN)
          p :: Nil
        else {
          val nextL = allEdges.filter(_.n1 == n).map(_.n2)
          if (nextL.isEmpty)
            p :: Nil
          else {
            //consider only NEW nodes
            val res = nextL.filterNot(p.nodes.contains(_)).map(i => p.append(i))
            res.flatMap(p => advance(p, allEdges, endN))
          }
        }
      }

      val initP = Path(List(Edge(startN, startN, 0)), allEdges)

      advance(initP, allEdges, endN)
    }

    val startN: Int = 1
    val endN: Int = n
    val fishTypes: Seq[Int] = shoppingCenters.flatMap(_._2.toSet).distinct.toSeq.sorted
    val allEdges: Seq[Edge] = road.map(e => Edge(e._1, e._2, e._3)).union(road.map(e => Edge(e._2, e._1, e._3))).toSeq

    val allPaths: Seq[Path] = FindAllPaths(startN, endN, allEdges)

    val connectingPaths: Seq[Path] = allPaths.filter(_.connects(startN, endN))

    val pairs: Seq[(Path, Path)] = connectingPaths.flatMap(e1 => connectingPaths.map(e2 => (e1, e2)))

    // filter the ones that collect all fish types
    val res: Int = pairs
      .filter(p => p._1.nodes.union(p._2.nodes).flatMap(n => shoppingCenters(n-1)._2).distinct.sorted == fishTypes)
      .map(p => Math.max(p._1.weight, p._2.weight)).min

    res
  }
}
