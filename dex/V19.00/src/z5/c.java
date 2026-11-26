package z5;

import g6.a0;
import g6.d0;
import g6.m;
import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c implements a0 {

    /* renamed from: a, reason: collision with root package name */
    public final m f10298a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f10299b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j f10300c;

    public c(j jVar) {
        this.f10300c = jVar;
        this.f10298a = new m(jVar.f10316c.a());
    }

    public final void A() {
        j jVar = this.f10300c;
        int i7 = jVar.f10318e;
        if (i7 == 6) {
            return;
        }
        if (i7 == 5) {
            j.i(jVar, this.f10298a);
            jVar.f10318e = 6;
        } else {
            throw new IllegalStateException("state: " + jVar.f10318e);
        }
    }

    @Override // g6.a0
    public final d0 a() {
        return this.f10298a;
    }

    @Override // g6.a0
    public long k(g6.h hVar, long j7) {
        j jVar = this.f10300c;
        i5.a.g("sink", hVar);
        try {
            return jVar.f10316c.k(hVar, j7);
        } catch (IOException e7) {
            jVar.f10315b.k();
            A();
            throw e7;
        }
    }
}
