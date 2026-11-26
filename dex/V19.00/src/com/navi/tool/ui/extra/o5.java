package com.navi.tool.ui.extra;

import android.view.View;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class o5 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5374a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5375b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5376c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f5377d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5378e;

    public /* synthetic */ o5(Object obj, Object obj2, boolean z6, Object obj3, int i7) {
        this.f5374a = i7;
        this.f5375b = obj;
        this.f5376c = obj2;
        this.f5377d = z6;
        this.f5378e = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5374a;
        boolean z6 = this.f5377d;
        Object obj = this.f5378e;
        Object obj2 = this.f5376c;
        Object obj3 = this.f5375b;
        switch (i7) {
            case 0:
                ((PermissionManagerActivity) obj3).lambda$showFreezeDialog$28((PermissionManagerActivity.AppInfo) obj2, z6, (androidx.appcompat.app.o) obj, view);
                break;
            case 1:
                ((PermissionManagerActivity) obj3).lambda$showSuspendDialog$34((PermissionManagerActivity.AppInfo) obj2, z6, (androidx.appcompat.app.o) obj, view);
                break;
            default:
                ((DesktopAssistantFragment) obj3).lambda$setupWeeklyVideoButton$65((String) obj2, z6, (String) obj, view);
                break;
        }
    }
}
