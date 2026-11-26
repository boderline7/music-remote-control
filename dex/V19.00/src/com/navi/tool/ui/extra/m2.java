package com.navi.tool.ui.extra;

import android.widget.RadioButton;
import android.widget.RadioGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m2 implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5332a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.z f5333b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5334c;

    public /* synthetic */ m2(androidx.fragment.app.z zVar, Object obj, int i7) {
        this.f5332a = i7;
        this.f5333b = zVar;
        this.f5334c = obj;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5332a;
        Object obj = this.f5334c;
        androidx.fragment.app.z zVar = this.f5333b;
        switch (i8) {
            case 0:
                ((DesktopAssistantFragment) zVar).lambda$showSetDockAppsDialog$15((RadioButton) obj, radioGroup, i7);
                break;
            case 1:
                ((DesktopAssistantFragment) zVar).lambda$showSetDesktopAppsDialog$18((RadioButton) obj, radioGroup, i7);
                break;
            case 2:
                ((DesktopAssistantFragment) zVar).lambda$showSetDesktopAppsDialog$19((RadioButton) obj, radioGroup, i7);
                break;
            default:
                ((CarLinkFragment) zVar).lambda$setupSeatSwitch$61((String) obj, radioGroup, i7);
                break;
        }
    }
}
