package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h implements a1 {

    /* renamed from: a, reason: collision with root package name */
    public final a1 f2479a;

    /* renamed from: b, reason: collision with root package name */
    public int f2480b = 0;

    /* renamed from: c, reason: collision with root package name */
    public int f2481c = -1;

    /* renamed from: d, reason: collision with root package name */
    public int f2482d = -1;

    /* renamed from: e, reason: collision with root package name */
    public Object f2483e = null;

    public h(a1 a1Var) {
        this.f2479a = a1Var;
    }

    @Override // androidx.recyclerview.widget.a1
    public final void a(int i7, int i8) {
        int i9;
        if (this.f2480b == 2 && (i9 = this.f2481c) >= i7 && i9 <= i7 + i8) {
            this.f2482d += i8;
            this.f2481c = i7;
        } else {
            e();
            this.f2481c = i7;
            this.f2482d = i8;
            this.f2480b = 2;
        }
    }

    @Override // androidx.recyclerview.widget.a1
    public final void b(int i7, int i8) {
        int i9;
        if (this.f2480b == 1 && i7 >= (i9 = this.f2481c)) {
            int i10 = this.f2482d;
            if (i7 <= i9 + i10) {
                this.f2482d = i10 + i8;
                this.f2481c = Math.min(i7, i9);
                return;
            }
        }
        e();
        this.f2481c = i7;
        this.f2482d = i8;
        this.f2480b = 1;
    }

    @Override // androidx.recyclerview.widget.a1
    public final void c(int i7, int i8) {
        e();
        this.f2479a.c(i7, i8);
    }

    @Override // androidx.recyclerview.widget.a1
    public final void d(int i7, int i8, Object obj) {
        int i9;
        if (this.f2480b == 3) {
            int i10 = this.f2481c;
            int i11 = this.f2482d;
            if (i7 <= i10 + i11 && (i9 = i7 + i8) >= i10 && this.f2483e == obj) {
                this.f2481c = Math.min(i7, i10);
                this.f2482d = Math.max(i11 + i10, i9) - this.f2481c;
                return;
            }
        }
        e();
        this.f2481c = i7;
        this.f2482d = i8;
        this.f2483e = obj;
        this.f2480b = 3;
    }

    public final void e() {
        int i7 = this.f2480b;
        if (i7 == 0) {
            return;
        }
        a1 a1Var = this.f2479a;
        if (i7 == 1) {
            a1Var.b(this.f2481c, this.f2482d);
        } else if (i7 == 2) {
            a1Var.a(this.f2481c, this.f2482d);
        } else if (i7 == 3) {
            a1Var.d(this.f2481c, this.f2482d, this.f2483e);
        }
        this.f2483e = null;
        this.f2480b = 0;
    }
}
