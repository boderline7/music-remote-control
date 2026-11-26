package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k3 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5280a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5281b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5282c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean[] f5283d;

    public /* synthetic */ k3(FileManagerActivity fileManagerActivity, androidx.appcompat.app.o oVar, boolean[] zArr) {
        this.f5281b = fileManagerActivity;
        this.f5282c = oVar;
        this.f5283d = zArr;
    }

    public /* synthetic */ k3(FileManagerActivity fileManagerActivity, boolean[] zArr, androidx.appcompat.app.o oVar) {
        this.f5281b = fileManagerActivity;
        this.f5283d = zArr;
        this.f5282c = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5280a;
        FileManagerActivity fileManagerActivity = this.f5281b;
        boolean[] zArr = this.f5283d;
        androidx.appcompat.app.o oVar = this.f5282c;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$uploadToCloudDisk$89(zArr, oVar, view);
                break;
            default:
                fileManagerActivity.lambda$promptForConflict$109(oVar, zArr, view);
                break;
        }
    }
}
