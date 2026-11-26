package com.sagittarius.v6.b;

import android.content.Context;
import android.os.Build;
import com.sagittarius.v6.A;
import com.sagittarius.v6.AppInfo;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public static String f6115a;

    /* renamed from: b, reason: collision with root package name */
    private static String f6116b;

    /* renamed from: c, reason: collision with root package name */
    private static String f6117c;

    /* renamed from: d, reason: collision with root package name */
    private static String f6118d;

    /* renamed from: e, reason: collision with root package name */
    private static int f6119e;

    /* renamed from: f, reason: collision with root package name */
    private static List f6120f;

    /* renamed from: g, reason: collision with root package name */
    private static List f6121g;

    /* renamed from: h, reason: collision with root package name */
    private static final String[] f6122h = null;

    static {
        b();
        f6118d = null;
        f6115a = null;
        f6120f = new ArrayList();
        f6121g = null;
        f6115a = f6122h[2];
        f6118d = f6122h[3];
        f6116b = f6122h[4] + AppInfo.f6106h;
        f6117c = f6122h[5];
        f6119e = f6118d.length();
    }

    private static File a(Context context, File file, String str) {
        File file2 = new File(file, f6116b);
        try {
            a(file2);
        } catch (IOException e7) {
            file2 = new File(context.getFilesDir(), f6116b);
            a(file2);
        }
        File file3 = new File(file2, str);
        a(file3);
        return file3;
    }

    private static ClassLoader a(Context context) {
        try {
            ClassLoader classLoader = context.getClassLoader();
            if (Build.VERSION.SDK_INT >= 14) {
                if (classLoader instanceof BaseDexClassLoader) {
                    return classLoader;
                }
            } else if ((classLoader instanceof DexClassLoader) || (classLoader instanceof PathClassLoader)) {
                return classLoader;
            }
            return null;
        } catch (RuntimeException e7) {
            return null;
        }
    }

    private static String a(String str, String str2) {
        byte[] a7 = com.sagittarius.v6.a.a.a(str);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str2.toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        return sb.toString();
    }

    public static Enumeration a(DexFile dexFile) {
        boolean z6;
        Enumeration enumeration;
        Enumeration<String> entries = dexFile.entries();
        String name = A.class.getName();
        while (true) {
            if (!entries.hasMoreElements()) {
                z6 = false;
                break;
            }
            if (entries.nextElement().startsWith(name)) {
                z6 = true;
                break;
            }
        }
        if (!z6) {
            return dexFile.entries();
        }
        synchronized (f6120f) {
            if (f6121g == null) {
                ArrayList arrayList = new ArrayList();
                Enumeration<String> entries2 = dexFile.entries();
                while (entries2.hasMoreElements()) {
                    String nextElement = entries2.nextElement();
                    if (!nextElement.startsWith(f6122h[0])) {
                        arrayList.add(nextElement);
                    }
                }
                Iterator it = f6120f.iterator();
                while (it.hasNext()) {
                    Enumeration<String> entries3 = ((DexFile) it.next()).entries();
                    while (entries3.hasMoreElements()) {
                        arrayList.add(entries3.nextElement());
                    }
                }
                f6121g = arrayList;
            }
            enumeration = Collections.enumeration(f6121g);
        }
        return enumeration;
    }

    public static void a(Context context, String str) {
        ClassLoader a7;
        try {
            String[] split = str.split(f6122h[6]);
            ArrayList arrayList = new ArrayList();
            for (String str2 : split) {
                arrayList.add(new File(str2));
            }
            if (arrayList.isEmpty() || (a7 = a(context)) == null) {
                return;
            }
            File a8 = a(context, new File(AppInfo.DATAPATH), f6117c);
            if (Build.VERSION.SDK_INT >= 23) {
                o.a(a7, arrayList, a8);
            } else if (Build.VERSION.SDK_INT >= 19) {
                n.a(a7, arrayList, a8);
            } else if (Build.VERSION.SDK_INT >= 14) {
                i.a(a7, arrayList);
            } else {
                q.a(a7, arrayList);
            }
            try {
                b.a(a7, new File(String.format(f6122h[7], AppInfo.DATAPATH, AppInfo.f6106h)));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Exception e7) {
            throw new RuntimeException(f6122h[8] + e7.getMessage() + f6122h[9]);
        }
    }

    public static void a(Context context, ByteBuffer byteBuffer) {
        ClassLoader a7 = a(context);
        if (a7 == null) {
            throw new RuntimeException(f6122h[11]);
        }
        if (Build.VERSION.SDK_INT < 26) {
            throw new RuntimeException(f6122h[10]);
        }
        try {
            p.a(a7, byteBuffer);
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public static void a(Context context, ByteBuffer[] byteBufferArr) {
        ClassLoader a7 = a(context);
        if (a7 == null) {
            throw new RuntimeException(f6122h[13]);
        }
        if (Build.VERSION.SDK_INT < 26) {
            throw new RuntimeException(f6122h[12]);
        }
        try {
            p.a(a7, byteBufferArr);
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    private static void a(File file) {
        file.mkdir();
        if (file.isDirectory()) {
            return;
        }
        file.getParentFile();
        throw new IOException(f6122h[14] + file.getPath());
    }

    static /* synthetic */ void a(Object obj, String str, Object[] objArr) {
        Field a7 = s.a(obj, str);
        Object[] objArr2 = (Object[]) a7.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        a7.set(obj, objArr3);
    }

    static /* synthetic */ void a(Object[] objArr) {
        try {
            Field a7 = s.a(objArr[0], f6122h[1]);
            for (Object obj : objArr) {
                f6120f.add((DexFile) a7.get(obj));
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private static void b() {
        String[] strArr = new String[15];
        f6122h = strArr;
        strArr[0] = a("FQ4GYjoXBgI4PRcTAjk6WA==", "vakLI");
        f6122h[1] = a("FgcMBy4eBw==", "rbtAG");
        f6122h[2] = a("bQZjNDA7", "CoMPU");
        f6122h[3] = a("fh4ZPQ==", "PtxOv");
        f6122h[4] = a("VA==", "zaLOB");
        f6122h[5] = a("eA==", "ILYCi");
        f6122h[6] = a("cQ==", "JlyWs");
        f6122h[7] = a("SxxrXGgdQCgbLw==", "noDrM");
        f6122h[8] = a("PhgNOiI3CBluIh0eFS8nHwwVJyQdTQcvIh8IBW5j", "smaNK");
        f6122h[9] = a("UUI=", "xlzSp");
        f6122h[10] = a("ISQjEjAkJhkIHC0nPxQoDC8oXAQmOSUWISc4JAM1aDo8ByUuJSIL", "HJPfQ");
        f6122h[11] = a("PSUdLAQ4Jyc2KDEmASocEC4WYiY4Kh0rKTsqCj0XdCIdeAshJwI=", "TKnXe");
        f6122h[12] = a("HisrPwIbKRElLhIoNzkaMyAgcTYZNi07Exg3LC4HVzU0KhcRKiom", "wEXKc");
        f6122h[13] = a("LQcHPRAoBT0nPCEEGzsIAAwMczIoCAc6PSsIECwDZAAHaR8xBRg=", "DitIq");
        f6122h[14] = a("Bw4CABclTx8DUiIdDg0GJE8PBQAkDB8DADhP", "Aoklr");
    }
}
