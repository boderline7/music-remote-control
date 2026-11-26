package com.google.android.material.appbar;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import q0.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements w {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ CoordinatorLayout f3274a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f3275b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f3276c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3277d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout.BaseBehavior f3278e;

    public c(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7) {
        this.f3278e = baseBehavior;
        this.f3274a = coordinatorLayout;
        this.f3275b = appBarLayout;
        this.f3276c = view;
        this.f3277d = i7;
    }

    @Override // q0.w
    public final boolean g(View view) {
        this.f3278e.D(this.f3274a, this.f3275b, this.f3276c, this.f3277d, new int[]{0, 0});
        return true;
    }
}
