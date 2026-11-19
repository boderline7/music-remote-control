#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
add_libdex.py —— 把原包里的「库 dex」补回重打包后的 APK

背景：
  原包是百度加固。它的 classes.dex 并不是壳，而是「库 dex」
  （androidx 694 类 + com.google 508 类 + android/support 61 类 + 加固壳 32 类），
  业务代码另外加密放在 assets/baiduprotect*.jar 里运行时加载。

  脱壳工具通常只 dump 业务代码，于是得到的源码/ dex 里完全没有 androidx。
  如果直接拿脱壳 dex 重打包，会缺 AppCompatActivity / Toolbar / AppCompatDelegate
  等父类与库类，启动即 NoClassDefFoundError 闪退。

本脚本把原包 classes.dex 原样作为 classes4.dex 追加进新包（字节不动，
所以库版本与业务代码 100% 匹配；已确认两侧类名零冲突）。

用法：
  python add_libdex.py <原版APK> <待补的APK> <输出APK>
示例：
  python add_libdex.py 车机助手正式版_V18.49_sign.apk unsigned.apk merged.apk

之后仍需：
  zipalign -f -p 4 merged.apk aligned.apk
  apksigner sign ... --out final.apk aligned.apk
"""
import sys
import zipfile
from zipfile import ZipFile, ZIP_DEFLATED, ZipInfo


def dex_names(z):
    return sorted(n for n in z.namelist() if n.endswith(".dex") and "/" not in n)


def main():
    if len(sys.argv) != 4:
        print(__doc__)
        return 1
    orig_path, target_path, out_path = sys.argv[1:4]

    orig = ZipFile(orig_path)
    # 原包的 classes.dex 即库 dex
    if "classes.dex" not in orig.namelist():
        print("原包中没有 classes.dex")
        return 1
    libdex = orig.read("classes.dex")

    src = ZipFile(target_path)
    existing = dex_names(src)
    next_idx = len(existing) + 1
    new_name = "classes.dex" if next_idx == 1 else "classes%d.dex" % next_idx
    while new_name in src.namelist():
        next_idx += 1
        new_name = "classes%d.dex" % next_idx

    with ZipFile(out_path, "w", ZIP_DEFLATED) as out:
        for info in src.infolist():
            out.writestr(info, src.read(info.filename), compress_type=info.compress_type)
        zi = ZipInfo(new_name)
        zi.compress_type = ZIP_DEFLATED
        out.writestr(zi, libdex)

    z = ZipFile(out_path)
    print("已写入 %s (%d 字节)" % (new_name, len(libdex)))
    print("最终 dex:", dex_names(z))
    print("总条目:", len(z.namelist()))
    return 0


if __name__ == "__main__":
    sys.exit(main())
