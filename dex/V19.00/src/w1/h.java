package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h {

    /* renamed from: a, reason: collision with root package name */
    public int f9426a;

    /* renamed from: b, reason: collision with root package name */
    public final j f9427b;

    /* renamed from: c, reason: collision with root package name */
    public final a2.s f9428c;

    /* renamed from: d, reason: collision with root package name */
    public final a2.n f9429d;

    public h(j jVar, a2.s sVar, a2.n nVar) {
        if (jVar == null) {
            throw new NullPointerException("opcode == null");
        }
        if (sVar == null) {
            throw new NullPointerException("position == null");
        }
        if (nVar == null) {
            throw new NullPointerException("registers == null");
        }
        this.f9426a = -1;
        this.f9427b = jVar;
        this.f9428c = sVar;
        this.f9429d = nVar;
    }

    public static x h(a2.s sVar, a2.m mVar, a2.m mVar2) {
        boolean z6 = mVar.e() == 1;
        boolean z7 = mVar.f238b.b().f3041b == 9;
        int i7 = mVar2.f237a;
        int i8 = mVar.f237a;
        return new x((i7 | i8) < 16 ? z7 ? k.MOVE_OBJECT : z6 ? k.MOVE : k.MOVE_WIDE : i8 < 256 ? z7 ? k.MOVE_OBJECT_FROM16 : z6 ? k.MOVE_FROM16 : k.MOVE_WIDE_FROM16 : z7 ? k.MOVE_OBJECT_16 : z6 ? k.MOVE_16 : k.MOVE_WIDE_16, sVar, a2.n.j(mVar, mVar2));
    }

    public abstract String a();

    public abstract int b();

    public String c() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public String d() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public final int e() {
        int i7 = this.f9426a;
        if (i7 >= 0) {
            return i7;
        }
        throw new RuntimeException("address not yet known");
    }

    public final String f() {
        int i7 = this.f9426a;
        return i7 != -1 ? String.format("%04x", Integer.valueOf(i7)) : n1.z.m1(System.identityHashCode(this));
    }

    public abstract String g();

    public h i(androidx.appcompat.app.n nVar) {
        a2.n nVar2 = this.f9429d;
        int length = nVar2.f6354b.length;
        a2.n nVar3 = new a2.n(length);
        for (int i7 = 0; i7 < length; i7++) {
            nVar3.g(i7, nVar.j((a2.m) nVar2.f(i7)));
        }
        nVar3.f6364a = false;
        if (!nVar3.equals(nVar2)) {
            nVar2 = nVar3;
        }
        return l(nVar2);
    }

    public abstract h j(j jVar);

    public abstract h k(int i7);

    public abstract h l(a2.n nVar);

    public abstract void m(e2.c cVar);

    public final String toString() {
        boolean z6;
        StringBuilder sb = new StringBuilder(100);
        sb.append(f());
        sb.append(' ');
        sb.append(this.f9428c);
        sb.append(": ");
        sb.append(this.f9427b.a());
        a2.n nVar = this.f9429d;
        if (nVar.f6354b.length != 0) {
            z6 = true;
            sb.append(nVar.h(" ", null, true));
        } else {
            z6 = false;
        }
        String a7 = a();
        if (a7 != null) {
            if (z6) {
                sb.append(',');
            }
            sb.append(' ');
            sb.append(a7);
        }
        return sb.toString();
    }
}
