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
                implementation(kotlin("stdlib-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}
