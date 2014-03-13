import AssemblyKeys._ 

Seq(assemblySettings: _*)

name := "SparkTest"

version := "1.0"

organization := "com.visiblemeasures"

// this will pull in any version of scala-library.jar from the repo for this build, regardless of OS-level Scala installation
scalaVersion := "2.10.3"

// set the Scala compile source directory to be <base>/src
scalaSource in Compile <<= baseDirectory(_ / "src/main/scala")

// set the Scala test source directory to be <base>/test
scalaSource in Test <<= baseDirectory(_ / "src/test/scala")

// Add any Maven-compatible repos - need the Typesafe repo to get Akka
// For multiple repositories, it could be a sequence: resolvers ++= Seq("name" at "url")
resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                  "Maven Central" at "http://repo1.maven.org/maven2/", 
//                  "VMC releases" at "http://bld200/nexus/content/repositories/vmc-releases",
//                  "VMC snapshots" at "http://bld200/nexus/content/repositories/vmc-snapshots",
//                  "VMC vendors" at "http://bld200/nexus/content/repositories/vmc-vendors",
                  "SBT Assembly" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases")

libraryDependencies ++= Seq(
  "org.apache.spark"    %%  "spark-core" %  "0.9.0-incubating"
//  "commons-daemon"    %  "commons-daemon" % "1.0.3",
//  "org.apache.spark"    %%  "spark-yarn" % "0.9.0-incubating"
)

mainClass in assembly := Some("com.visiblemeasures.hacks.SparkHack")