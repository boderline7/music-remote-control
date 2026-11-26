package s5;

import java.io.Closeable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x0 implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final androidx.appcompat.widget.y f8913a;

    /* renamed from: b, reason: collision with root package name */
    public final q0 f8914b;

    /* renamed from: c, reason: collision with root package name */
    public final String f8915c;

    /* renamed from: d, reason: collision with root package name */
    public final int f8916d;

    /* renamed from: e, reason: collision with root package name */
    public final a0 f8917e;

    /* renamed from: f, reason: collision with root package name */
    public final d0 f8918f;

    /* renamed from: g, reason: collision with root package name */
    public final a1 f8919g;

    /* renamed from: h, reason: collision with root package name */
    public final x0 f8920h;

    /* renamed from: i, reason: collision with root package name */
    public final x0 f8921i;

    /* renamed from: j, reason: collision with root package name */
    public final x0 f8922j;

    /* renamed from: k, reason: collision with root package name */
    public final long f8923k;

    /* renamed from: l, reason: collision with root package name */
    public final long f8924l;

    /* renamed from: m, reason: collision with root package name */
    public final x5.e f8925m;

    public x0(androidx.appcompat.widget.y yVar, q0 q0Var, String str, int i7, a0 a0Var, d0 d0Var, a1 a1Var, x0 x0Var, x0 x0Var2, x0 x0Var3, long j7, long j8, x5.e eVar) {
        this.f8913a = yVar;
        this.f8914b = q0Var;
        this.f8915c = str;
        this.f8916d = i7;
        this.f8917e = a0Var;
        this.f8918f = d0Var;
        this.f8919g = a1Var;
        this.f8920h = x0Var;
        this.f8921i = x0Var2;
        this.f8922j = x0Var3;
        this.f8923k = j7;
        this.f8924l = j8;
        this.f8925m = eVar;
    }

    public static String A(x0 x0Var, String str) {
        x0Var.getClass();
        String a7 = x0Var.f8918f.a(str);
        if (a7 == null) {
            return null;
        }
        return a7;
    }

    public final boolean B() {
        int i7 = this.f8916d;
        return 200 <= i7 && i7 < 300;
    }

    public final w0 C() {
        w0 w0Var = new w0();
        w0Var.f8900a = this.f8913a;
        w0Var.f8901b = this.f8914b;
        w0Var.f8902c = this.f8916d;
        w0Var.f8903d = this.f8915c;
        w0Var.f8904e = this.f8917e;
        w0Var.f8905f = this.f8918f.c();
        w0Var.f8906g = this.f8919g;
        w0Var.f8907h = this.f8920h;
        w0Var.f8908i = this.f8921i;
        w0Var.f8909j = this.f8922j;
        w0Var.f8910k = this.f8923k;
        w0Var.f8911l = this.f8924l;
        w0Var.f8912m = this.f8925m;
        return w0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a1 a1Var = this.f8919g;
        if (a1Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        a1Var.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.f8914b + ", code=" + this.f8916d + ", message=" + this.f8915c + ", url=" + ((h0) this.f8913a.f1482b) + '}';
    }
}
