name := "scala=server"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  val akkaVersion       = "2.5.3"
  val akkaHttpVersion   = "10.0.9"
  val scalaTestVersion  = "3.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
    "org.scalatest"     %% "scalatest" % scalaTestVersion % "test"
  )
}
