package com.navi.tool.utils;

import android.view.View;
import p0.s2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5863a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ s2 f5864b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f5865c;

    public /* synthetic */ i(s2 s2Var, View view, int i7) {
        this.f5863a = i7;
        this.f5864b = s2Var;
        this.f5865c = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5863a;
        View view = this.f5865c;
        s2 s2Var = this.f5864b;
        switch (i7) {
            case 0:
                DialogImeNavBarHelper.hideNavigationBar(s2Var, view);
                break;
            default:
                DialogImeNavBarHelper.hideNavigationBar(s2Var, view);
                break;
        }
    }
}
