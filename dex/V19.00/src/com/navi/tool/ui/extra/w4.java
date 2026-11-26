package com.navi.tool.ui.extra;

import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5543a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5544b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Set f5545c;

    public /* synthetic */ w4(int i7, PermissionManagerActivity permissionManagerActivity, Set set) {
        this.f5543a = i7;
        this.f5544b = permissionManagerActivity;
        this.f5545c = set;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5543a;
        Set set = this.f5545c;
        PermissionManagerActivity permissionManagerActivity = this.f5544b;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$checkAndPromptSuspendMismatch$24(set);
                break;
            default:
                permissionManagerActivity.lambda$checkAndPromptFrozenMismatch$19(set);
                break;
        }
    }
}
