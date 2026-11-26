package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h3 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5204a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5205b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5206c;

    public /* synthetic */ h3(FileManagerActivity fileManagerActivity, String str, int i7) {
        this.f5204a = i7;
        this.f5205b = fileManagerActivity;
        this.f5206c = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5204a;
        String str = this.f5206c;
        FileManagerActivity fileManagerActivity = this.f5205b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$startHttpServer$122(str, view);
                break;
            default:
                fileManagerActivity.lambda$updateBreadcrumbs$70(str, view);
                break;
        }
    }
}
