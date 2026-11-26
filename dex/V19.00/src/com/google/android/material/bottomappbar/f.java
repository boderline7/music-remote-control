package com.google.android.material.bottomappbar;

import androidx.appcompat.widget.ActionMenuView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ActionMenuView f3368a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f3369b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f3370c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f3371d;

    public f(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i7, boolean z6) {
        this.f3371d = bottomAppBar;
        this.f3368a = actionMenuView;
        this.f3369b = i7;
        this.f3370c = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f3369b;
        boolean z6 = this.f3370c;
        BottomAppBar bottomAppBar = this.f3371d;
        this.f3368a.setTranslationX(bottomAppBar.F(r3, i7, z6));
    }
}
