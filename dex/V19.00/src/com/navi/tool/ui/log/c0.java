package com.navi.tool.ui.log;

import android.view.View;
import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c0 implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5653a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f5654b;

    public /* synthetic */ c0(int i7, View view) {
        this.f5653a = i7;
        this.f5654b = view;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5653a;
        View view = this.f5654b;
        switch (i7) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$88(view, compoundButton, z6);
                break;
            case 1:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$89(view, compoundButton, z6);
                break;
            case 2:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$90(view, compoundButton, z6);
                break;
            case 3:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$92(view, compoundButton, z6);
                break;
            case 4:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$68(view, compoundButton, z6);
                break;
            case 5:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$69(view, compoundButton, z6);
                break;
            case 6:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$70(view, compoundButton, z6);
                break;
            case 7:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$72(view, compoundButton, z6);
                break;
            case 8:
                LogLinkFragment.lambda$showEditProfileDialogInternal$42(view, compoundButton, z6);
                break;
            case 9:
                LogLinkFragment.lambda$showEditProfileDialogInternal$43(view, compoundButton, z6);
                break;
            case 10:
                LogLinkFragment.lambda$showEditProfileDialogInternal$44(view, compoundButton, z6);
                break;
            case 11:
                LogLinkFragment.lambda$showEditProfileDialogInternal$45(view, compoundButton, z6);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$47(view, compoundButton, z6);
                break;
        }
    }
}
