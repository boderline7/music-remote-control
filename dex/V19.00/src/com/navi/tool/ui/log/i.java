package com.navi.tool.ui.log;

import android.content.DialogInterface;
import com.navi.tool.model.LogAssistantProfile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5695a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5696b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LogAssistantProfile f5697c;

    public /* synthetic */ i(LogLinkFragment logLinkFragment, LogAssistantProfile logAssistantProfile, int i7) {
        this.f5695a = i7;
        this.f5696b = logLinkFragment;
        this.f5697c = logAssistantProfile;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5695a;
        LogAssistantProfile logAssistantProfile = this.f5697c;
        LogLinkFragment logLinkFragment = this.f5696b;
        switch (i8) {
            case 0:
                logLinkFragment.lambda$initAssistantProfilesUi$25(logAssistantProfile, dialogInterface, i7);
                break;
            default:
                logLinkFragment.lambda$showCreateOrEditAssistantProfileDialog$65(logAssistantProfile, dialogInterface, i7);
                break;
        }
    }
}
