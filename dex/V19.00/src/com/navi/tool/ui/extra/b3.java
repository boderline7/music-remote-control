package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5043a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5044b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5045c;

    public /* synthetic */ b3(FileManagerActivity fileManagerActivity, androidx.appcompat.app.o oVar, int i7) {
        this.f5043a = i7;
        this.f5044b = fileManagerActivity;
        this.f5045c = oVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5043a;
        androidx.appcompat.app.o oVar = this.f5045c;
        FileManagerActivity fileManagerActivity = this.f5044b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$executePaste$104(oVar);
                break;
            default:
                fileManagerActivity.lambda$showMultiFileProperties$46(oVar);
                break;
        }
    }
}
