package com.sagittarius.v6.b;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6109a = null;

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

    private static void a() {
        String[] strArr = new String[5];
        f6109a = strArr;
        strArr[0] = a("NSkgKzUsOyA=", "EHTCy");
        f6109a[1] = a("BS4fOSUOAwIyIQo9EhQ6GSoIJDwZJg4j", "kOkPS");
        f6109a[2] = a("GwApFR8FNzsVEx4cFggYGhgoGD4BCz8CDgcLMwQJ", "hyZaz");
        f6109a[3] = a("CgcAHxIGEgM/LgILDhQ2FA==", "gfkzB");
        f6109a[4] = a("PRcVMTE2Ogg6NTIEGAgmJx4kNCI+Ew8sNA==", "SvaXG");
    }

    static /* synthetic */ void a(ClassLoader classLoader, File file) {
        Object obj = s.a(classLoader, f6109a[0]).get(classLoader);
        List list = (List) s.a(obj, f6109a[1]).get(obj);
        list.add(0, file);
        List list2 = (List) s.a(obj, f6109a[2]).get(obj);
        Method a7 = s.a(obj, f6109a[3], List.class);
        list.addAll(list2);
        Object[] objArr = (Object[]) a7.invoke(obj, list);
        Field a8 = s.a(obj, f6109a[4]);
        a8.setAccessible(true);
        a8.set(obj, objArr);
    }
}
