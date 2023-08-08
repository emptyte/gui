plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("net.kyori:indra-common:3.0.1")
  implementation("com.diffplug.spotless:spotless-plugin-gradle:6.18.0")
  implementation("io.papermc.paperweight:paperweight-userdev:1.5.5")
}

repositories {
  gradlePluginPortal()
}

tasks {
  compileKotlin {
    kotlinOptions {
      jvmTarget = "17"
    }
  }
}