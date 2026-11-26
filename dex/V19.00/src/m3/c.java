package m3;

import android.graphics.Typeface;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f7531d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e f7532e;

    public c(e eVar, f fVar) {
        this.f7532e = eVar;
        this.f7531d = fVar;
    }

    @Override // l6.f
    public final void a0(int i7) {
        this.f7532e.f7549m = true;
        this.f7531d.a0(i7);
    }

    @Override // l6.f
    public final void b0(Typeface typeface) {
        e eVar = this.f7532e;
        eVar.f7550n = Typeface.create(typeface, eVar.f7539c);
        eVar.f7549m = true;
        this.f7531d.c0(eVar.f7550n, false);
    }
}
