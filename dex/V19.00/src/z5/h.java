package z5;

import g6.d0;
import g6.m;
import g6.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements y {

    /* renamed from: a, reason: collision with root package name */
    public final m f10310a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f10311b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j f10312c;

    public h(j jVar) {
        this.f10312c = jVar;
        this.f10310a = new m(jVar.f10317d.a());
    }

    @Override // g6.y
    public final d0 a() {
        return this.f10310a;
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f10311b) {
            return;
        }
        this.f10311b = true;
        m mVar = this.f10310a;
        j jVar = this.f10312c;
        j.i(jVar, mVar);
        jVar.f10318e = 3;
    }

    @Override // g6.y, java.io.Flushable
    public final void flush() {
        if (this.f10311b) {
            return;
        }
        this.f10312c.f10317d.flush();
    }

    @Override // g6.y
    public final void z(g6.h hVar, long j7) {
        i5.a.g("source", hVar);
        if (!(!this.f10311b)) {
            throw new IllegalStateException("closed".toString());
        }
        long j8 = hVar.f6573b;
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        if (j7 < 0 || 0 > j8 || j8 < j7) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.f10312c.f10317d.z(hVar, j7);
    }
}
