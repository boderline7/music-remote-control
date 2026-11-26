package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class e0 {

    /* renamed from: a, reason: collision with root package name */
    public final h0 f2168a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2169b;

    /* renamed from: c, reason: collision with root package name */
    public int f2170c = -1;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f0 f2171d;

    public e0(f0 f0Var, h0 h0Var) {
        this.f2171d = f0Var;
        this.f2168a = h0Var;
    }

    public final void b(boolean z6) {
        if (z6 == this.f2169b) {
            return;
        }
        this.f2169b = z6;
        int i7 = z6 ? 1 : -1;
        f0 f0Var = this.f2171d;
        int i8 = f0Var.f2175c;
        f0Var.f2175c = i7 + i8;
        if (!f0Var.f2176d) {
            f0Var.f2176d = true;
            while (true) {
                try {
                    int i9 = f0Var.f2175c;
                    if (i8 == i9) {
                        break;
                    } else {
                        i8 = i9;
                    }
                } finally {
                    f0Var.f2176d = false;
                }
            }
        }
        if (this.f2169b) {
            f0Var.c(this);
        }
    }

    public void c() {
    }

    public boolean d(x xVar) {
        return false;
    }

    public abstract boolean e();
}
