package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5353a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5354b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ StringBuilder f5355c;

    public /* synthetic */ n3(androidx.appcompat.app.o oVar, StringBuilder sb, int i7) {
        this.f5353a = i7;
        this.f5354b = oVar;
        this.f5355c = sb;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5353a;
        StringBuilder sb = this.f5355c;
        androidx.appcompat.app.o oVar = this.f5354b;
        switch (i7) {
            case 0:
                FileManagerActivity.lambda$showMultiFileProperties$45(oVar, sb);
                break;
            default:
                FileManagerActivity.lambda$showSingleFileProperties$43(oVar, sb);
                break;
        }
    }
}
