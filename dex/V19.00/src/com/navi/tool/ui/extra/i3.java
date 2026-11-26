package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i3 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5228a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5229b;

    public /* synthetic */ i3(FileManagerActivity fileManagerActivity, int i7) {
        this.f5228a = i7;
        this.f5229b = fileManagerActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5228a;
        FileManagerActivity fileManagerActivity = this.f5229b;
        switch (i8) {
            case 0:
                fileManagerActivity.lambda$showReceiveProgressDialog$26(dialogInterface, i7);
                break;
            case 1:
                fileManagerActivity.lambda$startConfigBackup$8(dialogInterface, i7);
                break;
            case 2:
                fileManagerActivity.lambda$showCreateOptions$113(dialogInterface, i7);
                break;
            default:
                fileManagerActivity.lambda$showDeleteSelectionDialog$86(dialogInterface, i7);
                break;
        }
    }
}
