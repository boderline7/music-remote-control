package com.navi.tool.ui.extra;

import android.view.View;
import android.widget.CheckBox;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f5 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5162a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5163b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5164c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5165d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5166e;

    public /* synthetic */ f5(PermissionManagerActivity permissionManagerActivity, Object obj, Object obj2, Object obj3, int i7) {
        this.f5162a = i7;
        this.f5163b = permissionManagerActivity;
        this.f5164c = obj;
        this.f5165d = obj2;
        this.f5166e = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5162a;
        PermissionManagerActivity permissionManagerActivity = this.f5163b;
        Object obj = this.f5166e;
        Object obj2 = this.f5165d;
        Object obj3 = this.f5164c;
        switch (i7) {
            case 0:
                permissionManagerActivity.lambda$showPermissionDialog$41((List) obj3, (List) obj2, (PermissionManagerActivity.AppInfo) obj, view);
                break;
            default:
                permissionManagerActivity.lambda$showRemoveOwnerDialog$3((CheckBox) obj3, (CheckBox) obj2, (androidx.appcompat.app.o) obj, view);
                break;
        }
    }
}
