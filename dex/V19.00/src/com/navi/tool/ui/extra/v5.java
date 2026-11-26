package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5524a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ StorageAnalysisActivity f5525b;

    public /* synthetic */ v5(StorageAnalysisActivity storageAnalysisActivity, int i7) {
        this.f5524a = i7;
        this.f5525b = storageAnalysisActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5524a;
        StorageAnalysisActivity storageAnalysisActivity = this.f5525b;
        switch (i7) {
            case 0:
                storageAnalysisActivity.lambda$startScan$9();
                break;
            default:
                storageAnalysisActivity.lambda$startScan$8();
                break;
        }
    }
}
