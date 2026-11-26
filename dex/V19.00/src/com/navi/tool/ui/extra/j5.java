package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5264a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5265b;

    public /* synthetic */ j5(PermissionManagerActivity permissionManagerActivity, int i7) {
        this.f5264a = i7;
        this.f5265b = permissionManagerActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5264a;
        PermissionManagerActivity permissionManagerActivity = this.f5265b;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$loadApps$17();
                break;
            case 1:
                permissionManagerActivity.lambda$checkAndPromptFrozenMismatch$22();
                break;
            default:
                permissionManagerActivity.lambda$checkAndPromptSuspendMismatch$27();
                break;
        }
    }
}
