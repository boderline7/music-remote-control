package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y4 implements DialogInterface.OnShowListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5584a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5585b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5586c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5587d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity.AppInfo f5588e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f5589f;

    public /* synthetic */ y4(int i7, int i8, androidx.appcompat.app.o oVar, PermissionManagerActivity.AppInfo appInfo, PermissionManagerActivity permissionManagerActivity, boolean z6) {
        this.f5584a = i8;
        this.f5585b = permissionManagerActivity;
        this.f5586c = oVar;
        this.f5587d = i7;
        this.f5588e = appInfo;
        this.f5589f = z6;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        switch (this.f5584a) {
            case 0:
                this.f5585b.lambda$showFreezeDialog$29(this.f5586c, this.f5587d, this.f5588e, this.f5589f, dialogInterface);
                break;
            default:
                this.f5585b.lambda$showSuspendDialog$35(this.f5586c, this.f5587d, this.f5588e, this.f5589f, dialogInterface);
                break;
        }
    }
}
