package com.google.android.material.internal;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s extends l2 {

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ v f3880f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(v vVar, NavigationMenuView navigationMenuView) {
        super(navigationMenuView);
        this.f3880f = vVar;
    }

    @Override // androidx.recyclerview.widget.l2, p0.c
    public final void d(View view, q0.i iVar) {
        super.d(view, iVar);
        n nVar = this.f3880f.f3885e;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            v vVar = nVar.f3875d;
            if (i7 >= vVar.f3885e.f3872a.size()) {
                iVar.f8426a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i8, 1, false));
                return;
            } else {
                int itemViewType = vVar.f3885e.getItemViewType(i7);
                if (itemViewType == 0 || itemViewType == 1) {
                    i8++;
                }
                i7++;
            }
        }
    }
}
