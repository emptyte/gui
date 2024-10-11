plugins {
  id("gui.publishing-conventions")
}

dependencies {
  api(project(":${rootProject.name}-core"))
}
