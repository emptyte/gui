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
  api(project(":${rootProject.name}-api"))
  paperweight.paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}