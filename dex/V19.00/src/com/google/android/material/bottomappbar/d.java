package com.google.android.material.bottomappbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3361d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f3362e;

    public d(BottomAppBar bottomAppBar, int i7) {
        this.f3362e = bottomAppBar;
        this.f3361d = i7;
    }

    @Override // l6.f
    public final void d0(FloatingActionButton floatingActionButton) {
        int i7 = BottomAppBar.f3327v0;
        floatingActionButton.setTranslationX(this.f3362e.G(this.f3361d));
        floatingActionButton.l(new c(this), true);
    }
}
