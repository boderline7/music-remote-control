package y5;

import androidx.appcompat.app.n;
import androidx.appcompat.widget.u;
import androidx.appcompat.widget.y;
import c5.r;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import n1.z;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import s5.a1;
import s5.b1;
import s5.f0;
import s5.h0;
import s5.j0;
import s5.o0;
import s5.r0;
import s5.v0;
import s5.w0;
import s5.x0;
import x5.o;
import x5.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k implements j0 {
    public static final j Companion = new j();

    /* renamed from: a, reason: collision with root package name */
    public final o0 f10225a;

    public k(o0 o0Var) {
        i5.a.g("client", o0Var);
        this.f10225a = o0Var;
    }

    public static int d(x0 x0Var, int i7) {
        String A = x0.A(x0Var, "Retry-After");
        if (A == null) {
            return i7;
        }
        Pattern compile = Pattern.compile("\\d+");
        i5.a.f("compile(pattern)", compile);
        if (!compile.matcher(A).matches()) {
            return u.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        Integer valueOf = Integer.valueOf(A);
        i5.a.f("valueOf(header)", valueOf);
        return valueOf.intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // s5.j0
    public final x0 a(h hVar) {
        r rVar;
        int i7;
        x5.e eVar;
        SSLSocketFactory sSLSocketFactory;
        f6.f fVar;
        s5.j jVar;
        y yVar = hVar.f10220e;
        x5.j jVar2 = hVar.f10216a;
        boolean z6 = true;
        r rVar2 = r.INSTANCE;
        x0 x0Var = null;
        int i8 = 0;
        y yVar2 = yVar;
        boolean z7 = true;
        while (true) {
            jVar2.getClass();
            i5.a.g("request", yVar2);
            if (jVar2.f9697l != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            synchronized (jVar2) {
                try {
                    if (!(jVar2.f9699n ^ z6)) {
                        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                    }
                    if (!(jVar2.f9698m ^ z6)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z7) {
                o oVar = jVar2.f9689d;
                h0 h0Var = (h0) yVar2.f1482b;
                boolean z8 = h0Var.f8800i;
                o0 o0Var = jVar2.f9686a;
                if (z8) {
                    SSLSocketFactory sSLSocketFactory2 = o0Var.f8856o;
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("CLEARTEXT-only client");
                    }
                    f6.f fVar2 = o0Var.f8860s;
                    jVar = o0Var.f8861t;
                    sSLSocketFactory = sSLSocketFactory2;
                    fVar = fVar2;
                } else {
                    sSLSocketFactory = null;
                    fVar = null;
                    jVar = null;
                }
                rVar = rVar2;
                i7 = i8;
                jVar2.f9694i = new x5.f(oVar, new s5.a(h0Var.f8795d, h0Var.f8796e, o0Var.f8852k, o0Var.f8855n, sSLSocketFactory, fVar, jVar, o0Var.f8854m, o0Var.f8859r, o0Var.f8858q, o0Var.f8853l), jVar2, jVar2.f9690e);
            } else {
                rVar = rVar2;
                i7 = i8;
            }
            try {
                if (jVar2.f9701p) {
                    throw new IOException("Canceled");
                }
                try {
                    try {
                        x0 b7 = hVar.b(yVar2);
                        if (x0Var != null) {
                            w0 C = b7.C();
                            w0 C2 = x0Var.C();
                            C2.f8906g = null;
                            x0 a7 = C2.a();
                            if (a7.f8919g != null) {
                                throw new IllegalArgumentException("priorResponse.body != null".toString());
                            }
                            C.f8909j = a7;
                            b7 = C.a();
                        }
                        x0Var = b7;
                        eVar = jVar2.f9697l;
                        yVar2 = b(x0Var, eVar);
                    } catch (RouteException e7) {
                        r rVar3 = rVar;
                        if (!c(e7.f8067b, jVar2, yVar2, false)) {
                            IOException iOException = e7.f8066a;
                            i5.a.g("<this>", iOException);
                            i5.a.g("suppressed", rVar3);
                            Iterator it = rVar3.iterator();
                            while (it.hasNext()) {
                                z.e(iOException, (Exception) it.next());
                            }
                            throw iOException;
                        }
                        IOException iOException2 = e7.f8066a;
                        i5.a.g("<this>", rVar3);
                        ArrayList arrayList = new ArrayList(rVar3.size() + 1);
                        arrayList.addAll(rVar3);
                        arrayList.add(iOException2);
                        jVar2.g(true);
                        z7 = false;
                        z6 = true;
                        i8 = i7;
                        rVar2 = arrayList;
                    }
                } catch (IOException e8) {
                    if (!c(e8, jVar2, yVar2, !(e8 instanceof ConnectionShutdownException))) {
                        r rVar4 = rVar;
                        i5.a.g("suppressed", rVar4);
                        Iterator it2 = rVar4.iterator();
                        while (it2.hasNext()) {
                            z.e(e8, (Exception) it2.next());
                        }
                        throw e8;
                    }
                    r rVar5 = rVar;
                    i5.a.g("<this>", rVar5);
                    ArrayList arrayList2 = new ArrayList(rVar5.size() + 1);
                    arrayList2.addAll(rVar5);
                    arrayList2.add(e8);
                    jVar2.g(true);
                    rVar2 = arrayList2;
                    z6 = true;
                    i8 = i7;
                    z7 = false;
                }
                if (yVar2 == null) {
                    if (eVar != null && eVar.f9668e) {
                        if (!(!jVar2.f9696k)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        jVar2.f9696k = true;
                        jVar2.f9691f.i();
                    }
                    jVar2.g(false);
                    return x0Var;
                }
                v0 v0Var = (v0) yVar2.f1485e;
                if (v0Var != null && v0Var.isOneShot()) {
                    jVar2.g(false);
                    return x0Var;
                }
                a1 a1Var = x0Var.f8919g;
                if (a1Var != null) {
                    t5.b.c(a1Var);
                }
                i8 = i7 + 1;
                if (i8 > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + i8);
                }
                jVar2.g(true);
                rVar2 = rVar;
                z7 = true;
                z6 = true;
            } catch (Throwable th2) {
                jVar2.g(true);
                throw th2;
            }
        }
    }

    public final y b(x0 x0Var, x5.e eVar) {
        String A;
        f0 f0Var;
        x5.m mVar;
        b1 b1Var = (eVar == null || (mVar = eVar.f9670g) == null) ? null : mVar.f9704a;
        int i7 = x0Var.f8916d;
        y yVar = x0Var.f8913a;
        String str = (String) yVar.f1483c;
        if (i7 != 307 && i7 != 308) {
            if (i7 == 401) {
                return this.f10225a.f8848g.a(b1Var, x0Var);
            }
            if (i7 == 421) {
                v0 v0Var = (v0) yVar.f1485e;
                if ((v0Var != null && v0Var.isOneShot()) || eVar == null || !(!i5.a.c(eVar.f9666c.f9672b.f8755i.f8795d, eVar.f9670g.f9704a.f8764a.f8755i.f8795d))) {
                    return null;
                }
                x5.m mVar2 = eVar.f9670g;
                synchronized (mVar2) {
                    mVar2.f9713j = true;
                }
                return x0Var.f8913a;
            }
            if (i7 == 503) {
                x0 x0Var2 = x0Var.f8922j;
                if ((x0Var2 == null || x0Var2.f8916d != 503) && d(x0Var, u.MAX_ACTIVITY_COUNT_UNLIMITED) == 0) {
                    return x0Var.f8913a;
                }
                return null;
            }
            if (i7 == 407) {
                i5.a.d(b1Var);
                if (b1Var.f8765b.type() == Proxy.Type.HTTP) {
                    return this.f10225a.f8854m.a(b1Var, x0Var);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (i7 == 408) {
                if (!this.f10225a.f8847f) {
                    return null;
                }
                v0 v0Var2 = (v0) yVar.f1485e;
                if (v0Var2 != null && v0Var2.isOneShot()) {
                    return null;
                }
                x0 x0Var3 = x0Var.f8922j;
                if ((x0Var3 == null || x0Var3.f8916d != 408) && d(x0Var, 0) <= 0) {
                    return x0Var.f8913a;
                }
                return null;
            }
            switch (i7) {
                case x2.d.DEFAULT_FADE_ANIM_DURATION /* 300 */:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        o0 o0Var = this.f10225a;
        if (!o0Var.f8849h || (A = x0.A(x0Var, "Location")) == null) {
            return null;
        }
        y yVar2 = x0Var.f8913a;
        h0 h0Var = (h0) yVar2.f1482b;
        h0Var.getClass();
        try {
            f0Var = new f0();
            f0Var.b(h0Var, A);
        } catch (IllegalArgumentException unused) {
            f0Var = null;
        }
        h0 a7 = f0Var != null ? f0Var.a() : null;
        if (a7 == null) {
            return null;
        }
        if (!i5.a.c(a7.f8792a, ((h0) yVar2.f1482b).f8792a) && !o0Var.f8850i) {
            return null;
        }
        r0 f7 = yVar2.f();
        if (g.a(str)) {
            g.INSTANCE.getClass();
            boolean c7 = i5.a.c(str, "PROPFIND");
            int i8 = x0Var.f8916d;
            boolean z6 = c7 || i8 == 308 || i8 == 307;
            if (!(!i5.a.c(str, "PROPFIND")) || i8 == 308 || i8 == 307) {
                f7.d(str, z6 ? (v0) yVar2.f1485e : null);
            } else {
                f7.d("GET", null);
            }
            if (!z6) {
                f7.e("Transfer-Encoding");
                f7.e("Content-Length");
                f7.e("Content-Type");
            }
        }
        if (!t5.b.a((h0) yVar2.f1482b, a7)) {
            f7.e("Authorization");
        }
        f7.f8886a = a7;
        return f7.a();
    }

    public final boolean c(IOException iOException, x5.j jVar, y yVar, boolean z6) {
        q qVar;
        x5.m mVar;
        v0 v0Var;
        if (!this.f10225a.f8847f) {
            return false;
        }
        if ((z6 && (((v0Var = (v0) yVar.f1485e) != null && v0Var.isOneShot()) || (iOException instanceof FileNotFoundException))) || (iOException instanceof ProtocolException) || (!(iOException instanceof InterruptedIOException) ? !(((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) : (iOException instanceof SocketTimeoutException) && !z6)) {
            return false;
        }
        x5.f fVar = jVar.f9694i;
        i5.a.d(fVar);
        int i7 = fVar.f9677g;
        if (i7 != 0 || fVar.f9678h != 0 || fVar.f9679i != 0) {
            if (fVar.f9680j == null) {
                b1 b1Var = null;
                if (i7 <= 1 && fVar.f9678h <= 1 && fVar.f9679i <= 0 && (mVar = fVar.f9673c.f9695j) != null) {
                    synchronized (mVar) {
                        if (mVar.f9714k == 0) {
                            if (t5.b.a(mVar.f9704a.f8764a.f8755i, fVar.f9672b.f8755i)) {
                                b1Var = mVar.f9704a;
                            }
                        }
                    }
                }
                if (b1Var != null) {
                    fVar.f9680j = b1Var;
                } else {
                    n nVar = fVar.f9675e;
                    if ((nVar == null || !nVar.f()) && (qVar = fVar.f9676f) != null && !qVar.a()) {
                    }
                }
            }
            return true;
        }
        return false;
    }
}
