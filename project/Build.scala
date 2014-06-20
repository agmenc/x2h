import sbt._
import Keys._

object Build extends Build {

  def sharedSettings = Seq(
    name:="x2h",
    scalaVersion:= "2.11.1",
    crossScalaVersions := Seq("2.10.4", "2.11.1"),
    scalacOptions += "-deprecation",
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.11" % "test->default",
      "org.scalatest" %% "scalatest" % "2.1.3" % "test"
    ),
    // add scala-xml dependency when needed (for Scala 2.11 and newer)
    // this mechanism supports cross-version publishing
    libraryDependencies := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, scalaMajor)) if scalaMajor >= 11 => libraryDependencies.value :+ "org.scala-lang.modules" %% "scala-xml" % "1.0.1"
        case _ => libraryDependencies.value
      }
    }
  )

  lazy val main = Project(id = "main", base = file(".")).settings(sharedSettings: _*)
}
