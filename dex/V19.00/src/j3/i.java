package j3;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.q2;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends a {

    /* renamed from: g, reason: collision with root package name */
    public final float f6973g;

    /* renamed from: h, reason: collision with root package name */
    public final float f6974h;

    /* renamed from: i, reason: collision with root package name */
    public final float f6975i;

    public i(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f6973g = resources.getDimension(q2.e.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.f6974h = resources.getDimension(q2.e.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.f6975i = resources.getDimension(q2.e.m3_back_progress_side_container_max_scale_y_distance);
    }

    public final void a() {
        androidx.activity.c cVar = this.f6958f;
        this.f6958f = null;
        if (cVar == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.f6954b;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 1.0f));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i7), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setDuration(this.f6957e);
        animatorSet.start();
    }

    public final void b(androidx.activity.c cVar, int i7, AnimatorListenerAdapter animatorListenerAdapter, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        int i8;
        boolean z6 = cVar.f611d == 0;
        int i9 = d1.OVER_SCROLL_ALWAYS;
        View view = this.f6954b;
        boolean z7 = (Gravity.getAbsoluteGravity(i7, m0.d(view)) & 3) == 3;
        float scaleX = view.getScaleX() * view.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            i8 = z7 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
        } else {
            i8 = 0;
        }
        float f7 = scaleX + i8;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (z7) {
            f7 = -f7;
        }
        fArr[0] = f7;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.setInterpolator(new c1.b());
        ofFloat.setDuration(r2.a.c(this.f6955c, cVar.f610c, this.f6956d));
        ofFloat.addListener(new q2(this, z6, i7));
        ofFloat.addListener(animatorListenerAdapter);
        ofFloat.start();
    }

    public final void c(int i7, float f7, boolean z6) {
        float interpolation = this.f6953a.getInterpolation(f7);
        int i8 = d1.OVER_SCROLL_ALWAYS;
        View view = this.f6954b;
        boolean z7 = (Gravity.getAbsoluteGravity(i7, m0.d(view)) & 3) == 3;
        boolean z8 = z6 == z7;
        int width = view.getWidth();
        int height = view.getHeight();
        float f8 = width;
        if (f8 > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f9 = height;
            if (f9 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                return;
            }
            float f10 = this.f6973g / f8;
            float f11 = this.f6974h / f8;
            float f12 = this.f6975i / f9;
            if (z7) {
                f8 = 0.0f;
            }
            view.setPivotX(f8);
            if (!z8) {
                f11 = -f10;
            }
            float a7 = r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, f11, interpolation);
            float f13 = a7 + 1.0f;
            view.setScaleX(f13);
            float a8 = 1.0f - r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, f12, interpolation);
            view.setScaleY(a8);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
                    View childAt = viewGroup.getChildAt(i9);
                    childAt.setPivotX(z7 ? childAt.getWidth() + (width - childAt.getRight()) : -childAt.getLeft());
                    childAt.setPivotY(-childAt.getTop());
                    float f14 = z8 ? 1.0f - a7 : 1.0f;
                    float f15 = a8 != CropImageView.DEFAULT_ASPECT_RATIO ? (f13 / a8) * f14 : 1.0f;
                    childAt.setScaleX(f14);
                    childAt.setScaleY(f15);
                }
            }
        }
    }
}
