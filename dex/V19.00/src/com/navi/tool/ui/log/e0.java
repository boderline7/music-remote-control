package com.navi.tool.ui.log;

import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e0 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5669a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5670b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f5671c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ View f5672d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f5673e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f5674f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ List f5675g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ List f5676h;

    public /* synthetic */ e0(CheckBox checkBox, View view, View view2, View view3, View view4, ArrayList arrayList, ArrayList arrayList2, int i7) {
        this.f5669a = i7;
        this.f5670b = checkBox;
        this.f5671c = view;
        this.f5672d = view2;
        this.f5673e = view3;
        this.f5674f = view4;
        this.f5675g = arrayList;
        this.f5676h = arrayList2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f5669a) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$94(this.f5670b, this.f5671c, this.f5672d, this.f5673e, this.f5674f, this.f5675g, this.f5676h, view);
                break;
            case 1:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$74(this.f5670b, this.f5671c, this.f5672d, this.f5673e, this.f5674f, this.f5675g, this.f5676h, view);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$50(this.f5670b, this.f5671c, this.f5672d, this.f5673e, this.f5674f, this.f5675g, this.f5676h, view);
                break;
        }
    }
}
