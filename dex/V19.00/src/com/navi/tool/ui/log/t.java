package com.navi.tool.ui.log;

import android.content.DialogInterface;
import com.navi.tool.model.LogMediaProfile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5739a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5740b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LogMediaProfile f5741c;

    public /* synthetic */ t(LogLinkFragment logLinkFragment, LogMediaProfile logMediaProfile, int i7) {
        this.f5739a = i7;
        this.f5740b = logLinkFragment;
        this.f5741c = logMediaProfile;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5739a;
        LogMediaProfile logMediaProfile = this.f5741c;
        LogLinkFragment logLinkFragment = this.f5740b;
        switch (i8) {
            case 0:
                logLinkFragment.lambda$initMediaProfilesUi$16(logMediaProfile, dialogInterface, i7);
                break;
            default:
                logLinkFragment.lambda$showCreateOrEditMediaProfileDialog$85(logMediaProfile, dialogInterface, i7);
                break;
        }
    }
}
