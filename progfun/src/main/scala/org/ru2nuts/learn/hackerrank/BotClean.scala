package org.ru2nuts.learn.hackerrank

import scala.collection.mutable.{HashSet, ListBuffer, Queue}

/**
  * https://www.hackerrank.com/challenges/botclean?hr_b=1
  */
object BotClean {

  def main(args: Array[String]) = {
    val pos = Console.readLine
    val board = new Array[String](5)
    for (i <- 0 until 5) {
      board.update(i, Console.readLine)
    }
    nextMove(pos, board)
  }

  def nextMove(pos: String, board: Array[String]) = {
    val (sr, sc) = pos.splitAt(1)
    val (r, c) = (sr.toInt, sc.trim.toInt)

    if (board(r).charAt(c) == 'd')
      println("CLEAN")
    else {
      val pp = getPath(board, r, c)

      if (pp.nonEmpty)
        println(pp.get.head)

    }
  }

  def getPath(grid: Array[String], row: Int, col: Int): Option[List[String]] = {

    val allPaths = new ListBuffer[List[String]]()

    val visited = new HashSet[(Int, Int)]()

    val queue = new Queue[(Int, Int, ListBuffer[String])]()
    queue.enqueue((row, col, new ListBuffer[String]()))
    while (queue.nonEmpty) {
      val (r, c, currentPath) = queue.dequeue()
      if (grid(r).charAt(c) == 'd') {
        allPaths.append(currentPath.toList)
      }
      val adj = getAdjacent(grid, r, c, visited)

      adj.foreach(a => {
        val newCurrentPath = currentPath.clone()
        newCurrentPath.append(a._3)
        queue.enqueue((a._1, a._2, newCurrentPath))
      })
    }
    if (allPaths.nonEmpty)
      return Some(allPaths.minBy(_.length))
    else
      return None
  }

  def getAdjacent(grid: Array[String], row: Int, col: Int, visited: HashSet[(Int, Int)]): List[(Int, Int, String)] = {
    visited.add((row, col))
    val paths = List(
      if (row - 1 >= 0 && !visited.contains(row - 1, col))
        Some(row - 1, col, "UP")
      else
        None,
      if (col - 1 >= 0 && !visited.contains(row, col - 1))
        Some(row, col - 1, "LEFT")
      else
        None,
      if (row + 1 < grid.length && !visited.contains(row + 1, col))
        Some(row + 1, col, "DOWN")
      else
        None,
      if (col + 1 < grid.length && !visited.contains(row, col + 1))
        Some(row, col + 1, "RIGHT")
      else
        None)

    paths.filter(_.nonEmpty).map(_.get)
  }

}
