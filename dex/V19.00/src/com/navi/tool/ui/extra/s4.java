package com.navi.tool.ui.extra;

import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class s4 implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5453a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5454b;

    public /* synthetic */ s4(int i7, Object obj) {
        this.f5453a = i7;
        this.f5454b = obj;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5453a;
        Object obj = this.f5454b;
        switch (i8) {
            case 0:
                ((MiscSettingsFragment) obj).lambda$updateDisplayModeState$4(radioGroup, i7);
                break;
            default:
                ((TireLoginActivity) obj).lambda$buildLoginArea$2(radioGroup, i7);
                break;
        }
    }
}
