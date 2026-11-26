package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c6 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5078a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TireLoginActivity f5079b;

    public /* synthetic */ c6(TireLoginActivity tireLoginActivity, int i7) {
        this.f5078a = i7;
        this.f5079b = tireLoginActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5078a;
        TireLoginActivity tireLoginActivity = this.f5079b;
        switch (i7) {
            case 0:
                tireLoginActivity.lambda$buildLoginArea$3(view);
                break;
            case 1:
                tireLoginActivity.lambda$buildLoginArea$4(view);
                break;
            case 2:
                tireLoginActivity.lambda$buildLoginArea$5(view);
                break;
            case 3:
                tireLoginActivity.lambda$buildDataArea$6(view);
                break;
            case 4:
                tireLoginActivity.lambda$buildContentView$0(view);
                break;
            default:
                tireLoginActivity.lambda$buildContentView$1(view);
                break;
        }
    }
}
