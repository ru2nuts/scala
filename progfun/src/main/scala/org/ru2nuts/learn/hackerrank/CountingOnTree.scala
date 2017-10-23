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

  val cacheRes = mutable.HashMap[(Int, Int), List[Node]]()

  def getPath(currentNode: Node, prevNode: Node, end: Node): Option[List[Node]] = {
    if (currentNode == end)
      return Some(currentNode :: Nil)

    val cachedVal = cacheRes.get((currentNode.index, end.index))
    if (cachedVal.nonEmpty) {
      return Some(cachedVal.get)
    } else {
      val frontier = currentNode.adjacent.filter(_ != prevNode)
      if (frontier.nonEmpty) {
        frontier.foreach(e => {
          val p = getPath(e, currentNode, end)
          if (p.nonEmpty) {
            val result = currentNode :: p.get
            cacheRes.put((currentNode.index, end.index), result)
            return Some(result)
          }
        })
      }
      return None
    }
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
      val w = nodes(sc.nextInt - 1)
      val x = nodes(sc.nextInt - 1)
      val y = nodes(sc.nextInt - 1)
      val z = nodes(sc.nextInt - 1)

      //nodeValues
      val pathWX: List[Node] = getPath(w, w, x).getOrElse(Nil)
      val pathYZ: List[Node] = getPath(y, y, z).getOrElse(Nil)

      valToIndexMap.clear()
      var count = 0

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
      res.append(count).append('\n')
    })
    println(res.toString())
    val t1 = System.nanoTime()
    val write = new PrintWriter(new FileOutputStream(new File("~/Downloads/tree_count/timings.txt"), true))
    write.println("Elapsed time: " + (t1 - t0) + "ns")
    write.close()
  }
}