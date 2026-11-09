import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.carremote.client"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.carremote.client"
        minSdk = 26
        targetSdk = 35
        versionCode = 18
        versionName = "2.0.0"
    }

    // 自有签名库：与车机端共用一份，debug / release 都用它
    val keystoreProps = Properties().apply {
        val f = rootProject.file("keystore.properties")
        if (f.exists()) f.inputStream().use(::load)
    }
    signingConfigs {
        if (keystoreProps.isNotEmpty()) {
            create("release") {
                storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.findByName("release") ?: signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.findByName("release") ?: signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    // 2.0.0 起改成纯 WebView 壳：界面全部来自车机端 assets/remote.html，
    // 这里不再需要 Compose / Material3 / OkHttp，APK 体积从 ~16 MB 降到 ~100 KB 量级。
}

dependencies {
    // 下拉刷新由 remote.html 自己实现（页面顶部手势 + 右上角按钮），壳层不再加原生刷新控件
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
