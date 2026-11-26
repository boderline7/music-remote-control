package m3;

import android.graphics.Typeface;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends f {

    /* renamed from: d, reason: collision with root package name */
    public final Typeface f7528d;

    /* renamed from: e, reason: collision with root package name */
    public final a f7529e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f7530f;

    public b(com.google.android.material.internal.c cVar, Typeface typeface) {
        this.f7528d = typeface;
        this.f7529e = cVar;
    }

    @Override // l6.f
    public final void a0(int i7) {
        if (this.f7530f) {
            return;
        }
        com.google.android.material.internal.c cVar = (com.google.android.material.internal.c) this.f7529e;
        int i8 = cVar.f3803a;
        Typeface typeface = this.f7528d;
        com.google.android.material.internal.d dVar = cVar.f3804b;
        switch (i8) {
            case 0:
                if (dVar.m(typeface)) {
                    dVar.i(false);
                    break;
                }
                break;
            default:
                if (dVar.o(typeface)) {
                    dVar.i(false);
                    break;
                }
                break;
        }
    }

    @Override // l6.f
    public final void c0(Typeface typeface, boolean z6) {
        if (this.f7530f) {
            return;
        }
        com.google.android.material.internal.c cVar = (com.google.android.material.internal.c) this.f7529e;
        int i7 = cVar.f3803a;
        com.google.android.material.internal.d dVar = cVar.f3804b;
        switch (i7) {
            case 0:
                if (dVar.m(typeface)) {
                    dVar.i(false);
                    break;
                }
                break;
            default:
                if (dVar.o(typeface)) {
                    dVar.i(false);
                    break;
                }
                break;
        }
    }
}
