plugins {
    java
}

group = "my.robot"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(Libs.junit5_api)
    testImplementation(Libs.assertJ)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockito_inline)
    testImplementation(Libs.awaility)
    testRuntimeOnly(Libs.junit5_engine)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

configure<SourceSetContainer> {
    named("test") {
        java.srcDir("src/test-integration/java")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "robot.RobotApplication"
    }
}
