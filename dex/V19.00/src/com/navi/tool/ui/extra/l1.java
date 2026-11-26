package com.navi.tool.ui.extra;

import android.view.View;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l1 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5298a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5299b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5300c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Serializable f5301d;

    public /* synthetic */ l1(Object obj, int i7, Serializable serializable, int i8) {
        this.f5298a = i8;
        this.f5299b = obj;
        this.f5300c = i7;
        this.f5301d = serializable;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5298a;
        int i8 = this.f5300c;
        Object obj = this.f5301d;
        Object obj2 = this.f5299b;
        switch (i7) {
            case 0:
                ((DesktopAssistantCarControlHelper) obj2).lambda$showFragranceControlDialog$73(i8, (androidx.appcompat.app.o[]) obj, view);
                break;
            case 1:
                ((DesktopAssistantCarControlHelper) obj2).lambda$showFragranceControlDialog$74(i8, (androidx.appcompat.app.o[]) obj, view);
                break;
            default:
                ((FileManagerActivity) obj2).lambda$addCloudBreadcrumbItem$14(i8, (String) obj, view);
                break;
        }
    }
}
