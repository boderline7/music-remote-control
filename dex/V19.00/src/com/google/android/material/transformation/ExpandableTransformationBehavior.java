package com.google.android.material.transformation;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.d;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {

    /* renamed from: b, reason: collision with root package name */
    public AnimatorSet f4470b;

    public ExpandableTransformationBehavior() {
    }

    public ExpandableTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior
    public void w(View view, View view2, boolean z6, boolean z7) {
        AnimatorSet animatorSet = this.f4470b;
        boolean z8 = animatorSet != null;
        if (z8) {
            animatorSet.cancel();
        }
        AnimatorSet x6 = x(view, view2, z6, z8);
        this.f4470b = x6;
        x6.addListener(new d(12, this));
        this.f4470b.start();
        if (z7) {
            return;
        }
        this.f4470b.end();
    }

    public abstract AnimatorSet x(View view, View view2, boolean z6, boolean z7);
}
