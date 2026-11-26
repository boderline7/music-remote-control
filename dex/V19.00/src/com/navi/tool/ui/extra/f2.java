package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f2 implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5153a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5154b;

    public /* synthetic */ f2(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5153a = i7;
        this.f5154b = desktopAssistantFragment;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5153a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5154b;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$initLogic$54(compoundButton, z6);
                break;
            case 1:
                desktopAssistantFragment.lambda$initLogic$33(compoundButton, z6);
                break;
            case 2:
                desktopAssistantFragment.lambda$initLogic$55(compoundButton, z6);
                break;
            case 3:
                desktopAssistantFragment.lambda$initLogic$56(compoundButton, z6);
                break;
            case 4:
                desktopAssistantFragment.lambda$initLogic$36(compoundButton, z6);
                break;
            case 5:
                desktopAssistantFragment.lambda$initWeeklyVideoWallpaper$61(compoundButton, z6);
                break;
            case 6:
                desktopAssistantFragment.lambda$initMapCardLogic$82(compoundButton, z6);
                break;
            case 7:
                desktopAssistantFragment.lambda$initMapCardLogic$83(compoundButton, z6);
                break;
            case 8:
                desktopAssistantFragment.lambda$initRandomAndMixedWallpaperLogic$88(compoundButton, z6);
                break;
            default:
                desktopAssistantFragment.lambda$initRandomAndMixedWallpaperLogic$89(compoundButton, z6);
                break;
        }
    }
}
