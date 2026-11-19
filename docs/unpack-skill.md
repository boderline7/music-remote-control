---
name: navitool-unpack
description: 对百度加固的"车机助手 com.navi.tool"APK 进行脱壳并反编译为 Java 源码。用户上传或提供新版车机助手 .apk（包名 com.navi.tool，使用百度加固/baiduprotect），或要求对新版本 APK 重新脱壳/获取脱壳源码时调用。Do not use for 无加固 APK、其它应用的常规反编译。
---

# 车机助手 (com.navi.tool) 脱壳

> **这份文档在合并工程里的落位**（2026-11-19 从 `D:\模块开发\脱壳\SKILL.md` 并入）
>
> - 脚本：`scripts/unpack/attach_unpack.py` + `unpack.js`（即下文"复用脚本"那两行）
> - 产物：dex 放 `dex/<版本>/dex/`，反编译源码放 `dex/<版本>/src/`
> - 快速上手看 `docs/unpack.md`；本文是完整技能文档，价值在下面五个坑
> - 下文所有工具路径都是本机绝对路径，换机器要改。jadx 是 122M 的外部目录，没放进工程

对百度加固（baiduprotect）保护的车机助手 APK 执行"脱壳 -> 反编译 -> 交付源码"全流程。核心是通过 frida attach + 内存 dex 扫描抓取运行时解密加载的真实 dex，再用 jadx CLI 反编译。

## 前置环境（每次脱壳前确认）

- 一台已 root 的安卓设备，已开启 USB 调试，adb 可达（adb 不在 PATH 时用绝对路径）。
- 设备上 frida-server 已运行（root 权限）。
- 本机 Python 已装 `frida` 库。用 `frida.get_usb_device()` 连接。
- 工具路径（按本机实际位置填，下面只是占位）：
  - jadx CLI（批处理）：`<jadx>/bin/jadx.bat`
  - JDK 21：`<jdk-21>/bin/java.exe`
  - apktool：`<apktool.jar>`（本工程里是 `patch/apktool.jar`，不入库）

## 脱壳步骤

### 1. 分析 APK 与加固

检查 APK 内是否含百度加固标记（`assets/baiduprotect*.jar`、`baiduprotect*.i.dex`、`lib/*/libbaiduprotect.so`、`assets/baiduprotect.md`）。若确认使用百度加固，业务 classes.dex 被加密保护，必须走动态脱壳。用 zipfile 或 apktool 读取 `AndroidManifest.xml` 确认包名（通常 com.navi.tool）与 versionName。

### 2. 在设备上安装新 APK

**关键坑（MIUI 安装拦截）**：普通 `adb install` 和低权限 `pm install -r -g` 都会因 `INSTALL_FAILED_USER_RESTRICTED: Install canceled by user` 失败。必须用 root + pm 安装：

```powershell
$adb = "<adb 路径>"
& $adb push <apk路径> /data/local/tmp/navi.apk
& $adb shell "su -c 'pm install -r /data/local/tmp/navi.apk'"
```

确认安装：`& $adb shell "dumpsys package com.navi.tool | grep versionName"`

### 3. 启动应用并快速 attach

**关键坑（App 会退出）**：应用启动后约 5-12 秒内会触发 VIN 检测并退出进程（项目记忆：hook `g4.b.run` case=6 可阻断，但脱壳场景下只需在退出前注入）。因此启动后要立即（≤2 秒）attach：

```python
adb('shell','am','force-stop', PKG); time.sleep(1)
adb('shell','monkey','-p', PKG,'-c','android.intent.category.LAUNCHER','1')
time.sleep(1.5)
pid = adb('shell','pidof', PKG).stdout.strip()
dev = frida.get_usb_device(timeout=10)
session = dev.attach(int(pid))
```

不要用 spawn（百度加固反调试会导致 spawn 超时）。用 attach 模式。

### 4. 抓取内存 dex

**关键坑（dump 方式）**：不要用 JS 内 `File.open` 写设备文件（frida 17 的 File API 会报 unwritable/TypeError）。改用 `send()` 把 dex 字节回传给 Python 端落盘。

扫描脚本要点（参见 `scripts/unpack.js` 与 `scripts/attach_unpack.py`）：
- 用 `Memory.scanSync` 匹配 dex 魔数 `dex\n035\0`/`036`/`037`/`038` 头，遍历每段所有命中（不要用 `onMatch` 回调里 `return 'stop'`，那会漏抓）。
- 从 header offset 0x20 读 `file_size`，offset 0x24 读 `data_off`，校验后 `readByteArray(fileSize)` 完整 dump。
- 用文件前 64 字节指纹去重。
- 分多轮扫描（梅花 `Thread.sleep(0.3)` 间隔扫 8 轮），应对 dex 稍晚解密。
- Python 端在 `on_message` 中按 `payload['name']` 接收 `data`（frida 分离二进制数据），写入本地目录。

典型的成功产物是 3 个 dex：主业务 ~3.6MB、副业务 ~1.6MB、壳解密 ~196KB，魔数均为合法的 `dex\n038\0`/`dex\n035\0`。

### 5. 反编译为 Java

**关键坑（jadx GUI/JDK 路径）**：不要用 `java -jar jadx-1.5.1-all.jar` —— 那是 GUI 入口（会抛 `InvalidPathException`，且把多文件拼成单字符串也会报错）。必须用 CLI 版 `jadx.bat`，且每个 dex 作为**独立参数**传入（不要 join 成一个字符串）：

```powershell
$jadxBat = "<jadx>\bin\jadx.bat"
& $jadxBat -d <输出目录> --no-res --no-debug-info "d1.dex" "d2.dex" "d3.dex"
```

（批处理 `jadx.bat` 已内置正确 JDK/classpath。若用到 `java.exe`，用 JDK21 完整路径。）

反编译输出在 `<输出目录>/sources`。`com.navi.tool` 业务类大量被混淆为 `a.java`/`b.java` 等短名，但核心业务类保留原名：`service/CarClimateManager.java`（座椅通风/加热）、`service/CarSoundManager.java`、`service/AutoStartService.java` 等。

### 6. 交付

产物直接放进本工程，不再另建 D 盘目录：

```
D:\开发\车机助手\dex\V{versionName去点}\
├── dex\     # 脱壳得到的原始 dex（构建输入）
└── src\     # jadx 反编译的 Java（查阅用，不参与构建）
```

例如 V19.00 -> `dex\V19.00\`。版本号由 `make.py inspect` 读得出，别靠 APK 文件名猜。

## 复用脚本

- `scripts/unpack/attach_unpack.py` — Python 启动器：force-stop + monkey 启动 + 1.5s 后 attach + 注入 JS + 收 send 落盘 + 打印结果。`ADB` / `ADB_OUT` 可用环境变量覆盖。
- `scripts/unpack/unpack.js` — 核心 DEX dump 脚本（Memory.scanSync + send 回传）。

## 完成后清理

脱壳成功后删掉工作区中间产物（`apk_*`、`dump_*`、`jadx_src_*`、`*.js`、`frida_*.py`）。本工程的脱壳输出默认落在 `dump/`（已 gitignore），要留就整理进 `dex/<版本>/`，不留直接删。