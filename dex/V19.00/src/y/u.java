package y;

import androidx.constraintlayout.motion.widget.MotionLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u {

    /* renamed from: a, reason: collision with root package name */
    public float f9920a = Float.NaN;

    /* renamed from: b, reason: collision with root package name */
    public float f9921b = Float.NaN;

    /* renamed from: c, reason: collision with root package name */
    public int f9922c = -1;

    /* renamed from: d, reason: collision with root package name */
    public int f9923d = -1;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ MotionLayout f9924e;

    public u(MotionLayout motionLayout) {
        this.f9924e = motionLayout;
    }

    public final void a() {
        int i7 = this.f9922c;
        MotionLayout motionLayout = this.f9924e;
        if (i7 != -1 || this.f9923d != -1) {
            if (i7 == -1) {
                int i8 = this.f9923d;
                if (motionLayout.isAttachedToWindow()) {
                    motionLayout.D(i8, -1);
                } else {
                    if (motionLayout.f1586w0 == null) {
                        motionLayout.f1586w0 = new u(motionLayout);
                    }
                    motionLayout.f1586w0.f9923d = i8;
                }
            } else {
                int i9 = this.f9923d;
                if (i9 == -1) {
                    motionLayout.setState(i7, -1, -1);
                } else {
                    motionLayout.setTransition(i7, i9);
                }
            }
            motionLayout.setState(w.SETUP);
        }
        if (Float.isNaN(this.f9921b)) {
            if (Float.isNaN(this.f9920a)) {
                return;
            }
            motionLayout.setProgress(this.f9920a);
        } else {
            motionLayout.setProgress(this.f9920a, this.f9921b);
            this.f9920a = Float.NaN;
            this.f9921b = Float.NaN;
            this.f9922c = -1;
            this.f9923d = -1;
        }
    }
}
