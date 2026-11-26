package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class o2 implements DialogInterface.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5369a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5370b;

    public /* synthetic */ o2(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5369a = i7;
        this.f5370b = desktopAssistantFragment;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i7 = this.f5369a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5370b;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showAdjustTopInfoDialog$26(dialogInterface);
                break;
            default:
                desktopAssistantFragment.lambda$showSelectTopInfoDialog$22(dialogInterface);
                break;
        }
    }
}
