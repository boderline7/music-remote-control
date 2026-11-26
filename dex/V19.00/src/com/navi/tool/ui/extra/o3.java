package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class o3 implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5371a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5372b;

    public /* synthetic */ o3(FileManagerActivity fileManagerActivity, int i7) {
        this.f5371a = i7;
        this.f5372b = fileManagerActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5371a;
        FileManagerActivity fileManagerActivity = this.f5372b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$initTransferLogic$25(compoundButton, z6);
                break;
            default:
                fileManagerActivity.lambda$setupHttpServer$119(compoundButton, z6);
                break;
        }
    }
}
