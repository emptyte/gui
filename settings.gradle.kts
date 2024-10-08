pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "gui"

sequenceOf(
  "common",
  "core",
  "test-plugin"
).forEach {
  include("${rootProject.name}-$it")
  project(":${rootProject.name}-$it").projectDir = file(it)
}
