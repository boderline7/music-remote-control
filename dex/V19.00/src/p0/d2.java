package p0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d2 {

    /* renamed from: a, reason: collision with root package name */
    public final l2 f8116a;

    /* renamed from: b, reason: collision with root package name */
    public i0.c[] f8117b;

    public d2() {
        this(new l2());
    }

    public d2(l2 l2Var) {
        this.f8116a = l2Var;
    }

    public final void a() {
        i0.c[] cVarArr = this.f8117b;
        if (cVarArr != null) {
            i0.c cVar = cVarArr[n1.z.A0(1)];
            i0.c cVar2 = this.f8117b[n1.z.A0(2)];
            l2 l2Var = this.f8116a;
            if (cVar2 == null) {
                cVar2 = l2Var.f8161a.f(2);
            }
            if (cVar == null) {
                cVar = l2Var.f8161a.f(1);
            }
            g(i0.c.a(cVar, cVar2));
            i0.c cVar3 = this.f8117b[n1.z.A0(16)];
            if (cVar3 != null) {
                f(cVar3);
            }
            i0.c cVar4 = this.f8117b[n1.z.A0(32)];
            if (cVar4 != null) {
                d(cVar4);
            }
            i0.c cVar5 = this.f8117b[n1.z.A0(64)];
            if (cVar5 != null) {
                h(cVar5);
            }
        }
    }

    public abstract l2 b();

    public void c(int i7, i0.c cVar) {
        if (this.f8117b == null) {
            this.f8117b = new i0.c[9];
        }
        for (int i8 = 1; i8 <= 256; i8 <<= 1) {
            if ((i7 & i8) != 0) {
                this.f8117b[n1.z.A0(i8)] = cVar;
            }
        }
    }

    public void d(i0.c cVar) {
    }

    public abstract void e(i0.c cVar);

    public void f(i0.c cVar) {
    }

    public abstract void g(i0.c cVar);

    public void h(i0.c cVar) {
    }
}
