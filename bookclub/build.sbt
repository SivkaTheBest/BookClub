name := "bookclub"

version := "1.0-SNAPSHOT"


libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.2",
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings
