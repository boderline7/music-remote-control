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
// 只保留云音乐（:app）。qqmusic / kugoumusic / 跑跑桌面功能包不在本工程范围内。
include(":app")
