package com.navi.tool.ui.voice;

import android.widget.CompoundButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5844a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ VoiceNavigationFragment f5845b;

    public /* synthetic */ a(VoiceNavigationFragment voiceNavigationFragment, int i7) {
        this.f5844a = i7;
        this.f5845b = voiceNavigationFragment;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5844a;
        VoiceNavigationFragment voiceNavigationFragment = this.f5845b;
        switch (i7) {
            case 0:
                voiceNavigationFragment.lambda$initReturnAppControls$13(compoundButton, z6);
                break;
            case 1:
                voiceNavigationFragment.lambda$initRouteTransferControls$7(compoundButton, z6);
                break;
            case 2:
                voiceNavigationFragment.lambda$initRouteTransferControls$9(compoundButton, z6);
                break;
            case 3:
                voiceNavigationFragment.lambda$initFeatureControls$1(compoundButton, z6);
                break;
            case 4:
                voiceNavigationFragment.lambda$initFeatureControls$2(compoundButton, z6);
                break;
            case 5:
                voiceNavigationFragment.lambda$initFeatureControls$3(compoundButton, z6);
                break;
            case 6:
                voiceNavigationFragment.lambda$initFeatureControls$5(compoundButton, z6);
                break;
            default:
                voiceNavigationFragment.lambda$initFeatureControls$6(compoundButton, z6);
                break;
        }
    }
}
