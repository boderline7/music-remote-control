# 车机桥接（18766）

给「车机助手」加一个 HTTP 口，让手机网页能读到车机卡片、能点卡片上的东西。

**原则：车机助手的代码一个字节都不改。** 全部通过反射 + ContentProvider 启动钩子挂上去。

原有两个工程（`车机助手_改版工程`、`车机遥控_工程`）全程只读，只引用了它们的 apktool / keystore / 解包目录副本。

---

## 端口两条线

| 端口 | 谁提供 | 什么时候在 |
|---|---|---|
| **18766** | 车机助手（本工程的 dex） | **常驻**。三重触发：ContentProvider（进程一起来）+ 前台服务 START_STICKY（进程被回收）+ 开机广播；跟音乐模式无关 |
| 18765 | 车机端音乐包（汽水桥接包） | 看音源：官方网易云在跑，桥接包就绑；否则让位或关闭 |

车控只走 18766，所以「没放歌」「第三方在放」都不影响车控。手机打开 `http://<车机IP>:18766/` 就是车控页。

18766 起来后，通知栏会有一条常驻通知「车控桥接 · 18766 常驻中」（最低优先级、无声）。它的作用是让系统不再回收这个进程——有它才叫真的常驻。

**新装的车机助手要先手动打开一次。** 安卓对「刚安装还没启动过」的应用处于 stopped 状态，开机广播不会投递，所以第一次装完必须点一下桌面助手图标（或重启车机），之后才由服务自己维持。

## 两个包

| 包 | 装在哪 | 提供什么 | 版本 |
|---|---|---|---|
| `dist/车机助手_V18.62_bridge.apk` | 车机 | 18766：卡片数据 / 动作表 / 动作执行 / 车控页 | vC 14 · 18.62-mod |
| `dist/汽水音乐_桥接包_v2.10.6.apk` | 车机 | 桥接包：官方网易云模式下的 18765 + 车控页入口 + 入口页改版 | vC 49 · 2.10.6 |

手机端 App 不用动（它只是加载车机那份网页的壳），装完刷新页面即可。

```bash
bash install.sh      # 两个包一起推上去装
```

## 界面怎么用

**车机里打开「汽水音乐」入口页**（`com.luna.music.car` 的 Main）：

- 按钮两行六个：启动 / 重启 / 停止；刷新状态 / 打开音乐 App / 拉起助手。按下会变亮，点了有反馈
- 两行地址栏：蓝色 18765（音乐）、绿色 18766（车控），**只显示不可点**
- 状态框顶部先写清两条线：`车控 18766：在线` / `音乐 18765：运行中`，下面一行 `探测：` 写明每个地址试出来什么
- 车控探测在后台线程跑，不占主线程；连不上时界面照样能点
- 小状态栏常驻显示 `音乐：xx　车控：在线/离线`
- App 里不再有诊断按钮：车机屏幕没法选中复制，诊断统一放到车控页的设置里（见下）

**手机网页**（`http://<车机IP>:18765/`）：

- 右上角 **车图标** → 进车控页；或**从右往左滑**进、**从左往右滑**回
- 车机设置面板里加了「车控 18766 · 常驻」和「打开车控页」按钮
- 车控页：上半部分是车机助手的卡片（配色/圆角/字号都来自 `/cards` 的 `style`），下半部分是动作表（14 项，三组：空调 / 车身 / 底盘），点一下让助手自己执行
- 车控页右上角 **齿轮** → 「设置 · 诊断」：先看一行连接状态，点**生成诊断**取回整段文本，再**复制**或**下载**成 txt。
  文本里有端口是否绑定、引擎有没有绑上、安装包版本、动作项数、进程内日志和 logcat 摘要。
  手机浏览器里复制/下载都可用；App 内或微信里下载不弹窗就长按文本全选

车控页和音乐页是两条独立通道：音乐 WebSocket 断了，车控照样能用，反之亦然。

---

## 目录

```
src/com/navitool/bridge/
  BridgeProvider.java   ContentProvider，系统自动调用，启动一切
  Bridge.java           反射工具 + 抓 Activity 里的 carControlHelper
  HttpServer.java       极简 HTTP（ServerSocket，零依赖）
  Cards.java            读卡片数据 + 样式参数
  Actions.java          执行动作（调车机助手自己的方法）
  Probe.java            能力自检
  Page.java             18766 首页：读 assets/carbridge.html，读不到退回内置自检页
  Json.java             小工具

dex/classes.dex         编译产物，打包时作为 classes3.dex 塞进 APK
work/apk_decoded/       从改版工程复制来的解包目录（改这里，不动原目录）
work/cloudmusic-car/    从遥控工程复制来的车机端工程（改这里，不动原目录）
web/car.html            18766 独立车控页（构建时塞进 APK 的 assets）
web/remote.html         云音乐包网页（保留源文件，暂不出包）
web/remote_lunastub.html 桥接包网页（含车控页 + 设置面板入口）
_preview/mock.js        本地假数据，模拟 18765 / 18766 看网页效果
dist/                   成品 APK
compile.sh              Java -> dex
build.py                dex -> APK（含清单改动、对齐、签名）
build_remote.sh         网页 -> 汽水桥接包
install.sh              推到车机安装
```

## 构建

```bash
bash compile.sh       # 改完 Java 跑这个
python build.py       # 出车机助手包
bash build_remote.sh  # 出汽水桥接包
```

`build.py` 构建时会把 `work/apk_decoded` 复制到 `D:\cbwork\bridge_build`（ASCII 路径，否则 apktool 编 res 会挂），
在那个副本里加 provider 声明、改版本号，然后打包。

`build_remote.sh` 先把网页同步进 `work/cloudmusic-car`，再
`gradlew :lunastub:assembleRelease --offline`。Gradle 与 JDK 17 都走本机缓存，不需要联网。

`build.py` 还会把 `web/car.html` 拷成 APK 里的 `assets/carbridge.html`——18766 首页就是它，改页面不用重新编 Java。

**车机助手版本号固定 14 / 18.62-mod，签名固定 debug.keystore。** 这两个一动，覆盖安装就断了。
桥接包走 `keystore/carremote.jks`（和原来那些包同一把），vC 49 / 2.10.6。

## 看网页效果不用上车

```bash
node _preview/mock.js
```

然后浏览器开 `http://127.0.0.1:18765/`：18766 那边是假数据（4 张卡 + 动作表 + 一段假诊断），
用来调样式和手势，跟真车数据无关。

## 接口

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/` | 车控页（`assets/carbridge.html`，手机/车机浏览器直接打开） |
| GET | `/ping` | 存活 + 绑定状态 |
| GET | `/acts` | 动作表（分组）。只列车机助手里**真实存在**的方法，不存在的不返回 |
| GET | `/cards` | 卡片数据 + 样式参数 |
| POST | `/act` | `{"a":"handleTrunkClick"}`，带开关值的再补 `"v":1` |
| GET | `/probe` | 诊断用：能力/字段/方法清单（页面不再用它渲染） |
| GET | `/diag` | 诊断文本（纯文本）。端口、引擎绑定、包版本、动作项数、日志全在里面 |
| GET | `/diag.txt` | 同上，带 `Content-Disposition`，点链接直接存成文件 |

> `/act` 的参数是**方法名本身**（`handleTrunkClick` / `actionSetDriveModeSport`），
> 不再经过「id → 驼峰 → 猜方法名」那一层，名字对不上就是没有，不会点了没反应。
> 老写法（`steering_heat` 这类 id）仍会退回旧的匹配逻辑，不会断链。

## 自检页怎么覆盖

内置页在 dex 里，改起来要重新打包。想改前端又不想打包：

```bash
adb push my.html /sdcard/Android/data/com.navi.tool/files/web/index.html
```

服务端每次请求会优先读这个文件。

## 已知边界

- **依赖桌面助手在跑。** Helper 实例是桌面助手 Activity 里的那个，Activity 不出现就拿不到。
  车机助手是常驻桌面组件，正常情况下没问题；`/diag` 里的「界面 / 引擎」两行会明确告诉你有没有绑上。
- **只读引用，不新建 Helper。** 构造第二个 Helper 会扰乱界面结构，绝对不做。
- **弹窗类操作**（吹风模式、座椅按摩、香氛）走的是独立窗口，数据能读，点击要看 `handle*` 方法是否覆盖。
- **手机上验证不了车机数据。** 测试机没有车机总线，`getCarInfoValue` 会返回空，这是正常的。
