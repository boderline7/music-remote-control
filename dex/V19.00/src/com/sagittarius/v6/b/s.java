package com.sagittarius.v6.b;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6135a = null;

    static {
        a();
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

    public static Field a(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException e7) {
            }
        }
        throw new NoSuchFieldException(f6135a[0] + str + f6135a[1] + obj.getClass());
    }

    public static Method a(Object obj, String str, Class... clsArr) {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e7) {
            }
        }
        throw new NoSuchMethodException(f6135a[2] + str + f6135a[3] + Arrays.asList(clsArr) + f6135a[4] + obj.getClass());
    }

    private static void a() {
        String[] strArr = new String[5];
        f6135a = strArr;
        strArr[0] = a("IQswBQVH", "gbUia");
        f6135a[1] = a("SCcGPlEOJhwkFUggB2o=", "hIiJq");
        f6135a[2] = a("Kj0/AyoDeA==", "gXKkE");
        f6135a[3] = a("USQ4FRhRIzATERw2JQQCAnM=", "qSQap");
        f6135a[4] = a("awIqPlEtAzAkFWsFK2o=", "KlEJq");
    }

    public static void a(Object obj, String str, Object[] objArr) {
        Field a7 = a(obj, str);
        Object[] objArr2 = (Object[]) a7.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + 1);
        System.arraycopy(objArr, 0, objArr3, 0, 1);
        System.arraycopy(objArr2, 0, objArr3, 1, objArr2.length);
        a7.set(obj, objArr3);
    }
}
