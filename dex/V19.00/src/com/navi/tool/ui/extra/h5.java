package com.navi.tool.ui.extra;

import android.view.View;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h5 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5212a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity f5213b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ PermissionManagerActivity.AppInfo f5214c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5215d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5216e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5217f;

    public /* synthetic */ h5(int i7, int i8, androidx.appcompat.app.o oVar, PermissionManagerActivity.AppInfo appInfo, PermissionManagerActivity permissionManagerActivity, boolean z6) {
        this.f5212a = i8;
        this.f5213b = permissionManagerActivity;
        this.f5214c = appInfo;
        this.f5215d = z6;
        this.f5216e = i7;
        this.f5217f = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f5212a) {
            case 0:
                this.f5213b.lambda$showFreezeDialog$30(this.f5214c, this.f5215d, this.f5216e, this.f5217f, view);
                break;
            default:
                this.f5213b.lambda$showSuspendDialog$36(this.f5214c, this.f5215d, this.f5216e, this.f5217f, view);
                break;
        }
    }
}
