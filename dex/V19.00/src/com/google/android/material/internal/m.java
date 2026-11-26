package com.google.android.material.internal;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3869d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f3870e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n f3871f;

    public m(n nVar, int i7, boolean z6) {
        this.f3871f = nVar;
        this.f3869d = i7;
        this.f3870e = z6;
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        this.f8103a.onInitializeAccessibilityNodeInfo(view, iVar.f8426a);
        int i7 = this.f3869d;
        int i8 = 0;
        int i9 = i7;
        while (true) {
            n nVar = this.f3871f;
            if (i8 >= i7) {
                nVar.getClass();
                iVar.g(androidx.fragment.app.o.b(i9, 1, 1, 1, this.f3870e, view.isSelected()));
                return;
            } else {
                v vVar = nVar.f3875d;
                if (vVar.f3885e.getItemViewType(i8) == 2 || vVar.f3885e.getItemViewType(i8) == 3) {
                    i9--;
                }
                i8++;
            }
        }
    }
}
