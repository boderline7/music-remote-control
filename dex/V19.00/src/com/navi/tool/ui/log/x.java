package com.navi.tool.ui.log;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5751a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5752b;

    public /* synthetic */ x(LogLinkFragment logLinkFragment, int i7) {
        this.f5751a = i7;
        this.f5752b = logLinkFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5751a;
        LogLinkFragment logLinkFragment = this.f5752b;
        switch (i7) {
            case 0:
                logLinkFragment.lambda$initMediaProfilesUi$18(view);
                break;
            case 1:
                logLinkFragment.lambda$initMediaProfilesUi$19(view);
                break;
            case 2:
                logLinkFragment.lambda$initMediaProfilesUi$20(view);
                break;
            case 3:
                logLinkFragment.lambda$initMediaProfilesUi$21(view);
                break;
            case 4:
                logLinkFragment.lambda$initAssistantProfilesUi$27(view);
                break;
            case 5:
                logLinkFragment.lambda$initAssistantProfilesUi$28(view);
                break;
            case 6:
                logLinkFragment.lambda$initAssistantProfilesUi$29(view);
                break;
            case 7:
                logLinkFragment.lambda$initAssistantProfilesUi$30(view);
                break;
            case 8:
                logLinkFragment.lambda$initAppProfilesUi$9(view);
                break;
            case 9:
                logLinkFragment.lambda$initAppProfilesUi$10(view);
                break;
            case 10:
                logLinkFragment.lambda$initAppProfilesUi$11(view);
                break;
            default:
                logLinkFragment.lambda$initAppProfilesUi$12(view);
                break;
        }
    }
}
