package com.navi.tool.ui.log;

import android.view.View;
import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d0 implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5659a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f5660b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f5661c;

    public /* synthetic */ d0(View view, View view2, int i7) {
        this.f5659a = i7;
        this.f5660b = view;
        this.f5661c = view2;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5659a;
        View view = this.f5661c;
        View view2 = this.f5660b;
        switch (i8) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$91(view2, view, radioGroup, i7);
                break;
            case 1:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$71(view2, view, radioGroup, i7);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$46(view2, view, radioGroup, i7);
                break;
        }
    }
}
