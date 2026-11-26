package m3;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Context f7533d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ TextPaint f7534e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f f7535f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e f7536g;

    public d(e eVar, Context context, TextPaint textPaint, f fVar) {
        this.f7536g = eVar;
        this.f7533d = context;
        this.f7534e = textPaint;
        this.f7535f = fVar;
    }

    @Override // l6.f
    public final void a0(int i7) {
        this.f7535f.a0(i7);
    }

    @Override // l6.f
    public final void c0(Typeface typeface, boolean z6) {
        this.f7536g.g(this.f7533d, this.f7534e, typeface);
        this.f7535f.c0(typeface, z6);
    }
}
