plugins {
    id("gui.publishing-conventions")
}

dependencies {
  api(libs.annotations)
  arrayOf("validation", "bukkit").forEach {
    api("team.unnamed:commons-$it:3.1.0")
  }

  compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
  implementation("es.revengenetwork:storage-gson-dist:3.2.0")
  implementation("net.kyori:adventure-text-minimessage:4.14.0")
}