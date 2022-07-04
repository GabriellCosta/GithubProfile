plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:4.2.2")
    implementation(kotlin("gradle-plugin", "1.5.21"))
    implementation(kotlin("android-extensions"))
}

gradlePlugin {
    plugins {
        create("android-module") {
            id = "android-module"
            implementationClass = "AndroidModulePlugin"
        }

        create("android-compose-module") {
            id = "android-compose-module"
            implementationClass = "ComposePlugin"
        }

        create("kotlin-module") {
            id = "kotlin-module"
            implementationClass = "KotlinModulePlugin"
        }

        create("android-config-module") {
            id = "android-config-module"
            implementationClass = "AndroidConfigPlugin"
        }
    }
}
