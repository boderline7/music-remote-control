package com.navi.tool.ui.extra;

import android.view.View;
import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5379a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLinkFragment f5380b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5381c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5382d;

    public /* synthetic */ p(CarLinkFragment carLinkFragment, String str, Object obj, int i7) {
        this.f5379a = i7;
        this.f5380b = carLinkFragment;
        this.f5381c = str;
        this.f5382d = obj;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5379a;
        String str = this.f5381c;
        CarLinkFragment carLinkFragment = this.f5380b;
        Object obj = this.f5382d;
        switch (i7) {
            case 0:
                carLinkFragment.lambda$setupSeatSwitch$60(str, (View) obj, compoundButton, z6);
                break;
            default:
                carLinkFragment.lambda$setupSettingItem$46(str, (String) obj, compoundButton, z6);
                break;
        }
    }
}
