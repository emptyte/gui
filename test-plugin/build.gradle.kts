import org.gradle.configurationcache.extensions.capitalized
import java.util.*

plugins {
  id("project.common-conventions")
  id("com.github.johnrengelman.shadow") version "8.1.1"
  id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

dependencies {
  implementation(project(":${rootProject.name}-core"))
}

bukkit {
  val projectName = rootProject.name.split("-").joinToString("") { it.capitalized() }
  val pluginName = "Emptyte$projectName"
  name = pluginName
  version = project.version.toString()  // This is the version of the plugin
  description = "Emptyte $projectName plugin."
  author = "SrVenient"

  main = "team.emptyte.${projectName.lowercase(Locale.ROOT)}.plugin.${pluginName}Plugin"
  apiVersion = "1.20"

  commands {
    register("gui") {
      description = "Emptyte command."
    }
  }
}

tasks {
  shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
  }
}
