package com.navi.tool.ui.log;

import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5698a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5699b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f5700c;

    public /* synthetic */ j(LogLinkFragment logLinkFragment, Context context, int i7) {
        this.f5698a = i7;
        this.f5699b = logLinkFragment;
        this.f5700c = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5698a;
        Context context = this.f5700c;
        LogLinkFragment logLinkFragment = this.f5699b;
        switch (i7) {
            case 0:
                logLinkFragment.lambda$reloadMediaProfilesAsync$82(context);
                break;
            case 1:
                logLinkFragment.lambda$reloadAppProfilesAsync$38(context);
                break;
            default:
                logLinkFragment.lambda$reloadAssistantProfilesAsync$84(context);
                break;
        }
    }
}
