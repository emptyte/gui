pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "gui"

sequenceOf(
  "api",
  "runtime-bukkit:api"
).forEach {
  includePrefixed(it)
}

sequenceOf(
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
