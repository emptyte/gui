import com.diffplug.gradle.spotless.FormatExtension

plugins {
  `java-library`
  id("net.kyori.indra")
  id("net.kyori.indra.checkstyle")
  id("com.diffplug.spotless")
}

indra {
  javaVersions {
    target(17)
    minimumToolchain(17)
  }

  checkstyle("10.8.0")
}

dependencies {
  checkstyle("ca.stellardrift:stylecheck:0.2.0")
  compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
  compileOnly("team.emptyte:emptyte-paper-api:0.0.3")
  compileOnly("es.revengenetwork:storage-gson-dist:3.2.0")
}

spotless {
  fun FormatExtension.applyCommon() {
    trimTrailingWhitespace()
    indentWithSpaces(2)
    endWithNewline()
  }
  java {
    importOrder("", "\\#")
    removeUnusedImports()
    applyCommon()
  }
  kotlinGradle {
    applyCommon()
  }
}

repositories {
  mavenLocal()
  maven("https://repo.revengenetwork.es/repository/libs/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.unnamed.team/repository/unnamed-public/")
  maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

}

tasks {
  compileJava {
    dependsOn("spotlessApply")
    dependsOn("checkstyleMain")
    options.compilerArgs.add("-parameters")
  }
}