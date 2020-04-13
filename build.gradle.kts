plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.4-M1" apply false
}

allprojects {
    version = "0.1.1"

    repositories {
        maven {  setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/ktor") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
        maven { setUrl("https://dl.bintray.com/kotlin/exposed") }
        mavenCentral()
    }

}
