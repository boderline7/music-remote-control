package x5;

import g6.t;
import java.io.IOException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;
import s5.v0;
import s5.w0;
import s5.x0;
import s5.y;
import s5.y0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final j f9664a;

    /* renamed from: b, reason: collision with root package name */
    public final y f9665b;

    /* renamed from: c, reason: collision with root package name */
    public final f f9666c;

    /* renamed from: d, reason: collision with root package name */
    public final y5.e f9667d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f9668e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f9669f;

    /* renamed from: g, reason: collision with root package name */
    public final m f9670g;

    public e(j jVar, y yVar, f fVar, y5.e eVar) {
        i5.a.g("eventListener", yVar);
        this.f9664a = jVar;
        this.f9665b = yVar;
        this.f9666c = fVar;
        this.f9667d = eVar;
        this.f9670g = eVar.h();
    }

    public final IOException a(boolean z6, boolean z7, IOException iOException) {
        if (iOException != null) {
            e(iOException);
        }
        y yVar = this.f9665b;
        j jVar = this.f9664a;
        if (z7) {
            if (iOException != null) {
                yVar.getClass();
                i5.a.g("call", jVar);
            } else {
                yVar.getClass();
                i5.a.g("call", jVar);
            }
        }
        if (z6) {
            if (iOException != null) {
                yVar.getClass();
                i5.a.g("call", jVar);
            } else {
                yVar.getClass();
                i5.a.g("call", jVar);
            }
        }
        return jVar.i(this, z7, z6, iOException);
    }

    public final c b(androidx.appcompat.widget.y yVar, boolean z6) {
        this.f9668e = z6;
        v0 v0Var = (v0) yVar.f1485e;
        i5.a.d(v0Var);
        long contentLength = v0Var.contentLength();
        this.f9665b.getClass();
        i5.a.g("call", this.f9664a);
        return new c(this, this.f9667d.b(yVar, contentLength), contentLength);
    }

    public final y0 c(x0 x0Var) {
        y5.e eVar = this.f9667d;
        try {
            String A = x0.A(x0Var, "Content-Type");
            long a7 = eVar.a(x0Var);
            return new y0(A, a7, new t(new d(this, eVar.c(x0Var), a7)));
        } catch (IOException e7) {
            this.f9665b.getClass();
            i5.a.g("call", this.f9664a);
            e(e7);
            throw e7;
        }
    }

    public final w0 d(boolean z6) {
        try {
            w0 f7 = this.f9667d.f(z6);
            if (f7 != null) {
                f7.f8912m = this;
            }
            return f7;
        } catch (IOException e7) {
            this.f9665b.getClass();
            i5.a.g("call", this.f9664a);
            e(e7);
            throw e7;
        }
    }

    public final void e(IOException iOException) {
        this.f9669f = true;
        this.f9666c.c(iOException);
        m h7 = this.f9667d.h();
        j jVar = this.f9664a;
        synchronized (h7) {
            try {
                i5.a.g("call", jVar);
                if (iOException instanceof StreamResetException) {
                    if (((StreamResetException) iOException).f8068a == a6.b.REFUSED_STREAM) {
                        int i7 = h7.f9716m + 1;
                        h7.f9716m = i7;
                        if (i7 > 1) {
                            h7.f9712i = true;
                            h7.f9714k++;
                        }
                    } else if (((StreamResetException) iOException).f8068a != a6.b.CANCEL || !jVar.f9701p) {
                        h7.f9712i = true;
                        h7.f9714k++;
                    }
                } else if (h7.f9709f == null || (iOException instanceof ConnectionShutdownException)) {
                    h7.f9712i = true;
                    if (h7.f9715l == 0) {
                        m.d(jVar.f9686a, h7.f9704a, iOException);
                        h7.f9714k++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
