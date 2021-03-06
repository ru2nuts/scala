package org.ru2nuts.learn.hackerrank

import java.io.{File, FileOutputStream, PrintWriter}

import scala.collection.mutable

/**
  * https://www.hackerrank.com/challenges/counting-on-a-tree
  */
object CountingOnTree {

  class Node(val index: Int, val value: Long) {
    val adjacent: mutable.HashSet[Node] = mutable.HashSet[Node]()

    override def toString: String = "" + index + ":" + value + ": (" + adjacent.map(_.index).mkString(",") + ")"
  }

  val cachePaths = mutable.HashMap[(Int, Int), List[Node]]()

  val cacheCounts = mutable.HashMap[((Int, Int), (Int, Int)), Int]()

  def getPathBFS(from: Node, to: Node): Option[mutable.ListBuffer[Node]] = {
    val q = new mutable.Queue[(Node, mutable.ListBuffer[Node], Node)]()
    q.enqueue((from, mutable.ListBuffer[Node](from), from))
    while (q.nonEmpty) {
      val (node, currentPath, prevNode) = q.dequeue()
      val nextNodes = node.adjacent.filterNot(_ == prevNode)
      if (nextNodes.nonEmpty)
        nextNodes.foreach(n => {
          val nextPath = currentPath += n
          if (n == to) {
            return Some(nextPath)
          }
          else
            q.enqueue((n, nextPath, node))
        })
    }
    None
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val n = sc.nextInt
    val q = sc.nextInt

    val nodes = mutable.ArrayBuffer[Node]()
    (1 to n).foreach(index => {
      nodes += new Node(index, sc.nextLong)
    })

    (1 to n - 1).foreach(_ => {
      val n1 = nodes(sc.nextInt - 1)
      val n2 = nodes(sc.nextInt - 1)
      n1.adjacent.add(n2)
      n2.adjacent.add(n1)
    })

    val res = new StringBuilder
    val valToIndexMap = mutable.HashMap[Long, mutable.HashSet[Int]]()

    var t0 = System.nanoTime()

    (1 to q).map(_ => {
      val wi = sc.nextInt - 1
      val xi = sc.nextInt - 1
      val yi = sc.nextInt - 1
      val zi = sc.nextInt - 1

      val w = nodes(wi)
      val x = nodes(xi)
      val y = nodes(yi)
      val z = nodes(zi)

      var count = 0
      if (cacheCounts.contains((wi, xi), (yi, zi))) {
        count = cacheCounts.get((wi, xi), (yi, zi)).get

      } else if (cacheCounts.contains((xi, wi), (yi, zi))) {
        count = cacheCounts.get((xi, wi), (yi, zi)).get

      } else if (cacheCounts.contains((xi, wi), (zi, yi))) {
        count = cacheCounts.get((xi, wi), (zi, yi)).get

      } else if (cacheCounts.contains((wi, xi), (zi, yi))) {
        count = cacheCounts.get((wi, xi), (zi, yi)).get


      } else if (cacheCounts.contains((yi, zi), (wi, xi))) {
        count = cacheCounts.get((yi, zi), (wi, xi)).get

      } else if (cacheCounts.contains((yi, zi), (xi, wi))) {
        count = cacheCounts.get((yi, zi), (xi, wi)).get

      } else if (cacheCounts.contains((zi, yi), (xi, wi))) {
        count = cacheCounts.get((zi, yi), (xi, wi)).get

      } else if (cacheCounts.contains((zi, yi), (wi, xi))) {
        count = cacheCounts.get((zi, yi), (wi, xi)).get


      } else {
        //nodeValues
        val pathWX: mutable.ListBuffer[Node] = getPathBFS(w, w).getOrElse(mutable.ListBuffer.empty)
        val pathYZ: mutable.ListBuffer[Node] = getPathBFS(y, y).getOrElse(mutable.ListBuffer.empty)

        valToIndexMap.clear()

        pathWX.foreach(e => {
          val s = valToIndexMap.getOrElseUpdate(e.value, new mutable.HashSet[Int]())
          s.add(e.index)
        })
        pathYZ.foreach(e => {
          val optVal = valToIndexMap.get(e.value)
          if (optVal.nonEmpty) {
            val v = optVal.get
            if (v.size == 1) {
              if (v.head != e.index)
                count += 1
            }
            else // if (v.size > 1)
              count += v.count(ve => ve != e.index)
          }
        })
        cacheCounts.put(((wi, xi), (yi, zi)), count)
      }
      println(count)
      res.append(count).append('\n')
    })
    println(res.toString())

    val t1 = System.nanoTime()
    val write = new PrintWriter(new FileOutputStream(new File("~/Downloads/tree_count/timings.txt"), true))
    write.println("Elapsed time: " + (t1 - t0) + "ns")
    write.close()
  }
}