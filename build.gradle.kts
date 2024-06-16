plugins {
    application
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.microsoft.playwright:playwright:1.44.0")
}

application {
    mainClass.set("org.example.App")
    mainClass.set("com.microsoft.playwright.CLI")
}

// Usage: ./gradlew playwright --args="help"
tasks.register<JavaExec>("playwright") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<JavaExec>("playwrightRun") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.microsoft.playwright.CLI")
    args = project.findProperty("args")?.toString()?.split(' ') ?: listOf()
}
