package y1;

import com.android.dex.util.ExceptionWithContext;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a0 extends p implements Comparable {

    /* renamed from: a, reason: collision with root package name */
    public final int f10033a;

    /* renamed from: b, reason: collision with root package name */
    public int f10034b;

    /* renamed from: c, reason: collision with root package name */
    public c0 f10035c;

    /* renamed from: d, reason: collision with root package name */
    public int f10036d;

    public a0(int i7, int i8) {
        if (i7 <= 0 || ((i7 - 1) & i7) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        }
        if (i8 < -1) {
            throw new IllegalArgumentException("writeSize < -1");
        }
        this.f10033a = i7;
        this.f10034b = i8;
        this.f10035c = null;
        this.f10036d = -1;
    }

    @Override // y1.p
    public final int c() {
        int i7 = this.f10034b;
        if (i7 >= 0) {
            return i7;
        }
        throw new UnsupportedOperationException("writeSize is unknown");
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        a0 a0Var = (a0) obj;
        if (this == a0Var) {
            return 0;
        }
        q b7 = b();
        q b8 = a0Var.b();
        return b7 != b8 ? b7.compareTo(b8) : e(a0Var);
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        cVar.a(this.f10033a);
        try {
            if (this.f10034b < 0) {
                throw new UnsupportedOperationException("writeSize is unknown");
            }
            int f7 = f();
            if (cVar.f6349c == f7) {
                k(fVar, cVar);
                return;
            }
            throw new ExceptionWithContext("expected cursor " + f7 + "; actual value: " + cVar.f6349c, null);
        } catch (RuntimeException e7) {
            throw ExceptionWithContext.a("...while writing " + this, e7);
        }
    }

    public int e(a0 a0Var) {
        throw new UnsupportedOperationException("unsupported");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        a0 a0Var = (a0) obj;
        return b() == a0Var.b() && e(a0Var) == 0;
    }

    public final int f() {
        int i7 = this.f10036d;
        if (i7 < 0) {
            throw new RuntimeException("offset not yet known");
        }
        c0 c0Var = this.f10035c;
        if (i7 < 0) {
            c0Var.getClass();
            throw new IllegalArgumentException("relative < 0");
        }
        int i8 = c0Var.f10054d;
        if (i8 >= 0) {
            return i8 + i7;
        }
        throw new RuntimeException("fileOffset not yet set");
    }

    public final String g() {
        return "[" + Integer.toHexString(f()) + ']';
    }

    public final int h(c0 c0Var, int i7) {
        if (c0Var == null) {
            throw new NullPointerException("addedTo == null");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (this.f10035c != null) {
            throw new RuntimeException("already written");
        }
        int i8 = this.f10033a - 1;
        int i9 = (i7 + i8) & (~i8);
        this.f10035c = c0Var;
        this.f10036d = i9;
        i(c0Var, i9);
        return i9;
    }

    public void i(c0 c0Var, int i7) {
    }

    public final void j(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("writeSize < 0");
        }
        if (this.f10034b >= 0) {
            throw new UnsupportedOperationException("writeSize already set");
        }
        this.f10034b = i7;
    }

    public abstract void k(f fVar, e2.c cVar);
}
