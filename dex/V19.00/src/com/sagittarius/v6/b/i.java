package com.sagittarius.v6.b;

import dalvik.system.DexFile;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class i {

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f6123b = null;

    /* renamed from: a, reason: collision with root package name */
    private final j f6124a;

    static {
        a();
    }

    private i() {
        j mVar;
        Class<?> cls = Class.forName(f6123b[0]);
        try {
            mVar = new k(cls);
        } catch (NoSuchMethodException e7) {
            try {
                mVar = new l(cls);
            } catch (NoSuchMethodException e8) {
                mVar = new m(cls);
            }
        }
        this.f6124a = mVar;
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
        String[] strArr = new String[4];
        f6123b = strArr;
        strArr[0] = a("Fg4UID4ZQQsvJAYKFXgTFxcoNyMaIxElI1YqFDM6FwEM", "roxVW");
        f6123b[1] = a("BAM5BhUdETk=", "tbMnY");
        f6123b[2] = a("MzAUCgIyOAkhGiQ=", "WUlOn");
        f6123b[3] = a("IRgSBSk9HAsIAiUK", "Qyfml");
    }

    static void a(ClassLoader classLoader, List list) {
        int i7;
        Object obj = s.a(classLoader, f6123b[1]).get(classLoader);
        i iVar = new i();
        Object[] objArr = new Object[list.size()];
        for (int i8 = 0; i8 < objArr.length; i8++) {
            File file = (File) list.get(i8);
            j jVar = iVar.f6124a;
            String path = file.getPath();
            File parentFile = file.getParentFile();
            String name = file.getName();
            StringBuilder sb = new StringBuilder();
            int length = name.length();
            i7 = h.f6119e;
            sb.append(name.substring(0, length - i7));
            sb.append(h.f6115a);
            objArr[i8] = jVar.a(file, DexFile.loadDex(path, new File(parentFile, sb.toString()).getPath(), 0));
        }
        try {
            h.a(obj, f6123b[2], objArr);
        } catch (NoSuchFieldException e7) {
            h.a(obj, f6123b[3], objArr);
        }
        h.a(objArr);
    }
}
