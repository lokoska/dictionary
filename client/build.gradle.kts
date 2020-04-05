plugins {
    id("org.jetbrains.kotlin.js")
}

kotlin {
    target {
        useCommonJs()
        browser() {
            webpackTask {
                sourceMaps = false
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$SERIALIZATION_VERSION")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$COROUTINES_VERSION")
    implementation(project(":github"))
    implementation(project(":network"))
    implementation(project(":lib"))

    implementation(npm("core-js", "2.6.5"))
    implementation(npm("svg-inline-loader", "0.8.0"))
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
    implementation(npm("@jetbrains/logos", "1.1.6"))
    implementation(npm("@jetbrains/ring-ui", "3.0.4"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    testImplementation(npm("enzyme", "3.9.0"))
    testImplementation(npm("enzyme-adapter-react-16", "1.12.1"))
}
