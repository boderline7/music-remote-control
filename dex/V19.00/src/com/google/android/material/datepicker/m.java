package com.google.android.material.datepicker;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3646d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f3647e;

    public /* synthetic */ m(int i7, Object obj) {
        this.f3646d = i7;
        this.f3647e = obj;
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        int i7 = this.f3646d;
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        switch (i7) {
            case 0:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCollectionInfo(null);
                break;
            case 1:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                iVar.h(false);
                break;
            case 2:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                t tVar = (t) this.f3647e;
                accessibilityNodeInfo.setHintText(tVar.f3670n.getVisibility() == 0 ? tVar.getString(q2.k.mtrl_picker_toggle_to_year_selection) : tVar.getString(q2.k.mtrl_picker_toggle_to_day_selection));
                break;
            default:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCollectionInfo(null);
                break;
        }
    }
}
