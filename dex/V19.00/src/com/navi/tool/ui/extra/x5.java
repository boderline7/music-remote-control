package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5564a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TextEditorActivity f5565b;

    public /* synthetic */ x5(TextEditorActivity textEditorActivity, int i7) {
        this.f5564a = i7;
        this.f5565b = textEditorActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5564a;
        TextEditorActivity textEditorActivity = this.f5565b;
        switch (i7) {
            case 0:
                textEditorActivity.lambda$loadFileContent$2();
                break;
            default:
                textEditorActivity.lambda$saveFileContent$3();
                break;
        }
    }
}
