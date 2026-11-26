package com.navi.tool.ui.extra;

import java.util.HashSet;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5111a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5112b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5113c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Set f5114d;

    public /* synthetic */ d5(PermissionManagerActivity permissionManagerActivity, int i7, HashSet hashSet, int i8) {
        this.f5111a = i8;
        this.f5112b = permissionManagerActivity;
        this.f5113c = i7;
        this.f5114d = hashSet;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5111a;
        PermissionManagerActivity permissionManagerActivity = this.f5112b;
        Set set = this.f5114d;
        int i8 = this.f5113c;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$checkAndPromptFrozenMismatch$21(i8, set);
                break;
            default:
                permissionManagerActivity.lambda$checkAndPromptSuspendMismatch$26(i8, set);
                break;
        }
    }
}
