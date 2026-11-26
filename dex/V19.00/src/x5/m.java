package x5;

import a6.g0;
import a6.i0;
import a6.w;
import androidx.appcompat.widget.u;
import c5.r;
import g6.s;
import g6.t;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import n1.z;
import okhttp3.internal.connection.RouteException;
import s5.a0;
import s5.b1;
import s5.h0;
import s5.o0;
import s5.p0;
import s5.q0;
import s5.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends a6.m {
    public static final k Companion = new k();
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;

    /* renamed from: a, reason: collision with root package name */
    public final b1 f9704a;

    /* renamed from: b, reason: collision with root package name */
    public Socket f9705b;

    /* renamed from: c, reason: collision with root package name */
    public Socket f9706c;

    /* renamed from: d, reason: collision with root package name */
    public a0 f9707d;

    /* renamed from: e, reason: collision with root package name */
    public q0 f9708e;

    /* renamed from: f, reason: collision with root package name */
    public w f9709f;

    /* renamed from: g, reason: collision with root package name */
    public t f9710g;

    /* renamed from: h, reason: collision with root package name */
    public s f9711h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f9712i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f9713j;

    /* renamed from: k, reason: collision with root package name */
    public int f9714k;

    /* renamed from: l, reason: collision with root package name */
    public int f9715l;

    /* renamed from: m, reason: collision with root package name */
    public int f9716m;

    /* renamed from: n, reason: collision with root package name */
    public int f9717n;

    /* renamed from: o, reason: collision with root package name */
    public final ArrayList f9718o;

    /* renamed from: p, reason: collision with root package name */
    public long f9719p;

    public m(o oVar, b1 b1Var) {
        i5.a.g("connectionPool", oVar);
        i5.a.g("route", b1Var);
        this.f9704a = b1Var;
        this.f9717n = 1;
        this.f9718o = new ArrayList();
        this.f9719p = Long.MAX_VALUE;
    }

    public static void d(o0 o0Var, b1 b1Var, IOException iOException) {
        i5.a.g("client", o0Var);
        i5.a.g("failedRoute", b1Var);
        i5.a.g("failure", iOException);
        if (b1Var.f8765b.type() != Proxy.Type.DIRECT) {
            s5.a aVar = b1Var.f8764a;
            aVar.f8754h.connectFailed(aVar.f8755i.g(), b1Var.f8765b.address(), iOException);
        }
        v2.a aVar2 = o0Var.f8866y;
        synchronized (aVar2) {
            ((Set) aVar2.f9388b).add(b1Var);
        }
    }

    @Override // a6.m
    public final synchronized void a(w wVar, a6.o0 o0Var) {
        i5.a.g("connection", wVar);
        i5.a.g("settings", o0Var);
        this.f9717n = (o0Var.f454a & 16) != 0 ? o0Var.f455b[4] : u.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    @Override // a6.m
    public final void b(g0 g0Var) {
        i5.a.g("stream", g0Var);
        g0Var.c(a6.b.REFUSED_STREAM, null);
    }

    public final void c(int i7, int i8, int i9, boolean z6, j jVar, y yVar) {
        b1 b1Var;
        i5.a.g("call", jVar);
        i5.a.g("eventListener", yVar);
        if (this.f9708e != null) {
            throw new IllegalStateException("already connected".toString());
        }
        List list = this.f9704a.f8764a.f8757k;
        b bVar = new b(list);
        s5.a aVar = this.f9704a.f8764a;
        if (aVar.f8749c == null) {
            if (!list.contains(s5.p.CLEARTEXT)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String str = this.f9704a.f8764a.f8755i.f8795d;
            b6.s.Companion.getClass();
            if (!b6.s.f3002a.h(str)) {
                throw new RouteException(new UnknownServiceException(a.a.h("CLEARTEXT communication to ", str, " not permitted by network security policy")));
            }
        } else if (aVar.f8756j.contains(q0.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        do {
            try {
                b1 b1Var2 = this.f9704a;
                if (b1Var2.f8764a.f8749c == null || b1Var2.f8765b.type() != Proxy.Type.HTTP) {
                    try {
                        e(i7, i8, jVar, yVar);
                    } catch (IOException e7) {
                        e = e7;
                        Socket socket = this.f9706c;
                        if (socket != null) {
                            t5.b.d(socket);
                        }
                        Socket socket2 = this.f9705b;
                        if (socket2 != null) {
                            t5.b.d(socket2);
                        }
                        this.f9706c = null;
                        this.f9705b = null;
                        this.f9710g = null;
                        this.f9711h = null;
                        this.f9707d = null;
                        this.f9708e = null;
                        this.f9709f = null;
                        this.f9717n = 1;
                        b1 b1Var3 = this.f9704a;
                        InetSocketAddress inetSocketAddress = b1Var3.f8766c;
                        Proxy proxy = b1Var3.f8765b;
                        i5.a.g("inetSocketAddress", inetSocketAddress);
                        i5.a.g("proxy", proxy);
                        if (routeException == null) {
                            routeException = new RouteException(e);
                        } else {
                            z.e(routeException.f8066a, e);
                            routeException.f8067b = e;
                        }
                        if (!z6) {
                            throw routeException;
                        }
                        bVar.f9650d = true;
                        if (!bVar.f9649c) {
                            throw routeException;
                        }
                        if (e instanceof ProtocolException) {
                            throw routeException;
                        }
                        if (e instanceof InterruptedIOException) {
                            throw routeException;
                        }
                        if ((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) {
                            throw routeException;
                        }
                        if (e instanceof SSLPeerUnverifiedException) {
                            throw routeException;
                        }
                    }
                } else {
                    f(i7, i8, i9, jVar, yVar);
                    if (this.f9705b == null) {
                        b1Var = this.f9704a;
                        if (b1Var.f8764a.f8749c == null && b1Var.f8765b.type() == Proxy.Type.HTTP && this.f9705b == null) {
                            throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                        }
                        this.f9719p = System.nanoTime();
                        return;
                    }
                }
                g(bVar, jVar, yVar);
                b1 b1Var4 = this.f9704a;
                InetSocketAddress inetSocketAddress2 = b1Var4.f8766c;
                Proxy proxy2 = b1Var4.f8765b;
                i5.a.g("inetSocketAddress", inetSocketAddress2);
                i5.a.g("proxy", proxy2);
                b1Var = this.f9704a;
                if (b1Var.f8764a.f8749c == null) {
                }
                this.f9719p = System.nanoTime();
                return;
            } catch (IOException e8) {
                e = e8;
            }
        } while (e instanceof SSLException);
        throw routeException;
    }

    public final void e(int i7, int i8, j jVar, y yVar) {
        Socket createSocket;
        b1 b1Var = this.f9704a;
        Proxy proxy = b1Var.f8765b;
        s5.a aVar = b1Var.f8764a;
        Proxy.Type type = proxy.type();
        int i9 = type == null ? -1 : l.$EnumSwitchMapping$0[type.ordinal()];
        if (i9 == 1 || i9 == 2) {
            createSocket = aVar.f8748b.createSocket();
            i5.a.d(createSocket);
        } else {
            createSocket = new Socket(proxy);
        }
        this.f9705b = createSocket;
        InetSocketAddress inetSocketAddress = this.f9704a.f8766c;
        yVar.getClass();
        i5.a.g("call", jVar);
        i5.a.g("inetSocketAddress", inetSocketAddress);
        createSocket.setSoTimeout(i8);
        try {
            b6.s.Companion.getClass();
            b6.s.f3002a.e(createSocket, this.f9704a.f8766c, i7);
            try {
                this.f9710g = new t(i5.a.r(createSocket));
                this.f9711h = new s(i5.a.q(createSocket));
            } catch (NullPointerException e7) {
                if (i5.a.c(e7.getMessage(), "throw with null exception")) {
                    throw new IOException(e7);
                }
            }
        } catch (ConnectException e8) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f9704a.f8766c);
            connectException.initCause(e8);
            throw connectException;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x015e, code lost:
    
        if (r3 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0161, code lost:
    
        r8 = r20.f9705b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0163, code lost:
    
        if (r8 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0165, code lost:
    
        t5.b.d(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0168, code lost:
    
        r20.f9705b = null;
        r20.f9711h = null;
        r20.f9710g = null;
        i5.a.g("call", r24);
        i5.a.g("inetSocketAddress", r4.f8766c);
        i5.a.g("proxy", r4.f8765b);
        r13 = r19 + 1;
        r6 = null;
        r8 = true;
        r1 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(int r21, int r22, int r23, x5.j r24, s5.y r25) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.m.f(int, int, int, x5.j, s5.y):void");
    }

    public final void g(b bVar, j jVar, y yVar) {
        q0 q0Var;
        s5.a aVar = this.f9704a.f8764a;
        if (aVar.f8749c == null) {
            List list = aVar.f8756j;
            q0 q0Var2 = q0.H2_PRIOR_KNOWLEDGE;
            if (!list.contains(q0Var2)) {
                this.f9706c = this.f9705b;
                this.f9708e = q0.HTTP_1_1;
                return;
            } else {
                this.f9706c = this.f9705b;
                this.f9708e = q0Var2;
                l();
                return;
            }
        }
        yVar.getClass();
        i5.a.g("call", jVar);
        s5.a aVar2 = this.f9704a.f8764a;
        SSLSocketFactory sSLSocketFactory = aVar2.f8749c;
        SSLSocket sSLSocket = null;
        String str = null;
        try {
            i5.a.d(sSLSocketFactory);
            Socket socket = this.f9705b;
            h0 h0Var = aVar2.f8755i;
            Socket createSocket = sSLSocketFactory.createSocket(socket, h0Var.f8795d, h0Var.f8796e, true);
            i5.a.e("null cannot be cast to non-null type javax.net.ssl.SSLSocket", createSocket);
            SSLSocket sSLSocket2 = (SSLSocket) createSocket;
            try {
                s5.p a7 = bVar.a(sSLSocket2);
                if (a7.f8868b) {
                    b6.s.Companion.getClass();
                    b6.s.f3002a.d(sSLSocket2, aVar2.f8755i.f8795d, aVar2.f8756j);
                }
                sSLSocket2.startHandshake();
                SSLSession session = sSLSocket2.getSession();
                s5.z zVar = a0.Companion;
                i5.a.f("sslSocketSession", session);
                zVar.getClass();
                a0 a8 = s5.z.a(session);
                HostnameVerifier hostnameVerifier = aVar2.f8750d;
                i5.a.d(hostnameVerifier);
                if (!hostnameVerifier.verify(aVar2.f8755i.f8795d, session)) {
                    List a9 = a8.a();
                    if (!(!a9.isEmpty())) {
                        throw new SSLPeerUnverifiedException("Hostname " + aVar2.f8755i.f8795d + " not verified (no certificates)");
                    }
                    Object obj = a9.get(0);
                    i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", obj);
                    X509Certificate x509Certificate = (X509Certificate) obj;
                    StringBuilder sb = new StringBuilder("\n              |Hostname ");
                    sb.append(aVar2.f8755i.f8795d);
                    sb.append(" not verified:\n              |    certificate: ");
                    s5.j.Companion.getClass();
                    StringBuilder sb2 = new StringBuilder("sha256/");
                    g6.k kVar = g6.l.Companion;
                    byte[] encoded = x509Certificate.getPublicKey().getEncoded();
                    i5.a.f("publicKey.encoded", encoded);
                    sb2.append(g6.k.c(kVar, encoded).b("SHA-256").a());
                    sb.append(sb2.toString());
                    sb.append("\n              |    DN: ");
                    sb.append(x509Certificate.getSubjectDN().getName());
                    sb.append("\n              |    subjectAltNames: ");
                    f6.f.INSTANCE.getClass();
                    sb.append(f6.f.a(x509Certificate));
                    sb.append("\n              ");
                    throw new SSLPeerUnverifiedException(i5.a.x(sb.toString()));
                }
                s5.j jVar2 = aVar2.f8751e;
                i5.a.d(jVar2);
                this.f9707d = new a0(a8.f8758a, a8.f8759b, a8.f8760c, new s5.i(jVar2, a8, aVar2, 1));
                i5.a.g("hostname", aVar2.f8755i.f8795d);
                r rVar = r.INSTANCE;
                Iterator it = jVar2.f8806a.iterator();
                if (it.hasNext()) {
                    a.a.u(it.next());
                    throw null;
                }
                rVar.getClass();
                if (a7.f8868b) {
                    b6.s.Companion.getClass();
                    str = b6.s.f3002a.f(sSLSocket2);
                }
                this.f9706c = sSLSocket2;
                this.f9710g = new t(i5.a.r(sSLSocket2));
                this.f9711h = new s(i5.a.q(sSLSocket2));
                if (str != null) {
                    q0.Companion.getClass();
                    q0Var = p0.a(str);
                } else {
                    q0Var = q0.HTTP_1_1;
                }
                this.f9708e = q0Var;
                b6.s.Companion.getClass();
                b6.s.f3002a.a(sSLSocket2);
                if (this.f9708e == q0.HTTP_2) {
                    l();
                }
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    b6.s.Companion.getClass();
                    b6.s.f3002a.a(sSLSocket);
                }
                if (sSLSocket != null) {
                    t5.b.d(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x010a, code lost:
    
        if (f6.f.d(r2, (java.security.cert.X509Certificate) r1) != false) goto L68;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean h(s5.a r12, java.util.List r13) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.m.h(s5.a, java.util.List):boolean");
    }

    public final boolean i(boolean z6) {
        long j7;
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        long nanoTime = System.nanoTime();
        Socket socket = this.f9705b;
        i5.a.d(socket);
        Socket socket2 = this.f9706c;
        i5.a.d(socket2);
        t tVar = this.f9710g;
        i5.a.d(tVar);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        w wVar = this.f9709f;
        if (wVar != null) {
            synchronized (wVar) {
                if (wVar.f483g) {
                    return false;
                }
                if (wVar.f492p < wVar.f491o) {
                    if (nanoTime >= wVar.f493q) {
                        return false;
                    }
                }
                return true;
            }
        }
        synchronized (this) {
            j7 = nanoTime - this.f9719p;
        }
        if (j7 < IDLE_CONNECTION_HEALTHY_NS || !z6) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                boolean z7 = !tVar.t();
                socket2.setSoTimeout(soTimeout);
                return z7;
            } catch (Throwable th) {
                socket2.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final y5.e j(o0 o0Var, y5.h hVar) {
        Socket socket = this.f9706c;
        i5.a.d(socket);
        t tVar = this.f9710g;
        i5.a.d(tVar);
        s sVar = this.f9711h;
        i5.a.d(sVar);
        w wVar = this.f9709f;
        if (wVar != null) {
            return new a6.y(o0Var, this, hVar, wVar);
        }
        int i7 = hVar.f10222g;
        socket.setSoTimeout(i7);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        tVar.f6593a.a().g(i7, timeUnit);
        sVar.f6590a.a().g(hVar.f10223h, timeUnit);
        return new z5.j(o0Var, this, tVar, sVar);
    }

    public final synchronized void k() {
        this.f9712i = true;
    }

    public final void l() {
        String concat;
        Socket socket = this.f9706c;
        i5.a.d(socket);
        t tVar = this.f9710g;
        i5.a.d(tVar);
        s sVar = this.f9711h;
        i5.a.d(sVar);
        int i7 = 0;
        socket.setSoTimeout(0);
        w5.g gVar = w5.g.INSTANCE;
        a6.i iVar = new a6.i(gVar);
        String str = this.f9704a.f8764a.f8755i.f8795d;
        i5.a.g("peerName", str);
        iVar.f430c = socket;
        if (iVar.f428a) {
            concat = t5.b.okHttpName + ' ' + str;
        } else {
            concat = "MockWebServer ".concat(str);
        }
        i5.a.g("<set-?>", concat);
        iVar.f431d = concat;
        iVar.f432e = tVar;
        iVar.f433f = sVar;
        iVar.f434g = this;
        iVar.f436i = 0;
        w wVar = new w(iVar);
        this.f9709f = wVar;
        w.Companion.getClass();
        a6.o0 o0Var = w.B;
        this.f9717n = (o0Var.f454a & 16) != 0 ? o0Var.f455b[4] : u.MAX_ACTIVITY_COUNT_UNLIMITED;
        i0 i0Var = wVar.f501y;
        synchronized (i0Var) {
            try {
                if (i0Var.f442e) {
                    throw new IOException("closed");
                }
                if (i0Var.f439b) {
                    Logger logger = i0.f437g;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(t5.b.h(">> CONNECTION " + a6.h.CONNECTION_PREFACE.d(), new Object[0]));
                    }
                    i0Var.f438a.l(a6.h.CONNECTION_PREFACE);
                    i0Var.f438a.flush();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        i0 i0Var2 = wVar.f501y;
        a6.o0 o0Var2 = wVar.f494r;
        synchronized (i0Var2) {
            try {
                i5.a.g("settings", o0Var2);
                if (i0Var2.f442e) {
                    throw new IOException("closed");
                }
                i0Var2.C(0, Integer.bitCount(o0Var2.f454a) * 6, 4, 0);
                int i8 = 0;
                while (i8 < 10) {
                    if (((1 << i8) & o0Var2.f454a) != 0) {
                        i0Var2.f438a.j(i8 != 4 ? i8 != 7 ? i8 : 4 : 3);
                        i0Var2.f438a.o(o0Var2.f455b[i8]);
                    }
                    i8++;
                }
                i0Var2.f438a.flush();
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (wVar.f494r.a() != 65535) {
            wVar.f501y.G(0, r1 - 65535);
        }
        gVar.f().c(new w5.b(i7, wVar.f502z, wVar.f480d), 0L);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        b1 b1Var = this.f9704a;
        sb.append(b1Var.f8764a.f8755i.f8795d);
        sb.append(':');
        sb.append(b1Var.f8764a.f8755i.f8796e);
        sb.append(", proxy=");
        sb.append(b1Var.f8765b);
        sb.append(" hostAddress=");
        sb.append(b1Var.f8766c);
        sb.append(" cipherSuite=");
        a0 a0Var = this.f9707d;
        if (a0Var == null || (obj = a0Var.f8759b) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f9708e);
        sb.append('}');
        return sb.toString();
    }
}
