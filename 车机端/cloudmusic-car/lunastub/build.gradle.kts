import java.util.Properties

plugins {
    id("com.android.application")
}

/**
 * 「汽水音乐车机版」—— 官方网易云车机版的桥接层 + 遥控服务。
 *
 * 车机助手的音源表写死在 DesktopAssistantMusicUiHelper 里，按包名判断「是否已安装」：
 *   com.luna.music.car → source 5（汽水音乐车机版）
 * 没装就不进候选列表，也就选不中，主应用发的广播没人收。
 *
 * 本包职责：
 *   - 通过 MediaBrowser 连接官方车机版（com.netease.cloudmusic.iot），转发播放/暂停/切歌/歌词
 *   - 在 18765 端口提供遥控网页与 WebSocket（assets/remote.html）
 *   - 端口即角色：18765 被占说明第三方自研播放器（com.cloudmusic.car）在服务，本包让位不抢
 *
 * 之所以不直接改主应用包名：改了等于换一个 App，登录态、缓存、播放记录、遥控配对全部作废。
 */
android {
    namespace = "com.luna.music.car"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.luna.music.car"
        minSdk = 28
        targetSdk = 35
        // 与主应用区分版本线，避免车机助手把两个包的版本号混在一起看
        versionCode = 36
        versionName = "2.9.8"
    }

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
        // 调试能力默认关闭，需要时手动选 debug 变体：
        //   ./gradlew :lunastub:assembleRelease  → debuggable=false，发给车机用这个
        //   ./gradlew :lunastub:assembleDebug    → debuggable=true，需要 adb / run-as 时用
        // debuggable 是编译期属性，装到设备后无法运行时切换。
        debug {
            isDebuggable = true
            // 复用主应用同一套签名，方便一次 adb install 多个包
            signingConfig = signingConfigs.findByName("release") ?: signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            isDebuggable = false
            signingConfig = signingConfigs.findByName("release") ?: signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
