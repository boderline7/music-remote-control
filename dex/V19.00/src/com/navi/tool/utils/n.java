package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.utils.StoreDownloadManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5876a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f5877b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5878c;

    public /* synthetic */ n(Context context, int i7, String str) {
        this.f5876a = i7;
        this.f5877b = context;
        this.f5878c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5876a;
        String str = this.f5878c;
        Context context = this.f5877b;
        switch (i7) {
            case 0:
                StoreDownloadManager.AnonymousClass2.lambda$onError$1(context, str);
                break;
            case 1:
                AutoCrashUploader.lambda$maybeUpload$0(context, str);
                break;
            default:
                MegaImeHelper.lambda$scheduleRestore$0(context, str);
                break;
        }
    }
}
