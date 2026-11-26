package com.navi.tool.ui.log;

import android.content.DialogInterface;
import com.navi.tool.model.LogAppProfile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5701a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5702b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LogAppProfile f5703c;

    public /* synthetic */ k(LogLinkFragment logLinkFragment, LogAppProfile logAppProfile, int i7) {
        this.f5701a = i7;
        this.f5702b = logLinkFragment;
        this.f5703c = logAppProfile;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5701a;
        LogAppProfile logAppProfile = this.f5703c;
        LogLinkFragment logLinkFragment = this.f5702b;
        switch (i8) {
            case 0:
                logLinkFragment.lambda$initAppProfilesUi$7(logAppProfile, dialogInterface, i7);
                break;
            default:
                logLinkFragment.lambda$showCreateOrEditProfileDialog$39(logAppProfile, dialogInterface, i7);
                break;
        }
    }
}
