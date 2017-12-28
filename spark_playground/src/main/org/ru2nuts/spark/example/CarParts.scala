package org.ru2nuts.spark.example

import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object CarParts {

  def main(args: Array[String]) = {

    def fixCarIDsDS(spark: SparkSession): DataFrame = {
      import spark.implicits._

      List(
        ("1", "1"),
        ("2", "2"),
        ("3", "3"),
        ("Car 1", "1"),
        ("Car 2", "2"),
        ("Car 3", "3"),
        ("CAR 1", "1"),
        ("CAR 2", "2"),
        ("CAR 3", "3"),
        ("ONE", "1"),
        ("TWO", "2"),
        ("THREE", "3")
      ).toDF("car_all", "car")
    }

    def getCurrExchangeRateDS(spark: SparkSession): DataFrame = {
      import spark.implicits._

      List(
        ("USD", 1.0),
        ("GBP", 0.75),
        ("EUR", 0.84)
      ).toDF("CURRENCY", "RATE") //.select($"CURRENCY", $"RATE".as[Double])
    }

    val spark: SparkSession = org.apache.spark.sql.SparkSession.builder
      .master("local")
      .appName("CarParts")
      .getOrCreate;

    import spark.sqlContext.implicits._

    try {
      val additionalInfoRaw: DataFrame = spark.read
        .format("csv")
        .option("header", "true")
        .option("mode", "DROPMALFORMED")
        .load("/tmp/car_parts/additional_info.csv")

      val partCostsRaw = spark.read
        .format("csv")
        .option("header", "true")
        .option("mode", "DROPMALFORMED")
        .load("/tmp/car_parts/part_costs.csv")

      val partNumberToCarMapRaw = spark.read
        .format("csv")
        .option("header", "true")
        .option("mode", "DROPMALFORMED")
        .load("/tmp/car_parts/part_numbers.csv")


      val fixCarIDsMap = fixCarIDsDS(spark)

      // fix non-consistent car IDs
      val partNumberToCarMap = partNumberToCarMapRaw.select("PART_NO", "car")
        .join(fixCarIDsMap, partNumberToCarMapRaw("car") === fixCarIDsMap("car_all"))
        .select(partNumberToCarMapRaw("PART_NO"), fixCarIDsMap("car"))

      val additionalInfo = additionalInfoRaw.rdd.map(r => {
        // fix missing GBP for UK
        val curr = r.getString(1) match {
          case "UK" => "GBP"
          case _ => r.getString(2)
        }
        (r.getString(0), curr)
      }).toDF("PURCH_ORDER", "CURRENCY")

      // add missing currency by purchase order from "additional_info"
      val partCostLocalCurr = partCostsRaw
        .select("PURCH_ORDER", "CURRENCY", "PART_NO", "COST")
        .join(additionalInfo, partCostsRaw("PURCH_ORDER") === additionalInfo("PURCH_ORDER"), "left_outer")
        .select(partCostsRaw("PURCH_ORDER").as[String], {
          val curr1: Column = partCostsRaw("CURRENCY")
          val curr2: Column = additionalInfo("CURRENCY")
          when(curr1 isNotNull, curr1).otherwise(curr2)
        }.alias("CURRENCY"),
          partCostsRaw("PART_NO"),
          partCostsRaw("COST")
        )

      val getCurrExchangeRate = getCurrExchangeRateDS(spark)

      // convert cost to USD, broadcast small dataset
      val partCost = partCostLocalCurr
        .join(broadcast(getCurrExchangeRate), partCostLocalCurr("CURRENCY") === getCurrExchangeRate("CURRENCY"))
        .select(
          partCostLocalCurr("PART_NO"),
          (partCostLocalCurr("COST").as[Double] * getCurrExchangeRate("RATE").as[Double]).alias("COST").as[Double]
        )
        .groupBy($"PART_NO")
        .max("COST") // choosing max price here, could also track history of price changes for different times
        .select($"PART_NO", $"max(COST)".alias("COST"))

      // aggregate cost of parts per car
      val partPerCar = partNumberToCarMap
        .join(partCost, partNumberToCarMap("PART_NO") === partCost("PART_NO"))
        .groupBy(partNumberToCarMap("CAR"))
        .avg("COST")
        .alias("AVG_COST")

      partPerCar.foreach { r: Row =>
        println(r.mkString(","))
      }
    } finally {
      spark.stop()
    }
  }
}
