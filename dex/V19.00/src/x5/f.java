package x5;

import java.io.IOException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;
import s5.b1;
import s5.h0;
import s5.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final o f9671a;

    /* renamed from: b, reason: collision with root package name */
    public final s5.a f9672b;

    /* renamed from: c, reason: collision with root package name */
    public final j f9673c;

    /* renamed from: d, reason: collision with root package name */
    public final y f9674d;

    /* renamed from: e, reason: collision with root package name */
    public androidx.appcompat.app.n f9675e;

    /* renamed from: f, reason: collision with root package name */
    public q f9676f;

    /* renamed from: g, reason: collision with root package name */
    public int f9677g;

    /* renamed from: h, reason: collision with root package name */
    public int f9678h;

    /* renamed from: i, reason: collision with root package name */
    public int f9679i;

    /* renamed from: j, reason: collision with root package name */
    public b1 f9680j;

    public f(o oVar, s5.a aVar, j jVar, y yVar) {
        i5.a.g("connectionPool", oVar);
        i5.a.g("eventListener", yVar);
        this.f9671a = oVar;
        this.f9672b = aVar;
        this.f9673c = jVar;
        this.f9674d = yVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0345 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final x5.m a(int r15, int r16, int r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 915
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.f.a(int, int, int, boolean, boolean):x5.m");
    }

    public final boolean b(h0 h0Var) {
        i5.a.g("url", h0Var);
        h0 h0Var2 = this.f9672b.f8755i;
        return h0Var.f8796e == h0Var2.f8796e && i5.a.c(h0Var.f8795d, h0Var2.f8795d);
    }

    public final void c(IOException iOException) {
        i5.a.g("e", iOException);
        this.f9680j = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).f8068a == a6.b.REFUSED_STREAM) {
            this.f9677g++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.f9678h++;
        } else {
            this.f9679i++;
        }
    }
}
