package util.configuration

import net.lag.configgy.Configgy

object Configuration{
  val is = getClass.getClassLoader.getResourceAsStream("app.conf")

  val configFile = io.Source.fromInputStream(is)

  Configgy.configureFromString(configFile.mkString)
  val config = Configgy.config

  def apply(confKey: String): String = config.getString(confKey).getOrElse(throw new IllegalArgumentException("There's no entry for '"+confKey+"' in config file"))
}


