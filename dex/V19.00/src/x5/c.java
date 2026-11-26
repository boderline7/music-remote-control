package x5;

import g6.d0;
import g6.y;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements y {

    /* renamed from: a, reason: collision with root package name */
    public final y f9651a;

    /* renamed from: b, reason: collision with root package name */
    public final long f9652b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9653c;

    /* renamed from: d, reason: collision with root package name */
    public long f9654d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f9655e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e f9656f;

    public c(e eVar, y yVar, long j7) {
        i5.a.g("delegate", yVar);
        this.f9656f = eVar;
        this.f9651a = yVar;
        this.f9652b = j7;
    }

    public final void A() {
        this.f9651a.close();
    }

    public final IOException B(IOException iOException) {
        if (this.f9653c) {
            return iOException;
        }
        this.f9653c = true;
        return this.f9656f.a(false, true, iOException);
    }

    public final void C() {
        this.f9651a.flush();
    }

    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        return c.class.getSimpleName() + '(' + this.f9651a + ')';
    }

    @Override // g6.y
    public final d0 a() {
        return this.f9651a.a();
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f9655e) {
            return;
        }
        this.f9655e = true;
        long j7 = this.f9652b;
        if (j7 != -1 && this.f9654d != j7) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            A();
            B(null);
        } catch (IOException e7) {
            throw B(e7);
        }
    }

    @Override // g6.y, java.io.Flushable
    public final void flush() {
        try {
            C();
        } catch (IOException e7) {
            throw B(e7);
        }
    }

    @Override // g6.y
    public final void z(g6.h hVar, long j7) {
        i5.a.g("source", hVar);
        if (!(!this.f9655e)) {
            throw new IllegalStateException("closed".toString());
        }
        long j8 = this.f9652b;
        if (j8 == -1 || this.f9654d + j7 <= j8) {
            try {
                this.f9651a.z(hVar, j7);
                this.f9654d += j7;
                return;
            } catch (IOException e7) {
                throw B(e7);
            }
        }
        throw new ProtocolException("expected " + j8 + " bytes but received " + (this.f9654d + j7));
    }
}
