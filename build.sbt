import Dependencies._

val commonSettings: Seq[Setting[_]] = Seq(
  scalaVersion := "2.11.8"
)

val perConf = "test->test;compile->compile"

lazy val core = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      commonsIO % Compile,
      slf4jAPI % Compile,
      logback % Compile,

      scalaTest % Test
    )
  )
  .enablePlugins(SbtTwirl)

lazy val cli = (project in file("cli"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      scopt % Compile
    )
  )
  .dependsOn(core % perConf)

lazy val example = (project in file("example"))
  .settings(commonSettings: _*)
  .dependsOn(cli)
