import sbt._, Keys._

object Aux {
  val commonSettings: Seq[Setting[_]] = Seq(
    scalaVersion := "2.11.8",
    version := "0.0.1"
  )

  val perConf = "test->test;compile->compile"

  def mkProject(name: String): Project = Project(name, file(name))
    .settings(commonSettings: _*)

  def forCompile(moduleIDs: ModuleID*): Seq[ModuleID] = moduleIDs.map(_ % Compile)

  def forTest(moduleIDs: ModuleID*): Seq[ModuleID] = moduleIDs.map(_ % Test)
}
