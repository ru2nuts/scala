import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

seq(assemblySettings: _*)

name := "SparkTest"

version := "1.0"

crossScalaVersions := Seq("2.10.3")

organization := "com.visiblemeasures"

// this will pull in any version of scala-library.jar from the repo for this build, regardless of OS-level Scala installation
scalaVersion := "2.10.3"

// set the Scala compile source directory to be <base>/src
scalaSource in Compile <<= baseDirectory(_ / "src/main/scala")

// set the Scala test source directory to be <base>/test
scalaSource in Test <<= baseDirectory(_ / "src/test/scala")

// Add any Maven-compatible repos - need the Typesafe repo to get Akka
// For multiple repositories, it could be a sequence: resolvers ++= Seq("name" at "url")
resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Sonatype Repository" at "http://oss.sonatype.org/content/repositories/releases/",
  "Maven Central" at "http://repo1.maven.org/maven2/",
//                  "VMC releases" at "http://bld200/nexus/content/repositories/vmc-releases",
//                  "VMC snapshots" at "http://bld200/nexus/content/repositories/vmc-snapshots",
//                  "VMC vendors" at "http://bld200/nexus/content/repositories/vmc-vendors",
  "SBT Assembly" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases")

libraryDependencies ++= Seq(
  "org.apache.avro"    % "avro-mapred" % "1.7.3",
//  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
//  "org.apache.spark"    %%  "spark-core" %  "0.9.0-incubating"
  "commons-daemon"    %  "commons-daemon" % "1.0.5",
  "org.apache.spark"    %%  "spark-yarn" % "0.9.0-incubating" exclude("org.apache.commons", "commons-daemon")
)

net.virtualvoid.sbt.graph.Plugin.graphSettings

mainClass in assembly := Some("com.visiblemeasures.hacks.SparkHack")

//test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case PathList("javax", "servlet", "class", xs @ _*)         => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt"     => MergeStrategy.discard
    case x => MergeStrategy.first //old(x)
  }
}