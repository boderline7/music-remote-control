package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5590a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TextEditorActivity f5591b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5592c;

    public /* synthetic */ y5(TextEditorActivity textEditorActivity, String str, int i7) {
        this.f5590a = i7;
        this.f5591b = textEditorActivity;
        this.f5592c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5590a;
        String str = this.f5592c;
        TextEditorActivity textEditorActivity = this.f5591b;
        switch (i7) {
            case 0:
                textEditorActivity.lambda$saveFileContent$5(str);
                break;
            default:
                textEditorActivity.lambda$loadFileContent$0(str);
                break;
        }
    }
}
