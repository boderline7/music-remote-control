package s5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w0 {

    /* renamed from: a, reason: collision with root package name */
    public androidx.appcompat.widget.y f8900a;

    /* renamed from: b, reason: collision with root package name */
    public q0 f8901b;

    /* renamed from: d, reason: collision with root package name */
    public String f8903d;

    /* renamed from: e, reason: collision with root package name */
    public a0 f8904e;

    /* renamed from: g, reason: collision with root package name */
    public a1 f8906g;

    /* renamed from: h, reason: collision with root package name */
    public x0 f8907h;

    /* renamed from: i, reason: collision with root package name */
    public x0 f8908i;

    /* renamed from: j, reason: collision with root package name */
    public x0 f8909j;

    /* renamed from: k, reason: collision with root package name */
    public long f8910k;

    /* renamed from: l, reason: collision with root package name */
    public long f8911l;

    /* renamed from: m, reason: collision with root package name */
    public x5.e f8912m;

    /* renamed from: c, reason: collision with root package name */
    public int f8902c = -1;

    /* renamed from: f, reason: collision with root package name */
    public b0 f8905f = new b0();

    public static void b(String str, x0 x0Var) {
        if (x0Var != null) {
            if (x0Var.f8919g != null) {
                throw new IllegalArgumentException(str.concat(".body != null").toString());
            }
            if (x0Var.f8920h != null) {
                throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
            }
            if (x0Var.f8921i != null) {
                throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
            }
            if (x0Var.f8922j != null) {
                throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
            }
        }
    }

    public final x0 a() {
        int i7 = this.f8902c;
        if (i7 < 0) {
            throw new IllegalStateException(("code < 0: " + this.f8902c).toString());
        }
        androidx.appcompat.widget.y yVar = this.f8900a;
        if (yVar == null) {
            throw new IllegalStateException("request == null".toString());
        }
        q0 q0Var = this.f8901b;
        if (q0Var == null) {
            throw new IllegalStateException("protocol == null".toString());
        }
        String str = this.f8903d;
        if (str != null) {
            return new x0(yVar, q0Var, str, i7, this.f8904e, this.f8905f.b(), this.f8906g, this.f8907h, this.f8908i, this.f8909j, this.f8910k, this.f8911l, this.f8912m);
        }
        throw new IllegalStateException("message == null".toString());
    }

    public final void c(androidx.appcompat.widget.y yVar) {
        i5.a.g("request", yVar);
        this.f8900a = yVar;
    }
}
