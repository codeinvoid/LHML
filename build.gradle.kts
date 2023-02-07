import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven {
        name = "Nova Committee - Release"
        url = uri("https://maven.nova-committee.cn/releases/")
    }
    maven {
        name = "Nova Committee - Snapshot"
        url = uri("https://maven.nova-committee.cn/snapshots/")
    }
    maven { url =  uri("https://jitpack.io") }
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(compose.animationGraphics)
    implementation("dev.3-3:jmccc:3.1.1")
    implementation("dev.3-3:jmccc-mcdownloader:3.1.1")
    implementation("dev.3-3:jmccc-mojang-api:3.1.1")
    implementation("dev.3-3:jmccc-microsoft-authenticator:3.1.1")
    implementation("dev.3-3:jmccc-yggdrasil-authenticator:3.1.1")
    implementation("io.github.prismwork:prismconfig:0.2.0:all")
    implementation("com.arkivanov.decompose:decompose:1.0.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.Dansoftowner:jSystemThemeDetector:3.8")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinJvmComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}
