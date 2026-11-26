package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5234a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5235b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5236c;

    public /* synthetic */ i5(PermissionManagerActivity permissionManagerActivity, int i7, int i8) {
        this.f5234a = i8;
        this.f5235b = permissionManagerActivity;
        this.f5236c = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5234a;
        int i8 = this.f5236c;
        PermissionManagerActivity permissionManagerActivity = this.f5235b;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$checkAndPromptSuspendMismatch$23(i8);
                break;
            default:
                permissionManagerActivity.lambda$checkAndPromptFrozenMismatch$18(i8);
                break;
        }
    }
}
