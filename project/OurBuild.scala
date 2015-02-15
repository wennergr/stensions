import sbt._
import sbt.{Project, Build}
import sbt.Keys._

object OurBuild extends Build {

  override lazy val settings = super.settings ++ Seq(
    scalaVersion  := "2.10.4",
    organization  := "org.wennergr",
    version       := "1-SNAPSHOT"
  )

  lazy val stensionLibrary: Project = Project(
    id = "stensions",
    base = file("stensions"),
    settings = Project.defaultSettings
  )
    .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
}
