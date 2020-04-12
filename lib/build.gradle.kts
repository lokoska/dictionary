plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js {
        useCommonJs()
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
    jvm {

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$COROUTINES_VERSION")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$COROUTINES_VERSION")
                implementation(npm("core-js", "2.6.5"))
                implementation("org.jetbrains.kotlinx:kotlinx-html:0.6.12")
                implementation("org.jetbrains:kotlin-react:16.13.0-$KOTLIN_WRAPPER_VERSION")
                implementation("org.jetbrains:kotlin-react-dom:16.13.0-$KOTLIN_WRAPPER_VERSION")
                implementation("org.jetbrains:kotlin-styled:1.0.0-$KOTLIN_WRAPPER_VERSION")
                implementation("org.jetbrains:kotlin-extensions:1.0.1-$KOTLIN_WRAPPER_VERSION")
                implementation("org.jetbrains:kotlin-css-js:1.0.0-$KOTLIN_WRAPPER_VERSION")
                implementation(npm("react", "16.13.0"))
                implementation(npm("react-dom", "16.13.0"))
                implementation(npm("react-is", "16.13.0"))
                implementation(npm("inline-style-prefixer", "5.1.0"))
                implementation(npm("styled-components", "4.3.2"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
    }
}
