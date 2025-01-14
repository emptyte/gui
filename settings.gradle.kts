pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "gui"

includePrefixed("api")

sequenceOf(
  "api",
  "test-plugin"
).forEach {
  includePrefixed("runtime-bukkit:$it")
}

sequenceOf(
  "1_20_2",
  "1_20_4",
  "1_20_6",
  "1_21"
).forEach {
  includePrefixed("runtime-bukkit:adapt:v$it")
}

fun includePrefixed(name: String) {
  val kebabName = name.replace(":", "-")
  val path = name.replace(":", "/")
  val baseName = "${rootProject.name}-$kebabName"

  include(baseName)
  project(":$baseName").projectDir = file(path)
}
