plugins {
  id("gui.publishing-conventions")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

repositories {
  maven("https://repo.extendedclip.com/releases/")
}

dependencies {
  compileOnly(libs.paper)
  compileOnly("me.clip:placeholderapi:2.11.6")
}
