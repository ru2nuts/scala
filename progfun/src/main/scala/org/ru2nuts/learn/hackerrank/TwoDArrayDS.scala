

object TwoDArrayDS {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    val arr = Array.ofDim[Int](6, 6);
    for (arr_i <- 0 to 6 - 1) {
      for (arr_j <- 0 to 6 - 1) {
        arr(arr_i)(arr_j) = sc.nextInt();
      }
    }

    val hgSize = 3

    var max = Int.MinValue
    val maxStartOfHG = 6 - hgSize
    for (row <- 0 to maxStartOfHG) {
      for (col <- 0 to maxStartOfHG) {
        val curSum = sumOfHG(arr, row, col, 6, hgSize)
        if (curSum > max)
          max = curSum
      }
    }
    print(max)
  }


  def sumOfHG(matrix: Array[Array[Int]], row: Int, col: Int, matrixSize: Int = 6, hgSize: Int = 3): Int = {
    val maxRow = row + hgSize - 1
    val maxCol = col + hgSize - 1
    var colShift = -1
    var step = 0
    (for (i <- row to maxRow) yield {
      colShift = if (step <= hgSize / 2)
        colShift + 1
      else
        colShift - 1

      step = step + 1

      (for (j <- col + colShift to maxCol - colShift) yield matrix(i)(j)).sum
    }).sum
  }

}
