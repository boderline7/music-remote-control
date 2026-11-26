package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5341a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLinkFragment f5342b;

    public /* synthetic */ n(CarLinkFragment carLinkFragment, int i7) {
        this.f5341a = i7;
        this.f5342b = carLinkFragment;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5341a;
        CarLinkFragment carLinkFragment = this.f5342b;
        switch (i7) {
            case 0:
                carLinkFragment.lambda$initSoundSettings$44(compoundButton, z6);
                break;
            case 1:
                carLinkFragment.lambda$initSoundSettings$45(compoundButton, z6);
                break;
            case 2:
                carLinkFragment.lambda$new$0(compoundButton, z6);
                break;
            case 3:
                carLinkFragment.lambda$initSeatClimateSettings$47(compoundButton, z6);
                break;
            case 4:
                carLinkFragment.lambda$initSeatClimateSettings$57(compoundButton, z6);
                break;
            case 5:
                carLinkFragment.lambda$initSeatClimateSettings$59(compoundButton, z6);
                break;
            case 6:
                carLinkFragment.lambda$initSeatClimateSettings$48(compoundButton, z6);
                break;
            case 7:
                carLinkFragment.lambda$initSeatClimateSettings$50(compoundButton, z6);
                break;
            case 8:
                carLinkFragment.lambda$initSeatClimateSettings$51(compoundButton, z6);
                break;
            case 9:
                carLinkFragment.lambda$initSeatClimateSettings$53(compoundButton, z6);
                break;
            case 10:
                carLinkFragment.lambda$initSeatClimateSettings$55(compoundButton, z6);
                break;
            case 11:
                carLinkFragment.lambda$initSeatClimateSettings$56(compoundButton, z6);
                break;
            case 12:
                carLinkFragment.lambda$initLogic$3(compoundButton, z6);
                break;
            case 13:
                carLinkFragment.lambda$initLogic$15(compoundButton, z6);
                break;
            case 14:
                carLinkFragment.lambda$initLogic$16(compoundButton, z6);
                break;
            case 15:
                carLinkFragment.lambda$initLogic$17(compoundButton, z6);
                break;
            case 16:
                carLinkFragment.lambda$initLogic$18(compoundButton, z6);
                break;
            case 17:
                carLinkFragment.lambda$initLogic$19(compoundButton, z6);
                break;
            case 18:
                carLinkFragment.lambda$initLogic$4(compoundButton, z6);
                break;
            case 19:
                carLinkFragment.lambda$initLogic$24(compoundButton, z6);
                break;
            case 20:
                carLinkFragment.lambda$initLogic$25(compoundButton, z6);
                break;
            case 21:
                carLinkFragment.lambda$initLogic$26(compoundButton, z6);
                break;
            case 22:
                carLinkFragment.lambda$initLogic$27(compoundButton, z6);
                break;
            case 23:
                carLinkFragment.lambda$initLogic$28(compoundButton, z6);
                break;
            case 24:
                carLinkFragment.lambda$initLogic$29(compoundButton, z6);
                break;
            case 25:
                carLinkFragment.lambda$initLogic$33(compoundButton, z6);
                break;
            case 26:
                carLinkFragment.lambda$initLogic$5(compoundButton, z6);
                break;
            case 27:
                carLinkFragment.lambda$initLogic$34(compoundButton, z6);
                break;
            case 28:
                carLinkFragment.lambda$initLogic$36(compoundButton, z6);
                break;
            default:
                carLinkFragment.lambda$initLogic$37(compoundButton, z6);
                break;
        }
    }
}
