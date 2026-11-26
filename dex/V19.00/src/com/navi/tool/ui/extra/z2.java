package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z2 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5600a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5601b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ File f5602c;

    public /* synthetic */ z2(FileManagerActivity fileManagerActivity, File file, int i7) {
        this.f5600a = i7;
        this.f5601b = fileManagerActivity;
        this.f5602c = file;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5600a;
        File file = this.f5602c;
        FileManagerActivity fileManagerActivity = this.f5601b;
        switch (i8) {
            case 0:
                fileManagerActivity.lambda$showUnzipOptionsDialog$82(file, dialogInterface, i7);
                break;
            default:
                fileManagerActivity.lambda$showUnzipDialog$81(file, dialogInterface, i7);
                break;
        }
    }
}
