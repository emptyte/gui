plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":${rootProject.name}-common"))
  api(libs.caffeine)
}
