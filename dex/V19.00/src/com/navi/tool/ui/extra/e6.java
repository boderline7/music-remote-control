package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e6 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5146a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TireLoginActivity f5147b;

    public /* synthetic */ e6(TireLoginActivity tireLoginActivity, int i7) {
        this.f5146a = i7;
        this.f5147b = tireLoginActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5146a;
        TireLoginActivity tireLoginActivity = this.f5147b;
        switch (i7) {
            case 0:
                tireLoginActivity.lambda$login$14();
                break;
            default:
                tireLoginActivity.lambda$focusCaptchaInput$10();
                break;
        }
    }
}
