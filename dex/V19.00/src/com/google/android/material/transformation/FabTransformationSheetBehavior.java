package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import i6.i;
import java.util.HashMap;
import p0.d1;
import p0.l0;
import q2.b;
import r2.e;
import r2.g;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class FabTransformationSheetBehavior extends FabTransformationBehavior {

    /* renamed from: i, reason: collision with root package name */
    public HashMap f4479i;

    public FabTransformationSheetBehavior() {
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public final i D(Context context, boolean z6) {
        int i7 = z6 ? b.mtrl_fab_transformation_sheet_expand_spec : b.mtrl_fab_transformation_sheet_collapse_spec;
        i iVar = new i(20);
        iVar.f6848b = e.b(context, i7);
        iVar.f6849c = new g();
        return iVar;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public final void w(View view, View view2, boolean z6, boolean z7) {
        ViewParent parent = view2.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z6) {
                this.f4479i = new HashMap(childCount);
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                boolean z8 = (childAt.getLayoutParams() instanceof c0.e) && (((c0.e) childAt.getLayoutParams()).f3007a instanceof FabTransformationScrimBehavior);
                if (childAt != view2 && !z8) {
                    if (z6) {
                        this.f4479i.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        int i8 = d1.OVER_SCROLL_ALWAYS;
                        l0.s(childAt, 4);
                    } else {
                        HashMap hashMap = this.f4479i;
                        if (hashMap != null && hashMap.containsKey(childAt)) {
                            int intValue = ((Integer) this.f4479i.get(childAt)).intValue();
                            int i9 = d1.OVER_SCROLL_ALWAYS;
                            l0.s(childAt, intValue);
                        }
                    }
                }
            }
            if (!z6) {
                this.f4479i = null;
            }
        }
        super.w(view, view2, z6, z7);
    }
}
