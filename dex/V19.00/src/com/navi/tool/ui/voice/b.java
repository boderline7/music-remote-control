package com.navi.tool.ui.voice;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5846a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ VoiceNavigationFragment f5847b;

    public /* synthetic */ b(VoiceNavigationFragment voiceNavigationFragment, int i7) {
        this.f5846a = i7;
        this.f5847b = voiceNavigationFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5846a;
        VoiceNavigationFragment voiceNavigationFragment = this.f5847b;
        switch (i7) {
            case 0:
                voiceNavigationFragment.lambda$initReturnAppControls$14(view);
                break;
            case 1:
                voiceNavigationFragment.lambda$initRestartControls$18(view);
                break;
            default:
                voiceNavigationFragment.lambda$initRouteTransferControls$8(view);
                break;
        }
    }
}
