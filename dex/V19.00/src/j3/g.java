package j3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends a {

    /* renamed from: g, reason: collision with root package name */
    public final float f6965g;

    /* renamed from: h, reason: collision with root package name */
    public final float f6966h;

    public g(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f6965g = resources.getDimension(q2.e.m3_back_progress_bottom_container_max_scale_x_distance);
        this.f6966h = resources.getDimension(q2.e.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    public final AnimatorSet a() {
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.f6954b;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 1.0f));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i7), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setInterpolator(new c1.b());
        return animatorSet;
    }

    public final void b(float f7) {
        float interpolation = this.f6953a.getInterpolation(f7);
        View view = this.f6954b;
        float width = view.getWidth();
        float height = view.getHeight();
        if (width <= CropImageView.DEFAULT_ASPECT_RATIO || height <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        float f8 = this.f6965g / width;
        float f9 = this.f6966h / height;
        float a7 = 1.0f - r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, f8, interpolation);
        float a8 = 1.0f - r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, f9, interpolation);
        view.setScaleX(a7);
        view.setPivotY(height);
        view.setScaleY(a8);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                View childAt = viewGroup.getChildAt(i7);
                childAt.setPivotY(-childAt.getTop());
                childAt.setScaleY(a8 != CropImageView.DEFAULT_ASPECT_RATIO ? a7 / a8 : 1.0f);
            }
        }
    }
}
