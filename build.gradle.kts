plugins {
    id("fabric-loom")
    `maven-publish`
    java
}

group = property("group")!!
version = property("mod_version")!!

repositories {

}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(getProperties())
            expand(mutableMapOf("version" to project.version))
        }
    }
    jar {
        from("LICENSE")
    }

    remapJar {
        val minecraft_version: String by project
        val max_minecraft_version: String by project
        archiveBaseName.set("Splashed!")
        archiveClassifier.set("")
        archiveVersion.set(String.format(
            "%s_%s-%s",
            version,
            minecraft_version,
            max_minecraft_version
        ))
    }
}
