# 车机遥控

车机控制、音乐控制、行车记录仪三件事，一套网页 + 两个 App + 手机端，一个仓库走完全程。

## 声明

本工程为个人自用改造，只在自己车上跑，不分发。

- 「车机助手」（`com.navi.tool`）及原厂素材版权归原厂所有。本工程不提供、不分发原厂安装包与任何提取产物——`apk/`、`dex/` 均不入库，使用者需自行从合法渠道取得。
- 自行编写的部分是网关与页面：车控页面、音乐端、手机端、记录仪页都不依赖别人的代码。
- **请支持原版**，购买官方服务与授权。本工程与原作者无关，不做商业分发。

## 目录

```
make.py                 唯一构建入口（inspect / build / verify）
patches.py              补丁声明表
src\bridge\             网关注入源码
web\pages\              四个页面 + 统一外壳（shell\）
music\cloudmusic-car\   音乐端：app（第三方网易云）/ lunastub（桥接 + 记录仪）
phone\                  手机端 android / ios
patch\                  补丁素材、配置包、apktool
scripts\                各端构建脚本、推送脚本、锚点核对
docs\                   架构 / 接口 / 构建 / 脱壳 / 排障 / 约定
dist\                   产物
```

`apk\` 与 `dex\` 是构建输入，不入库。

## 出包

```bash
python make.py build \
  --apk "apk/navitool-19.00-protected.apk" \
  --dex "dex/V19.00/dex" \
  --inject --no-so --assets-config --free-weather --no-seat-confirm --floating-map --no-weather \
  --out "dist/车机助手_V18.73_bridge_源19.00_去so_带配置_无天气.apk"
```

约 1 分半，自校验 22/22 补丁落地。

```bash
python make.py inspect <dex目录>    # 新 dex 能不能直接用
python make.py build --dex <dex目录> # 纯 mod 包（不带网关）
python make.py verify <apk>         # 校验任意产物

python web/stitch.py                # 改过 web/shell/* 先跑这个
bash scripts/build_remote.sh        # 音乐端两个包
bash scripts/build_phone.sh         # 安卓手机端
bash scripts/build_ios.sh           # iOS 走 GitHub 云构建
bash scripts/install.sh             # 推最新包到车机安装
node preview/mock.js                # 不上车看页面：http://127.0.0.1:18765/
```

## 开关

| 开关 | 作用 |
|---|---|
| `--inject` | 网关 + 车控页 + classes3.dex |
| `--no-so` | 整块去 so，破反篡改自杀 |
| `--assets-config` | 预置瑞虎9+通用配置（8 包 / 162 文件） |
| `--free-weather` | 目的地播报天气改 Open-Meteo（免 key） |
| `--free-desk-weather` | 桌面卡片天气也改 Open-Meteo（与 `--no-weather` 互斥） |
| `--no-seat-confirm` | 去掉座椅「应用配置」确认框 |
| `--floating-map` | 悬浮地图切走即结束进程 |
| `--no-weather` | 停用桌面天气，保留目的地播报 |

默认都不开。当前交付那版七个一起开，不含 `--free-desk-weather`。

## 端口

| 端口 | 谁在提供 | 什么时候在 |
|---|---|---|
| **18766** | 车机助手（注入的网关） | 常驻 |
| **18765** | 第三方网易云车机版 | 它在跑时 |
| **18764** | 桥接包 | 官方音源模式 |
| **18767** | 桥接包 `DvrSvc` | 常驻 |

手机和浏览器只连 18766，页面上切三个 tab。18764 与 18765 互斥，同一时刻只有一个在跑。

## 版本与签名

- 版本号一律不动，改内容就重出同名包，随时能退回上一版。
- 车机助手 `18.73-mod` / code `25`，`CN=Navitool Mod`；三个手机端与音乐端包同一张 `CarRemote` 证书。
- 纯网页改动只影响车机端，手机端刷新页面即可。

产物名里 `源19.00` 是来源版本，`无天气` 是桌面卡片已停用（目的地播报仍在）。

## 文档

- `docs/architecture.md` 四层注入与端口线
- `docs/interfaces.md` HTTP 接口
- `docs/build.md` 构建细节与外部路径
- `docs/conventions.md` 改了这么多次定下的规矩
- `docs/troubleshooting.md` 排障与已知问题
- `docs/unpack.md` / `unpack-skill.md` 脱壳流程

旧的分散目录保留未动，确认无误后可自行删除。
