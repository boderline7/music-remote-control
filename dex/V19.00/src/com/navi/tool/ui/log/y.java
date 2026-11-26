package com.navi.tool.ui.log;

import android.view.View;
import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5753a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f5754b;

    public /* synthetic */ y(int i7, View view) {
        this.f5753a = i7;
        this.f5754b = view;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5753a;
        View view = this.f5754b;
        switch (i8) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$87(view, radioGroup, i7);
                break;
            case 1:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$67(view, radioGroup, i7);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$41(view, radioGroup, i7);
                break;
        }
    }
}
