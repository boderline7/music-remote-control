package com.google.android.material.transformation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import n1.z;
import r2.f;
import v3.c;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public static final long COLLAPSE_DELAY = 0;
    public static final long COLLAPSE_DURATION = 150;
    public static final long EXPAND_DELAY = 75;
    public static final long EXPAND_DURATION = 150;

    /* renamed from: c, reason: collision with root package name */
    public final f f4477c;

    /* renamed from: d, reason: collision with root package name */
    public final f f4478d;

    public FabTransformationScrimBehavior() {
        this.f4477c = new f(75L);
        this.f4478d = new f(0L);
    }

    public FabTransformationScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4477c = new f(75L);
        this.f4478d = new f(0L);
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, c0.b
    public final boolean f(View view, View view2) {
        return view2 instanceof FloatingActionButton;
    }

    @Override // c0.b
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public final AnimatorSet x(View view, View view2, boolean z6, boolean z7) {
        ObjectAnimator ofFloat;
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        f fVar = z6 ? this.f4477c : this.f4478d;
        if (z6) {
            if (!z7) {
                view2.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.ALPHA, 1.0f);
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.ALPHA, CropImageView.DEFAULT_ASPECT_RATIO);
        }
        fVar.a(ofFloat);
        arrayList.add(ofFloat);
        AnimatorSet animatorSet = new AnimatorSet();
        z.S0(animatorSet, arrayList);
        animatorSet.addListener(new c(z6, view2));
        return animatorSet;
    }
}
