import com.typesafe.startscript.StartScriptPlugin

name := "DRADIS"

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

version := "0.2"

libraryDependencies ++= Seq(
	"com.typesafe.akka" % "akka-actor" % "2.0.2",
    "com.typesafe.akka" % "akka-slf4j" % "2.0.2",
    "net.sf.opencsv" % "opencsv" % "2.1",
    "net.databinder" %% "dispatch-http" % "0.8.8",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127",
    "net.liftweb" % "lift-json_2.9.1" % "2.4",
    "ch.qos.logback" % "logback-classic" % "1.0.0",
    "org.scalaj" % "scalaj-collection_2.9.1" % "1.2",
    "joda-time" % "joda-time" % "2.1",
    "hirondelle.date4j" % "date4j" % "1.0" from "http://www.date4j.net/date4j.jar",
    "net.lag" % "configgy" % "2.0.0",
    "commons-io" % "commons-io" % "2.0.1",
    "com.fasterxml.uuid" % "java-uuid-generator" % "3.1.1",
    "cc.spray" % "spray-server" % "1.0-M2",
    "cc.spray" % "spray-client" % "1.0-M2",
    "cc.spray" % "spray-can" % "1.0-M2",
    "cc.spray" %% "spray-json" % "1.1.1",
    //"com.twitter" %% "util-eval" % "3.0.0",
    "org.mongodb" %% "casbah" % "2.4.0",
    "com.novus" %% "salat" % "1.9.1-SNAPSHOT",
    "org.squeryl" %% "squeryl" % "0.9.5-2",
    "mysql" % "mysql-connector-java" % "5.1.10",
    "c3p0" % "c3p0" % "0.9.1.2"
)

resolvers ++= Seq("Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
                "Typesafe Snapshot Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
                "Glassfish repo"  at "http://download.java.net/maven/glassfish/",
                "spray repo"      at "http://repo.spray.cc/",
                "Twitter Maven Repo" at "http://maven.twttr.com/",
                "Apache Repo" at "http://maven.apache.org",
                "OSS Sonatype" at "http://oss.sonatype.org/content/repositories/releases/",
                "OSS Sonatype Snaps" at "http://oss.sonatype.org/content/repositories/snapshots/")



// reduce the maximum number of errors shown by the Scala compiler
maxErrors := 20

// increase the time between polling for file changes when using continuous execution
pollInterval := 1000

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

scalacOptions += "-deprecation"

scalaVersion := "2.9.2"

// set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

// set the prompt (for the current project) to include the username
shellPrompt := { state => System.getProperty("user.name") + "> " }

// only show 20 lines of stack traces
traceLevel := 20

logLevel := Level.Info

mainClass in Compile := Some("kaptest.radar.util.Boot")

artifactName := { (config: String, module: ModuleID, artifact: Artifact) =>
  artifact.name + "." + artifact.extension
}