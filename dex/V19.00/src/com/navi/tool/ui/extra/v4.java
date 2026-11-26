package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5521a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CompoundButton f5522b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f5523c;

    public /* synthetic */ v4(CompoundButton compoundButton, boolean z6, int i7) {
        this.f5521a = i7;
        this.f5522b = compoundButton;
        this.f5523c = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5521a;
        boolean z6 = this.f5523c;
        CompoundButton compoundButton = this.f5522b;
        switch (i7) {
            case 0:
                compoundButton.setChecked(z6);
                break;
            default:
                compoundButton.setChecked(z6);
                break;
        }
    }
}
