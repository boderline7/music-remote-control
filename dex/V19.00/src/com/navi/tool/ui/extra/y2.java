package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5579a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantMusicUiHelper.CoverRequestCallback f5580b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantMusicUiHelper.CoverRequest f5581c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Bitmap f5582d;

    public /* synthetic */ y2(DesktopAssistantMusicUiHelper.CoverRequestCallback coverRequestCallback, DesktopAssistantMusicUiHelper.CoverRequest coverRequest, Bitmap bitmap, int i7) {
        this.f5579a = i7;
        this.f5580b = coverRequestCallback;
        this.f5581c = coverRequest;
        this.f5582d = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5579a;
        DesktopAssistantMusicUiHelper.CoverRequestCallback coverRequestCallback = this.f5580b;
        Bitmap bitmap = this.f5582d;
        DesktopAssistantMusicUiHelper.CoverRequest coverRequest = this.f5581c;
        switch (i7) {
            case 0:
                DesktopAssistantMusicUiHelper.AnonymousClass2.lambda$onLoaded$0(coverRequestCallback, coverRequest, bitmap);
                break;
            default:
                DesktopAssistantMusicUiHelper.lambda$executeCoverRequest$4(coverRequestCallback, coverRequest, bitmap);
                break;
        }
    }
}
