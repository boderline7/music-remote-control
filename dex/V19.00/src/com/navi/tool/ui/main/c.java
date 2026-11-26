package com.navi.tool.ui.main;

import com.navi.tool.ui.main.StoreServiceFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5767a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ StoreServiceFragment.AnonymousClass1 f5768b;

    public /* synthetic */ c(StoreServiceFragment.AnonymousClass1 anonymousClass1, int i7) {
        this.f5767a = i7;
        this.f5768b = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5767a;
        StoreServiceFragment.AnonymousClass1 anonymousClass1 = this.f5768b;
        switch (i7) {
            case 0:
                anonymousClass1.lambda$onDownload$0();
                break;
            default:
                anonymousClass1.lambda$onDownloadAndExtract$1();
                break;
        }
    }
}
