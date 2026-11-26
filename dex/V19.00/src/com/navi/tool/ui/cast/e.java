package com.navi.tool.ui.cast;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4981a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ScreenCastingFragment f4982b;

    public /* synthetic */ e(ScreenCastingFragment screenCastingFragment, int i7) {
        this.f4981a = i7;
        this.f4982b = screenCastingFragment;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f4981a;
        ScreenCastingFragment screenCastingFragment = this.f4982b;
        switch (i7) {
            case 0:
                screenCastingFragment.lambda$initClusterEnlargeSwitch$7(compoundButton, z6);
                break;
            default:
                screenCastingFragment.lambda$initSwitch$6(compoundButton, z6);
                break;
        }
    }
}
