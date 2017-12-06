package org.ru2nuts.learn.hackerrank.ctci

/**
  * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
  */
object ConnectedCellInAGrid {

  def visitCell(rowCount: Int, colCount: Int, row: Int, col: Int, grid: Array[Array[Int]], visited: Array[Array[Boolean]]): Int = {
    if (row == -1 || col == -1 || row >= rowCount || col >= colCount) {
      0
    } else {
      if (visited(row)(col)) {
        0
      } else {
        visited(row)(col) = true
        if (grid(row)(col) == 0) {
          0
        } else {
          1 +
            visitCell(rowCount, colCount, row - 1, col - 1, grid, visited) +
            visitCell(rowCount, colCount, row - 1, col, grid, visited) +
            visitCell(rowCount, colCount, row - 1, col + 1, grid, visited) +
            visitCell(rowCount, colCount, row, col - 1, grid, visited) +
            visitCell(rowCount, colCount, row, col + 1, grid, visited) +
            visitCell(rowCount, colCount, row + 1, col - 1, grid, visited) +
            visitCell(rowCount, colCount, row + 1, col, grid, visited) +
            visitCell(rowCount, colCount, row + 1, col + 1, grid, visited)
        }
      }
    }
  }

  def getLargestRegionCellCount(rowCount: Int, colCount: Int, grid: Array[Array[Int]]): Int = {
    val visited = Array.ofDim[Boolean](rowCount, colCount) //filled with "false"?
    var maxRegionSize = 0
    for (row <- 0 to rowCount - 1) {
      for (col <- 0 to colCount - 1) {
        if (!visited(row)(col)) {
          val countRegionCells: Int = visitCell(rowCount, colCount, row, col, grid, visited)
          if (maxRegionSize < countRegionCells) {
            maxRegionSize = countRegionCells
          }
        }
      }
    }
    maxRegionSize
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var m = sc.nextInt();
    var grid = Array.ofDim[Int](n, m);
    for (grid_i <- 0 to n - 1) {
      for (grid_j <- 0 to m - 1) {
        grid(grid_i)(grid_j) = sc.nextInt();
      }
    }
    println(getLargestRegionCellCount(n, m, grid))
  }
}
