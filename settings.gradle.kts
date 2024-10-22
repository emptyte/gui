pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "gui"

sequenceOf(
  "api",
  "runtime-bukkit:api",
  "runtime-bukkit:adapt:v1_21_1"
).forEach {
  includePrefixed(it)
}

fun includePrefixed(name: String) {
  val kebabName = name.replace(":", "-")
  val path = name.replace(":", "/")
  val baseName = "${rootProject.name}-$kebabName"

  include(baseName)
  project(":$baseName").projectDir = file(path)
}
