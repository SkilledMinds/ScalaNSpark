name := "ScalaNSpark"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "com.twitter" % "hbc-twitter4j" % "2.2.0"
//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"
//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.2.0"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.8"


libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.1"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.6"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.3.32"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.0.0"
