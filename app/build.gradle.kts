plugins {
    application
    id("io.freefair.lombok") version "8.1.0"
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Работа с picocli
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.5")

    // Работа с Jackson ObjectMapper
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
application {
    mainClass = "hexlet.code.App"
}