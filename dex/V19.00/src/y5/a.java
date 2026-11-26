package y5;

import a6.k0;
import androidx.appcompat.widget.y;
import c5.r;
import g6.n;
import s5.a1;
import s5.b0;
import s5.d0;
import s5.h0;
import s5.j0;
import s5.l0;
import s5.r0;
import s5.t;
import s5.v0;
import s5.w0;
import s5.x0;
import s5.y0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements j0 {

    /* renamed from: a, reason: collision with root package name */
    public final t f10210a;

    public a(t tVar) {
        i5.a.g("cookieJar", tVar);
        this.f10210a = tVar;
    }

    @Override // s5.j0
    public final x0 a(h hVar) {
        a1 a1Var;
        y yVar = hVar.f10220e;
        r0 f7 = yVar.f();
        v0 v0Var = (v0) yVar.f1485e;
        if (v0Var != null) {
            l0 contentType = v0Var.contentType();
            if (contentType != null) {
                f7.c("Content-Type", contentType.f8812a);
            }
            long contentLength = v0Var.contentLength();
            if (contentLength != -1) {
                f7.c("Content-Length", String.valueOf(contentLength));
                f7.e("Transfer-Encoding");
            } else {
                f7.c("Transfer-Encoding", "chunked");
                f7.e("Content-Length");
            }
        }
        String e7 = yVar.e("Host");
        boolean z6 = false;
        Object obj = yVar.f1482b;
        if (e7 == null) {
            f7.c("Host", t5.b.w((h0) obj, false));
        }
        if (yVar.e("Connection") == null) {
            f7.c("Connection", "Keep-Alive");
        }
        if (yVar.e("Accept-Encoding") == null && yVar.e("Range") == null) {
            f7.c("Accept-Encoding", "gzip");
            z6 = true;
        }
        h0 h0Var = (h0) obj;
        t tVar = this.f10210a;
        ((k0) tVar).getClass();
        i5.a.g("url", h0Var);
        r.INSTANCE.getClass();
        if (yVar.e("User-Agent") == null) {
            f7.c("User-Agent", t5.b.userAgent);
        }
        x0 b7 = hVar.b(f7.a());
        d0 d0Var = b7.f8918f;
        f.d(tVar, h0Var, d0Var);
        w0 C = b7.C();
        C.c(yVar);
        if (z6 && o5.k.C("gzip", x0.A(b7, "Content-Encoding")) && f.a(b7) && (a1Var = b7.f8919g) != null) {
            n nVar = new n(((y0) a1Var).f8928c);
            b0 c7 = d0Var.c();
            c7.c("Content-Encoding");
            c7.c("Content-Length");
            C.f8905f = c7.b().c();
            C.f8906g = new y0(x0.A(b7, "Content-Type"), -1L, new g6.t(nVar));
        }
        return C.a();
    }
}
