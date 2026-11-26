package com.navi.tool.ui.voice;

import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5848a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ VoiceNavigationFragment f5849b;

    public /* synthetic */ c(VoiceNavigationFragment voiceNavigationFragment, int i7) {
        this.f5848a = i7;
        this.f5849b = voiceNavigationFragment;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5848a;
        VoiceNavigationFragment voiceNavigationFragment = this.f5849b;
        switch (i8) {
            case 0:
                voiceNavigationFragment.lambda$initRouteTransferControls$10(radioGroup, i7);
                break;
            default:
                voiceNavigationFragment.lambda$initFeatureControls$4(radioGroup, i7);
                break;
        }
    }
}
