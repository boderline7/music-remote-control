package com.sagittarius.v6.b;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class n {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6128a = null;

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
        f6128a = strArr;
        strArr[0] = a("CRQWORQQBhY=", "yubQX");
        f6128a[1] = a("CSwANAcIJB0fHx4=", "mIxqk");
        f6128a[2] = a("AzI3KgQCOioBHBQEOh8YFTI8HA0DEjcMDRcjJgAGFA==", "gWOoh");
        f6128a[3] = a("IV8jRggQEwkWGQEfAkYJHQIFCApIHQ0NCCwVFCMBDR0JCBk=", "hplfm");
        f6128a[4] = a("JCoNEAgsMyMZKSQuCAE/", "IKfuL");
    }

    static void a(ClassLoader classLoader, List list, File file) {
        IOException[] iOExceptionArr;
        Object obj = s.a(classLoader, f6128a[0]).get(classLoader);
        ArrayList arrayList = new ArrayList();
        Object[] objArr = (Object[]) s.a(obj, f6128a[4], ArrayList.class, File.class, ArrayList.class).invoke(obj, new ArrayList(list), file, arrayList);
        h.a(obj, f6128a[1], objArr);
        h.a(objArr);
        if (arrayList.size() > 0) {
            Field a7 = s.a(obj, f6128a[2]);
            IOException[] iOExceptionArr2 = (IOException[]) a7.get(obj);
            if (iOExceptionArr2 == null) {
                iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
            } else {
                IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                arrayList.toArray(iOExceptionArr3);
                System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                iOExceptionArr = iOExceptionArr3;
            }
            a7.set(obj, iOExceptionArr);
            IOException iOException = new IOException(f6128a[3]);
            iOException.initCause((Throwable) arrayList.get(0));
            throw iOException;
        }
    }
}
