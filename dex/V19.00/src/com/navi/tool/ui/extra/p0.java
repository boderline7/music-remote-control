package com.navi.tool.ui.extra;

import android.graphics.Bitmap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5383a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5384b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5385c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5386d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5387e;

    public /* synthetic */ p0(DesktopAssistantActivity desktopAssistantActivity, int i7, Object obj, boolean z6, int i8) {
        this.f5383a = i8;
        this.f5384b = desktopAssistantActivity;
        this.f5385c = i7;
        this.f5387e = obj;
        this.f5386d = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5383a;
        int i8 = this.f5385c;
        DesktopAssistantActivity desktopAssistantActivity = this.f5384b;
        boolean z6 = this.f5386d;
        Object obj = this.f5387e;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$decodeWallpaperAsync$95(i8, (Bitmap) obj, z6);
                break;
            default:
                desktopAssistantActivity.lambda$decodeWallpaperAsync$96(i8, (String) obj, z6);
                break;
        }
    }
}
