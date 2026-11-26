package com.navi.tool.ui.extra;

import android.view.View;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q3 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5419a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5420b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ File f5421c;

    public /* synthetic */ q3(FileManagerActivity fileManagerActivity, File file, int i7) {
        this.f5419a = i7;
        this.f5420b = fileManagerActivity;
        this.f5421c = file;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5419a;
        File file = this.f5421c;
        FileManagerActivity fileManagerActivity = this.f5420b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$refreshStorageVolumes$1(file, view);
                break;
            default:
                fileManagerActivity.lambda$updateBreadcrumbs$71(file, view);
                break;
        }
    }
}
