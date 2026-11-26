package com.navi.tool.ui.extra;

import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5288a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5289b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity.AppInfo f5290c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5291d;

    public /* synthetic */ k5(PermissionManagerActivity permissionManagerActivity, PermissionManagerActivity.AppInfo appInfo, boolean z6, int i7) {
        this.f5288a = i7;
        this.f5289b = permissionManagerActivity;
        this.f5290c = appInfo;
        this.f5291d = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5288a;
        PermissionManagerActivity permissionManagerActivity = this.f5289b;
        boolean z6 = this.f5291d;
        PermissionManagerActivity.AppInfo appInfo = this.f5290c;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$executeSuspendAction$39(appInfo, z6);
                break;
            default:
                permissionManagerActivity.lambda$executeFreezeAction$33(appInfo, z6);
                break;
        }
    }
}
