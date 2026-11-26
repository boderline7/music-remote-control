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
// 「行车记录仪车机端」（com.yuhan.dvrcar）：18767 记录仪 + 文件传输，不含任何音乐逻辑。
// 模块名还叫 lunastub，历史原因，改名会牵动一堆构建脚本。
include(":lunastub")
