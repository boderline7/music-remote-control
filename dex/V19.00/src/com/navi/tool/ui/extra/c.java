package com.navi.tool.ui.extra;

import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5059a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EditText f5060b;

    public /* synthetic */ c(EditText editText, int i7) {
        this.f5059a = i7;
        this.f5060b = editText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5059a;
        EditText editText = this.f5060b;
        switch (i7) {
            case 0:
                AdditionalFeaturesFragment.lambda$showChangeNameDialog$47(editText);
                break;
            case 1:
                DesktopAssistantFragment.lambda$showChangeTopInfoNameDialog$31(editText);
                break;
            default:
                DesktopAssistantFragment.lambda$showChangeNameDialog$10(editText);
                break;
        }
    }
}
