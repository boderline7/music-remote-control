package y;

import androidx.constraintlayout.motion.widget.MotionLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends o {

    /* renamed from: a, reason: collision with root package name */
    public float f9893a = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: b, reason: collision with root package name */
    public float f9894b = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: c, reason: collision with root package name */
    public float f9895c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ MotionLayout f9896d;

    public q(MotionLayout motionLayout) {
        this.f9896d = motionLayout;
    }

    @Override // y.o
    public final float a() {
        return this.f9896d.f1583v;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        float f8 = this.f9893a;
        MotionLayout motionLayout = this.f9896d;
        if (f8 > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f9 = this.f9895c;
            if (f8 / f9 < f7) {
                f7 = f8 / f9;
            }
            motionLayout.f1583v = f8 - (f9 * f7);
            return ((f8 * f7) - (((f9 * f7) * f7) / 2.0f)) + this.f9894b;
        }
        float f10 = this.f9895c;
        if ((-f8) / f10 < f7) {
            f7 = (-f8) / f10;
        }
        motionLayout.f1583v = (f10 * f7) + f8;
        return (((f10 * f7) * f7) / 2.0f) + (f8 * f7) + this.f9894b;
    }
}
