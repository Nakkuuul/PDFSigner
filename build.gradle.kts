plugins {
    application
    kotlin("jvm") version "1.9.20"
}

group = "com.yourcompany"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("sapphirebroking.pdfsigner.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Server Core
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")

    // Content negotiation + JSON (Jackson)
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-jackson:2.3.4")

    // Logging
    implementation("io.ktor:ktor-server-call-logging:2.3.4")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // Crypto: BouncyCastle
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")

    // PDF Signing: iText 7
    implementation("com.itextpdf:itext7-core:7.2.5")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
