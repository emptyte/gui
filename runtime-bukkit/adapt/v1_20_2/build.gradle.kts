plugins {
  id("gui.runtime-bukkit-conventions")
  id("io.papermc.paperweight.userdev") version "1.7.3"
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}

dependencies {
  paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")

  implementation(project(":${rootProject.name}-runtime-bukkit-api"))
}
