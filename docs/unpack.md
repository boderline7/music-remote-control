# 脱壳

原厂包是**百度加固**的，apktool 直接解出来只有壳，`com/navi/tool` 的业务类一个都看不到。
构建要的是脱壳后的真 dex，这一步绕不过去。

> 完整技能文档（含五个踩过的坑：MIUI 安装拦截 / 5-12 秒退出 / 不能 spawn / 不能 File.open / jadx 必须 CLI）见 `docs/unpack-skill.md`。本文只讲怎么在本工程里跑通。

## 为什么必须脱

验证一下就明白：

```bash
python scripts/verify_host.py apk/navitool-19.00-protected.apk
```

9 条致命项全红——`DesktopAssistantActivity` 找不到、`carControlHelper` 字段不存在。
不是包坏了，是类还在加固壳的内存里没解密。

## 工具

`scripts/unpack/` 两个文件：

| 文件 | 作用 |
|---|---|
| `attach_unpack.py` | 启动器：force-stop → monkey 拉起 → 1.5s 内 attach → 注入 JS → 接收回传写盘 |
| `unpack.js` | 在目标进程里 `Memory.scanSync` 扫 dex 魔数头（`dex\n035\0` / `036`），完整 dump 后 `send()` 回传 |

依赖：`pip install frida`；设备已 root、`frida-server` 在跑、`adb` 能连上。

## 步骤

```bash
# 1. 装原包到车机（脚本内部会 push + pm install -r）
python scripts/unpack/attach_unpack.py apk/navitool-19.00-protected.apk

# 2. 等 35 秒，看 [RCV] 输出，dex 落到 dump/
ls dump/
```

输出目录默认工程根 `dump/`，可用环境变量改：

```bash
export ADB="/path/to/adb"      # 不在 PATH 时填绝对路径
export ADB_OUT="D:/somewhere"  # 默认 <工程根>/dump
```

## 关键坑

- **必须抢时间**。应用起来 5–12 秒内会做 VIN 校验然后退出，晚了进程没了就 attach 不上。脚本里固定 `sleep 1.5` 后取 pid，是在这个窗口里。
- **dump 出来的是全部 dex**，包括壳自己的。构建时 `make.py` 会按继承链筛（`classify` + `check_chain`），只留业务 dex。
- **dex 命名不用改**，`make.py build --dex <目录>` 按文件名排序反汇编，顺序稳定即可。

## 接进构建

把 dump 出来的 dex 放进 `dex/<版本>/dex/`，然后：

```bash
python make.py inspect dex/V19.00/dex     # 先看继承链完不完整
python make.py build --apk ... --dex dex/V19.00/dex
```

`inspect` 会打印每个 dex 的类数、是否被判定为壳、继承链是否闭合。链不闭合就别往下走。

## 当前版本

`dex/V19.00/` 是 2026-11-18 从 `navitool-19.00-protected.apk` 脱出来的：

- `dex_1_3779968.dex`（2842 类，其中 `com/navi` 987）、`dex_2_1633180.dex`（1345 类，androidx / google 库）
- `src/` 是 jadx 反编译的 3436 个 java，仅供查阅，**不参与构建**
- 锚点核对：`python scripts/verify_host.py` 默认就查这份 dex，15 条锚点全过
