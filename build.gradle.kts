plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("it.unibo.alice.tuprolog:2p-core:4.1.1")
    implementation("it.unibo.alice.tuprolog:2p-ui:4.1.1")
}

tasks.test {
    useJUnitPlatform()
}