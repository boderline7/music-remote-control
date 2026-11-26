package com.navi.tool.ui.extra;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c5 implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5074a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5075b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5076c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f5077d;

    public /* synthetic */ c5(PermissionManagerActivity permissionManagerActivity, String str, String str2, int i7) {
        this.f5074a = i7;
        this.f5075b = permissionManagerActivity;
        this.f5076c = str;
        this.f5077d = str2;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5074a;
        PermissionManagerActivity permissionManagerActivity = this.f5075b;
        String str = this.f5077d;
        String str2 = this.f5076c;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$addPermissionSwitch$46(str2, str, compoundButton, z6);
                break;
            default:
                permissionManagerActivity.lambda$addVirtualSwitch$48(str2, str, compoundButton, z6);
                break;
        }
    }
}
