package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5496a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5497b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f5498c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5499d;

    public /* synthetic */ u4(PermissionManagerActivity permissionManagerActivity, boolean z6, boolean z7, int i7) {
        this.f5496a = i7;
        this.f5497b = permissionManagerActivity;
        this.f5498c = z6;
        this.f5499d = z7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5496a;
        PermissionManagerActivity permissionManagerActivity = this.f5497b;
        boolean z6 = this.f5499d;
        boolean z7 = this.f5498c;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$executeSuspendAction$38(z7, z6);
                break;
            default:
                permissionManagerActivity.lambda$executeFreezeAction$32(z7, z6);
                break;
        }
    }
}
