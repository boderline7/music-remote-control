package com.navi.tool.ui.log;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5662a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f5663b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f5664c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ List f5665d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ EditText f5666e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ List f5667f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ EditText f5668g;

    public /* synthetic */ e(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, List list2, EditText editText2, int i7) {
        this.f5662a = i7;
        this.f5663b = linearLayout;
        this.f5664c = linearLayout2;
        this.f5665d = list;
        this.f5666e = editText;
        this.f5667f = list2;
        this.f5668g = editText2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f5662a) {
            case 0:
                LogLinkFragment.lambda$showEditProfileDialogInternal$49(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
            case 1:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$105(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
            case 2:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$73(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
            case 3:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$78(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
            case 4:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$93(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$60(this.f5663b, this.f5664c, this.f5665d, this.f5666e, this.f5667f, this.f5668g, view);
                break;
        }
    }
}
