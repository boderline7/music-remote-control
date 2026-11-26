package cn.kuwo.autosdk;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v {

    /* renamed from: b, reason: collision with root package name */
    public static final String f3138b;

    /* renamed from: c, reason: collision with root package name */
    public static final String f3139c;

    /* renamed from: d, reason: collision with root package name */
    public static final String f3140d;

    /* renamed from: e, reason: collision with root package name */
    public static final String f3141e;

    /* renamed from: f, reason: collision with root package name */
    public static final String[] f3142f;

    /* renamed from: a, reason: collision with root package name */
    public Random f3143a;

    static {
        StringBuilder sb = new StringBuilder();
        String g7 = a.a.g(t1.b.f9024a, "/kwmusiccar/Cache");
        if (!TextUtils.isEmpty(g7)) {
            String str = File.separator;
            if (!g7.endsWith(str)) {
                g7 = a.a.g(g7, str);
            }
        }
        File file = new File(g7);
        if (!file.exists()) {
            file.mkdirs();
        }
        String i7 = a.a.i(sb, g7, "CacheMgr/");
        f3138b = i7;
        f3139c = "_yyyy_MM_dd_HH_mm_ss";
        f3140d = ".tmp";
        f3141e = ".delay";
        f3142f = new String[]{".tmp"};
        t1.i.c(i7);
    }

    public static String a(int i7, int i8, String str) {
        t1.f fVar = new t1.f();
        fVar.setTime((i7 * i8 * 1000) + fVar.getTime());
        String str2 = str.hashCode() + new SimpleDateFormat(f3139c, Locale.CHINA).format((Date) fVar) + f3140d;
        StringBuilder sb = new StringBuilder();
        sb.append(f3138b);
        sb.append(t.CATEGORY_LYRICS);
        return a.a.i(sb, File.separator, str2);
    }

    public static void b(int i7, int i8, String str, String str2) {
        t1.i.c(f3138b + t.CATEGORY_LYRICS);
        File e7 = e(str);
        if (e7 != null) {
            String path = e7.getPath();
            if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                t1.i.b(e7.getPath());
            }
        }
        File file = new File(a(i7, i8, str));
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
        } catch (IOException e8) {
            e8.printStackTrace();
        }
    }

    public static File e(String str) {
        File[] fileArr;
        String i7 = a.a.i(new StringBuilder(), f3138b, t.CATEGORY_LYRICS);
        String str2 = str.hashCode() + "_\\d{4}.+\\.tmp";
        if (!TextUtils.isEmpty(i7) && !TextUtils.isEmpty(str2)) {
            File file = new File(i7);
            if (file.isDirectory()) {
                fileArr = file.listFiles(new t1.h(str2));
                if (fileArr != null || fileArr.length <= 0) {
                    return null;
                }
                return fileArr[0];
            }
        }
        fileArr = null;
        if (fileArr != null) {
        }
        return null;
    }

    public final void c(int i7, int i8, String str, byte[] bArr) {
        t1.i.c(f3138b + t.CATEGORY_LYRICS);
        File e7 = e(str);
        if (e7 != null) {
            String path = e7.getPath();
            if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                d(e7.getPath());
            }
        }
        String a7 = a(i7, i8, str);
        File file = new File(a7);
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
            } finally {
                fileOutputStream.close();
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            d(a7);
        }
    }

    public final void d(String str) {
        t1.i.b(str);
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                File file2 = new File(str);
                File[] fileArr = null;
                r1 = null;
                t1.g gVar = null;
                if (file2.isDirectory()) {
                    String[] strArr = f3142f;
                    if (strArr != null && strArr.length > 0) {
                        gVar = new t1.g(strArr);
                    }
                    fileArr = file2.listFiles(gVar);
                }
                for (File file3 : fileArr) {
                    d(file3.getPath());
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            String str2 = File.separator;
            int lastIndexOf = str.lastIndexOf(str2);
            String i7 = a.a.i(sb, lastIndexOf != -1 ? str.substring(0, lastIndexOf) : h0.FRAGMENT_ENCODE_SET, str2);
            StringBuilder sb2 = new StringBuilder();
            Random random = this.f3143a;
            sb2.append(random.nextInt());
            sb2.append(f3141e);
            String sb3 = sb2.toString();
            while (true) {
                String g7 = a.a.g(i7, sb3);
                if (!TextUtils.isEmpty(g7) && new File(g7).exists()) {
                    sb3 = random.nextInt() + sb3;
                }
            }
            file.renameTo(new File(sb3));
        }
    }
}
