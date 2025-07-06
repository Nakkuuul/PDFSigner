plugins {
    application
    kotlin("jvm") version "1.9.20"
}

group = "com.yourcompany"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.sapphirebroking.pdfsigner.AppKt")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Server Core & Engine
    implementation("io.ktor:ktor-server-core-jvm:2.3.4")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.4")

    // Content negotiation with Jackson (JSON support)
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-jackson:2.3.4")

    // File upload support (needed for multipart handling)
    implementation("io.ktor:ktor-server-html-builder:2.3.4")
    implementation("io.ktor:ktor-server-cio:2.3.4")

    // Ktor Utilities
    implementation("io.ktor:ktor-server-call-logging:2.3.4")
    implementation("io.ktor:ktor-server-default-headers:2.3.4")
    implementation("io.ktor:ktor-server-compression:2.3.4")
    implementation("io.ktor:ktor-server-status-pages:2.3.4")
    implementation("io.ktor:ktor-server-cors:2.3.4")
    implementation("io.ktor:ktor-server-auth:2.3.4")
    implementation("io.ktor:ktor-server-host-common:2.3.4")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // PDF Signing: iText 7
    implementation("com.itextpdf:itext7-core:7.2.5")

    // BouncyCastle for PEM + crypto handling
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")

    // Unit Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
