plugins {
  id("gui.common-conventions")
  `maven-publish`
}

configure<PublishingExtension> {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])
    }
  }
}
