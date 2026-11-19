package com.navitool.bridge;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 配置预置（离线自洽）：
 * 进程启动时把 APK assets/navi_configs/ 里的配置包解压到 /sdcard/navitool/，免云端下发。
 * 内含瑞虎9 联动配置 + 7 个通用配置（情景/提示音/免唤醒/语音搜/手势/图标/语音包）。
 * 这 8 套之间无同名冲突，直接平铺即可。其它车型的配置不进包。
 * 只首启解压一次（标记文件存在即跳过），不覆盖用户之后手动改过的配置。
 *
 * 作为 ContentProvider 注入：Android 进程启动时自动实例化，无需改原 app 任何代码。
 */
public final class ConfigAssets extends ContentProvider {

    private static final String ASSETS_DIR = "navi_configs";
    private static final String MARK = "navitool/.config_installed_v1";

    private static final String[] PACKS = {
            "HU90325",        // 瑞虎9 联动配置
            "0421Scenefile",  // 情景模式
            "0317carlink",    // 车联提示音
            "MHX0604",        // 通用免唤醒
            "YYSS0730",       // 语音搜索收藏歌曲
            "YGGestures0328", // 双指/三指手势
            "ios_icon",       // IOS 风格图标
            "Crazymp30328"    // 疯狂动物城语音包
    };

    @Override
    public boolean onCreate() {
        // 异步解压，避免 ContentProvider 主线程阻塞触发 ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                install();
            }
        }, "cfg-install").start();
        return true;
    }

    private void install() {
        Context ctx = getContext();
        if (ctx == null) {
            return;
        }
        File sd = Environment.getExternalStorageDirectory();
        File mark = new File(sd, MARK);
        if (mark.exists()) {
            Bridge.note("config: already installed, skip");
            return;
        }
        // 目标根是 /sdcard 本身：配置 zip 内路径已带 navitool/ 前缀，
        // 直接解压到 sd 才得到 /sdcard/navitool/profile/...，否则会多一层 navitool/navitool。
        File root = sd;
        Bridge.note("config: sd=" + sd.getAbsolutePath() + " exists=" + sd.exists()
                + " writable=" + sd.canWrite());
        int n = 0;
        try {
            for (String name : PACKS) {
                try {
                    n += unzip(ctx, name, root);
                } catch (Throwable e) {
                    Bridge.note("config: unzip " + name + " failed " + Bridge.root(e));
                }
            }
            Bridge.note("config: unzipped " + n + " files, writing mark");
            FileOutputStream fos = new FileOutputStream(mark);
            fos.write("v1".getBytes("UTF-8"));
            fos.close();
            Bridge.note("config: installed " + n + " files");
        } catch (Throwable t) {
            Bridge.note("config: install failed " + Bridge.root(t));
        }
    }

    /** 把一个 assets 包解压到 dest，返回写入文件数 */
    private int unzip(Context ctx, String name, File dest) throws Exception {
        InputStream in = ctx.getAssets().open(ASSETS_DIR + "/" + name + ".zip");
        ZipInputStream zis = new ZipInputStream(in);
        int count = 0;
        byte[] buf = new byte[8192];
        try {
            ZipEntry e;
            while ((e = zis.getNextEntry()) != null) {
                if (e.isDirectory()) {
                    zis.closeEntry();
                    continue;
                }
                File f = new File(dest, e.getName());
                File p = f.getParentFile();
                if (p != null && !p.exists()) {
                    p.mkdirs();
                }
                OutputStream os = new FileOutputStream(f);
                int len;
                while ((len = zis.read(buf)) > 0) {
                    os.write(buf, 0, len);
                }
                os.close();
                zis.closeEntry();
                count++;
            }
        } finally {
            zis.close();
        }
        return count;
    }

    // ---- ContentProvider 要求的空实现 ----
    @Override
    public Cursor query(Uri uri, String[] p, String s, String[] a, String o) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues v) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] a) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues v, String s, String[] a) {
        return 0;
    }
}
