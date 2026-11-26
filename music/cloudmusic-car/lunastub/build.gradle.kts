import java.util.Properties

plugins {
    id("com.android.application")
}

/**
 * 「车控互联」—— 文件传输（小绿传）+ 行车记录仪（18767）。
 *
 * 历史：这个包原本是「汽水音乐车机版」，兼着官方网易云的桥接（18764）和车机助手的
 * 汽水音源通道。桥接代码 2026-11 已整块剥离，音乐相关一律不在这个包里；音源改由
 * 车机助手的酷我槽位直连第三方网易云（com.cloudmusic.car），跟本包无关。
 *
 * 职责就两条：
 *   - 首页：文件传输（小绿传），合并进本包，见 scripts/merge_xiaolv.py
 *   - 行车记录仪：DvrSvc 常驻 18767，列表走设备 7878 控制口，文件走 80 端口中转缓存
 *
 * 包名 com.yuhan.dvrcar 是新的，和原来的 com.luna.music.car 不共用一个安装位——老包
 * 想彻底清掉得手动卸载，不然桌面上会并排两个图标。
 */
android {
    namespace = "com.yuhan.dvrcar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yuhan.dvrcar"
        minSdk = 28
        targetSdk = 35
        // 与其它几个包区分版本线。固定住：以后改内容也重出同一个 2.10.29 / 72。
        versionCode = 72
        versionName = "2.10.29"
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
