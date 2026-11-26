package com.navi.tool.ui.extra;

import android.graphics.Bitmap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5507a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5508b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Bitmap f5509c;

    public /* synthetic */ v0(DesktopAssistantActivity desktopAssistantActivity, Bitmap bitmap, int i7) {
        this.f5507a = i7;
        this.f5508b = desktopAssistantActivity;
        this.f5509c = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5507a;
        Bitmap bitmap = this.f5509c;
        DesktopAssistantActivity desktopAssistantActivity = this.f5508b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$loadCachedMusicInfo$33(bitmap);
                break;
            default:
                desktopAssistantActivity.lambda$onAlbumArtUpdateEvent$42(bitmap);
                break;
        }
    }
}
