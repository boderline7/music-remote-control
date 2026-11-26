#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""第三方网易云音源槽位 · smali 级改动表

原厂车机助手的音源表把「酷我 6.x」放在 source=1、「酷我 7.x」放在 source=4，两条分支
都直接调 `cn.kuwo.autosdk` 的私有管理器。现在酷我不用了，这两个槽位改指第三方网易云
（`com.cloudmusic.car`）。

为什么打 smali 而不写 Java：这些分支埋在原厂几万行混淆代码里（DesktopAssistantActivity
7299 行），改哪句就动哪句，比整方法重写安全得多。所以这里不用 patches.py 的 body 补丁，
而是「在指定方法块内做定点替换」——每条都断言命中次数，命中数不符立刻报错，绝不半改。

真正干活的通道复用了原厂给「汽水音乐」留的那条（jadx 里的 `k4.a`，公版 smali 名
`Li4/a`）：它只是发 `com.luna.music.car.PLAYER_CONTROL` / `PLAYER_SYNC` 广播，
第三方网易云侧的 `LunaCompat` 已经完整实现了收发（播控、封面、逐行歌词、收藏）。
把它接过来，source=1/4 就和原来的 source=5 行为一致，而 source=5 一个字没动。
"""

import re
from pathlib import Path

# ---------------------------------------------------------------- 常量

CLOUD_PKG = "com.cloudmusic.car"
# 原厂「汽水音乐桥接」客户端（jadx 反编译里叫 k4.a）。发送 PLAYER_* 广播、注册歌词接收器。
LUNA = "Li4/a;"
KUWO_MGR = r"Lcom/navi/tool/amapflying/util/kuwo/(?:New)?KuwoMusicManager;"

HELPER = "smali/com/navi/tool/ui/extra/DesktopAssistantMusicUiHelper.smali"
ACTIVITY = "smali/com/navi/tool/ui/extra/DesktopAssistantActivity.smali"
FRAGMENT = "smali/com/navi/tool/ui/extra/DesktopAssistantFragment.smali"
HUD = "smali/com/navi/tool/service/MobileHudService.smali"
MAIN = "smali/com/navi/tool/MainActivity.smali"
FLOAT = "smali/com/navi/tool/amapflying/util/FloatingWindowService.smali"
LYRIC_RX = "smali/com/navi/tool/amapflying/receiver/LunaMusicLyricReceiver.smali"
MEDIA_SESSION = "smali/j4/e.smali"
LAYOUT = "res/layout/fragment_desktop_assistant.xml"

# 通用替换：酷我管理器 -> 汽水那条广播通道。顺序无所谓，彼此方法名不同。
SWAPS = [
    (r"invoke-static \{([vp]\d+)\}, " + KUWO_MGR + r"->getInstance\(Landroid/content/Context;\)" + KUWO_MGR,
     r"invoke-static {\1}, " + LUNA + r"->c(Landroid/content/Context;)" + LUNA),
    # 播控：助手是 (isPlaying, controlType)，广播通道只要 controlType；0 就是切换，语义一致
    (r"invoke-virtual \{([vp]\d+), [vp]\d+, ([vp]\d+)\}, " + KUWO_MGR + r"->dispatchControl\(ZI\)V",
     r"invoke-virtual {\1, \2}, " + LUNA + r"->b(I)V"),
    (r"invoke-virtual \{([vp]\d+)\}, " + KUWO_MGR + r"->start\(\)V",
     r"invoke-virtual {\1}, " + LUNA + r"->g()V"),
    (r"invoke-virtual \{([vp]\d+)\}, " + KUWO_MGR + r"->stop\(\)V",
     r"invoke-virtual {\1}, " + LUNA + r"->h()V"),
    (r"invoke-virtual \{([vp]\d+)\}, " + KUWO_MGR + r"->refreshState\(\)V",
     r"invoke-virtual {\1}, " + LUNA + r"->f()V"),
]

# 循环模式：酷我 setPlayMode(0/1/2)，广播通道用 5/6/7（顺序/单曲/随机），要现算。
LOOP_OLD = (
    r"invoke-static \{([vp]\d+)\}, " + KUWO_MGR +
    r"->getInstance\(Landroid/content/Context;\)" + KUWO_MGR +
    r"\n\n    move-result-object \1\n\n    invoke-virtual \{\1, ([vp]\d+)\}, " + KUWO_MGR + r"->setPlayMode\(I\)V")
LOOP_NEW = (
    r"const/4 v2, 0x5\n\n    add-int/2addr v2, \2\n\n    "
    r"invoke-static {\1}, " + LUNA + r"->c(Landroid/content/Context;)" + LUNA +
    r"\n\n    move-result-object \1\n\n    invoke-virtual {\1, v2}, " + LUNA + r"->b(I)V")

# 要按「方法块内做通用替换」处理的目标：凡是按音源分流 start/stop/refresh/播控的地方。
SWAP_TARGETS = [
    dict(file=HELPER, method="dispatchMusicControl(Landroid/content/Context;Landroid/content/SharedPreferences;ZI)V",
         tag="桌面播控分支", expect=4),
    dict(file=HELPER, method="startMusicManagerBySource(Landroid/content/Context;ILcom/navi/tool/ui/extra/LyricsAdapter;)V",
         tag="按音源启播", expect=4),
    dict(file=HELPER, method="refreshMusicStateBySource(Landroid/content/Context;I)V",
         tag="按音源刷新", expect=4),
    dict(file=HELPER, method="stopMusicManagers(Landroid/content/Context;)V",
         tag="停全部音源", expect=4),
    dict(file=HELPER, method="stopAllMusicServices(Landroid/app/Activity;Landroid/widget/TextView;Landroid/widget/TextView;"
                         "Landroid/widget/TextView;Landroid/widget/TextView;Lcom/navi/tool/ui/extra/LyricsAdapter;"
                         "Landroid/widget/ImageButton;Lcom/navi/tool/view/SpectrumView;)V",
         tag="退出时停音源", expect=4),
    dict(file=FRAGMENT, method="checkAndInitMusicService()V", tag="桌面Fragment启播", expect=4),
    dict(file=FRAGMENT, method="stopMusicServices()V", tag="桌面Fragment停音源", expect=4),
    dict(file=HUD, method="startManagedMusicManager(I)V", tag="HUD启播", expect=4),
    dict(file=HUD, method="stopManagedMusicManager()V", tag="HUD停音源", expect=4),
    dict(file=HUD, method="lambda$refreshManagedMusicState$3(I)V", tag="HUD刷新", expect=4),
    dict(file=MAIN, method="startMusicManagerForBridge(Landroid/content/SharedPreferences;)V", tag="主界面启播", expect=4),
    dict(file=FLOAT, method="o(I)V", method_alt="m(I)V", tag="悬浮窗启播", expect=4),
    dict(file=FLOAT, method="p()V", method_alt="n()V", tag="悬浮窗停音源", expect=4),
]

# 定点文本替换：(文件, 方法签名或 None=整文件, 标签, 旧, 新, 次数)
TEXT_EDITS = [
    # ---- 包名解析：1/4 -> com.cloudmusic.car ----
    (HELPER, "resolveMusicPackageForSource(ILandroid/content/SharedPreferences;)Ljava/lang/String;",
     "包名解析 1/4", '    const-string p0, "cn.kuwo.kwmusiccar"',
     '    const-string p0, "%s"' % CLOUD_PKG, 1),
    (HELPER, "resolveMusicSourceFromPackage(Landroid/content/Context;Ljava/lang/String;)I",
     "包名反查 com.cloudmusic.car", '    const-string v0, "cn.kuwo.kwmusiccar"',
     '    const-string v0, "%s"' % CLOUD_PKG, 1),
    # 酷我原来按版本号 6.x/7.x 分 1/4，这里直接给 1，不再读版本
    (HELPER, "resolveMusicSourceFromPackage(Landroid/content/Context;Ljava/lang/String;)I",
     "包名反查恒为 1", "    if-eqz v0, :cond_2c\n\n    invoke-static {p0, p1}, "
     "Lcom/navi/tool/ui/extra/DesktopAssistantMusicUiHelper;->getPackageVersionName",
     "    if-eqz v0, :cond_2c\n\n    const/4 p0, 0x1\n\n    return p0\n\n    invoke-static {p0, p1}, "
     "Lcom/navi/tool/ui/extra/DesktopAssistantMusicUiHelper;->getPackageVersionName", 1),

    # ---- 显示名：1/4 -> 第三方网易云 ----
    (HELPER, "resolveMusicSourceDisplayName(I)Ljava/lang/String;",
     "显示名 source=4", r'    const-string p0, "\u65b0\u7248\u9177\u6211"',
     r'    const-string p0, "\u7b2c\u4e09\u65b9\u7f51\u6613\u4e91"', 1),
    (HELPER, "resolveMusicSourceDisplayName(I)Ljava/lang/String;",
     "显示名 source=1", r'    const-string p0, "\u9177\u6211\u97f3\u4e50"',
     r'    const-string p0, "\u7b2c\u4e09\u65b9\u7f51\u6613\u4e91"', 1),

    # ---- 候选列表：酷我那条换成第三方网易云 ----
    (HELPER, "getInstalledMusicSourceCandidates(Landroid/content/Context;)Ljava/util/List;",
     "候选列表", '    const-string v1, "cn.kuwo.kwmusiccar"\n\n    const-string v2, "\\u9177\\u6211\\u97f3\\u4e50\\u8f66\\u673a\\u7248"',
     '    const-string v1, "%s"\n\n    const-string v2, "\\u7b2c\\u4e09\\u65b9\\u7f51\\u6613\\u4e91"' % CLOUD_PKG, 1),

    # ---- 桌面设置页：单选文案，两个酷我槽位已合并成一个 ----
    (LAYOUT, None, "设置页单选文案", 'android:text="酷我音乐车机版"', 'android:text="第三方网易云"', 1),
    (LAYOUT, None, "设置页隐藏旧酷我单选", 'android:id="@id/radio_source_new_kuwo"',
     'android:id="@id/radio_source_new_kuwo" android:visibility="gone"', 1),
    (FRAGMENT, "getMusicSourceDisplayName(I)Ljava/lang/String;", "Fragment 显示名 1",
     r'    const-string p1, "\u9177\u6211\u97f3\u4e50\u8f66\u673a\u7248"',
     r'    const-string p1, "\u7b2c\u4e09\u65b9\u7f51\u6613\u4e91"', 1),
    (FRAGMENT, "getMusicSourceDisplayName(I)Ljava/lang/String;", "Fragment 显示名 4",
     r'    const-string p1, "\u65b0\u9177\u6211\u97f3\u4e50\u8f66\u673a\u7248"',
     r'    const-string p1, "\u7b2c\u4e09\u65b9\u7f51\u6613\u4e91"', 1),

    # ---- 桌面 Fragment 自己的包名反查（自动识别当前音源用） ----
    (FRAGMENT, "resolveMusicSourceFromPackage(Ljava/lang/String;)I", "Fragment 包名反查",
     '    const-string v0, "cn.kuwo.kwmusiccar"', '    const-string v0, "%s"' % CLOUD_PKG, 1),
    (FRAGMENT, "resolveMusicSourceFromPackage(Ljava/lang/String;)I", "Fragment 包名反查恒为 1",
     "    if-eqz v0, :cond_2c\n\n    invoke-direct {p0, p1}, "
     "Lcom/navi/tool/ui/extra/DesktopAssistantFragment;->getPackageVersionName",
     "    if-eqz v0, :cond_2c\n\n    const/4 p1, 0x1\n\n    return p1\n\n    invoke-direct {p0, p1}, "
     "Lcom/navi/tool/ui/extra/DesktopAssistantFragment;->getPackageVersionName", 1),

    # ---- 汽水音源没了，这条通道的兜底标题别再显示「汽水音乐」 ----
    (LYRIC_RX, None, "歌词通道兜底标题", r'    const-string p1, "\u6c7d\u6c34\u97f3\u4e50"',
     r'    const-string p1, "\u7b2c\u4e09\u65b9\u7f51\u6613\u4e91"', 1),
]

# 事件上的「音源号」：LunaMusicLyricReceiver 原本把歌词/状态事件硬编码成 5，
# 桌面那边是 `lVar.f6756b == prefs.desktop_music_source` 才收。槽位挪到 1/4 以后
# 必须按当前 prefs 打标，否则歌词和封面全被丢掉。这里给这个类补一个静态取值方法。
TAG_METHOD = """\
.method public static modSourceTag()I
    .registers 4

    # [MOD] 事件音源号 = 当前选中的音源（默认 5，保持原行为）
    sget-object v0, Lcom/navi/tool/amapflying/receiver/LunaMusicLyricReceiver;->g:Landroid/content/Context;

    if-eqz v0, :mod_tag_default

    const-string v1, "navi_tool_prefs"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "desktop_music_source"

    const/4 v2, 0x5

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0

    :mod_tag_default
    const/4 v0, 0x5

    return v0
.end method
"""

TAG_SITES = [
    # 状态事件（h4.m.l）
    ("    const/4 p1, 0x5\n\n    iput p1, p0, Lg4/m;->l:I",
     "    invoke-static {}, Lcom/navi/tool/amapflying/receiver/LunaMusicLyricReceiver;->modSourceTag()I\n\n"
     "    move-result p1\n\n    iput p1, p0, Lg4/m;->l:I", "状态事件音源号"),
    # 歌词事件（h4.l.b）
    ("    const/4 v6, 0x5\n\n    iput v6, v5, Lg4/l;->b:I",
     "    invoke-static {}, Lcom/navi/tool/amapflying/receiver/LunaMusicLyricReceiver;->modSourceTag()I\n\n"
     "    move-result v6\n\n    iput v6, v5, Lg4/l;->b:I", "歌词事件音源号"),
]

# 通用音源(source=6)的联网兜底搜词：拿「歌名 歌手」去 search.kuwo.cn 搜 rid，再取歌词。
# 搜到同名不同版本就串台，每首歌还多两轮请求，整段删掉。
LYRIC_SEARCH_THREAD = (
    "    new-instance p1, Ljava/lang/Thread;\n\n"
    "    new-instance v2, Lx0/n;\n\n"
    "    const/4 v3, 0x3\n\n"
    "    invoke-direct {v2, p0, v0, v1, v3}, Lx0/n;-><init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I)V\n\n"
    "    invoke-direct {p1, v2}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V\n\n"
    "    invoke-virtual {p1}, Ljava/lang/Thread;->start()V\n\n")

# 收藏：原方法整块是酷我专用（toggleFavorite + 回调 + 异常处理），换成广播通道的 3/4。
FAVORITE_OLD_SIG = "toggleKuwoFavorite(Landroid/content/Context;ZLandroid/content/SharedPreferences;Lcom/navi/tool/ui/extra/DesktopAssistantMusicUiHelper$FavoriteCallback;)V"
FAVORITE_NEW = """\
.method private static toggleKuwoFavorite(Landroid/content/Context;ZLandroid/content/SharedPreferences;Lcom/navi/tool/ui/extra/DesktopAssistantMusicUiHelper$FavoriteCallback;)V
    .registers 5

    # [MOD] 收藏走第三方网易云：controlType 3 = 收藏、4 = 取消
    invoke-static {p0}, Li4/a;->c(Landroid/content/Context;)Li4/a;

    move-result-object v0

    const/4 v1, 0x4

    if-eqz p1, :mod_like

    const/4 v1, 0x3

    :mod_like
    invoke-virtual {v0, v1}, Li4/a;->b(I)V

    if-eqz p3, :mod_done

    invoke-interface {p3, p1}, Lcom/navi/tool/ui/extra/DesktopAssistantMusicUiHelper$FavoriteCallback;->onSuccess(Z)V

    :mod_done
    return-void
.end method"""

# 「打开内置歌词页」的入口。所有进内置歌词页的路径都会走到这里，一处拦全部：
#   lambda$initViews$57   点歌名
#   lambda$initViews$53   LyricsAdapter$OnLineClickListener 点歌词行（a0.smali:112）
#   access$3800           $9 广播唤起
# 原来只改了 57，点歌词行仍走 53 直接开出 3D 歌词页——这是上一版没生效的原因。
OPEN_OLD = """\
.method private openLyricsFxPage()V
    .registers 14

    invoke-direct {p0}, Lcom/navi/tool/ui/extra/DesktopAssistantActivity;->closeSurfaceForLyricsPage()V"""

OPEN_NEW = """\
.method private openLyricsFxPage()V
    .registers 14

    # [MOD] 进内置歌词页（含 3D 歌词）之前先跳第三方网易云的大歌词屏，
    # 跳不过去（没装 / 被系统拦）才走原逻辑，不打断桌面。
    invoke-static {p0}, Lcom/navitool/bridge/MusicJump;->openCloudLyrics(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :mod_lyrics_builtin

    return-void

    :mod_lyrics_builtin
    invoke-direct {p0}, Lcom/navi/tool/ui/extra/DesktopAssistantActivity;->closeSurfaceForLyricsPage()V"""


# ---------------------------------------------------------------- 实现

def _read(decoded, rel):
    p = Path(decoded) / rel
    if not p.exists():
        raise RuntimeError(f"找不到 {rel}")
    return p


def _method_span(src, sig, rel):
    m = re.search(r"\.method[^\n]*" + re.escape(sig), src)
    if not m:
        raise RuntimeError(f"{rel}: 方法签名没找到 {sig}")
    if len(re.findall(r"\.method[^\n]*" + re.escape(sig), src)) != 1:
        raise RuntimeError(f"{rel}: 方法签名命中多处 {sig}")
    start = m.start()
    end = src.find(".end method", m.end())
    if end < 0:
        raise RuntimeError(f"{rel}: {sig} 没有 .end method")
    return start, end + len(".end method")


def _edit_span(src, rel, sig, pairs, tag):
    """在方法块（sig 为 None 时整文件）内做定点替换，每对必须命中指定次数。"""
    if sig is None:
        start, end = 0, len(src)
    else:
        start, end = _method_span(src, sig, rel)
    block = src[start:end]
    for old, new, want in pairs:
        got = block.count(old)
        if got != want:
            raise RuntimeError(f"[{tag}] {rel} 命中 {got} 次，期望 {want} 次：{old.strip()[:60]}")
        block = block.replace(old, new)
    return src[:start] + block + src[end:]


def apply_music_patches(decoded, inject=True):
    """返回逐条结果 [(tag, 详情)]，任何一条对不上直接抛错。"""
    decoded = Path(decoded)
    results = []
    cache = {}

    # 汽水广播客户端的混淆名每版会洗牌：19.10 是 Li4/a;，18.62 是 Lk4/a;（方法集完全一致：
    # c 取实例 / b 发控制 / g 起 / h 停 / f 刷状态）。按方法特征探测，不认死类名。
    luna = LUNA
    for cand in ("Li4/a;", "Lk4/a;"):
        probe_file = decoded / "smali" / (cand[1:-1] + ".smali")
        if probe_file.exists():
            probe = probe_file.read_text(encoding="utf-8", errors="ignore")
            if ("->c(Landroid/content/Context;)" in probe
                    and "->h()V" in probe and "->b(I)V" in probe):
                luna = cand
                break

    def L(text):
        """把模板里写死的 Li4/a; 换成这一版实际用的类描述符。"""
        return text.replace(LUNA, luna) if luna != LUNA else text

    # 事件类（原厂播放器事件 x/m 的 l:I 字段）的混淆包名每版会变：19.10 是 g4，18.62 是 h4。
    # 两个包名在包里同时存在，所以不能按目录猜，直接看接收器里实际引用的是哪一个。

    def load(rel):
        if rel not in cache:
            cache[rel] = _read(decoded, rel).read_text(encoding="utf-8")
        return cache[rel]

    def save():
        for rel, s in cache.items():
            _read(decoded, rel).write_text(s, encoding="utf-8")

    # 1) 循环模式先处理掉：它要现算 5+mode，不能走通用替换
    s = load(HELPER)
    start, end = _method_span(s, "dispatchLoopMode(Landroid/content/Context;II)V", HELPER)
    block = s[start:end]
    block2, n = re.subn(L(LOOP_OLD), L(LOOP_NEW), block)
    if n != 2:
        raise RuntimeError(f"[循环模式] 命中 {n} 处，期望 2 处")
    cache[HELPER] = s[:start] + block2 + s[end:]
    results.append(("循环模式 1/4 -> 5/6/7", "2 处"))

    # 2) 按音源分流的方法块：通用替换
    for t in SWAP_TARGETS:
        s = load(t["file"])
        sig = t["method"]
        # 混淆名各版本会洗牌（18.62 的悬浮窗启播是 m(I)V，19.10 是 o(I)V），
        # 主名不在就用候选名，改的还是同一个方法块。
        if t.get("method_alt") and not re.search(r"\.method[^\n]*" + re.escape(sig), s):
            sig = t["method_alt"]
        start, end = _method_span(s, sig, t["file"])
        block = s[start:end]
        total = 0
        for pat, rep in SWAPS:
            block, n = re.subn(L(pat), L(rep), block)
            total += n
        if total != t["expect"]:
            raise RuntimeError(f"[{t['tag']}] 替换 {total} 处，期望 {t['expect']} 处")
        if re.search(r"kuwo/(?:New)?KuwoMusicManager", block):
            raise RuntimeError(f"[{t['tag']}] 方法里还有酷我调用，没换干净")
        cache[t["file"]] = s[:start] + block + s[end:]
        results.append((t["tag"], f"{total} 处"))

    # 2b) 「停全部音源」的两个方法里，酷我两条分支换完会和原有的汽水分支连成三次 h()。
    #     refcount 进去会自己夹到 0，不算错，但没必要减三次，合并成一次。
    one = (r"    invoke-static \{p0\}, " + luna + r"->c\(Landroid/content/Context;\)" + luna +
           r"\n\n    move-result-object v0\n\n    invoke-virtual \{v0\}, " + luna + r"->h\(\)V\n\n")
    dedupe = re.compile(r"(?P<blk>" + one + r")(?:" + one + r")+")
    for sig in ("stopMusicManagers(Landroid/content/Context;)V",
                "stopAllMusicServices(Landroid/app/Activity;Landroid/widget/TextView;Landroid/widget/TextView;"
                "Landroid/widget/TextView;Landroid/widget/TextView;Lcom/navi/tool/ui/extra/LyricsAdapter;"
                "Landroid/widget/ImageButton;Lcom/navi/tool/view/SpectrumView;)V"):
        s = load(HELPER)
        start, end = _method_span(s, sig, HELPER)
        block, n = dedupe.subn(r"\g<blk>", s[start:end])
        if n != 1:
            raise RuntimeError(f"[停音源去重] {sig.split('(')[0]} 命中 {n} 处，期望 1 处")
        cache[HELPER] = s[:start] + block + s[end:]
    results.append(("停音源只减一次引用", "2 个方法"))

    # 3) 收藏整方法替换
    s = load(HELPER)
    start, end = _method_span(s, FAVORITE_OLD_SIG, HELPER)
    cache[HELPER] = s[:start] + L(FAVORITE_NEW) + s[end:]
    results.append(("收藏走网易云", "整方法"))

    # 4) 定点文本替换
    for rel, sig, tag, old, new, want in TEXT_EDITS:
        cache[rel] = _edit_span(load(rel), rel, sig, [(old, new, want)], tag)
        results.append((tag, "1 处"))

    # 5) 事件音源号（必须在 TEXT_EDITS 之后，兜底标题那条也在这个文件里）
    s = load(LYRIC_RX)
    _evt = re.search(r"L([a-z0-9]{1,3})/m;->l:I", s)
    evt = _evt.group(1) if _evt else "g4"
    for old, new, tag in TAG_SITES:
        old = old.replace("Lg4/", "L%s/" % evt)
        new = new.replace("Lg4/", "L%s/" % evt)
        if s.count(old) != 1:
            raise RuntimeError(f"[{tag}] 命中 {s.count(old)} 次，期望 1 次")
        s = s.replace(old, new)
    if ".method public static modSourceTag()I" not in s:
        s = s.rstrip("\n") + "\n\n" + TAG_METHOD
    cache[LYRIC_RX] = s
    results.append(("事件音源号跟随当前音源", "2 处 + 1 个方法"))

    # 6) 通用音源联网搜词（MediaSession 回调 h(MediaMetadataCompat)：19.10 是 j4/e，18.62 是 l4/e；
    #    线程包装类同理 x0/n -> y0/n）
    media = MEDIA_SESSION
    thread_cls = "Lx0/n;"
    for mrel, trel in (("smali/j4/e.smali", "Lx0/n;"), ("smali/l4/e.smali", "Ly0/n;")):
        mf = decoded / mrel
        if mf.exists() and "->h(Landroid/support/v4/media/MediaMetadataCompat;)V" in mf.read_text(
                encoding="utf-8", errors="ignore"):
            media = mrel
            thread_cls = trel
            break
    thr = LYRIC_SEARCH_THREAD.replace("Lx0/n;", thread_cls)
    s = load(media)
    start, end = _method_span(s, "h(Landroid/support/v4/media/MediaMetadataCompat;)V", media)
    block = s[start:end]
    if block.count(thr) != 1:
        raise RuntimeError("[通用音源搜词] 线程块没对上")
    cache[media] = s[:start] + block.replace(thr, "") + s[end:]
    results.append(("断掉通用音源搜词", "1 处"))

    # 7) 内置歌词页入口（依赖注入的 MusicJump，纯 mod 包不加）
    if inject:
        s = load(ACTIVITY)
        if not re.search(r"\.method[^\n]*openLyricsFxPage\(\)V", s):
            # 18.62 没有 3D 歌词页，也就没有这个入口；不硬塞，记一笔跳过
            results.append(("内置歌词页入口", "跳过：本版无 openLyricsFxPage"))
        else:
            start, end = _method_span(s, "openLyricsFxPage()V", ACTIVITY)
            head = s[start:start + len(OPEN_OLD)]
            if head != OPEN_OLD:
                raise RuntimeError("[歌词页入口] 方法头与预期不一致")
            cache[ACTIVITY] = s[:start] + OPEN_NEW + s[start + len(OPEN_OLD):]
            results.append(("内置歌词页先跳第三方网易云", "openLyricsFxPage 入口 1 处"))

    save()

    # 8) 死引用修正（只对 18.62 这类「Li4/a 是 AMap Runnable」的版本生效）
    #    18.62 的 smali/i4/a 只有 <init> 和 run()，但原厂代码里有 54 处按「汽水客户端」
    #    调它的 c(Context)/b(I)/g()/h()/f()。这些调用一执行就 NoSuchMethodError——
    #    「悬浮音乐」打开后 initMusicService → updateLoopModeState 正好走到，表现就是闪退。
    #    18.62 真正的客户端是 Lk4/a（方法集完整），所以把这几类调用整体改指过去；
    #    new-instance / <init>(BroadcastReceiver,Context,int) 是 AMap 的 Runnable 用法，保持不动。
    i4a = decoded / "smali" / "i4" / "a.smali"
    if i4a.exists() and "->c(Landroid/content/Context;)" not in i4a.read_text(
            encoding="utf-8", errors="ignore"):
        rules = [
            ("Li4/a;->c(Landroid/content/Context;)Li4/a;",
             "Lk4/a;->c(Landroid/content/Context;)Lk4/a;"),
            ("Li4/a;->b(I)V", "Lk4/a;->b(I)V"),
            ("Li4/a;->g()V", "Lk4/a;->g()V"),
            ("Li4/a;->h()V", "Lk4/a;->h()V"),
            ("Li4/a;->f()V", "Lk4/a;->f()V"),
        ]
        dead = 0
        for d in decoded.glob("smali*"):
            if not d.is_dir():
                continue
            for f in d.rglob("*.smali"):
                t = f.read_text(encoding="utf-8", errors="ignore")
                if "Li4/a;->" not in t:
                    continue
                n = 0
                for old, new in rules:
                    c = t.count(old)
                    if c:
                        t = t.replace(old, new)
                        n += c
                if n:
                    f.write_text(t, encoding="utf-8")
                    dead += n
        if dead < 40:
            raise RuntimeError(f"[死引用修正] 只改了 {dead} 处，期望 54 处左右，检查版本假设是否成立")
        results.append(("Li4/a 死引用改指 k4/a", f"{dead} 处"))

    return results
