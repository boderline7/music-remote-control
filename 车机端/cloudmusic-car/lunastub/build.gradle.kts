import java.util.Properties

plugins {
    id("com.android.application")
}

/**
 * 「汽水音乐车机版」占位包 —— 只为让车机助手（com.navi.tool）的音源候选列表出现这一项。
 *
 * 车机助手的音源表写死在 DesktopAssistantMusicUiHelper 里，按包名判断「是否已安装」：
 *   com.luna.music.car → source 5（汽水音乐车机版）
 * 没装就不进候选列表，也就选不中，主应用发的广播没人收。
 *
 * 这个包本身不含任何音乐逻辑：点开只把主应用（com.cloudmusic.car）拉到前台。
 * 真正的播放/状态/歌词全部由主应用的 LunaCompat 通过广播直连。
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
        versionCode = 1
        versionName = "1.0.0"
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
        // 复用主应用同一套签名，方便一次 adb install 多个包
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
}
