import com.diffplug.gradle.spotless.FormatExtension
import java.util.*

plugins {
  id("net.kyori.indra")
  id("net.kyori.indra.checkstyle")
  id("com.diffplug.spotless")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(17)
    minimumToolchain(17)
    strictVersions(true)
  }
  checkstyle(libs.versions.checkstyle.get())
}

repositories {
  mavenLocal()
  maven("https://repo.fenixteam.org/repository/libs/")
  maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
  checkstyle(libs.stylecheck)
  compileOnly(libs.paper)
  compileOnly(libs.emptyte)
  compileOnly(libs.storage.gson)
  compileOnly(libs.configurate)
}

spotless {
  fun FormatExtension.applyCommon() {
    trimTrailingWhitespace()
    endWithNewline()
    indentWithSpaces(2)
  }
  java {
    importOrderFile(rootProject.file(".spotless/emptyte.importorder"))
    removeUnusedImports()
    applyCommon()
  }
  kotlinGradle {
    applyCommon()
  }
}

tasks {
  jar {
    manifest {
      attributes(
        "Specification-Version" to project.version,
        "Specification-Vendor" to "emptyte-team",
        "Implementation-Build-Date" to Date()
      )
    }
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name()
    dependsOn(spotlessApply)
    dependsOn(checkstyleMain)
    options.compilerArgs.add("-parameters")
  }
}
