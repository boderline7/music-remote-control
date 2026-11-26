package b2;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends w {
    public static final v BOOLEAN;
    public static final v BOOLEAN_ARRAY;
    public static final v BYTE;
    public static final v BYTE_ARRAY;
    public static final v CHARACTER;
    public static final v CHAR_ARRAY;
    public static final v DOUBLE;
    public static final v DOUBLE_ARRAY;
    public static final v FLOAT;
    public static final v FLOAT_ARRAY;
    public static final v INTEGER;
    public static final v INT_ARRAY;
    public static final v LONG;
    public static final v LONG_ARRAY;
    public static final v METHOD_HANDLE;
    public static final v OBJECT;
    public static final v SHORT;
    public static final v SHORT_ARRAY;
    public static final v VAR_HANDLE;
    public static final v VOID;

    /* renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap f2942c = new ConcurrentHashMap(1000, 0.75f);

    /* renamed from: a, reason: collision with root package name */
    public final c2.c f2943a;

    /* renamed from: b, reason: collision with root package name */
    public u f2944b;

    static {
        v vVar = new v(c2.c.OBJECT);
        OBJECT = vVar;
        v vVar2 = new v(c2.c.BOOLEAN_CLASS);
        BOOLEAN = vVar2;
        v vVar3 = new v(c2.c.BYTE_CLASS);
        BYTE = vVar3;
        v vVar4 = new v(c2.c.CHARACTER_CLASS);
        CHARACTER = vVar4;
        v vVar5 = new v(c2.c.DOUBLE_CLASS);
        DOUBLE = vVar5;
        v vVar6 = new v(c2.c.FLOAT_CLASS);
        FLOAT = vVar6;
        v vVar7 = new v(c2.c.LONG_CLASS);
        LONG = vVar7;
        v vVar8 = new v(c2.c.INTEGER_CLASS);
        INTEGER = vVar8;
        v vVar9 = new v(c2.c.SHORT_CLASS);
        SHORT = vVar9;
        v vVar10 = new v(c2.c.VOID_CLASS);
        VOID = vVar10;
        v vVar11 = new v(c2.c.BOOLEAN_ARRAY);
        BOOLEAN_ARRAY = vVar11;
        v vVar12 = new v(c2.c.BYTE_ARRAY);
        BYTE_ARRAY = vVar12;
        v vVar13 = new v(c2.c.CHAR_ARRAY);
        CHAR_ARRAY = vVar13;
        v vVar14 = new v(c2.c.DOUBLE_ARRAY);
        DOUBLE_ARRAY = vVar14;
        v vVar15 = new v(c2.c.FLOAT_ARRAY);
        FLOAT_ARRAY = vVar15;
        v vVar16 = new v(c2.c.LONG_ARRAY);
        LONG_ARRAY = vVar16;
        v vVar17 = new v(c2.c.INT_ARRAY);
        INT_ARRAY = vVar17;
        v vVar18 = new v(c2.c.SHORT_ARRAY);
        SHORT_ARRAY = vVar18;
        v vVar19 = new v(c2.c.METHOD_HANDLE);
        METHOD_HANDLE = vVar19;
        VAR_HANDLE = new v(c2.c.VAR_HANDLE);
        h(vVar);
        h(vVar2);
        h(vVar3);
        h(vVar4);
        h(vVar5);
        h(vVar6);
        h(vVar7);
        h(vVar8);
        h(vVar9);
        h(vVar10);
        h(vVar11);
        h(vVar12);
        h(vVar13);
        h(vVar14);
        h(vVar15);
        h(vVar16);
        h(vVar17);
        h(vVar18);
        h(vVar19);
    }

    public v(c2.c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        }
        if (cVar == c2.c.KNOWN_NULL) {
            throw new UnsupportedOperationException("KNOWN_NULL is not representable");
        }
        this.f2943a = cVar;
        this.f2944b = null;
    }

    public static void h(v vVar) {
        if (f2942c.putIfAbsent(vVar.f2943a, vVar) == null) {
            return;
        }
        throw new IllegalStateException("Attempted re-init of " + vVar);
    }

    @Override // c2.d
    public final c2.c b() {
        return c2.c.CLASS;
    }

    @Override // e2.i
    public final String d() {
        return this.f2943a.d();
    }

    @Override // b2.a
    public final int e(a aVar) {
        return this.f2943a.f3040a.compareTo(((v) aVar).f2943a.f3040a);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof v) {
            return this.f2943a == ((v) obj).f2943a;
        }
        return false;
    }

    @Override // b2.a
    public final String f() {
        return "type";
    }

    public final u g() {
        if (this.f2944b == null) {
            this.f2944b = new u(this.f2943a.f3040a);
        }
        return this.f2944b;
    }

    public final int hashCode() {
        return this.f2943a.f3040a.hashCode();
    }

    public final String toString() {
        return "type{" + this.f2943a.d() + '}';
    }
}
