package com.navi.tool.ui.extra;

import android.view.View;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import java.io.File;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v2 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5514a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ File f5515b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.recyclerview.widget.j2 f5516c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ List f5517d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean[] f5518e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5519f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ androidx.recyclerview.widget.h1 f5520g;

    public /* synthetic */ v2(androidx.recyclerview.widget.h1 h1Var, File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, int i7) {
        this.f5514a = i7;
        this.f5520g = h1Var;
        this.f5515b = file;
        this.f5516c = j2Var;
        this.f5517d = list;
        this.f5518e = zArr;
        this.f5519f = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5514a;
        androidx.recyclerview.widget.h1 h1Var = this.f5520g;
        switch (i7) {
            case 0:
                ((DesktopAssistantFragment.AnonymousClass12) h1Var).lambda$onBindViewHolder$1(this.f5515b, this.f5516c, this.f5517d, this.f5518e, this.f5519f, view);
                break;
            default:
                ((DesktopAssistantFragment.AnonymousClass21) h1Var).lambda$onBindViewHolder$3(this.f5515b, this.f5516c, this.f5517d, this.f5518e, this.f5519f, view);
                break;
        }
    }
}
