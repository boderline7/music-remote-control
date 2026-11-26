package com.navi.tool.ui.extra;

import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5554a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantMusicUiHelper.CoverRequestCallback f5555b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantMusicUiHelper.CoverRequest f5556c;

    public /* synthetic */ x2(DesktopAssistantMusicUiHelper.CoverRequest coverRequest, DesktopAssistantMusicUiHelper.CoverRequestCallback coverRequestCallback) {
        this.f5556c = coverRequest;
        this.f5555b = coverRequestCallback;
    }

    public /* synthetic */ x2(DesktopAssistantMusicUiHelper.CoverRequestCallback coverRequestCallback, DesktopAssistantMusicUiHelper.CoverRequest coverRequest) {
        this.f5555b = coverRequestCallback;
        this.f5556c = coverRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5554a;
        DesktopAssistantMusicUiHelper.CoverRequest coverRequest = this.f5556c;
        DesktopAssistantMusicUiHelper.CoverRequestCallback coverRequestCallback = this.f5555b;
        switch (i7) {
            case 0:
                DesktopAssistantMusicUiHelper.lambda$executeCoverRequest$5(coverRequest, coverRequestCallback);
                break;
            default:
                DesktopAssistantMusicUiHelper.lambda$applyDefaultCoverIfCurrent$3(coverRequestCallback, coverRequest);
                break;
        }
    }
}
