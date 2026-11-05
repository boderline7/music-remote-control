"""创建 GitHub Release 并上传两个 APK。

用法（bash）：
    export GITHUB_TOKEN=...        # 或用 git credential 取
    python _工具/make_release.py

不做的事：不改 tag 指向的提交（默认用 main 当前 HEAD）、不删旧 release。
"""
import json
import os
import sys
import urllib.parse
import urllib.request

OWNER = "boderline7"
REPO = "music-remote-control"
TAG = "v2.2.1"
TITLE = "2.2.1（车机）+ 1.8.6（手机）"

ASSETS = [
    ("dist/cloudmusic-car-v2.2.1-遥控-debug.apk", "music-remote-car-2.2.1.apk"),
    ("dist/车机遥控-v1.8.6.apk", "music-remote-phone-1.8.6.apk"),
]

BODY = """车机端 **2.2.1**（versionCode 32）+ 手机端 **1.8.6**（versionCode 15），配套使用。

## 装哪个

| 文件 | 装在哪 | 说明 |
|---|---|---|
| `music-remote-car-2.2.1.apk` | 车机 | 播放器 + 遥控服务，覆盖安装即可，同签名不用卸载 |
| `music-remote-phone-1.8.6.apk` | 手机 | 独立遥控 App，**可选** |
| 浏览器 | 后排任意手机 | 不用装东西，打开 `http://<车机IP>:18765` |

## 手机端包名变了

1.8.6 起 `com.yuhan.carremote` → `com.carremote.client`。Android 视作另一个应用：

- 旧版必须先卸载，否则桌面会出现两个同名图标
- 卸载后车机地址、音量、上次连接记录会清空，装完重新发现或手填一次 IP
- 车机端不受影响，不用重装

## 车机端要点

- 设置页打开「手机遥控」，页面会显示本机 IP 和 `carremote.local` 的自检状态
- 车机 IP 常变（开热点做网关时尤其明显），域名 `carremote.local:18765` 会自动跟着变；解析不到就直接用 IP
- 同时在线设备上限默认 4，可调 1~8

## 签名

两个包都是 `assembleDebug` 产物，但用的是自建签名库（不是 Android 调试密钥），
所以能直接覆盖安装旧版、不需要先卸载。将来换签名才需要卸载重装。

## 安全

无 TLS、无 PIN，局域网内明文。只用于车内可信网络，**不要把 18765 映射到公网**。

## 校验

```
{sha}
```
"""


def api(url, token, data=None, method=None):
    req = urllib.request.Request(url, data=data, method=method)
    req.add_header("Authorization", "Bearer " + token)
    req.add_header("Accept", "application/vnd.github+json")
    req.add_header("X-GitHub-Api-Version", "2022-11-28")
    if data is not None:
        req.add_header("Content-Type", "application/json")
    with urllib.request.urlopen(req, timeout=60) as r:
        return json.loads(r.read().decode())


def sha256_of(path):
    import hashlib
    h = hashlib.sha256()
    with open(path, "rb") as f:
        for chunk in iter(lambda: f.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


def upload(upload_url, path, name, token):
    url = upload_url.replace("{?name,label}", "") + "?name=" + urllib.parse.quote(name)
    size = os.path.getsize(path)
    req = urllib.request.Request(url, data=open(path, "rb"), method="POST")
    req.add_header("Authorization", "Bearer " + token)
    req.add_header("Content-Type", "application/vnd.android.package-archive")
    req.add_header("Content-Length", str(size))
    with urllib.request.urlopen(req, timeout=900) as r:
        return json.loads(r.read().decode())


def main():
    token = os.environ.get("GITHUB_TOKEN", "").strip()
    if not token:
        sys.exit("缺少 GITHUB_TOKEN")

    lines = []
    for path, _ in ASSETS:
        lines.append("%-46s %s" % (os.path.basename(path), sha256_of(path)))
    body = BODY.format(sha="\n".join(lines))

    rel = api(
        "https://api.github.com/repos/%s/%s/releases" % (OWNER, REPO),
        token,
        data=json.dumps({
            "tag_name": TAG,
            "target_commitish": "main",
            "name": TITLE,
            "body": body,
            "draft": False,
            "prerelease": False,
        }).encode(),
        method="POST",
    )
    print("release:", rel["html_url"])

    for path, name in ASSETS:
        a = upload(rel["upload_url"], path, name, token)
        print("  uploaded %-32s %8.1f MB" % (a["name"], a["size"] / 1048576))

    print("done")


if __name__ == "__main__":
    main()
