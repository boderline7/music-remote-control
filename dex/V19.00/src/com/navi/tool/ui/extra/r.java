package com.navi.tool.ui.extra;

import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5426a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLinkFragment f5427b;

    public /* synthetic */ r(CarLinkFragment carLinkFragment, int i7) {
        this.f5426a = i7;
        this.f5427b = carLinkFragment;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5426a;
        CarLinkFragment carLinkFragment = this.f5427b;
        switch (i8) {
            case 0:
                carLinkFragment.lambda$initSeatClimateSettings$58(radioGroup, i7);
                break;
            case 1:
                carLinkFragment.lambda$initSeatClimateSettings$49(radioGroup, i7);
                break;
            case 2:
                carLinkFragment.lambda$initSeatClimateSettings$52(radioGroup, i7);
                break;
            case 3:
                carLinkFragment.lambda$initSeatClimateSettings$54(radioGroup, i7);
                break;
            case 4:
                carLinkFragment.lambda$initLogic$21(radioGroup, i7);
                break;
            case 5:
                carLinkFragment.lambda$initLogic$22(radioGroup, i7);
                break;
            case 6:
                carLinkFragment.lambda$initLogic$23(radioGroup, i7);
                break;
            case 7:
                carLinkFragment.lambda$initLogic$32(radioGroup, i7);
                break;
            default:
                carLinkFragment.lambda$initLogic$41(radioGroup, i7);
                break;
        }
    }
}
