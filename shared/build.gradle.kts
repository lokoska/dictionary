plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlinx-serialization")
}

val kotlin_version = "pre.94-kotlin-1.3.70" // for kotlin-wrappers
val ktor_version = "1.3.2"
val kotlinx_serialization_version = "0.20.0"
val logback_version = "1.2.3"

kotlin {
    js {
        browser {
            testTask {
                testLogging {
                    showExceptions = true
//                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
        nodejs {
            testTask {
                testLogging {
                    showExceptions = true
//                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains:kotlin-css:1.0.0-$kotlin_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinx_serialization_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinx_serialization_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

//compileKotlin {
//    kotlinOptions.freeCompilerArgs = [
//            "-Xallow-result-return-typ"
//    ]
//}

if(false) tasks.getByName<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>("compileKotlinJs") {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xir-produce-js", "-Xgenerate-dts")
    }
}
