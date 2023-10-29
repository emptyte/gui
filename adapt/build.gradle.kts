plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":${rootProject.name}-api"))
}
