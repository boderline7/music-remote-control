package com.navi.tool.ui.log;

import android.content.DialogInterface;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5707a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5708b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5709c;

    public /* synthetic */ m(LogLinkFragment logLinkFragment, List list, int i7) {
        this.f5707a = i7;
        this.f5708b = logLinkFragment;
        this.f5709c = list;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5707a;
        List list = this.f5709c;
        LogLinkFragment logLinkFragment = this.f5708b;
        switch (i8) {
            case 0:
                logLinkFragment.lambda$deleteSelectedAssistantProfiles$36(list, dialogInterface, i7);
                break;
            case 1:
                logLinkFragment.lambda$deleteSelectedMediaProfiles$34(list, dialogInterface, i7);
                break;
            default:
                logLinkFragment.lambda$deleteSelectedAppProfiles$32(list, dialogInterface, i7);
                break;
        }
    }
}
