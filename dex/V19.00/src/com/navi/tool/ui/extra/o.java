package com.navi.tool.ui.extra;

import com.navi.tool.ui.theme.PositionSettingsDialog;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class o implements PositionSettingsDialog.OnPositionSetListener, androidx.lifecycle.h0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5359a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLinkFragment f5360b;

    public /* synthetic */ o(CarLinkFragment carLinkFragment, int i7) {
        this.f5359a = i7;
        this.f5360b = carLinkFragment;
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        int i7 = this.f5359a;
        CarLinkFragment carLinkFragment = this.f5360b;
        Boolean bool = (Boolean) obj;
        switch (i7) {
            case 1:
                carLinkFragment.lambda$initLogic$13(bool);
                break;
            case 2:
                carLinkFragment.lambda$initLogic$20(bool);
                break;
            case 3:
                carLinkFragment.lambda$initLogic$42(bool);
                break;
            case 4:
                carLinkFragment.lambda$initLogic$6(bool);
                break;
            case 5:
                carLinkFragment.lambda$initLogic$9(bool);
                break;
            default:
                carLinkFragment.lambda$initLogic$12(bool);
                break;
        }
    }

    @Override // com.navi.tool.ui.theme.PositionSettingsDialog.OnPositionSetListener
    public final void onPositionSet(int i7, int i8) {
        this.f5360b.lambda$initLogic$30(i7, i8);
    }
}
