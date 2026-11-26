package com.google.android.material.appbar;

import android.view.View;
import q0.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d implements w {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f3279a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f3280b;

    public d(AppBarLayout appBarLayout, boolean z6) {
        this.f3279a = appBarLayout;
        this.f3280b = z6;
    }

    @Override // q0.w
    public final boolean g(View view) {
        this.f3279a.setExpanded(this.f3280b);
        return true;
    }
}
