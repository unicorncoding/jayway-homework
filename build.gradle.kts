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
    testImplementation(Libs.junit5_params)
    testImplementation(Libs.assertJ)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockito_inline)
    testImplementation(Libs.awaility)
    testRuntimeOnly(Libs.junit5_engine)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "robot.RobotApplication"
    }
}
