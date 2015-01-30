name := "mllib_serialization"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.2.0"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.9"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "org.jblas" % "jblas" % "1.2.3"

libraryDependencies += "com.github.fommil.netlib" % "all" % "1.1.2"

libraryDependencies += "com.twitter" %% "chill" % "0.5.2"
