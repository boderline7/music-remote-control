package com.navi.tool.ui.extra;

import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5567a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f5568b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5569c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5570d;

    public /* synthetic */ y0(DesktopAssistantActivity desktopAssistantActivity, String str, boolean z6) {
        this.f5569c = desktopAssistantActivity;
        this.f5570d = str;
        this.f5568b = z6;
    }

    public /* synthetic */ y0(FileManagerActivity fileManagerActivity, boolean z6, File file) {
        this.f5569c = fileManagerActivity;
        this.f5568b = z6;
        this.f5570d = file;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = this.f5570d;
        int i7 = this.f5567a;
        boolean z6 = this.f5568b;
        androidx.appcompat.app.r rVar = this.f5569c;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity) rVar).lambda$sendMapBroadcast$111((String) obj, z6);
                break;
            default:
                ((FileManagerActivity) rVar).lambda$enterZipMode$84(z6, (File) obj);
                break;
        }
    }
}
