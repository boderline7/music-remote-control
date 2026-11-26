package com.google.android.material.tabs;

import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements View.OnLayoutChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f4257a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TabLayout.TabView f4258b;

    public c(TabLayout.TabView tabView, View view) {
        this.f4258b = tabView;
        this.f4257a = view;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        View view2 = this.f4257a;
        if (view2.getVisibility() == 0) {
            int i15 = TabLayout.TabView.f4237l;
            this.f4258b.c(view2);
        }
    }
}
