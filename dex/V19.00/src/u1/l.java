package u1;

import com.google.zxing.WriterException;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {

    /* renamed from: e, reason: collision with root package name */
    public static boolean f9276e;

    /* renamed from: f, reason: collision with root package name */
    public static boolean f9277f;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f9278a;

    /* renamed from: b, reason: collision with root package name */
    public final Serializable f9279b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f9280c;

    /* renamed from: d, reason: collision with root package name */
    public Object f9281d;

    public l() {
        this.f9279b = new LinkedHashMap();
    }

    public l(String str, Charset charset, boolean z6, d4.a aVar) {
        this.f9279b = str;
        this.f9278a = z6;
        this.f9280c = new y3.d(str, charset, -1);
        this.f9281d = aVar;
    }

    public static void a(e4.f[][][] fVarArr, int i7, e4.f fVar) {
        e4.f[] fVarArr2 = fVarArr[i7 + fVar.f6385d][fVar.f6384c];
        d4.b bVar = fVar.f6382a;
        char c7 = 0;
        if (bVar != null) {
            int i8 = e4.e.f6381b[bVar.ordinal()];
            char c8 = 1;
            if (i8 != 1) {
                c7 = 2;
                if (i8 != 2) {
                    c8 = 3;
                    if (i8 != 3) {
                        if (i8 != 4) {
                            throw new IllegalStateException("Illegal mode " + bVar);
                        }
                    }
                }
                c7 = c8;
            }
        }
        e4.f fVar2 = fVarArr2[c7];
        if (fVar2 != null) {
            if (fVar2.f6387f <= fVar.f6387f) {
                return;
            }
        }
        fVarArr2[c7] = fVar;
    }

    public static boolean c(d4.b bVar, char c7) {
        int i7 = e4.e.f6381b[bVar.ordinal()];
        if (i7 == 1) {
            return e4.c.b(String.valueOf(c7));
        }
        if (i7 != 2) {
            return i7 != 3 ? i7 == 4 : c7 >= '0' && c7 <= '9';
        }
        if (c7 >= '`') {
            int[] iArr = e4.c.f6374a;
        } else if (e4.c.f6374a[c7] != -1) {
            return true;
        }
        return false;
    }

    public static d4.c i(e4.h hVar) {
        int i7 = e4.e.f6380a[hVar.ordinal()];
        return i7 != 1 ? i7 != 2 ? d4.c.b(40) : d4.c.b(26) : d4.c.b(9);
    }

    public final void b(d4.c cVar, e4.f[][][] fVarArr, int i7, e4.f fVar) {
        int i8;
        y3.d dVar = (y3.d) this.f9280c;
        int length = dVar.f10184a.length;
        int i9 = dVar.f10185b;
        Serializable serializable = this.f9279b;
        if (i9 < 0 || !dVar.a(((String) serializable).charAt(i7), i9)) {
            i9 = 0;
        } else {
            length = i9 + 1;
        }
        int i10 = length;
        for (int i11 = i9; i11 < i10; i11++) {
            if (((y3.d) this.f9280c).a(((String) serializable).charAt(i7), i11)) {
                a(fVarArr, i7, new e4.f(this, d4.b.BYTE, i7, i11, 1, fVar, cVar));
            }
        }
        d4.b bVar = d4.b.KANJI;
        String str = (String) serializable;
        if (c(bVar, str.charAt(i7))) {
            a(fVarArr, i7, new e4.f(this, bVar, i7, 0, 1, fVar, cVar));
        }
        int length2 = str.length();
        d4.b bVar2 = d4.b.ALPHANUMERIC;
        if (c(bVar2, str.charAt(i7))) {
            int i12 = i7 + 1;
            a(fVarArr, i7, new e4.f(this, bVar2, i7, 0, (i12 >= length2 || !c(bVar2, str.charAt(i12))) ? 1 : 2, fVar, cVar));
        }
        d4.b bVar3 = d4.b.NUMERIC;
        if (c(bVar3, str.charAt(i7))) {
            int i13 = i7 + 1;
            if (i13 >= length2 || !c(bVar3, str.charAt(i13))) {
                i8 = 1;
            } else {
                int i14 = i7 + 2;
                i8 = (i14 >= length2 || !c(bVar3, str.charAt(i14))) ? 2 : 3;
            }
            a(fVarArr, i7, new e4.f(this, bVar3, i7, 0, i8, fVar, cVar));
        }
    }

    public final h1.b d(p pVar) {
        LinkedHashMap linkedHashMap = h(pVar.f9296a).f9275i;
        if (linkedHashMap.containsKey(pVar)) {
            throw new IllegalStateException("already declared: " + pVar);
        }
        String str = pVar.f9298c;
        j jVar = new j(pVar, (str.equals("<init>") || str.equals("<clinit>")) ? 65537 : 1);
        linkedHashMap.put(pVar, jVar);
        return jVar.f9266c;
    }

    public final void e(m mVar, int i7) {
        LinkedHashMap linkedHashMap = h(mVar.f9282a).f9274h;
        if (linkedHashMap.containsKey(mVar)) {
            throw new IllegalStateException("already declared: " + mVar);
        }
        if ((i7 & (-4320)) == 0) {
            linkedHashMap.put(mVar, new i(mVar, i7));
        } else {
            throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(i7));
        }
    }

    public final androidx.appcompat.app.g f(d4.c cVar) {
        int i7;
        String str = (String) this.f9279b;
        int length = str.length();
        e4.f[][][] fVarArr = (e4.f[][][]) Array.newInstance((Class<?>) e4.f.class, length + 1, ((y3.d) this.f9280c).f10184a.length, 4);
        b(cVar, fVarArr, 0, null);
        for (int i8 = 1; i8 <= length; i8++) {
            for (int i9 = 0; i9 < ((y3.d) this.f9280c).f10184a.length; i9++) {
                for (int i10 = 0; i10 < 4; i10++) {
                    e4.f fVar = fVarArr[i8][i9][i10];
                    if (fVar != null && i8 < length) {
                        b(cVar, fVarArr, i8, fVar);
                    }
                }
            }
        }
        int i11 = -1;
        int i12 = Integer.MAX_VALUE;
        int i13 = -1;
        for (int i14 = 0; i14 < ((y3.d) this.f9280c).f10184a.length; i14++) {
            for (int i15 = 0; i15 < 4; i15++) {
                e4.f fVar2 = fVarArr[length][i14][i15];
                if (fVar2 != null && (i7 = fVar2.f6387f) < i12) {
                    i11 = i14;
                    i13 = i15;
                    i12 = i7;
                }
            }
        }
        if (i11 >= 0) {
            return new androidx.appcompat.app.g(this, cVar, fVarArr[length][i11][i13]);
        }
        throw new WriterException(a.a.h("Internal error: failed to encode \"", str, "\""));
    }

    public final ClassLoader g(File file, File file2, ClassLoader classLoader) {
        try {
            try {
                Object obj = this.f9280c;
                boolean z6 = ((ClassLoader) obj) != null;
                ClassLoader classLoader2 = classLoader != null ? classLoader : ((ClassLoader) obj) != null ? (ClassLoader) obj : null;
                Class<?> cls = Class.forName("dalvik.system.BaseDexClassLoader");
                if (z6 && !cls.isAssignableFrom(classLoader2.getClass())) {
                    if (!classLoader2.getClass().getName().equals("java.lang.BootClassLoader") && !f9277f) {
                        System.err.println("Cannot share classloader as shared classloader '" + classLoader2 + "' is not a subclass of '" + cls + "'");
                        f9277f = true;
                    }
                    z6 = false;
                }
                if (this.f9278a) {
                    try {
                        if (!z6) {
                            return (ClassLoader) cls.getConstructor(String.class, File.class, String.class, ClassLoader.class, Boolean.TYPE).newInstance(file.getPath(), file2.getAbsoluteFile(), null, classLoader2, Boolean.TRUE);
                        }
                        classLoader2.getClass().getMethod("addDexPath", String.class, Boolean.TYPE).invoke(classLoader2, file.getPath(), Boolean.TRUE);
                        return classLoader2;
                    } catch (InvocationTargetException e7) {
                        if (!(e7.getCause() instanceof SecurityException)) {
                            throw e7;
                        }
                        if (!f9276e) {
                            System.err.println("Cannot allow to call blacklisted super methods. This might break spying on system classes." + e7.getCause());
                            f9276e = true;
                        }
                    }
                }
                if (!z6) {
                    return (ClassLoader) Class.forName("dalvik.system.DexClassLoader").getConstructor(String.class, String.class, String.class, ClassLoader.class).newInstance(file.getPath(), file2.getAbsolutePath(), null, classLoader2);
                }
                classLoader2.getClass().getMethod("addDexPath", String.class).invoke(classLoader2, file.getPath());
                return classLoader2;
            } catch (InvocationTargetException e8) {
                throw new RuntimeException(e8.getCause());
            }
        } catch (ClassNotFoundException e9) {
            throw new UnsupportedOperationException("load() requires a Dalvik VM", e9);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (InstantiationException unused2) {
            throw new AssertionError();
        } catch (NoSuchMethodException unused3) {
            throw new AssertionError();
        }
    }

    public final k h(q qVar) {
        Map map = (Map) this.f9279b;
        k kVar = (k) map.get(qVar);
        if (kVar != null) {
            return kVar;
        }
        k kVar2 = new k(qVar);
        map.put(qVar, kVar2);
        return kVar2;
    }
}
