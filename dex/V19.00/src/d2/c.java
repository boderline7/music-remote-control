package d2;

import a2.n;
import a2.s;
import a2.t;
import a2.u;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import u1.o;
import u1.p;
import u1.q;
import u1.r;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static final int VERSION = 1;

    /* renamed from: h, reason: collision with root package name */
    public static final Map f6269h = Collections.synchronizedMap(new HashMap());

    /* renamed from: i, reason: collision with root package name */
    public static final HashMap f6270i;

    /* renamed from: j, reason: collision with root package name */
    public static final HashMap f6271j;

    /* renamed from: k, reason: collision with root package name */
    public static final HashMap f6272k;

    /* renamed from: a, reason: collision with root package name */
    public final Class f6273a;

    /* renamed from: c, reason: collision with root package name */
    public InvocationHandler f6275c;

    /* renamed from: d, reason: collision with root package name */
    public File f6276d;

    /* renamed from: b, reason: collision with root package name */
    public final ClassLoader f6274b = c.class.getClassLoader();

    /* renamed from: e, reason: collision with root package name */
    public final Class[] f6277e = new Class[0];

    /* renamed from: f, reason: collision with root package name */
    public final Object[] f6278f = new Object[0];

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f6279g = new ArrayList();

    static {
        HashMap hashMap = new HashMap();
        f6270i = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        hashMap.put(Integer.TYPE, Integer.class);
        hashMap.put(Byte.TYPE, Byte.class);
        hashMap.put(Long.TYPE, Long.class);
        hashMap.put(Short.TYPE, Short.class);
        hashMap.put(Float.TYPE, Float.class);
        hashMap.put(Double.TYPE, Double.class);
        hashMap.put(Character.TYPE, Character.class);
        f6271j = new HashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            q a7 = q.a((Class) entry.getKey());
            q a8 = q.a((Class) entry.getValue());
            f6271j.put(a7, a8.b(a8, "valueOf", a7));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Boolean.TYPE, q.a(Boolean.class).b(q.BOOLEAN, "booleanValue", new q[0]));
        hashMap2.put(Integer.TYPE, q.a(Integer.class).b(q.INT, "intValue", new q[0]));
        hashMap2.put(Byte.TYPE, q.a(Byte.class).b(q.BYTE, "byteValue", new q[0]));
        hashMap2.put(Long.TYPE, q.a(Long.class).b(q.LONG, "longValue", new q[0]));
        hashMap2.put(Short.TYPE, q.a(Short.class).b(q.SHORT, "shortValue", new q[0]));
        hashMap2.put(Float.TYPE, q.a(Float.class).b(q.FLOAT, "floatValue", new q[0]));
        hashMap2.put(Double.TYPE, q.a(Double.class).b(q.DOUBLE, "doubleValue", new q[0]));
        hashMap2.put(Character.TYPE, q.a(Character.class).b(q.CHAR, "charValue", new q[0]));
        f6272k = hashMap2;
    }

    public c(Class cls) {
        this.f6273a = cls;
    }

    public static void b(HashSet hashSet, HashSet hashSet2, Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if ((method.getModifiers() & 16) != 0) {
                a aVar = new a(method);
                hashSet2.add(aVar);
                hashSet.remove(aVar);
            } else if ((method.getModifiers() & 8) == 0 && ((Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) && (!method.getName().equals("finalize") || method.getParameterTypes().length != 0))) {
                a aVar2 = new a(method);
                if (!hashSet2.contains(aVar2)) {
                    hashSet.add(aVar2);
                }
            }
        }
        if (cls.isInterface()) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                b(hashSet, hashSet2, cls2);
            }
        }
    }

    public static void c(h1.b bVar, Method method, o oVar, o oVar2) {
        q a7 = q.a(AbstractMethodError.class);
        q[] qVarArr = {q.STRING};
        a7.getClass();
        p pVar = new p(a7, q.VOID, "<init>", new r(qVarArr));
        bVar.j(oVar, "'" + method + "' cannot be called");
        o[] oVarArr = {oVar};
        if (oVar2 == null) {
            throw new IllegalArgumentException();
        }
        a2.p pVar2 = a2.r.NEW_INSTANCE;
        s sVar = (s) bVar.f6667h;
        bVar.a(new t(pVar2, sVar, n.EMPTY, (c2.b) bVar.f6669j, a7.f9304c), null);
        bVar.k(oVar2, true);
        String a8 = pVar.a(true);
        if (a8 == null) {
            ConcurrentHashMap concurrentHashMap = c2.a.f3034e;
            throw new NullPointerException("descriptor == null");
        }
        ConcurrentHashMap concurrentHashMap2 = c2.a.f3034e;
        c2.a aVar = (c2.a) concurrentHashMap2.get(a8);
        if (aVar == null) {
            c2.a b7 = c2.a.b(a8);
            c2.a aVar2 = (c2.a) concurrentHashMap2.putIfAbsent(b7.f3035a, b7);
            aVar = aVar2 != null ? aVar2 : b7;
        }
        bVar.h(new a2.p(52, aVar.c(), c2.b.THROWABLE), pVar, null, oVar2, oVarArr);
        bVar.a(new u(a2.r.THROW, sVar, n.i(oVar2.a()), (c2.b) bVar.f6669j), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x077d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x044f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x080a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a() {
        /*
            Method dump skipped, instructions count: 4306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: d2.c.a():java.lang.Object");
    }
}
