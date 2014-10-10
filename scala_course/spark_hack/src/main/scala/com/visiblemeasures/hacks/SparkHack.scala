package com.visiblemeasures.spark

import org.apache.spark
import spark.SparkContext
import org.apache.avro.mapred
import mapred.AvroKey
import org.apache.avro.generic.GenericRecord
import org.apache.hadoop.io.NullWritable
import org.apache.avro.mapreduce.AvroKeyInputFormat
import java.lang.Long
import java.util.{Date, Calendar, HashMap}

/**
 * K-means clustering in Spark/MLLib
 */
object SparkHacks {

  def main(args: Array[String]) = {
    if (args.length != 3) {
      println("Usage: master SparkHacks hdfsInputPath hdfsOutputPath")
      println("       E.g.: " +
        "Cluster URL to connect to (e.g. mesos://host:port, spark://host:port, local[4]);" +
        "Input path /DEV/data/reptar/hourly;" +
        "Output path: /DEV/output/vtv/vtv_sae_uniques_by_week/")
      System.exit(1)
    }

    // Attach command line args to references
    val Array(master, hdfsInputPath, hdfsOutputPath) = args.take(3).toArray

    // Create SparkContext for distributed processing (RDDs, map/reduce, etc.)
    val sc = new SparkContext(master = master, appName = "SparkVTV_SAE_Uniques_by_week",
      sparkHome = System.getenv("SPARK_HOME"), jars = null) //List("target/scala-2.10/simple-project_2.10-1.0.jar")

    val data = readAvro(sc, hdfsInputPath).cache()
      .filter(filterByPlacementAndCust00)
      .map(buildResultTuple)
      .distinct()
      .groupBy(tuple => tuple._1)
      .map(t => (t._1, t._2.map(tt => (tt._2))))

    //sc.textFile(hdfsInputPath).map(_.split(",")).map(x => (x.head, x.tail.map(_.toDouble))).cache()
    println(data.count())
    //data.foreach(tuple => println(tuple._1, tuple._2))
    data.saveAsTextFile(hdfsOutputPath)
  }


  def filterByPlacementAndCust00(tuple: (AvroKey[GenericRecord], NullWritable)): Boolean = {
    val placementId = tuple._1.datum().get("placement_id")
    val custom = tuple._1.datum().get("custom")
    if (custom != null) {
      println(custom)
    }
    placementId != null && custom != null &&
     (placementId == 15669 ||
      placementId == 15693 ||
      placementId == 20289 ||
      placementId == 20305 ||
      placementId == 20309 ||
      placementId == 20317 ||
      placementId == 20333 ||
      placementId == 20337 ||
      placementId == 20353 ||
      placementId == 23109 ||
      placementId == 24309 ||
      placementId == 24313)
  }


  def buildResultTuple(tuple: (AvroKey[GenericRecord], NullWritable)): (Int, String) = {
    val cal = Calendar.getInstance()
    cal.setTime(new Date(tuple._1.datum().get("timestamp").asInstanceOf[Long].toLong))
    val k = cal.get(Calendar.WEEK_OF_YEAR)
    val v = tuple._1.datum().get("custom").asInstanceOf[HashMap[String,String]].get("cust00")
    (k, v)
  }


  private def readAvro(sparkContext: SparkContext, path: String) = {
    sparkContext.newAPIHadoopFile[
      AvroKey[GenericRecord],
      NullWritable,
      AvroKeyInputFormat[GenericRecord]
      ](path)
  }
}