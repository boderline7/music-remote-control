package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5408a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLinkFragment f5409b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5410c;

    public /* synthetic */ q(CarLinkFragment carLinkFragment, String str, int i7) {
        this.f5408a = i7;
        this.f5409b = carLinkFragment;
        this.f5410c = str;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5408a;
        String str = this.f5410c;
        CarLinkFragment carLinkFragment = this.f5409b;
        switch (i7) {
            case 0:
                carLinkFragment.lambda$setupMobileHudCheckBox$43(str, compoundButton, z6);
                break;
            default:
                carLinkFragment.lambda$setupSimpleSwitch$62(str, compoundButton, z6);
                break;
        }
    }
}
