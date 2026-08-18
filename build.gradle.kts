plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
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
    // Phase 3: Spring + JPA, added now that we're building the real persistence adapter.
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Phase 6: the driving (HTTP) adapter.
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Kept from the domain/use-case phases.
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.mockito:mockito-junit-jupiter:5.14.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Phase 6: needed for @WebMvcTest + MockMvc, to test the HTTP layer in isolation
    // (mocked use case, no real database) — a different slice than the integrationTest
    // suite, which hits a real Postgres.
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

// Phase 4: integrationTest exercises real infrastructure adapters (PostgreSQL via
// Testcontainers). It requires a running Docker daemon and is intentionally NOT wired
// into `check` — run it explicitly with `./gradlew integrationTest`.
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register<JvmTestSuite>("integrationTest") {
            useJUnitJupiter()
            dependencies {
                implementation(project())
                // main's `implementation`-scoped deps aren't visible here just from
                // depending on project() — Gradle only exposes `api`-scoped ones across
                // source sets, so JPA needs to be declared again explicitly.
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("org.springframework.boot:spring-boot-testcontainers")
                // Pinned explicitly (not left to Spring Boot's managed version) — Docker
                // Engine 29 raised its minimum API version, and Testcontainers versions
                // before 2.x ship an internal client too old to speak it. 2.x also
                // renamed these artifacts with a "testcontainers-" prefix.
                //
                // The core module is pinned too: spring-boot-testcontainers otherwise
                // drags in testcontainers:1.19.8 via Spring's managed BOM, and Gradle's
                // conflict resolution silently downgrades everything back to it.
                implementation("org.testcontainers:testcontainers:2.0.5")
                implementation("org.testcontainers:testcontainers-junit-jupiter:2.0.5")
                implementation("org.testcontainers:testcontainers-postgresql:2.0.5")
                runtimeOnly("org.postgresql:postgresql")
            }
            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}
