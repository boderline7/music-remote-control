package com.navi.tool.utils;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5858a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5859b;

    public /* synthetic */ e(int i7, Object obj) {
        this.f5858a = i7;
        this.f5859b = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5858a;
        Object obj = this.f5859b;
        switch (i8) {
            case 0:
                ((ConfigSyncManager) obj).lambda$checkPostDownloadPermissions$5(dialogInterface, i7);
                break;
            case 1:
                ((ConfigSyncManager) obj).lambda$checkPostDownloadPermissions$6(dialogInterface, i7);
                break;
            default:
                ((AudioPlayerHelper) obj).lambda$playAudio$0(dialogInterface, i7);
                break;
        }
    }
}
