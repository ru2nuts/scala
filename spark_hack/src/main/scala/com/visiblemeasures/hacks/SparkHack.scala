package com.visiblemeasures.spark

import org.apache.spark
import spark.SparkContext
import spark.rdd.RDD
import spark.mllib.clustering.KMeans

/**
 * K-means clustering in Spark/MLLib
 */
object SparkHacks {
  
  def main(args: Array[String]) = {
    
    if (args.length != 7) {
      
      println("Usage: SparkHacks master hdfsInputPath hdfsOutputPath k maxIters numRuns epsilon")
      System.exit(1)
    }
    
    // Attach command line args to references
    val Array(master, hdfsInputPath, hdfsOutputPath, k, maxIters, numRuns, epsilon) = args.take(7).toArray
    
    // Create SparkContext for distributed processing (RDDs, map/reduce, etc.)
    val sc = new SparkContext(master = master, appName = "SparkKMeans",
                              sparkHome = System.getenv("SPARK_HOME"), jars = null)
    
    // Convert CSV input file into RDD[String, Array[Double]], i.e. user hashes followed by numeric values
    // Cache the RDD for in-memory processing
    val data = sc.textFile(hdfsInputPath).map(_.split(",")).map(x => (x.head, x.tail.map(_.toDouble))).cache()
    
    // Get just Array[Double] out of the dataset, user hashes need to be dropped for training
    val featurizedData = data.map(_._2)
    
    // See convenience method kMeansModel() below in this code file
    val model = kMeansModel(k.toInt, maxIters.toInt, epsilon.toDouble, numRuns.toInt, featurizedData) 
    
    // Stdout goes to Altiscale logs
    println("Cost function value: " + model.computeCost(featurizedData))
    
    // Persist results in HDFS - can still obtain RDD reference for later use if desired
    val clusteredData = data.map(x => Array(x._1, model.predict(x._2)).mkString(","))
                                 clusteredData.saveAsTextFile(hdfsOutputPath)
      
  }
  
  
  /*
   * Using KMeansModel.train() is bad because it doesn't allow one to set the convergence 
   * criterion, etc.On the other hand, KMeans() uses a builder pattern instead of constructor args
   * (it's an actual builder pattern rather than mutating setters). Hence this convenience method.
   */
  def kMeansModel(k: Int, maxIters: Int, epsilon: Double, numRuns: Int, data: RDD[Array[Double]]) = 
    new KMeans().setK(k.toInt).setMaxIterations(maxIters.toInt).setEpsilon(epsilon)
                .setRuns(numRuns.toInt).setInitializationMode("k-means||").run(data)

}