import net.civmc.civgradle.CivGradleExtension

plugins {
    `java-library`
    id("net.civmc.civgradle") version "2.+"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

configure<CivGradleExtension> {
    pluginName = project.property("pluginName") as String
}

tasks {
    shadowJar {
        exclude("org.slf4j")
        exclude("org.checkerframework")
        // helper function to relocate a package into our package
        fun reloc(pkg: String) = relocate(pkg, "com.github.maxopoly.zeus.relocations.$pkg")

        reloc("org.json")
        reloc("com.rabbitmq")
        reloc("org.apache.logging.log4j")
        reloc("org.apache.commons")
        reloc("org.yaml")
        reloc("com.google.guava")
        reloc("com.zaxxer")
        reloc("org.postgresql")
    }
}

dependencies {
    implementation("org.json:json:20160810")
    implementation("com.rabbitmq:amqp-client:5.6.0") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
    }
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.yaml:snakeyaml:1.18")
    implementation("com.google.guava:guava:r05") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
    }
    implementation("com.zaxxer:HikariCP:2.4.6") {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
    }
    implementation("org.postgresql:postgresql:42.2.18") {
        exclude(group = "org.checkerframework", module = "checkerframework")
    }
}