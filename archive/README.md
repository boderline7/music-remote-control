# archive

只读。不要修这里的代码，也不要让构建依赖它。

| 目录 | 是什么 | 为什么留着 |
|---|---|---|
| `phone-proto/` | 手机端早期协议草案（`Protocol.java` / `protocol.json` / 测试脚本） | 安卓 `Protocol.kt` 的常量与它同源，出问题时拿来对照 |

原 `qqmusic/` 与 `kugoumusic/` 两个跑跑桌面功能包模块已于 2026-11-17 删除
（`settings.gradle.kts` 里本来就没 include，基线之后零改动）。需要时从 git 历史取：

```bash
git log --oneline -- music/cloudmusic-car/qqmusic      # 找到删除前的提交
git checkout <commit> -- music/cloudmusic-car/qqmusic  # 取回
```
