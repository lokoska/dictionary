plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlinx-serialization")
}

kotlin {
    js {
        browser {
            testTask {
                testLogging {
                    showExceptions = true
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
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
                implementation("org.jetbrains:kotlin-css:1.0.0-$KOTLIN_WRAPPER_VERSION")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$SERIALIZATION_VERSION")
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$SERIALIZATION_VERSION")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$COROUTINES_VERSION")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks.getByName<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>("compileKotlinJs") {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xallow-result-return-type")
//        freeCompilerArgs += listOf("-Xir-produce-js", "-Xgenerate-dts")
    }
}
