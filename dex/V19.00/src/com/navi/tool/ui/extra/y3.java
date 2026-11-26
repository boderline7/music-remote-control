package com.navi.tool.ui.extra;

import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.ui.extra.QrCodeLoginDialog;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y3 implements QrCodeLoginDialog.OnLoginSuccessListener, QrCodeLoginDialog.OnLoginFailedListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5583a;

    public /* synthetic */ y3(FileManagerActivity fileManagerActivity) {
        this.f5583a = fileManagerActivity;
    }

    @Override // com.navi.tool.ui.extra.QrCodeLoginDialog.OnLoginFailedListener
    public final void onFailed(String str) {
        this.f5583a.lambda$showCloudDiskLoginDialog$5(str);
    }

    @Override // com.navi.tool.ui.extra.QrCodeLoginDialog.OnLoginSuccessListener
    public final void onSuccess(CloudDiskCredential cloudDiskCredential) {
        this.f5583a.lambda$showCloudDiskLoginDialog$3(cloudDiskCredential);
    }
}
