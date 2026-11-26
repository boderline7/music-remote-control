package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 {
    public static final b0 INSTANCE = new b0();

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap f2149a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public static final HashMap f2150b = new HashMap();

    public static void a(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(obj);
            i5.a.f("{\n            constructo…tance(`object`)\n        }", newInstance);
            a.a.C(newInstance);
            throw null;
        } catch (IllegalAccessException e7) {
            throw new RuntimeException(e7);
        } catch (InstantiationException e8) {
            throw new RuntimeException(e8);
        } catch (InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b6, code lost:
    
        if (r8.booleanValue() != false) goto L71;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(java.lang.Class r13) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.b0.b(java.lang.Class):int");
    }
}
