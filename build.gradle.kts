
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
    testRuntimeOnly(Libs.junit5_engine)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}

tasks.test {
    useJUnitPlatform()
}