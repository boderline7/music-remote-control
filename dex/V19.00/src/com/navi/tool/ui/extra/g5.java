package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g5 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5185a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5186b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Set f5187c;

    public /* synthetic */ g5(int i7, PermissionManagerActivity permissionManagerActivity, Set set) {
        this.f5185a = i7;
        this.f5186b = permissionManagerActivity;
        this.f5187c = set;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5185a;
        Set set = this.f5187c;
        PermissionManagerActivity permissionManagerActivity = this.f5186b;
        switch (i8) {
            case 0:
                permissionManagerActivity.lambda$checkAndPromptSuspendMismatch$25(set, dialogInterface, i7);
                break;
            default:
                permissionManagerActivity.lambda$checkAndPromptFrozenMismatch$20(set, dialogInterface, i7);
                break;
        }
    }
}
