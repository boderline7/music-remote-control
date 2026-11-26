package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d6 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5115a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TireLoginActivity f5116b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5117c;

    public /* synthetic */ d6(TireLoginActivity tireLoginActivity, String str, int i7) {
        this.f5115a = i7;
        this.f5116b = tireLoginActivity;
        this.f5117c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5115a;
        String str = this.f5117c;
        TireLoginActivity tireLoginActivity = this.f5116b;
        switch (i7) {
            case 0:
                tireLoginActivity.lambda$refreshCaptcha$8(str);
                break;
            case 1:
                tireLoginActivity.lambda$login$15(str);
                break;
            default:
                tireLoginActivity.lambda$doFetch$18(str);
                break;
        }
    }
}
