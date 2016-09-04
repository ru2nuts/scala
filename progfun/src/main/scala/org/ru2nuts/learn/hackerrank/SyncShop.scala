package org.ru2nuts.learn.hackerrank

/**
  * Created by ru2nuts on 9/3/16.
  */
object SyncShop {

  def main(args: Array[String]) {
    var n = 5
    var m = 5
    var k = 5

    // shopping center type: number of fish types, array of fish types
    var shoppingCenters = new Array[(Int, Array[Int])](n)
    for (i <- 0 to n - 1) {
      val tn = 1
      val fishTypes = new Array[Int](tn)
      for (j <- 0 to tn - 1) {
        fishTypes(j) = i + 1
      }
      shoppingCenters(i) = (tn, fishTypes)
    }

    // road type: node 1, node 2, edge weight
    val road: Array[(Int, Int, Int)] = Array(
      (1, 2, 10),
      (1, 3, 10),
      (2, 4, 10),
      (3, 5, 10),
      (4, 5, 10)
    )

    val res = proc(n, m, k, shoppingCenters, road)

  }


  def proc(n: Int, m: Int, k: Int, shoppingCenters: Array[(Int, Array[Int])], road: Array[(Int, Int, Int)]) = {

    // get a set (S) of edges with distinct fish types (so that we collect all types of fish)
    // add to that set edges 1 and N
    // all combinations of 2 sub-paths form 1 to N, covering all edges in S (min sub-path is 1->N)
    // traverse all combinations, choose min total weight one

    // all combinations of 2 sub-paths from 1->N (which collect all fish types)
    // choose min total weight combination
    // e.g.: edges 1,2,3,N
    // 1->N, 1->2->N, 1->3-N, 1->3-N, 1->3->2->N

    // all paths from 1 -> N
    // all combinations of two paths (different or same)
    // choose only combinations, that collect all fish types
    // find combo with min total weight

    //type Edge =

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

      val result = advance(initP, allEdges, endN)

      result

      // until no patsh found
      //   find path from start to end, pN
      //   save pN to resulting paths list
      //   remove

      //      def recur(frontier: List[Int]) = {
      //        if (frontier)
      //      }
    }

    val startN: Int = 1
    val endN: Int = n
    val fishTypes: Seq[Int] = shoppingCenters.flatMap(_._2.toSet).distinct.toSeq.sorted
    val allEdges: Seq[Edge] = road.map(e => Edge(e._1, e._2, e._3)).toSeq

    val allPaths: Seq[Path] = FindAllPaths(startN, endN, allEdges)

    val connectingPaths: Seq[Path] = allPaths.filter(_.connects(startN, endN))

    val pairs: Seq[(Path, Path)] = connectingPaths.flatMap(e1 => connectingPaths.map(e2 => (e1, e2)))

    // filter the ones that collect all fish types
    val res: Int = pairs
      .filter(p => p._1.nodes.union(p._2.nodes).distinct.sorted == fishTypes)
      .map(p => Math.max(p._1.weight, p._2.weight)).min

    res
  }
}
