import java.nio.file.Files

rootProject.name = "gui"

// menu modules
includePrefixed("api")

Files.walk(rootDir.toPath().resolve("adapt"), 1).forEach {
  val name = it.fileName.toString()
  if (Files.isDirectory(it) && name.startsWith("v")) {
    includePrefixed("adapt:$name")
  }
}

fun includePrefixed(name: String) {
  val kebabName = name.replace(':', '-')
  val path = name.replace(':', '/')
  val baseName = "${rootProject.name}-$kebabName"

  include(baseName)
  project(":$baseName").projectDir = file(path)
}