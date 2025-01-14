import org.gradle.configurationcache.extensions.capitalized
import java.nio.file.Files
import java.util.*

plugins {
  id("gui.runtime-bukkit-conventions")
  id("com.github.johnrengelman.shadow") version "8.1.1"
  id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

dependencies {
  implementation(project(":${rootProject.name}-runtime-bukkit-api"))

  sequenceOf(
    "1_20_2",
    "1_20_4",
    "1_20_6",
    "1_21"
  ).forEach {
    runtimeOnly(project(":${rootProject.name}-runtime-bukkit-adapt-v$it", configuration = "reobf"))
  }
}

bukkit {
  val projectName = rootProject.name.split("-").joinToString("") { it.capitalized() }
  val pluginName = "Emptyte$projectName"
  name = pluginName
  version = project.version.toString()  // This is the version of the plugin
  description = "Emptyte $projectName plugin."
  author = "SrVenient"

  main = "team.emptyte.${projectName.lowercase(Locale.ROOT)}.${pluginName}Plugin"
  apiVersion = "1.20"

  commands {
    register("gui") {
      description = "Main command."
    }
  }
}

tasks {
  shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
  }
}
