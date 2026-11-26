package x5;

import androidx.appcompat.widget.y;
import b6.s;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import s5.h0;
import s5.o0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements s5.f {

    /* renamed from: a, reason: collision with root package name */
    public final o0 f9686a;

    /* renamed from: b, reason: collision with root package name */
    public final y f9687b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f9688c;

    /* renamed from: d, reason: collision with root package name */
    public final o f9689d;

    /* renamed from: e, reason: collision with root package name */
    public final s5.y f9690e;

    /* renamed from: f, reason: collision with root package name */
    public final i f9691f;

    /* renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f9692g;

    /* renamed from: h, reason: collision with root package name */
    public Object f9693h;

    /* renamed from: i, reason: collision with root package name */
    public f f9694i;

    /* renamed from: j, reason: collision with root package name */
    public m f9695j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f9696k;

    /* renamed from: l, reason: collision with root package name */
    public e f9697l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f9698m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f9699n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f9700o;

    /* renamed from: p, reason: collision with root package name */
    public volatile boolean f9701p;

    /* renamed from: q, reason: collision with root package name */
    public volatile e f9702q;

    /* renamed from: r, reason: collision with root package name */
    public volatile m f9703r;

    public j(o0 o0Var, y yVar, boolean z6) {
        i5.a.g("client", o0Var);
        i5.a.g("originalRequest", yVar);
        this.f9686a = o0Var;
        this.f9687b = yVar;
        this.f9688c = z6;
        this.f9689d = (o) o0Var.f8843b.f9388b;
        s5.y yVar2 = (s5.y) o0Var.f8846e.f6294b;
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        i5.a.g("$this_asFactory", yVar2);
        this.f9690e = yVar2;
        i iVar = new i(this);
        iVar.g(0, TimeUnit.MILLISECONDS);
        this.f9691f = iVar;
        this.f9692g = new AtomicBoolean();
        this.f9700o = true;
    }

    public static final String a(j jVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(jVar.f9701p ? "canceled " : h0.FRAGMENT_ENCODE_SET);
        sb.append(jVar.f9688c ? "web socket" : "call");
        sb.append(" to ");
        sb.append(((h0) jVar.f9687b.f1482b).f());
        return sb.toString();
    }

    public final void b(m mVar) {
        if (!t5.b.assertionsEnabled || Thread.holdsLock(mVar)) {
            if (this.f9695j != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.f9695j = mVar;
            mVar.f9718o.add(new h(this, this.f9693h));
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + mVar);
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket k7;
        boolean z6 = t5.b.assertionsEnabled;
        if (z6 && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        m mVar = this.f9695j;
        if (mVar != null) {
            if (z6 && Thread.holdsLock(mVar)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + mVar);
            }
            synchronized (mVar) {
                k7 = k();
            }
            if (this.f9695j == null) {
                if (k7 != null) {
                    t5.b.d(k7);
                }
                this.f9690e.getClass();
            } else if (k7 != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        if (!this.f9696k && this.f9691f.i()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            s5.y yVar = this.f9690e;
            i5.a.d(interruptedIOException);
            yVar.getClass();
        } else {
            this.f9690e.getClass();
        }
        return interruptedIOException;
    }

    public final Object clone() {
        return new j(this.f9686a, this.f9687b, this.f9688c);
    }

    public final void d() {
        Socket socket;
        if (this.f9701p) {
            return;
        }
        this.f9701p = true;
        e eVar = this.f9702q;
        if (eVar != null) {
            eVar.f9667d.cancel();
        }
        m mVar = this.f9703r;
        if (mVar != null && (socket = mVar.f9705b) != null) {
            t5.b.d(socket);
        }
        this.f9690e.getClass();
    }

    public final void e(s5.g gVar) {
        g e7;
        if (!this.f9692g.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        s.Companion.getClass();
        this.f9693h = s.f3002a.g();
        this.f9690e.getClass();
        y.s sVar = this.f9686a.f8842a;
        g gVar2 = new g(this, gVar);
        sVar.getClass();
        synchronized (sVar) {
            ((ArrayDeque) sVar.f9915e).add(gVar2);
            if (!this.f9688c && (e7 = sVar.e(((h0) this.f9687b.f1482b).f8795d)) != null) {
                gVar2.f9682b = e7.f9682b;
            }
        }
        sVar.j();
    }

    public final x0 f() {
        if (!this.f9692g.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        this.f9691f.h();
        s.Companion.getClass();
        this.f9693h = s.f3002a.g();
        this.f9690e.getClass();
        try {
            y.s sVar = this.f9686a.f8842a;
            synchronized (sVar) {
                ((ArrayDeque) sVar.f9917g).add(this);
            }
            return h();
        } finally {
            y.s sVar2 = this.f9686a.f8842a;
            sVar2.getClass();
            sVar2.f((ArrayDeque) sVar2.f9917g, this);
        }
    }

    public final void g(boolean z6) {
        e eVar;
        synchronized (this) {
            if (!this.f9700o) {
                throw new IllegalStateException("released".toString());
            }
        }
        if (z6 && (eVar = this.f9702q) != null) {
            eVar.f9667d.cancel();
            eVar.f9664a.i(eVar, true, true, null);
        }
        this.f9697l = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final s5.x0 h() {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            s5.o0 r0 = r11.f9686a
            java.util.List r0 = r0.f8844c
            c5.n.G1(r0, r2)
            y5.k r0 = new y5.k
            s5.o0 r1 = r11.f9686a
            r0.<init>(r1)
            r2.add(r0)
            y5.a r0 = new y5.a
            s5.o0 r1 = r11.f9686a
            s5.t r1 = r1.f8851j
            r0.<init>(r1)
            r2.add(r0)
            v5.b r0 = new v5.b
            s5.o0 r1 = r11.f9686a
            r1.getClass()
            r0.<init>()
            r2.add(r0)
            x5.a r0 = x5.a.INSTANCE
            r2.add(r0)
            boolean r0 = r11.f9688c
            if (r0 != 0) goto L3f
            s5.o0 r0 = r11.f9686a
            java.util.List r0 = r0.f8845d
            c5.n.G1(r0, r2)
        L3f:
            y5.b r0 = new y5.b
            boolean r1 = r11.f9688c
            r0.<init>(r1)
            r2.add(r0)
            y5.h r9 = new y5.h
            r3 = 0
            r4 = 0
            androidx.appcompat.widget.y r5 = r11.f9687b
            s5.o0 r0 = r11.f9686a
            int r6 = r0.f8863v
            int r7 = r0.f8864w
            int r8 = r0.f8865x
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            androidx.appcompat.widget.y r2 = r11.f9687b     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            s5.x0 r2 = r9.b(r2)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            boolean r3 = r11.f9701p     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            if (r3 != 0) goto L6c
            r11.j(r0)
            return r2
        L6c:
            t5.b.c(r2)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            throw r2     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
        L77:
            r2 = move-exception
            goto L89
        L79:
            r1 = move-exception
            r2 = 1
            java.io.IOException r1 = r11.j(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            i5.a.e(r3, r1)     // Catch: java.lang.Throwable -> L85
            throw r1     // Catch: java.lang.Throwable -> L85
        L85:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
        L89:
            if (r1 != 0) goto L8e
            r11.j(r0)
        L8e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.j.h():s5.x0");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022 A[Catch: all -> 0x0018, TryCatch #1 {all -> 0x0018, blocks: (B:49:0x0013, B:12:0x0022, B:14:0x0026, B:15:0x0028, B:17:0x002c, B:21:0x0035, B:23:0x0039, B:9:0x001c), top: B:48:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026 A[Catch: all -> 0x0018, TryCatch #1 {all -> 0x0018, blocks: (B:49:0x0013, B:12:0x0022, B:14:0x0026, B:15:0x0028, B:17:0x002c, B:21:0x0035, B:23:0x0039, B:9:0x001c), top: B:48:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.io.IOException i(x5.e r3, boolean r4, boolean r5, java.io.IOException r6) {
        /*
            r2 = this;
            java.lang.String r0 = "exchange"
            i5.a.g(r0, r3)
            x5.e r0 = r2.f9702q
            boolean r3 = i5.a.c(r3, r0)
            if (r3 != 0) goto Le
            return r6
        Le:
            monitor-enter(r2)
            r3 = 1
            r0 = 0
            if (r4 == 0) goto L1a
            boolean r1 = r2.f9698m     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L20
            goto L1a
        L18:
            r3 = move-exception
            goto L41
        L1a:
            if (r5 == 0) goto L43
            boolean r1 = r2.f9699n     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L43
        L20:
            if (r4 == 0) goto L24
            r2.f9698m = r0     // Catch: java.lang.Throwable -> L18
        L24:
            if (r5 == 0) goto L28
            r2.f9699n = r0     // Catch: java.lang.Throwable -> L18
        L28:
            boolean r4 = r2.f9698m     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L32
            boolean r5 = r2.f9699n     // Catch: java.lang.Throwable -> L18
            if (r5 != 0) goto L32
            r5 = r3
            goto L33
        L32:
            r5 = r0
        L33:
            if (r4 != 0) goto L3e
            boolean r4 = r2.f9699n     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L3e
            boolean r4 = r2.f9700o     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L3e
            r0 = r3
        L3e:
            r4 = r0
            r0 = r5
            goto L44
        L41:
            monitor-exit(r2)
            throw r3
        L43:
            r4 = r0
        L44:
            monitor-exit(r2)
            if (r0 == 0) goto L59
            r5 = 0
            r2.f9702q = r5
            x5.m r5 = r2.f9695j
            if (r5 == 0) goto L59
            monitor-enter(r5)
            int r0 = r5.f9715l     // Catch: java.lang.Throwable -> L56
            int r0 = r0 + r3
            r5.f9715l = r0     // Catch: java.lang.Throwable -> L56
            monitor-exit(r5)
            goto L59
        L56:
            r3 = move-exception
            monitor-exit(r5)
            throw r3
        L59:
            if (r4 == 0) goto L60
            java.io.IOException r3 = r2.c(r6)
            return r3
        L60:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: x5.j.i(x5.e, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    public final IOException j(IOException iOException) {
        boolean z6;
        synchronized (this) {
            z6 = false;
            if (this.f9700o) {
                this.f9700o = false;
                if (!this.f9698m) {
                    if (!this.f9699n) {
                        z6 = true;
                    }
                }
            }
        }
        return z6 ? c(iOException) : iOException;
    }

    public final Socket k() {
        m mVar = this.f9695j;
        i5.a.d(mVar);
        if (t5.b.assertionsEnabled && !Thread.holdsLock(mVar)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + mVar);
        }
        ArrayList arrayList = mVar.f9718o;
        Iterator it = arrayList.iterator();
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            }
            if (i5.a.c(((Reference) it.next()).get(), this)) {
                break;
            }
            i7++;
        }
        if (i7 == -1) {
            throw new IllegalStateException("Check failed.".toString());
        }
        arrayList.remove(i7);
        this.f9695j = null;
        if (arrayList.isEmpty()) {
            mVar.f9719p = System.nanoTime();
            o oVar = this.f9689d;
            oVar.getClass();
            if (t5.b.assertionsEnabled && !Thread.holdsLock(mVar)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + mVar);
            }
            boolean z6 = mVar.f9712i;
            w5.c cVar = oVar.f9722c;
            if (z6 || oVar.f9720a == 0) {
                mVar.f9712i = true;
                ConcurrentLinkedQueue concurrentLinkedQueue = oVar.f9724e;
                concurrentLinkedQueue.remove(mVar);
                if (concurrentLinkedQueue.isEmpty()) {
                    cVar.a();
                }
                Socket socket = mVar.f9706c;
                i5.a.d(socket);
                return socket;
            }
            cVar.c(oVar.f9723d, 0L);
        }
        return null;
    }
}
