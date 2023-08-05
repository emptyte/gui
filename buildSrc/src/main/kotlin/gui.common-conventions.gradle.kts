plugins {
    `java-library`
}

repositories {
  mavenLocal()
  maven("https://repo.codemc.io/repository/nms/")
  maven("https://repo.revengenetwork.es/repository/libs/")
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.unnamed.team/repository/unnamed-public/")
  maven("https://oss.sonatype.org/content/repositories/snapshots")
  mavenCentral()
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    compileJava {
      options.compilerArgs.add("-parameters")
    }
}