package com.navi.tool.ui.extra;

import com.navi.tool.tire.api.TireBrand;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b6 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5054a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TireLoginActivity f5055b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ TireBrand f5056c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f5057d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f5058e;

    public /* synthetic */ b6(TireLoginActivity tireLoginActivity, TireBrand tireBrand, String str, String str2, int i7) {
        this.f5054a = i7;
        this.f5055b = tireLoginActivity;
        this.f5056c = tireBrand;
        this.f5057d = str;
        this.f5058e = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5054a;
        TireBrand tireBrand = this.f5056c;
        TireLoginActivity tireLoginActivity = this.f5055b;
        String str = this.f5058e;
        String str2 = this.f5057d;
        switch (i7) {
            case 0:
                tireLoginActivity.lambda$login$16(tireBrand, str2, str);
                break;
            default:
                tireLoginActivity.lambda$sendCode$13(tireBrand, str2, str);
                break;
        }
    }
}
