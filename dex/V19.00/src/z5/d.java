package z5;

import g6.d0;
import g6.m;
import g6.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements y {

    /* renamed from: a, reason: collision with root package name */
    public final m f10301a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f10302b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j f10303c;

    public d(j jVar) {
        this.f10303c = jVar;
        this.f10301a = new m(jVar.f10317d.a());
    }

    @Override // g6.y
    public final d0 a() {
        return this.f10301a;
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f10302b) {
            return;
        }
        this.f10302b = true;
        this.f10303c.f10317d.r("0\r\n\r\n");
        j.i(this.f10303c, this.f10301a);
        this.f10303c.f10318e = 3;
    }

    @Override // g6.y, java.io.Flushable
    public final synchronized void flush() {
        if (this.f10302b) {
            return;
        }
        this.f10303c.f10317d.flush();
    }

    @Override // g6.y
    public final void z(g6.h hVar, long j7) {
        i5.a.g("source", hVar);
        if (!(!this.f10302b)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j7 == 0) {
            return;
        }
        j jVar = this.f10303c;
        jVar.f10317d.e(j7);
        jVar.f10317d.r("\r\n");
        jVar.f10317d.z(hVar, j7);
        jVar.f10317d.r("\r\n");
    }
}
