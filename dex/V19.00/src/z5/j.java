package z5;

import com.navi.tool.service.ConfigBackupService;
import g6.a0;
import g6.y;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import o5.k;
import s5.d0;
import s5.h0;
import s5.o0;
import s5.q0;
import s5.v0;
import s5.w0;
import s5.x0;
import x5.m;
import y5.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements y5.e {
    public static final f Companion = new f();

    /* renamed from: a, reason: collision with root package name */
    public final o0 f10314a;

    /* renamed from: b, reason: collision with root package name */
    public final m f10315b;

    /* renamed from: c, reason: collision with root package name */
    public final g6.j f10316c;

    /* renamed from: d, reason: collision with root package name */
    public final g6.i f10317d;

    /* renamed from: e, reason: collision with root package name */
    public int f10318e;

    /* renamed from: f, reason: collision with root package name */
    public final b f10319f;

    /* renamed from: g, reason: collision with root package name */
    public d0 f10320g;

    public j(o0 o0Var, m mVar, g6.j jVar, g6.i iVar) {
        i5.a.g("connection", mVar);
        this.f10314a = o0Var;
        this.f10315b = mVar;
        this.f10316c = jVar;
        this.f10317d = iVar;
        this.f10319f = new b(jVar);
    }

    public static final void i(j jVar, g6.m mVar) {
        jVar.getClass();
        g6.d0 d0Var = mVar.f6577d;
        g6.d0 d0Var2 = g6.d0.NONE;
        i5.a.g("delegate", d0Var2);
        mVar.f6577d = d0Var2;
        d0Var.a();
        d0Var.b();
    }

    @Override // y5.e
    public final long a(x0 x0Var) {
        if (!y5.f.a(x0Var)) {
            return 0L;
        }
        if (k.C("chunked", x0.A(x0Var, "Transfer-Encoding"))) {
            return -1L;
        }
        return t5.b.j(x0Var);
    }

    @Override // y5.e
    public final y b(androidx.appcompat.widget.y yVar, long j7) {
        v0 v0Var = (v0) yVar.f1485e;
        if (v0Var != null && v0Var.isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (k.C("chunked", yVar.e("Transfer-Encoding"))) {
            if (this.f10318e == 1) {
                this.f10318e = 2;
                return new d(this);
            }
            throw new IllegalStateException(("state: " + this.f10318e).toString());
        }
        if (j7 == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.f10318e == 1) {
            this.f10318e = 2;
            return new h(this);
        }
        throw new IllegalStateException(("state: " + this.f10318e).toString());
    }

    @Override // y5.e
    public final a0 c(x0 x0Var) {
        if (!y5.f.a(x0Var)) {
            return j(0L);
        }
        if (k.C("chunked", x0.A(x0Var, "Transfer-Encoding"))) {
            h0 h0Var = (h0) x0Var.f8913a.f1482b;
            if (this.f10318e == 4) {
                this.f10318e = 5;
                return new e(this, h0Var);
            }
            throw new IllegalStateException(("state: " + this.f10318e).toString());
        }
        long j7 = t5.b.j(x0Var);
        if (j7 != -1) {
            return j(j7);
        }
        if (this.f10318e == 4) {
            this.f10318e = 5;
            this.f10315b.k();
            return new i(this);
        }
        throw new IllegalStateException(("state: " + this.f10318e).toString());
    }

    @Override // y5.e
    public final void cancel() {
        Socket socket = this.f10315b.f9705b;
        if (socket != null) {
            t5.b.d(socket);
        }
    }

    @Override // y5.e
    public final void d() {
        this.f10317d.flush();
    }

    @Override // y5.e
    public final void e() {
        this.f10317d.flush();
    }

    @Override // y5.e
    public final w0 f(boolean z6) {
        b bVar = this.f10319f;
        int i7 = this.f10318e;
        if (i7 != 1 && i7 != 2 && i7 != 3) {
            throw new IllegalStateException(("state: " + this.f10318e).toString());
        }
        try {
            l lVar = y5.m.Companion;
            String g7 = bVar.f10296a.g(bVar.f10297b);
            bVar.f10297b -= g7.length();
            lVar.getClass();
            y5.m a7 = l.a(g7);
            int i8 = a7.f10227b;
            w0 w0Var = new w0();
            q0 q0Var = a7.f10226a;
            i5.a.g("protocol", q0Var);
            w0Var.f8901b = q0Var;
            w0Var.f8902c = i8;
            String str = a7.f10228c;
            i5.a.g(ConfigBackupService.EXTRA_MESSAGE, str);
            w0Var.f8903d = str;
            w0Var.f8905f = bVar.a().c();
            if (z6 && i8 == 100) {
                return null;
            }
            if (i8 == 100) {
                this.f10318e = 3;
                return w0Var;
            }
            if (102 > i8 || i8 >= 200) {
                this.f10318e = 4;
                return w0Var;
            }
            this.f10318e = 3;
            return w0Var;
        } catch (EOFException e7) {
            throw new IOException(a.a.B("unexpected end of stream on ", this.f10315b.f9704a.f8764a.f8755i.f()), e7);
        }
    }

    @Override // y5.e
    public final void g(androidx.appcompat.widget.y yVar) {
        y5.i iVar = y5.i.INSTANCE;
        Proxy.Type type = this.f10315b.f9704a.f8765b.type();
        i5.a.f("connection.route().proxy.type()", type);
        iVar.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append((String) yVar.f1483c);
        sb.append(' ');
        Object obj = yVar.f1482b;
        if (((h0) obj).f8800i || type != Proxy.Type.HTTP) {
            sb.append(y5.i.a((h0) obj));
        } else {
            sb.append((h0) obj);
        }
        sb.append(" HTTP/1.1");
        String sb2 = sb.toString();
        i5.a.f("StringBuilder().apply(builderAction).toString()", sb2);
        k((d0) yVar.f1484d, sb2);
    }

    @Override // y5.e
    public final m h() {
        return this.f10315b;
    }

    public final g j(long j7) {
        if (this.f10318e == 4) {
            this.f10318e = 5;
            return new g(this, j7);
        }
        throw new IllegalStateException(("state: " + this.f10318e).toString());
    }

    public final void k(d0 d0Var, String str) {
        i5.a.g("headers", d0Var);
        i5.a.g("requestLine", str);
        if (this.f10318e != 0) {
            throw new IllegalStateException(("state: " + this.f10318e).toString());
        }
        g6.i iVar = this.f10317d;
        iVar.r(str).r("\r\n");
        int size = d0Var.size();
        for (int i7 = 0; i7 < size; i7++) {
            iVar.r(d0Var.b(i7)).r(": ").r(d0Var.d(i7)).r("\r\n");
        }
        iVar.r("\r\n");
        this.f10318e = 1;
    }
}
