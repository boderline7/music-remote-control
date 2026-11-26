package u1;

import b2.v;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q {
    public static final q BOOLEAN;
    public static final q BYTE;
    public static final q CHAR;
    public static final q DOUBLE;
    public static final q FLOAT;
    public static final q INT;
    public static final q LONG;
    public static final q OBJECT;
    public static final q SHORT;
    public static final q STRING;
    public static final q VOID;

    /* renamed from: d, reason: collision with root package name */
    public static final HashMap f9301d;

    /* renamed from: a, reason: collision with root package name */
    public final String f9302a;

    /* renamed from: b, reason: collision with root package name */
    public final c2.c f9303b;

    /* renamed from: c, reason: collision with root package name */
    public final v f9304c;

    static {
        q qVar = new q(c2.c.BOOLEAN);
        BOOLEAN = qVar;
        q qVar2 = new q(c2.c.BYTE);
        BYTE = qVar2;
        q qVar3 = new q(c2.c.CHAR);
        CHAR = qVar3;
        q qVar4 = new q(c2.c.DOUBLE);
        DOUBLE = qVar4;
        q qVar5 = new q(c2.c.FLOAT);
        FLOAT = qVar5;
        q qVar6 = new q(c2.c.INT);
        INT = qVar6;
        q qVar7 = new q(c2.c.LONG);
        LONG = qVar7;
        q qVar8 = new q(c2.c.SHORT);
        SHORT = qVar8;
        q qVar9 = new q(c2.c.VOID);
        VOID = qVar9;
        OBJECT = new q(c2.c.OBJECT);
        STRING = new q(c2.c.STRING);
        HashMap hashMap = new HashMap();
        f9301d = hashMap;
        hashMap.put(Boolean.TYPE, qVar);
        hashMap.put(Byte.TYPE, qVar2);
        hashMap.put(Character.TYPE, qVar3);
        hashMap.put(Double.TYPE, qVar4);
        hashMap.put(Float.TYPE, qVar5);
        hashMap.put(Integer.TYPE, qVar6);
        hashMap.put(Long.TYPE, qVar7);
        hashMap.put(Short.TYPE, qVar8);
        hashMap.put(Void.TYPE, qVar9);
    }

    public q(c2.c cVar) {
        this(cVar.f3040a, cVar);
    }

    public q(String str, c2.c cVar) {
        if (str == null || cVar == null) {
            throw null;
        }
        this.f9302a = str;
        this.f9303b = cVar;
        v vVar = new v(cVar);
        v vVar2 = (v) v.f2942c.putIfAbsent(cVar, vVar);
        this.f9304c = vVar2 != null ? vVar2 : vVar;
    }

    public static q a(Class cls) {
        if (cls.isPrimitive()) {
            return (q) f9301d.get(cls);
        }
        String replace = cls.getName().replace('.', '/');
        if (!cls.isArray()) {
            replace = "L" + replace + ';';
        }
        try {
            return new q(replace, replace.equals("V") ? c2.c.VOID : c2.c.g(replace));
        } catch (NullPointerException unused) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public final p b(q qVar, String str, q... qVarArr) {
        return new p(this, qVar, str, new r(qVarArr));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof q) && ((q) obj).f9302a.equals(this.f9302a);
    }

    public final int hashCode() {
        return this.f9302a.hashCode();
    }

    public final String toString() {
        return this.f9302a;
    }
}
