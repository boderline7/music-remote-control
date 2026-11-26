package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.utils.StoreDownloadManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5869a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5870b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5871c;

    public /* synthetic */ l(int i7, Object obj, Object obj2) {
        this.f5869a = i7;
        this.f5870b = obj;
        this.f5871c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5869a;
        Object obj = this.f5871c;
        Object obj2 = this.f5870b;
        switch (i7) {
            case 0:
                StoreDownloadManager.AnonymousClass1.lambda$onResponse$1((Context) obj2, (x0) obj);
                break;
            case 1:
                StoreDownloadManager.AnonymousClass1.lambda$onResponse$3((Context) obj2, (Exception) obj);
                break;
            case 2:
                StoreDownloadManager.AnonymousClass1.lambda$onFailure$0((Context) obj2, (IOException) obj);
                break;
            case 3:
                AnrWatchdog.lambda$loop$2(obj2, (boolean[]) obj);
                break;
            case 4:
                ((ConfigSyncManager) obj2).lambda$maybeDownloadAndApply$1((String) obj);
                break;
            default:
                DeviceTaskRunner.lambda$exec$0((Process) obj2, (ByteArrayOutputStream) obj);
                break;
        }
    }
}
