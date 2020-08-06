plugins {
    kotlin("multiplatform") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
}

group = "org.openapitools"
version = "1.0.0"

val kotlin_version = "1.3.72"
val ktor_version = "1.3.2"

repositories {
    jcenter()
}

kotlin {
    /*
     * To find out how to configure the targets, please follow the link:
     * https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets
     */
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                // Setup the Kotlin compiler options for the 'main' compilation:
                jvmTarget = "1.8"
            }
        }
    }
    ios {
        binaries {
            framework {
                freeCompilerArgs = listOf("-Xobjc-generics")
            }
        }
    }
    js()

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/common/main")

            dependencies {
                implementation(kotlin("stdlib-common"))
                api("io.ktor:ktor-client-core:$ktor_version")
                api("io.ktor:ktor-client-json:$ktor_version")
                api("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }

        val commonTest by getting {
            kotlin.srcDir("src/common/test")

            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.ktor:ktor-client-mock:$ktor_version")
            }
        }

        val jvmMain by getting {
            kotlin.srcDir("src/jvm/main")

            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("io.ktor:ktor-client-core-jvm:$ktor_version")
                api("io.ktor:ktor-client-json-jvm:$ktor_version")
                api("io.ktor:ktor-client-serialization-jvm:$ktor_version")
            }
        }

        val jvmTest by getting {
            kotlin.srcDir("src/jvm/test")

            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.6.2")
                implementation("io.ktor:ktor-client-mock-jvm:$ktor_version")
            }
        }

        val iosMain by getting {
            kotlin.srcDir("src/ios/main")

            dependencies {
                api("io.ktor:ktor-client-ios:$ktor_version")
                api("io.ktor:ktor-client-json-native:$ktor_version")
                api("io.ktor:ktor-client-serialization-native:$ktor_version")
            }
        }

        val iosTest by getting {
            kotlin.srcDir("src/ios/test")

            dependencies {
                implementation("io.ktor:ktor-client-mock-native:$ktor_version")
            }
        }

        val jsMain by getting {
            kotlin.srcDir("src/js/main")

            dependencies {
                implementation(kotlin("stdlib-js"))
                api("io.ktor:ktor-client-js:$ktor_version")
                api("io.ktor:ktor-client-json-js:$ktor_version")
                api("io.ktor:ktor-client-serialization-js:$ktor_version")
            }
        }

        val jsTest by getting {
            kotlin.srcDir("src/js/test")

            dependencies {
                implementation("io.ktor:ktor-client-mock-js:$ktor_version")
            }
        }
    }
}
