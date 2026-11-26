package com.navi.tool.ui.log;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5704a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5705b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5706c;

    public /* synthetic */ l(LogLinkFragment logLinkFragment, List list, int i7) {
        this.f5704a = i7;
        this.f5705b = logLinkFragment;
        this.f5706c = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5704a;
        List list = this.f5706c;
        LogLinkFragment logLinkFragment = this.f5705b;
        switch (i7) {
            case 0:
                logLinkFragment.lambda$reloadAppProfilesAsync$37(list);
                break;
            case 1:
                logLinkFragment.lambda$reloadMediaProfilesAsync$81(list);
                break;
            default:
                logLinkFragment.lambda$reloadAssistantProfilesAsync$83(list);
                break;
        }
    }
}
