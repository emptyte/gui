plugins {
  id("project.base-conventions")
  id("io.papermc.paperweight.userdev")
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}

dependencies {
  api(project(":${rootProject.name}-adapt"))
  paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}