package g3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Property;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends q {
    public StateListAnimator N;

    @Override // g3.q
    public final float e() {
        return this.f6515v.getElevation();
    }

    @Override // g3.q
    public final void f(Rect rect) {
        if (((FloatingActionButton) this.f6516w.f9388b).f3732k) {
            super.f(rect);
            return;
        }
        if (this.f6499f) {
            FloatingActionButton floatingActionButton = this.f6515v;
            int sizeDimension = floatingActionButton.getSizeDimension();
            int i7 = this.f6504k;
            if (sizeDimension < i7) {
                int sizeDimension2 = (i7 - floatingActionButton.getSizeDimension()) / 2;
                rect.set(sizeDimension2, sizeDimension2, sizeDimension2, sizeDimension2);
                return;
            }
        }
        rect.set(0, 0, 0, 0);
    }

    @Override // g3.q
    public final void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i7) {
        Drawable drawable;
        p3.j t6 = t();
        this.f6495b = t6;
        t6.setTintList(colorStateList);
        if (mode != null) {
            this.f6495b.setTintMode(mode);
        }
        p3.j jVar = this.f6495b;
        FloatingActionButton floatingActionButton = this.f6515v;
        jVar.l(floatingActionButton.getContext());
        if (i7 > 0) {
            Context context = floatingActionButton.getContext();
            p3.p pVar = this.f6494a;
            pVar.getClass();
            b bVar = new b(pVar);
            int i8 = q2.d.design_fab_stroke_top_outer_color;
            Object obj = g0.g.f6423a;
            int a7 = g0.d.a(context, i8);
            int a8 = g0.d.a(context, q2.d.design_fab_stroke_top_inner_color);
            int a9 = g0.d.a(context, q2.d.design_fab_stroke_end_inner_color);
            int a10 = g0.d.a(context, q2.d.design_fab_stroke_end_outer_color);
            bVar.f6447i = a7;
            bVar.f6448j = a8;
            bVar.f6449k = a9;
            bVar.f6450l = a10;
            float f7 = i7;
            if (bVar.f6446h != f7) {
                bVar.f6446h = f7;
                bVar.f6440b.setStrokeWidth(f7 * 1.3333f);
                bVar.f6452n = true;
                bVar.invalidateSelf();
            }
            if (colorStateList != null) {
                bVar.f6451m = colorStateList.getColorForState(bVar.getState(), bVar.f6451m);
            }
            bVar.f6454p = colorStateList;
            bVar.f6452n = true;
            bVar.invalidateSelf();
            this.f6497d = bVar;
            b bVar2 = this.f6497d;
            bVar2.getClass();
            p3.j jVar2 = this.f6495b;
            jVar2.getClass();
            drawable = new LayerDrawable(new Drawable[]{bVar2, jVar2});
        } else {
            this.f6497d = null;
            drawable = this.f6495b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(n3.a.c(colorStateList2), drawable, null);
        this.f6496c = rippleDrawable;
        this.f6498e = rippleDrawable;
    }

    @Override // g3.q
    public final void h() {
    }

    @Override // g3.q
    public final void i() {
        r();
    }

    @Override // g3.q
    public final void j(int[] iArr) {
    }

    @Override // g3.q
    public final void k(float f7, float f8, float f9) {
        FloatingActionButton floatingActionButton = this.f6515v;
        if (floatingActionButton.getStateListAnimator() == this.N) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(q.H, s(f7, f9));
            stateListAnimator.addState(q.I, s(f7, f8));
            stateListAnimator.addState(q.J, s(f7, f8));
            stateListAnimator.addState(q.K, s(f7, f8));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, y.c.ELEVATION, f7).setDuration(0L));
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, CropImageView.DEFAULT_ASPECT_RATIO).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(q.C);
            stateListAnimator.addState(q.L, animatorSet);
            stateListAnimator.addState(q.M, s(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
            this.N = stateListAnimator;
            floatingActionButton.setStateListAnimator(stateListAnimator);
        }
        if (p()) {
            r();
        }
    }

    @Override // g3.q
    public final void n(ColorStateList colorStateList) {
        Drawable drawable = this.f6496c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(n3.a.c(colorStateList));
        } else {
            super.n(colorStateList);
        }
    }

    @Override // g3.q
    public final boolean p() {
        return ((FloatingActionButton) this.f6516w.f9388b).f3732k || (this.f6499f && this.f6515v.getSizeDimension() < this.f6504k);
    }

    @Override // g3.q
    public final void q() {
    }

    public final AnimatorSet s(float f7, float f8) {
        AnimatorSet animatorSet = new AnimatorSet();
        FloatingActionButton floatingActionButton = this.f6515v;
        animatorSet.play(ObjectAnimator.ofFloat(floatingActionButton, y.c.ELEVATION, f7).setDuration(0L)).with(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f8).setDuration(100L));
        animatorSet.setInterpolator(q.C);
        return animatorSet;
    }

    public final p3.j t() {
        p3.p pVar = this.f6494a;
        pVar.getClass();
        return new r(pVar);
    }
}
