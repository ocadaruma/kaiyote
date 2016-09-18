import Aux._
import Dependencies._

lazy val core = mkProject("core")
  .settings(
    libraryDependencies ++= forCompile(commonsIO, slf4jAPI, logback) ++ forTest(scalatest)
  )
  .enablePlugins(SbtTwirl)

lazy val cli = mkProject("cli")
  .settings(
    libraryDependencies ++= forCompile(scopt)
  )
  .dependsOn(core % perConf)

lazy val example = mkProject("example")
  .dependsOn(cli)

lazy val plugin = mkProject("plugin")
  .settings(sbtPlugin := true)
  .dependsOn(cli)
