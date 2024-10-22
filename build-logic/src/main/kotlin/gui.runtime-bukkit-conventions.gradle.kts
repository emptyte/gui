plugins {
  id("gui.publishing-conventions")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
  compileOnly(libs.paper)
}
