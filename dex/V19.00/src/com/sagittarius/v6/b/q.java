package com.sagittarius.v6.b;

import dalvik.system.DexFile;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipFile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class q {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6131a = null;

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
        String[] strArr = new String[6];
        f6131a = strArr;
        strArr[0] = a("JQwHOw==", "UmsSW");
        f6131a[1] = a("fxEXCA==", "Qurph");
        f6131a[2] = a("PxcrBzAh", "RGJsX");
        f6131a[3] = a("LzI7HwEx", "BtRsd");
        f6131a[4] = a("HDszOxo=", "qaZKi");
        f6131a[5] = a("ABEEDwE=", "mUawr");
    }

    static void a(ClassLoader classLoader, List list) {
        int size = list.size();
        Field a7 = s.a(classLoader, f6131a[0]);
        StringBuilder sb = new StringBuilder((String) a7.get(classLoader));
        String[] strArr = new String[size];
        File[] fileArr = new File[size];
        ZipFile[] zipFileArr = new ZipFile[size];
        DexFile[] dexFileArr = new DexFile[size];
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            File file = (File) listIterator.next();
            String absolutePath = file.getAbsolutePath();
            sb.append(':');
            sb.append(absolutePath);
            int previousIndex = listIterator.previousIndex();
            strArr[previousIndex] = absolutePath;
            fileArr[previousIndex] = file;
            zipFileArr[previousIndex] = new ZipFile(file);
            dexFileArr[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + f6131a[1], 0);
        }
        a7.set(classLoader, sb.toString());
        h.a(classLoader, f6131a[2], strArr);
        h.a(classLoader, f6131a[3], fileArr);
        h.a(classLoader, f6131a[4], zipFileArr);
        h.a(classLoader, f6131a[5], dexFileArr);
        h.a(dexFileArr);
    }
}
