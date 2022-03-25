ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaHbaseClient"
  )

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-common" % "3.2.2",
  "org.apache.hadoop" % "hadoop-client" % "3.2.2",
  "org.apache.hbase" % "hbase-common" % "2.4.11",
  "org.apache.hbase" % "hbase-client" % "2.4.11"
)
