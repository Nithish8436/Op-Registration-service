plugins {
    java
}

group = "com.kgh"
version = "0.0.1-SNAPSHOT"
description = "OP Registration domain service (hexagonal)"

java {
    toolchain {
        // The template pins a bleeding-edge JDK; 21 (LTS) is used here instead so the
        // toolchain is realistically installable. Bump later if the team standardizes higher.
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Deliberately just enough to unit-test the domain core in isolation: no Spring, no
    // JPA, no Kafka. Those arrive later, only when we build the adapters that need them.
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
