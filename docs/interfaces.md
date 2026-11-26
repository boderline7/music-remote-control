# 接口

## 18766（车机助手）

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/` | 车控页（`assets/carbridge.html`，手机/车机浏览器直接打开） |
| GET | `/ping` | 存活 + 绑定状态 |
| GET | `/acts` | 动作表（分组）。只列车机助手里**真实存在**的方法 |
| GET | `/cards` | 卡片数据 + 样式参数 |
| POST | `/act` | `{"a":"handleHvacAutoClick"}`，带开关值的补 `"v":1` |
| POST | `/fan` | 前后排风量：`{"v":3}` 改前排（1-7），`{"r":0}` 改后排（0-4，0 关）；不带参数读当前档位 |
| POST | `/temp` | 温度：`{"v":26}`，15-30 ℃ 整度；不带参数读当前温度 |
| POST | `/seat` | 座椅：`{"p":"rl","k":"heat","lv":3}`，`p` 取 `fl/fr/rl/rr`，`k` 取 `heat/vent`，`lv` 是目标档位（0 关 / 1-3）；不带参数读四座状态 |
| GET | `/probe` | 诊断用：能力/字段/方法清单（页面不再用它渲染） |
| GET | `/diag` | 诊断文本。端口、引擎绑定、包版本、动作项数、日志全在里面 |
| GET | `/diag.txt` | 同上，带 `Content-Disposition`，点链接直接存成文件 |
| GET | `/acts.log` | 操作记录文本：每次 `/act` 的结果、耗时、错误，最新的在上面 |
| GET | `/acts.log.txt` | 同上，带下载头，存成 `act-log.txt` |
| GET | `/cards.txt` | 车况快照文本：每张卡每项的名字和值 |
| GET | `/acts.txt` | 动作表清单：按钮文字、方法名、参数，方法缺失的会标出来 |
| GET | `/dump` | 全量打包：诊断 + 车况 + 动作表 + 操作记录（`/dump.txt` 带下载头） |
| GET | `/log/rec?on=1\|0` | 日志记录开关。不带 `on` 只读状态（返回 `rec` 与已存行数）；`on=0` 停写新行、已有的留着 |
| GET | `/log/clear` | 清空操作记录（只清计数与缓冲，不动服务） |
| GET | `/restart` | 软重启：重抓 Activity / Helper，再要一次前台服务；返回是否绑上 |
| GET | `/gateway` | 网关状态：`{"ok":1,"music":<端口>}`。`music` 为 0 表示音乐后端没在跑 |

> `/act` 的参数是**方法名本身**（`handleTrunkClick` / `actionSetDriveModeSport`），
> 不再经过「id → 驼峰 → 猜方法名」那一层。老写法（`steering_heat` 这类 id）仍会退回旧的匹配逻辑，不会断链。

网关另有三个入口别名，转发到对应后端：`/music/*`、`/api/*`（音乐）、`/dvr/*`（记录仪）。

## 18767（行车记录仪）

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/` | 记录仪页（`assets/dvr.html`） |
| GET | `/ping` | 存活 + 本机 IP |
| GET | `/dvr/probe` | 探测记录仪 IP，返回 `{ok,ip,via}` |
| GET | `/dvr/state` | 当前/固定 IP、是否已断开 |
| GET | `/dvr/disconnect` | 断开：停止通讯（记住的 IP 保留） |
| GET | `/dvr/setip?ip=` | 设固定 IP（留空恢复自动探测） |
| GET | `/dvr/list?path=/SD0/NOR/M_video/` | 目录清单：`{ok,path,parent,dirs:[{n,p}],list:[{f,t,s}],n,stat}`，文件按时间倒序 |
| GET | `/dvr/fs?p=/SD0/NOR/M_video/` | 原厂页代理：目录页整页转出来，链接改写成 18767 的地址 |
| GET | `/dvr/file?p=/SD0/NOR/M_video/x.MP4&dl=1` | 文件流（带 Range）；`dl=1` 带下载头 |

## 页面参数

三个页面（车控 / 桥接 / 云音乐车机版）都认这套参数：

| 参数 | 谁带 | 页面做什么 |
|---|---|---|
| `pane=car\|music\|dvr` | 外壳贴 iframe 时 | 标记自己是被嵌进来的哪一块 |
| `embed=1` | 老版切换条 | 收掉自己的顶栏，只留内容，避免两层头 |
| `client=android` / `client=ios` | 手机 App 打开页面时 | 客户端来源标识，随握手上报；切换条仍由页面自己画 |
| `cv=` | 手机 App | 版本号，随握手上报给车机侧 |
| `b=` | 外壳自动加 | 构建号，内容一变就变，用来去缓存 |

`{{HOST}}` 由服务端按请求 `Host` 填好，客户端脚本挂了页面里的地址也点得动。
