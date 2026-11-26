package z5;

import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import o5.k;
import s5.d0;
import s5.h0;
import s5.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends c {

    /* renamed from: d, reason: collision with root package name */
    public final h0 f10304d;

    /* renamed from: e, reason: collision with root package name */
    public long f10305e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f10306f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ j f10307g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(j jVar, h0 h0Var) {
        super(jVar);
        i5.a.g("url", h0Var);
        this.f10307g = jVar;
        this.f10304d = h0Var;
        this.f10305e = -1L;
        this.f10306f = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f10299b) {
            return;
        }
        if (this.f10306f && !t5.b.g(this, TimeUnit.MILLISECONDS)) {
            this.f10307g.f10315b.k();
            A();
        }
        this.f10299b = true;
    }

    @Override // z5.c, g6.a0
    public final long k(g6.h hVar, long j7) {
        i5.a.g("sink", hVar);
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f10299b)) {
            throw new IllegalStateException("closed".toString());
        }
        if (!this.f10306f) {
            return -1L;
        }
        long j8 = this.f10305e;
        j jVar = this.f10307g;
        if (j8 == 0 || j8 == -1) {
            if (j8 != -1) {
                jVar.f10316c.p();
            }
            try {
                this.f10305e = jVar.f10316c.v();
                String obj = k.U(jVar.f10316c.p()).toString();
                if (this.f10305e < 0 || (obj.length() > 0 && !k.R(obj, ";", false))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f10305e + obj + '\"');
                }
                if (this.f10305e == 0) {
                    this.f10306f = false;
                    jVar.f10320g = jVar.f10319f.a();
                    o0 o0Var = jVar.f10314a;
                    i5.a.d(o0Var);
                    d0 d0Var = jVar.f10320g;
                    i5.a.d(d0Var);
                    y5.f.d(o0Var.f8851j, this.f10304d, d0Var);
                    A();
                }
                if (!this.f10306f) {
                    return -1L;
                }
            } catch (NumberFormatException e7) {
                throw new ProtocolException(e7.getMessage());
            }
        }
        long k7 = super.k(hVar, Math.min(j7, this.f10305e));
        if (k7 != -1) {
            this.f10305e -= k7;
            return k7;
        }
        jVar.f10315b.k();
        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
        A();
        throw protocolException;
    }
}
