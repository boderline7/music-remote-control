package com.navi.tool.ui.extra;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5320a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5321b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5322c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5323d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5324e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5325f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f5326g;

    public /* synthetic */ l5(FileManagerActivity fileManagerActivity, TextView textView, ProgressBar progressBar, TextView textView2, File file, androidx.appcompat.app.o oVar) {
        this.f5321b = fileManagerActivity;
        this.f5322c = textView;
        this.f5323d = progressBar;
        this.f5324e = textView2;
        this.f5325f = file;
        this.f5326g = oVar;
    }

    public /* synthetic */ l5(PermissionManagerActivity permissionManagerActivity, ArrayList arrayList, LinearLayout linearLayout, PermissionManagerActivity.AppInfo appInfo, List list, List list2) {
        this.f5321b = permissionManagerActivity;
        this.f5322c = arrayList;
        this.f5325f = linearLayout;
        this.f5326g = appInfo;
        this.f5323d = list;
        this.f5324e = list2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5320a;
        Object obj = this.f5326g;
        Object obj2 = this.f5325f;
        Object obj3 = this.f5324e;
        Object obj4 = this.f5323d;
        Object obj5 = this.f5322c;
        androidx.appcompat.app.r rVar = this.f5321b;
        switch (i7) {
            case 0:
                ((PermissionManagerActivity) rVar).lambda$showPermissionDialog$43((List) obj5, (LinearLayout) obj2, (PermissionManagerActivity.AppInfo) obj, (List) obj4, (List) obj3);
                break;
            default:
                ((FileManagerActivity) rVar).lambda$executePaste$105((TextView) obj5, (ProgressBar) obj4, (TextView) obj3, (File) obj2, (androidx.appcompat.app.o) obj);
                break;
        }
    }
}
