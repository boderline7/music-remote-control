package com.navi.tool.ui.log;

import android.content.DialogInterface;
import com.navi.tool.ui.voice.VoiceNavigationFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5710a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.z f5711b;

    public /* synthetic */ n(androidx.fragment.app.z zVar, int i7) {
        this.f5710a = i7;
        this.f5711b = zVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5710a;
        androidx.fragment.app.z zVar = this.f5711b;
        switch (i8) {
            case 0:
                ((LogLinkFragment) zVar).lambda$showCreateOrEditAssistantProfileDialog$63(dialogInterface, i7);
                break;
            default:
                ((VoiceNavigationFragment) zVar).lambda$initRestartControls$17(dialogInterface, i7);
                break;
        }
    }
}
