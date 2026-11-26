package com.navi.tool.ui.extra;

import android.view.View;
import android.widget.CompoundButton;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5504a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5505b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5506c;

    public /* synthetic */ v(int i7, Object obj, Object obj2) {
        this.f5504a = i7;
        this.f5505b = obj;
        this.f5506c = obj2;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5504a;
        Object obj = this.f5506c;
        Object obj2 = this.f5505b;
        switch (i7) {
            case 0:
                ((CarLinkFragment) obj2).lambda$initLogic$35((View) obj, compoundButton, z6);
                break;
            default:
                DesktopAssistantCarControlHelper.lambda$showSeatPanelActionDialog$50((Set) obj2, (String) obj, compoundButton, z6);
                break;
        }
    }
}
