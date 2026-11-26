package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a6 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5023a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TireLoginActivity f5024b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5025c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5026d;

    public /* synthetic */ a6(TireLoginActivity tireLoginActivity, String str, boolean z6, int i7) {
        this.f5023a = i7;
        this.f5024b = tireLoginActivity;
        this.f5025c = str;
        this.f5026d = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5023a;
        TireLoginActivity tireLoginActivity = this.f5024b;
        boolean z6 = this.f5026d;
        String str = this.f5025c;
        switch (i7) {
            case 0:
                tireLoginActivity.lambda$refreshCaptcha$9(str, z6);
                break;
            default:
                tireLoginActivity.lambda$refreshCaptcha$7(str, z6);
                break;
        }
    }
}
