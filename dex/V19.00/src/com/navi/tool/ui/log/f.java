package com.navi.tool.ui.log;

import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5677a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CheckBox f5678b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f5679c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ View f5680d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f5681e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f5682f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ List f5683g;

    public /* synthetic */ f(CheckBox checkBox, View view, View view2, View view3, View view4, ArrayList arrayList, int i7) {
        this.f5677a = i7;
        this.f5678b = checkBox;
        this.f5679c = view;
        this.f5680d = view2;
        this.f5681e = view3;
        this.f5682f = view4;
        this.f5683g = arrayList;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f5677a) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$96(this.f5678b, this.f5679c, this.f5680d, this.f5681e, this.f5682f, this.f5683g, view);
                break;
            case 1:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$76(this.f5678b, this.f5679c, this.f5680d, this.f5681e, this.f5682f, this.f5683g, view);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$52(this.f5678b, this.f5679c, this.f5680d, this.f5681e, this.f5682f, this.f5683g, view);
                break;
        }
    }
}
