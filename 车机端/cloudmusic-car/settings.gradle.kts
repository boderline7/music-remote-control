pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "cloudmusic-car"
// 只保留网易云（:app）。qqmusic / kugoumusic / 跑跑桌面功能包不在本工程范围内。
include(":app")
// 「汽水音乐车机版」占位包：让车机助手的音源候选出现该项，不含任何音乐逻辑。
include(":lunastub")
