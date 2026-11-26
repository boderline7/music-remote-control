package com.google.android.material.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;
import i6.i;
import r2.a;
import r2.e;
import r2.f;
import r2.g;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* renamed from: c, reason: collision with root package name */
    public final Rect f4471c;

    /* renamed from: d, reason: collision with root package name */
    public final RectF f4472d;

    /* renamed from: e, reason: collision with root package name */
    public final RectF f4473e;

    /* renamed from: f, reason: collision with root package name */
    public final int[] f4474f;

    /* renamed from: g, reason: collision with root package name */
    public float f4475g;

    /* renamed from: h, reason: collision with root package name */
    public float f4476h;

    public FabTransformationBehavior() {
        this.f4471c = new Rect();
        this.f4472d = new RectF();
        this.f4473e = new RectF();
        this.f4474f = new int[2];
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4471c = new Rect();
        this.f4472d = new RectF();
        this.f4473e = new RectF();
        this.f4474f = new int[2];
    }

    public static float B(i iVar, f fVar, float f7) {
        long j7 = fVar.f8511a;
        f f8 = ((e) iVar.f6848b).f("expansion");
        return a.a(f7, CropImageView.DEFAULT_ASPECT_RATIO, fVar.b().getInterpolation((((f8.f8511a + f8.f8512b) + 17) - j7) / fVar.f8512b));
    }

    public static Pair y(float f7, float f8, boolean z6, i iVar) {
        f f9;
        f f10;
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO || f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            f9 = ((e) iVar.f6848b).f("translationXLinear");
            f10 = ((e) iVar.f6848b).f("translationYLinear");
        } else if ((!z6 || f8 >= CropImageView.DEFAULT_ASPECT_RATIO) && (z6 || f8 <= CropImageView.DEFAULT_ASPECT_RATIO)) {
            f9 = ((e) iVar.f6848b).f("translationXCurveDownwards");
            f10 = ((e) iVar.f6848b).f("translationYCurveDownwards");
        } else {
            f9 = ((e) iVar.f6848b).f("translationXCurveUpwards");
            f10 = ((e) iVar.f6848b).f("translationYCurveUpwards");
        }
        return new Pair(f9, f10);
    }

    public final float A(View view, View view2, g gVar) {
        RectF rectF = this.f4472d;
        RectF rectF2 = this.f4473e;
        C(view, rectF);
        rectF.offset(this.f4475g, this.f4476h);
        C(view2, rectF2);
        gVar.getClass();
        return (rectF2.centerY() - rectF.centerY()) + CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void C(View view, RectF rectF) {
        rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f4474f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public abstract i D(Context context, boolean z6);

    @Override // com.google.android.material.transformation.ExpandableBehavior, c0.b
    public final boolean f(View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    @Override // c0.b
    public final void g(c0.e eVar) {
        if (eVar.f3014h == 0) {
            eVar.f3014h = 80;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03d9 A[LOOP:0: B:41:0x03d7->B:42:0x03d9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b7  */
    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.animation.AnimatorSet x(android.view.View r25, android.view.View r26, boolean r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 998
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transformation.FabTransformationBehavior.x(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    public final float z(View view, View view2, g gVar) {
        RectF rectF = this.f4472d;
        RectF rectF2 = this.f4473e;
        C(view, rectF);
        rectF.offset(this.f4475g, this.f4476h);
        C(view2, rectF2);
        gVar.getClass();
        return (rectF2.centerX() - rectF.centerX()) + CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
