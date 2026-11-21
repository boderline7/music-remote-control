# 车机遥控

车机控制、音乐控制、行车记录仪，一套网页 + 两个 App + 手机端。

## 声明

本工程为个人自用改造，只在本人车上使用，不对外分发。

- 「助手」及原厂素材版权归原厂所有。本工程不提供、不分发原厂安装包与任何提取产物，`apk/`、`dex/` 均不入库，使用者需自行从合法渠道取得。
- 页面、网关、手机端均为自行编写；音乐端的来源见下节。
- **请支持原版**，购买官方服务与授权。本工程与原作者无关。

## 云音乐端来源与许可

`music\cloudmusic-car` 派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（LGPL-3.0），
接口与 eapi 加密移植自其上游 [missuo/kumone](https://github.com/missuo/kumone)（LGPL-3.0），应用图标亦取自后者。

- 上游的 `LICENSE`、`COPYING.GPL` 保留在 `music\cloudmusic-car\` 下，派生后的源码与全部改动都公开在这里。
- 不含网易云官方客户端的代码或素材，仓库内没有音频文件；VIP、无版权曲目置灰并跳过，不做破解。
- 仅供自用，不做商业分发。

## 结构

```
src\bridge\             网关源码
web\pages\              四个页面 + 统一外壳
music\cloudmusic-car\   音乐端（app / lunastub）
phone\                  手机端 android / ios
```

## 环境

构建输入端（`make.py`）需要：

- Python 3.8+（只用标准库，无第三方依赖）
- JDK 17
- Android SDK：`build-tools 34.0.0` + `platforms/android-35`
- apktool 2.11.1，放到 `patch/apktool.jar`（体积大，不在仓库里，自行下载）

音乐端与手机端走 Gradle wrapper（8.14.5 / AGP 8.7.3 / Kotlin 2.0.21），首次构建需联网拉依赖。
iOS 需 macOS + Xcode + XcodeGen，或直接走仓库内的 GitHub Actions 云构建。

路径不写死在代码里，按需提供：

```bash
# 方式一：工程根建 local.env（不入库）
JB=<jdk-17 目录>
BT=<build-tools 目录>
ANDROID_JAR=<sdk>/platforms/android-35/android.jar

# 方式二：scripts/config.local.sh（不入库，脚本用）
```

缺配置会直接报出缺哪一项。脱壳另需 root 设备 + frida-server，装包另需 adb。

## 端口

| 端口 | 谁在提供 | 什么时候在 |
|---|---|---|
| **18766** | 助手（网关） | 常驻 |
| **18765** | 第三方网易云车机版 | 它在跑时 |
| **18764** | 桥接包 | 官方音源模式 |
| **18767** | 桥接包 `DvrSvc` | 常驻 |

手机和浏览器只连 18766，页面上切三个 tab：车机控制 / 音乐控制 / 行车记录仪。18764 与 18765 互斥。
