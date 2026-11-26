package com.sagittarius.v6.b;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.sagittarius.v6.AppInfo;
import com.sagittarius.v6.StubApplication;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    private static Context f6136a;

    /* renamed from: b, reason: collision with root package name */
    private static Application f6137b;

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f6138c = null;

    static {
        g();
        f6137b = null;
        f6136a = null;
    }

    public static String a() {
        try {
            return (String) Class.forName(f6138c[3]).getDeclaredMethod(Build.VERSION.SDK_INT >= 18 ? f6138c[4] : f6138c[5], new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e7) {
            return f6138c[6];
        }
    }

    public static String a(String str) {
        if (Build.VERSION.SDK_INT < 17) {
            return f6138c[1] + AppInfo.PKGNAME;
        }
        try {
            new File(str).listFiles();
            return str;
        } catch (Exception e7) {
            return f6138c[2] + AppInfo.PKGNAME;
        }
    }

    public static void a(Application application) {
        if (StubApplication.mRealApplicationPatched || StubApplication.mRealApplication == null) {
            return;
        }
        StubApplication.mRealApplicationPatched = true;
        g.a(f6136a, application, StubApplication.mRealApplication);
    }

    public static void a(Application application, Context context) {
        f6137b = application;
        f6136a = context;
    }

    public static void a(Context context) {
        try {
            new Thread(new u(context)).start();
        } catch (Exception e7) {
        }
    }

    private static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(28:0|1|(1:3)|4|5|(3:7|8|(4:10|11|12|13)(1:18))|20|21|23|24|25|26|27|28|29|(2:30|(3:32|33|34)(1:48))|49|(1:51)|53|54|55|38|39|40|11|12|13|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c0, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b9, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00bd, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00be, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b5, code lost:
    
        r0 = null;
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x009c, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x009d, code lost:
    
        r0 = null;
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.lang.String r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 196
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sagittarius.v6.b.t.a(java.lang.String, java.lang.String):boolean");
    }

    private static String b(String str, String str2) {
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

    public static void b() {
        String str;
        if (Build.VERSION.SDK_INT >= 30 || ((AppInfo.SUPPORT_ARCH != null && AppInfo.SUPPORT_ARCH.contains(f6138c[10])) || !e())) {
            if ((AppInfo.FLAGS & 65536) != 0) {
                f();
                return;
            } else {
                System.loadLibrary(AppInfo.LIBNAME);
                return;
            }
        }
        String str2 = f6138c[16] + AppInfo.LIBNAME + f6138c[17];
        if (d()) {
            str = f6138c[18] + AppInfo.LIBNAME + f6138c[19];
        } else {
            str = f6138c[20] + AppInfo.LIBNAME + f6138c[21];
        }
        String str3 = (AppInfo.DATAPATH + f6138c[22]) + File.separator + str2;
        a(str, str3);
        System.load(str3);
    }

    public static void b(Application application) {
        if (StubApplication.mRealApplication != null) {
            g.b(f6136a, application, StubApplication.mRealApplication);
        }
    }

    public static void c() {
        boolean z6 = true;
        if ((AppInfo.FLAGS & 64) != 0 && Build.VERSION.SDK_INT >= 23 && (Build.BRAND.equalsIgnoreCase(f6138c[23]) || Build.BRAND.equalsIgnoreCase(f6138c[24]))) {
            z6 = false;
        }
        if (z6) {
            return;
        }
        File[] listFiles = new File(AppInfo.DATAPATH + f6138c[25]).listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file : listFiles) {
            if (!file.isDirectory()) {
                SharedPreferences sharedPreferences = f6137b.getSharedPreferences(file.getName().replace(f6138c[26], f6138c[27]), 0);
                try {
                    Class<?> cls = sharedPreferences.getClass();
                    Field declaredField = cls.getDeclaredField(f6138c[28]);
                    declaredField.setAccessible(true);
                    if (declaredField.getBoolean(sharedPreferences)) {
                        declaredField.set(sharedPreferences, Boolean.FALSE);
                        synchronized (sharedPreferences) {
                            try {
                                Method declaredMethod = cls.getDeclaredMethod(f6138c[29], new Class[0]);
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(sharedPreferences, new Object[0]);
                            } catch (Exception e7) {
                                Method declaredMethod2 = cls.getDeclaredMethod(f6138c[30], new Class[0]);
                                declaredMethod2.setAccessible(true);
                                declaredMethod2.invoke(sharedPreferences, new Object[0]);
                            }
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e8) {
                }
            }
        }
    }

    private static boolean d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f6138c[7])));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (readLine.endsWith(f6138c[8])) {
                    bufferedReader.close();
                    return true;
                }
            }
        } catch (Exception e7) {
        }
        return false;
    }

    private static boolean e() {
        boolean z6 = false;
        try {
            byte[] bArr = new byte[20];
            FileInputStream fileInputStream = new FileInputStream(f6138c[9]);
            fileInputStream.read(bArr, 0, 20);
            if (bArr[18] == 3) {
                fileInputStream.close();
                z6 = true;
            } else {
                fileInputStream.close();
            }
        } catch (Exception e7) {
        }
        return z6;
    }

    private static void f() {
        String[] strArr = Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        String str = AppInfo.DATAPATH + f6138c[11];
        for (String str2 : strArr) {
            String str3 = f6138c[12] + AppInfo.LIBNAME + f6138c[13] + str2;
            String str4 = str + File.separator + (f6138c[14] + AppInfo.LIBNAME + f6138c[15]);
            if (a(str3, str4)) {
                try {
                    System.load(str4);
                    return;
                } catch (Exception e7) {
                }
            }
        }
    }

    private static void g() {
        String[] strArr = new String[31];
        f6138c = strArr;
        strArr[0] = b("QBsaOSI=", "nwuZI");
        f6138c[1] = b("ShUJIgBKFQkiAEo=", "eqhVa");
        f6138c[2] = b("QA4SATlADhIBOUA=", "ojsuX");
        f6138c[3] = b("MyQRJzs7Lls0JCJkNDYgOzwcIS0GIgcwNTY=", "RJuUT");
        f6138c[4] = b("EjQQOQgfNTI5AhIkETgjECwH", "qAbKm");
        f6138c[5] = b("JTcfKCooNj07LC0jCj8BJy8I", "FBmZO");
        f6138c[6] = b(h0.FRAGMENT_ENCODE_SET, "wmRSO");
        f6138c[7] = b("WSAYAihZIw8BLVk9Cx04", "vPjmK");
        f6138c[8] = b("bQcTPyYnGUUuOyxbBiU8KREYemY=", "BtjLR");
        f6138c[9] = b("bAkpHj4mF38PIy1VPAQkKB8i", "CzPmJ");
        f6138c[10] = b("EWtf", "iSiSJ");
        f6138c[11] = b("QmQUIDwBIxc=", "mJuRQ");
        f6138c[12] = b("PgYv", "RoMUM");
        f6138c[13] = b("Ew==", "LiAmI");
        f6138c[14] = b("NgQE", "Zmfkx");
        f6138c[15] = b("TTkM", "cJcoV");
        f6138c[16] = b("AgUH", "nleis");
        f6138c[17] = b("Qz8r", "mLDft");
        f6138c[18] = b("FTEJ", "yXkOm");
        f6138c[19] = b("LChCcgZFZA==", "sPzDY");
        f6138c[20] = b("Dz8z", "cVQSz");
        f6138c[21] = b("HjJXWw==", "AJomu");
        f6138c[22] = b("fVkrUXw+HjE=", "RwSiJ");
        f6138c[23] = b("EC0VMxwx", "XXtDy");
        f6138c[24] = b("LAcZBR8=", "dHWJM");
        f6138c[25] = b("RjwONiYMKzknJgwpFQ==", "iOfWT");
        f6138c[26] = b("YTQvCQ==", "OLBeT");
        f6138c[27] = b(h0.FRAGMENT_ENCODE_SET, "QAdCY");
        f6138c[28] = b("NT0WKDA9FQ==", "XqyIT");
        f6138c[29] = b("OxksLBAlGSAMPyQdASc1PBMp", "WvMHV");
        f6138c[30] = b("GCAEBhUGIAgmOgck", "tOebS");
    }
}
