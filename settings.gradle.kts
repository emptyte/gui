rootProject.name = "gui"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

// item modules
includePrefixed("item:api")

// menu modules
includePrefixed("menu:api")

// menu adapters
arrayOf("1_17_R1", "1_20_R1").forEach {
    includePrefixed("menu:adapt:v$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}