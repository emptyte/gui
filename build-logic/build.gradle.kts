plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.build.indra)
  implementation(libs.build.spotless)
  //implementation("io.papermc.paperweight:paperweight-userdev:1.7.2")
  compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

repositories {
  maven(url = "https://repo.stellardrift.ca/repository/internal/") {
    name = "stellardriftReleases"
    mavenContent { releasesOnly() }
  }
  maven(url = "https://repo.stellardrift.ca/repository/snapshots/") {
    name = "stellardriftSnapshots"
    mavenContent { snapshotsOnly() }
  }
  gradlePluginPortal()
}

kotlin {
  target {
    compilations.configureEach {
      kotlinOptions {
        jvmTarget = "21"
      }
    }
  }
}
