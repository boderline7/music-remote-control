package x5;

import g6.a0;
import g6.d0;
import java.io.IOException;
import java.net.ProtocolException;
import s5.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements a0 {

    /* renamed from: a, reason: collision with root package name */
    public final a0 f9657a;

    /* renamed from: b, reason: collision with root package name */
    public final long f9658b;

    /* renamed from: c, reason: collision with root package name */
    public long f9659c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f9660d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f9661e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f9662f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e f9663g;

    public d(e eVar, a0 a0Var, long j7) {
        i5.a.g("delegate", a0Var);
        this.f9663g = eVar;
        this.f9657a = a0Var;
        this.f9658b = j7;
        this.f9660d = true;
        if (j7 == 0) {
            B(null);
        }
    }

    public final void A() {
        this.f9657a.close();
    }

    public final IOException B(IOException iOException) {
        if (this.f9661e) {
            return iOException;
        }
        this.f9661e = true;
        e eVar = this.f9663g;
        if (iOException == null && this.f9660d) {
            this.f9660d = false;
            eVar.f9665b.getClass();
            i5.a.g("call", eVar.f9664a);
        }
        return eVar.a(true, false, iOException);
    }

    /* renamed from: C, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        return d.class.getSimpleName() + '(' + this.f9657a + ')';
    }

    @Override // g6.a0
    public final d0 a() {
        return this.f9657a.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f9662f) {
            return;
        }
        this.f9662f = true;
        try {
            A();
            B(null);
        } catch (IOException e7) {
            throw B(e7);
        }
    }

    @Override // g6.a0
    public final long k(g6.h hVar, long j7) {
        i5.a.g("sink", hVar);
        if (!(!this.f9662f)) {
            throw new IllegalStateException("closed".toString());
        }
        try {
            long k7 = this.f9657a.k(hVar, j7);
            if (this.f9660d) {
                this.f9660d = false;
                e eVar = this.f9663g;
                y yVar = eVar.f9665b;
                j jVar = eVar.f9664a;
                yVar.getClass();
                i5.a.g("call", jVar);
            }
            if (k7 == -1) {
                B(null);
                return -1L;
            }
            long j8 = this.f9659c + k7;
            long j9 = this.f9658b;
            if (j9 == -1 || j8 <= j9) {
                this.f9659c = j8;
                if (j8 == j9) {
                    B(null);
                }
                return k7;
            }
            throw new ProtocolException("expected " + j9 + " bytes but received " + j8);
        } catch (IOException e7) {
            throw B(e7);
        }
    }
}
