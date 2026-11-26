package a6;

import com.navi.tool.service.ConfigBackupService;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.http2.StreamResetException;
import s5.q0;
import s5.w0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y implements y5.e {
    public static final x Companion = new x();

    /* renamed from: g, reason: collision with root package name */
    public static final List f503g = t5.b.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", d.TARGET_METHOD_UTF8, d.TARGET_PATH_UTF8, d.TARGET_SCHEME_UTF8, d.TARGET_AUTHORITY_UTF8);

    /* renamed from: h, reason: collision with root package name */
    public static final List f504h = t5.b.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* renamed from: a, reason: collision with root package name */
    public final x5.m f505a;

    /* renamed from: b, reason: collision with root package name */
    public final y5.h f506b;

    /* renamed from: c, reason: collision with root package name */
    public final w f507c;

    /* renamed from: d, reason: collision with root package name */
    public volatile g0 f508d;

    /* renamed from: e, reason: collision with root package name */
    public final q0 f509e;

    /* renamed from: f, reason: collision with root package name */
    public volatile boolean f510f;

    public y(s5.o0 o0Var, x5.m mVar, y5.h hVar, w wVar) {
        i5.a.g("connection", mVar);
        this.f505a = mVar;
        this.f506b = hVar;
        this.f507c = wVar;
        q0 q0Var = q0.H2_PRIOR_KNOWLEDGE;
        this.f509e = o0Var.f8859r.contains(q0Var) ? q0Var : q0.HTTP_2;
    }

    @Override // y5.e
    public final long a(x0 x0Var) {
        if (y5.f.a(x0Var)) {
            return t5.b.j(x0Var);
        }
        return 0L;
    }

    @Override // y5.e
    public final g6.y b(androidx.appcompat.widget.y yVar, long j7) {
        g0 g0Var = this.f508d;
        i5.a.d(g0Var);
        return g0Var.f();
    }

    @Override // y5.e
    public final g6.a0 c(x0 x0Var) {
        g0 g0Var = this.f508d;
        i5.a.d(g0Var);
        return g0Var.f419i;
    }

    @Override // y5.e
    public final void cancel() {
        this.f510f = true;
        g0 g0Var = this.f508d;
        if (g0Var != null) {
            g0Var.e(b.CANCEL);
        }
    }

    @Override // y5.e
    public final void d() {
        g0 g0Var = this.f508d;
        i5.a.d(g0Var);
        g0Var.f().close();
    }

    @Override // y5.e
    public final void e() {
        this.f507c.flush();
    }

    @Override // y5.e
    public final w0 f(boolean z6) {
        s5.d0 d0Var;
        g0 g0Var = this.f508d;
        if (g0Var == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (g0Var) {
            g0Var.f421k.h();
            while (g0Var.f417g.isEmpty() && g0Var.f423m == null) {
                try {
                    g0Var.k();
                } catch (Throwable th) {
                    g0Var.f421k.l();
                    throw th;
                }
            }
            g0Var.f421k.l();
            if (!(!g0Var.f417g.isEmpty())) {
                IOException iOException = g0Var.f424n;
                if (iOException != null) {
                    throw iOException;
                }
                b bVar = g0Var.f423m;
                i5.a.d(bVar);
                throw new StreamResetException(bVar);
            }
            Object removeFirst = g0Var.f417g.removeFirst();
            i5.a.f("headersQueue.removeFirst()", removeFirst);
            d0Var = (s5.d0) removeFirst;
        }
        x xVar = Companion;
        q0 q0Var = this.f509e;
        xVar.getClass();
        i5.a.g("protocol", q0Var);
        ArrayList arrayList = new ArrayList(20);
        int size = d0Var.size();
        y5.m mVar = null;
        for (int i7 = 0; i7 < size; i7++) {
            String b7 = d0Var.b(i7);
            String d7 = d0Var.d(i7);
            if (i5.a.c(b7, d.RESPONSE_STATUS_UTF8)) {
                y5.m.Companion.getClass();
                mVar = y5.l.a("HTTP/1.1 " + d7);
            } else if (!f504h.contains(b7)) {
                i5.a.g("name", b7);
                i5.a.g("value", d7);
                arrayList.add(b7);
                arrayList.add(o5.k.U(d7).toString());
            }
        }
        if (mVar == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        w0 w0Var = new w0();
        w0Var.f8901b = q0Var;
        w0Var.f8902c = mVar.f10227b;
        String str = mVar.f10228c;
        i5.a.g(ConfigBackupService.EXTRA_MESSAGE, str);
        w0Var.f8903d = str;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        s5.b0 b0Var = new s5.b0();
        ArrayList arrayList2 = b0Var.f8763a;
        i5.a.g("<this>", arrayList2);
        i5.a.g("elements", strArr);
        List asList = Arrays.asList(strArr);
        i5.a.f("asList(this)", asList);
        arrayList2.addAll(asList);
        w0Var.f8905f = b0Var;
        if (z6 && w0Var.f8902c == 100) {
            return null;
        }
        return w0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0100 A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #2 {all -> 0x00c9, blocks: (B:30:0x00bc, B:32:0x00c3, B:33:0x00cc, B:35:0x00d0, B:37:0x00e6, B:39:0x00ee, B:43:0x00fa, B:45:0x0100, B:77:0x0192, B:78:0x0197), top: B:29:0x00bc, outer: #0 }] */
    @Override // y5.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(androidx.appcompat.widget.y r19) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.y.g(androidx.appcompat.widget.y):void");
    }

    @Override // y5.e
    public final x5.m h() {
        return this.f505a;
    }
}
