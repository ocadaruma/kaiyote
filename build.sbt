import Dependencies._

val commonSettings: Seq[Setting[_]] = Seq(
  scalaVersion := "2.11.8"
)

lazy val core = (project in file("core"))
  .enablePlugins(SbtTwirl)

lazy val cli = (project in file("cli"))
  .settings(
    libraryDependencies ++= Seq(
      scopt % Compile
    )
  )
  .dependsOn(core)
