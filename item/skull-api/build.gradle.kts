plugins {
    id("gui.publishing-conventions")
}

dependencies {
    api(project(":gui-item-api"))
    compileOnly("org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT")
}