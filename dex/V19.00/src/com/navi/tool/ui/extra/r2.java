package com.navi.tool.ui.extra;

import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r2 implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5431a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5432b;

    public /* synthetic */ r2(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5431a = i7;
        this.f5432b = desktopAssistantFragment;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5431a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5432b;
        switch (i8) {
            case 0:
                desktopAssistantFragment.lambda$initMusicLogic$106(radioGroup, i7);
                break;
            case 1:
                desktopAssistantFragment.lambda$initMusicLogic$109(radioGroup, i7);
                break;
            case 2:
                desktopAssistantFragment.lambda$initMusicLogic$128(radioGroup, i7);
                break;
            case 3:
                desktopAssistantFragment.lambda$initMusicLogic$129(radioGroup, i7);
                break;
            case 4:
                desktopAssistantFragment.lambda$initMusicLogic$130(radioGroup, i7);
                break;
            case 5:
                desktopAssistantFragment.lambda$initMusicLogic$101(radioGroup, i7);
                break;
            case 6:
                desktopAssistantFragment.lambda$initMusicLogic$105(radioGroup, i7);
                break;
            default:
                desktopAssistantFragment.lambda$initLogic$37(radioGroup, i7);
                break;
        }
    }
}
