plugins {
    kotlin("jvm") version "1.9.20"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Librería para manejar JSON 
    implementation("org.json:json:20231018")
}

application {
    mainClass.set("MainKt")
}