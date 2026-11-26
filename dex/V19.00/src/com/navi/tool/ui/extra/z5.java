package com.navi.tool.ui.extra;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5608a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TextEditorActivity f5609b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ IOException f5610c;

    public /* synthetic */ z5(TextEditorActivity textEditorActivity, IOException iOException, int i7) {
        this.f5608a = i7;
        this.f5609b = textEditorActivity;
        this.f5610c = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5608a;
        IOException iOException = this.f5610c;
        TextEditorActivity textEditorActivity = this.f5609b;
        switch (i7) {
            case 0:
                textEditorActivity.lambda$loadFileContent$1(iOException);
                break;
            default:
                textEditorActivity.lambda$saveFileContent$4(iOException);
                break;
        }
    }
}
