package com.sagittarius.v6.b;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import com.sagittarius.v6.StubApplication;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6114a = null;

    static {
        a();
    }

    public static Application a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            return (Application) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            throw new IllegalStateException(th);
        }
    }

    private static Object a(Context context, Class cls) {
        if (cls == null) {
            try {
                cls = Class.forName(f6114a[1]);
            } catch (Throwable th) {
                return null;
            }
        }
        Method method = cls.getMethod(f6114a[2], new Class[0]);
        method.setAccessible(true);
        Object invoke = method.invoke(cls, new Object[0]);
        if (invoke != null || context == null) {
            return invoke;
        }
        Field field = context.getClass().getField(f6114a[3]);
        field.setAccessible(true);
        Object obj = field.get(context);
        Field declaredField = obj.getClass().getDeclaredField(f6114a[4]);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
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

    private static void a() {
        String[] strArr = new String[20];
        f6114a = strArr;
        strArr[0] = a("DT8TMhIECQYgFC8kCScUFD8=", "lKgSq");
        f6114a[1] = a("NAMuPSA8CWQuPyVDCyw7PBsjOzYBBTgqLjE=", "UmJOO");
        f6114a[2] = a("Ex4rKA8eHxg5HhkdMC4TJAMrPwsU", "pkYZj");
        f6114a[3] = a("LzQEOygnHCoqJw==", "BxkZL");
        f6114a[4] = a("JjExJzM9GSYqDiMCNzI+", "KpRSZ");
        f6114a[5] = a("IAg3GgooAn0JFTFIEgsRKBA6HBwVDiENBCU=", "AfShe");
        f6114a[6] = a("PTktIjg5ES8KPCAcKigtJBksJQ==", "PpCKL");
        f6114a[7] = a("MRkOOjksHw==", "BxcIL");
        f6114a[8] = a("PBYdNhchJx0zNTAjGDU4Ig==", "QWqZV");
        f6114a[9] = a("IAQwNgEnBDY6Gz0VIQEA", "SaDyt");
        f6114a[10] = a("FjgeGSMeMlQKPAd4NgQtEzMeKjwc", "wVzkL");
        f6114a[11] = a("FC8GJQwcJUw2EwVvIzQXHDcLIxohKRAyAhFlMjYAHiAFMiobJw0=", "uAbWc");
        f6114a[12] = a("LgQdKg4qJgwuCywr", "CEmZb");
        f6114a[13] = a("JTADKwYhEA==", "HbfXB");
        f6114a[14] = a("BwocDyEPIjIeLg==", "jFsnE");
        f6114a[15] = a("ORs7GyA1LD8L", "TKZxK");
        f6114a[16] = a("HRgfJhwFOBkwIxEpETQUFTk=", "pJzUs");
        f6114a[17] = a("OCILEA8lJA==", "KCfcz");
        f6114a[18] = a("Egk8JScaA3Y2OANJGTQ8GhExIzEnDyoyKRc=", "sgXWH");
        f6114a[19] = a("NDs4Aw4wEzorCikePwkbLRs5BA==", "YrVjz");
    }

    public static void a(Context context, Application application) {
        if (application != null) {
            try {
                Method declaredMethod = ContextWrapper.class.getDeclaredMethod(f6114a[0], Context.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(application, context);
            } catch (Throwable th) {
                throw new IllegalStateException(th);
            }
        }
    }

    public static void a(Context context, Application application, Application application2) {
        Class<?> cls;
        Field field;
        try {
            Class<?> cls2 = Class.forName(f6114a[5]);
            try {
                Object a7 = a(context, cls2);
                Field declaredField = cls2.getDeclaredField(f6114a[6]);
                declaredField.setAccessible(true);
                Application application3 = (Application) declaredField.get(a7);
                if (!Build.BRAND.equals(f6114a[7]) && application2 != null && application3 == application) {
                    declaredField.set(a7, application2);
                }
                if (application2 != null) {
                    Field declaredField2 = cls2.getDeclaredField(f6114a[8]);
                    declaredField2.setAccessible(true);
                    List list = (List) declaredField2.get(a7);
                    for (int i7 = 0; i7 < list.size(); i7++) {
                        if (list.get(i7) == application) {
                            list.set(i7, application2);
                        }
                    }
                    try {
                        Method declaredMethod = StubApplication.mContext.getClass().getDeclaredMethod(f6114a[9], Context.class);
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(StubApplication.mContext, application2);
                    } catch (Throwable th) {
                    }
                }
                try {
                    cls = Class.forName(f6114a[10]);
                } catch (ClassNotFoundException e7) {
                    cls = Class.forName(f6114a[11]);
                }
                Field declaredField3 = cls.getDeclaredField(f6114a[12]);
                declaredField3.setAccessible(true);
                cls.getDeclaredField(f6114a[13]).setAccessible(true);
                try {
                    field = Application.class.getDeclaredField(f6114a[14]);
                } catch (NoSuchFieldException e8) {
                    field = null;
                }
                for (int i8 = 0; i8 < 2; i8++) {
                    Field declaredField4 = cls2.getDeclaredField(new String[]{f6114a[15], f6114a[16]}[i8]);
                    declaredField4.setAccessible(true);
                    Iterator it = ((Map) declaredField4.get(a7)).entrySet().iterator();
                    while (it.hasNext()) {
                        Object obj = ((WeakReference) ((Map.Entry) it.next()).getValue()).get();
                        if (obj != null && declaredField3.get(obj) == application) {
                            if (application2 != null) {
                                declaredField3.set(obj, application2);
                            }
                            if (application2 != null && field != null) {
                                field.set(application2, obj);
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        } catch (Throwable th3) {
        }
    }

    public static void b(Context context, Application application, Application application2) {
        if (Build.BRAND.equals(f6114a[17])) {
            try {
                Class<?> cls = Class.forName(f6114a[18]);
                Object a7 = a(context, cls);
                Field declaredField = cls.getDeclaredField(f6114a[19]);
                declaredField.setAccessible(true);
                Application application3 = (Application) declaredField.get(a7);
                if (application2 == null || application3 != application) {
                    return;
                }
                declaredField.set(a7, application2);
            } catch (Exception e7) {
            }
        }
    }
}
