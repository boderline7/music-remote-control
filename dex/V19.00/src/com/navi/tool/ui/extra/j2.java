package com.navi.tool.ui.extra;

import android.content.Context;
import com.navi.tool.utils.CrashReportUploader;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5250a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f5251b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5252c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.z f5253d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5254e;

    public /* synthetic */ j2(androidx.fragment.app.z zVar, boolean z6, String str, Object obj, int i7) {
        this.f5250a = i7;
        this.f5253d = zVar;
        this.f5251b = z6;
        this.f5252c = str;
        this.f5254e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5250a;
        String str = this.f5252c;
        boolean z6 = this.f5251b;
        Object obj = this.f5254e;
        androidx.fragment.app.z zVar = this.f5253d;
        switch (i7) {
            case 0:
                ((DesktopAssistantFragment) zVar).lambda$postVideoWallpaperSaveResult$67(z6, str, (Context) obj);
                break;
            default:
                ((MiscSettingsFragment) zVar).lambda$postCrashUi$3(z6, str, (CrashReportUploader.Result) obj);
                break;
        }
    }
}
