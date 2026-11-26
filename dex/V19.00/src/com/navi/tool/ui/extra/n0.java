package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n0 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5343a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5344b;

    public /* synthetic */ n0(DesktopAssistantActivity desktopAssistantActivity, int i7) {
        this.f5343a = i7;
        this.f5344b = desktopAssistantActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5343a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5344b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$initViews$60(view);
                break;
            case 1:
                desktopAssistantActivity.lambda$initViews$54(view);
                break;
            case 2:
                desktopAssistantActivity.lambda$initViews$57(view);
                break;
            case 3:
                desktopAssistantActivity.lambda$initViews$58(view);
                break;
            case 4:
                desktopAssistantActivity.lambda$initViews$59(view);
                break;
            case 5:
                desktopAssistantActivity.lambda$setupMusicControls$97(view);
                break;
            case 6:
                desktopAssistantActivity.lambda$setupMusicControls$98(view);
                break;
            case 7:
                desktopAssistantActivity.lambda$setupMusicControls$99(view);
                break;
            case 8:
                desktopAssistantActivity.lambda$setupMusicControls$100(view);
                break;
            case 9:
                desktopAssistantActivity.lambda$setupMusicControls$101(view);
                break;
            case 10:
                desktopAssistantActivity.lambda$setupDockLogic$48(view);
                break;
            default:
                desktopAssistantActivity.lambda$setupFloatingHandleInteraction$105(view);
                break;
        }
    }
}
