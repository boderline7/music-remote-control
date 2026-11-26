package com.google.android.material.appbar;

import android.view.View;
import android.widget.ScrollView;
import com.google.android.material.appbar.AppBarLayout;
import q0.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout.BaseBehavior f3273d;

    public b(AppBarLayout.BaseBehavior baseBehavior) {
        this.f3273d = baseBehavior;
    }

    @Override // p0.c
    public final void d(View view, i iVar) {
        this.f8103a.onInitializeAccessibilityNodeInfo(view, iVar.f8426a);
        iVar.h(this.f3273d.f3234o);
        iVar.f(ScrollView.class.getName());
    }
}
