plugins {
  id("gui.publishing-conventions")
  id("io.papermc.paperweight.userdev") version "1.7.3"
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}

dependencies {
  paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

  compileOnly(project(":${rootProject.name}-runtime-bukkit-api"))
}
