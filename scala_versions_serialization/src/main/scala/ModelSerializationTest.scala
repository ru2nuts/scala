import org.apache.spark.{SparkConf, SparkContext}
import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import com.twitter.chill.MeatLocker
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.classification.{LogisticRegressionModel, NaiveBayes, NaiveBayesModel, SVMWithSGD}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.feature.{Normalizer, StandardScaler}
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.{Matrices, Matrix, Vector, Vectors}
import org.apache.spark.mllib.optimization.{LBFGS, LogisticGradient, SquaredL2Updater}
import org.apache.spark.mllib.random.RandomRDDs._
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.mllib.stat.test.ChiSqTestResult
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD

object Hi {
  def main(args: Array[String]) = {
    println("Hi!")

    val conf = new SparkConf(true)
      .setAppName("spark_model_serialization")
      .setMaster("local[4]")

    val sc = new SparkContext(conf)

    val data = sc.textFile("../../../apache/spark/data/mllib/sample_naive_bayes_data.txt")
    val parsedData = data.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }
    // Split data into training (60%) and test (40%).
    val splits = parsedData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val training = splits(0)
    val test = splits(1)

    val model = NaiveBayes.train(training, lambda = 1.0)

    val modelFilePath = "./naive_bayes_model"

    val oos = new ObjectOutputStream(new FileOutputStream(modelFilePath))
    val boxedItem = MeatLocker(model)
    oos.writeObject(boxedItem)
    oos.close

    //serialize model
    val dmo = new ObjectInputStream(new FileInputStream(modelFilePath))
    //deserialize model
    val dm = dmo.readObject().asInstanceOf[MeatLocker[NaiveBayesModel]].get

    val predictionAndLabel = test.map(p => (dm.predict(p.features), p.label))
    val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()

    predictionAndLabel.collect.foreach(pl => println(s"Prediction And Label: $pl"))

		println(s"Accuracy: $accuracy")
		
		sc.stop

		println("Done")
  }
}