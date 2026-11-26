package y1;

import java.util.Collection;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c0 {

    /* renamed from: a, reason: collision with root package name */
    public final String f10051a;

    /* renamed from: b, reason: collision with root package name */
    public final f f10052b;

    /* renamed from: c, reason: collision with root package name */
    public final int f10053c;

    /* renamed from: d, reason: collision with root package name */
    public int f10054d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f10055e;

    public c0(String str, f fVar, int i7) {
        if (fVar == null) {
            throw new NullPointerException("file == null");
        }
        if (i7 <= 0 || ((i7 - 1) & i7) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        }
        this.f10051a = str;
        this.f10052b = fVar;
        this.f10053c = i7;
        this.f10054d = -1;
        this.f10055e = false;
    }

    public abstract int a(p pVar);

    public final int b() {
        int i7 = this.f10054d;
        if (i7 >= 0) {
            return i7;
        }
        throw new RuntimeException("fileOffset not set");
    }

    public abstract Collection c();

    public final void d() {
        g();
        e();
        this.f10055e = true;
    }

    public abstract void e();

    public final void f() {
        if (!this.f10055e) {
            throw new RuntimeException("not prepared");
        }
    }

    public final void g() {
        if (this.f10055e) {
            throw new RuntimeException("already prepared");
        }
    }

    public abstract int h();

    public final void i(e2.c cVar) {
        f();
        cVar.a(this.f10053c);
        int i7 = cVar.f6349c;
        int i8 = this.f10054d;
        if (i8 < 0) {
            this.f10054d = i7;
        } else if (i8 != i7) {
            throw new RuntimeException("alignment mismatch: for " + this + ", at " + i7 + ", but expected " + this.f10054d);
        }
        if (cVar.d()) {
            String str = this.f10051a;
            if (str != null) {
                cVar.b(0, "\n" + str + ":");
            } else if (i7 != 0) {
                cVar.b(0, "\n");
            }
        }
        j(cVar);
    }

    public abstract void j(e2.c cVar);
}
