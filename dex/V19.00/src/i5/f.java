package i5;

import h5.m;
import h5.n;
import h5.o;
import h5.p;
import h5.q;
import h5.r;
import h5.s;
import h5.t;
import h5.u;
import h5.v;
import h5.w;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements m5.a, d {
    public static final e Companion = new e();

    /* renamed from: b, reason: collision with root package name */
    public static final Map f6807b;

    /* renamed from: a, reason: collision with root package name */
    public final Class f6808a;

    static {
        int i7 = 0;
        List asList = Arrays.asList(h5.a.class, h5.l.class, p.class, q.class, r.class, s.class, t.class, u.class, v.class, w.class, h5.b.class, h5.c.class, h5.d.class, h5.e.class, h5.f.class, h5.g.class, h5.h.class, h5.i.class, h5.j.class, h5.k.class, m.class, n.class, o.class);
        a.f("asList(this)", asList);
        ArrayList arrayList = new ArrayList(c5.l.x1(asList));
        for (Object obj : asList) {
            int i8 = i7 + 1;
            if (i7 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new b5.a((Class) obj, Integer.valueOf(i7)));
            i7 = i8;
        }
        f6807b = c5.l.E1(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        a.f("primitiveFqNames.values", values);
        for (String str : values) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            a.f("kotlinName", str);
            sb.append(o5.k.T(str));
            sb.append("CompanionObject");
            hashMap3.put(sb.toString(), str.concat(".Companion"));
        }
        for (Map.Entry entry : f6807b.entrySet()) {
            hashMap3.put(((Class) entry.getKey()).getName(), "kotlin.Function" + ((Number) entry.getValue()).intValue());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.I0(hashMap3.size()));
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), o5.k.T((String) entry2.getValue()));
        }
    }

    public f(Class cls) {
        a.g("jClass", cls);
        this.f6808a = cls;
    }

    @Override // i5.d
    public final Class a() {
        return this.f6808a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f) && a.c(z.l0(this), z.l0((m5.a) obj));
    }

    public final int hashCode() {
        return z.l0(this).hashCode();
    }

    public final String toString() {
        return this.f6808a.toString() + " (Kotlin reflection is not available)";
    }
}
