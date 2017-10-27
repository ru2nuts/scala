package org.ru2nuts.spark.example

import scala.util.Random

class ReservoirSampling {

  def sample(size: Int, inputSizeLimit: Int, input: Iterator[Int]): Array[Int] = {
    def r = new Random

    def step(sampleArray: Array[Int], samplePos: Int, inputPos: Int, input: Iterator[Int]): Array[Int] = {
      if (!input.hasNext)
        sampleArray
      else if (inputPos >= inputSizeLimit)
        sampleArray
      else if (samplePos < size) {
        sampleArray(samplePos) = input.next()
        step(sampleArray, samplePos + 1, inputPos + 1, input)
      } else {
        val nr = r.nextInt(inputPos)
        if (nr < sampleArray.size) {
          sampleArray(nr) = input.next()
        }
        step(sampleArray, samplePos + 1, inputPos + 1, input)
      }
    }
    step(new Array[Int](size), 0, 0, input)
  }

}
