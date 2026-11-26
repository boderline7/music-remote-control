package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a5 implements DialogInterface.OnShowListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5018a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5019b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5020c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity.AppInfo f5021d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f5022e;

    public /* synthetic */ a5(PermissionManagerActivity permissionManagerActivity, androidx.appcompat.app.o oVar, PermissionManagerActivity.AppInfo appInfo, boolean z6, int i7) {
        this.f5018a = i7;
        this.f5019b = permissionManagerActivity;
        this.f5020c = oVar;
        this.f5021d = appInfo;
        this.f5022e = z6;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        int i7 = this.f5018a;
        androidx.appcompat.app.o oVar = this.f5020c;
        PermissionManagerActivity permissionManagerActivity = this.f5019b;
        boolean z6 = this.f5022e;
        PermissionManagerActivity.AppInfo appInfo = this.f5021d;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$showSuspendDialog$37(oVar, appInfo, z6, dialogInterface);
                break;
            default:
                permissionManagerActivity.lambda$showFreezeDialog$31(oVar, appInfo, z6, dialogInterface);
                break;
        }
    }
}
