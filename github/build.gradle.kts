plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js {
        browser {

        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":network"))
                implementation(project(":lib"))
                implementation(kotlin("stdlib-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$COROUTINES_VERSION")
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
