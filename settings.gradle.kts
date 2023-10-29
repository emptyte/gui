pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "gui"

sequenceOf("api", "adapt").forEach {
  include("${rootProject.name}-$it")
  project(":${rootProject.name}-$it").projectDir = file(it)
}
include("plugin")
