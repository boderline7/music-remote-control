package com.sagittarius.v6.b;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class p {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6130a = null;

    static {
        a();
    }

    private p() {
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
        f6130a = strArr;
        strArr[0] = a("OAoZAS4hGBk=", "Hkmib");
        f6130a[1] = a("IgMOAycjCxMoPzU=", "FfvFK");
        f6130a[2] = a("PSYUMQY8LgkaHioQGQQaKyYfBw89BhQXDyk3BRsEKg==", "YCltj");
        f6130a[3] = a("Om0CTBQLISgcBRotI0wVBjAkAhZTLywHFDcnNSkdFi8oAgU=", "sBMlq");
        f6130a[4] = a("GRQBIS0aOA8pCwYMLiEcMRkPKQEaARk=", "tujDd");
    }

    static void a(ClassLoader classLoader, ByteBuffer byteBuffer) {
        a(classLoader, new ByteBuffer[]{byteBuffer});
    }

    static void a(ClassLoader classLoader, ByteBuffer[] byteBufferArr) {
        IOException[] iOExceptionArr;
        Object obj = s.a(classLoader, f6130a[0]).get(classLoader);
        ArrayList arrayList = new ArrayList();
        Object[] a7 = a(obj, byteBufferArr, arrayList);
        synchronized (p.class) {
            try {
                h.a(obj, f6130a[1], a7);
                h.a(a7);
                if (arrayList.size() > 0) {
                    Field a8 = s.a(obj, f6130a[2]);
                    IOException[] iOExceptionArr2 = (IOException[]) a8.get(obj);
                    if (iOExceptionArr2 == null) {
                        iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                    } else {
                        IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                        arrayList.toArray(iOExceptionArr3);
                        System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                        iOExceptionArr = iOExceptionArr3;
                    }
                    a8.set(obj, iOExceptionArr);
                    IOException iOException = new IOException(f6130a[3]);
                    iOException.initCause((Throwable) arrayList.get(0));
                    throw iOException;
                }
            } finally {
            }
        }
    }

    private static Object[] a(Object obj, ByteBuffer[] byteBufferArr, ArrayList arrayList) {
        return (Object[]) s.a(obj, f6130a[4], ByteBuffer[].class, List.class).invoke(obj, byteBufferArr, arrayList);
    }
}
