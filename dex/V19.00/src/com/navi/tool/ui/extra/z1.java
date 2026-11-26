package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z1 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5598a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5599b;

    public /* synthetic */ z1(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5598a = i7;
        this.f5599b = desktopAssistantFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5598a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5599b;
        switch (i8) {
            case 0:
                desktopAssistantFragment.lambda$showWallpaperMenuDialog$131(dialogInterface, i7);
                break;
            case 1:
                desktopAssistantFragment.lambda$refreshWeeklyButtons$59(dialogInterface, i7);
                break;
            default:
                desktopAssistantFragment.lambda$refreshWeeklyVideoButtons$62(dialogInterface, i7);
                break;
        }
    }
}
