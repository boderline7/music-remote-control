package com.sagittarius.v6.b;

import android.os.Build;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    private static Method f6132a;

    /* renamed from: b, reason: collision with root package name */
    private static Object f6133b;

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f6134c = null;

    static {
        b();
        f6133b = null;
        f6132a = null;
        if (Build.VERSION.SDK_INT > 27) {
            try {
                Method declaredMethod = Class.class.getDeclaredMethod(f6134c[0], String.class, Class[].class);
                Class<?> cls = Class.forName(f6134c[1]);
                Method method = (Method) declaredMethod.invoke(cls, f6134c[2], null);
                f6132a = (Method) declaredMethod.invoke(cls, f6134c[3], new Class[]{String[].class});
                f6133b = method.invoke(null, new Object[0]);
            } catch (Exception e7) {
            }
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

    public static void a() {
        a(f6134c[4]);
    }

    private static boolean a(String... strArr) {
        Method method;
        Object obj = f6133b;
        if (obj == null || (method = f6132a) == null) {
            return false;
        }
        try {
            method.invoke(obj, strArr);
            return true;
        } catch (Exception e7) {
            return false;
        }
    }

    private static void b() {
        String[] strArr = new String[5];
        f6134c = strArr;
        strArr[0] = a("FzMmETATOjMnMBQbNyE9HzI=", "pVRUU");
        f6134c[1] = a("HhUjADMRWjwPKQ4RIlgMNyY6GC4TGSo=", "ztOvZ");
        f6134c[2] = a("JA8OATItHhM+Ig==", "CjzSG");
        f6134c[3] = a("JCQQOTAzJQEfGCcoIQk8OjEQGDY5Mg==", "WAdqY");
        f6134c[4] = a("Ag==", "NSSgw");
    }
}
