package y;

import android.graphics.Rect;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d0 {

    /* renamed from: a, reason: collision with root package name */
    public final int f9784a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9785b;

    /* renamed from: c, reason: collision with root package name */
    public final n f9786c;

    /* renamed from: d, reason: collision with root package name */
    public final int f9787d;

    /* renamed from: f, reason: collision with root package name */
    public final androidx.appcompat.widget.y f9789f;

    /* renamed from: g, reason: collision with root package name */
    public final Interpolator f9790g;

    /* renamed from: i, reason: collision with root package name */
    public float f9792i;

    /* renamed from: j, reason: collision with root package name */
    public float f9793j;

    /* renamed from: m, reason: collision with root package name */
    public final boolean f9796m;

    /* renamed from: e, reason: collision with root package name */
    public final t.g f9788e = new t.g(0);

    /* renamed from: h, reason: collision with root package name */
    public boolean f9791h = false;

    /* renamed from: l, reason: collision with root package name */
    public final Rect f9795l = new Rect();

    /* renamed from: k, reason: collision with root package name */
    public long f9794k = System.nanoTime();

    public d0(androidx.appcompat.widget.y yVar, n nVar, int i7, int i8, int i9, Interpolator interpolator, int i10, int i11) {
        this.f9796m = false;
        this.f9789f = yVar;
        this.f9786c = nVar;
        this.f9787d = i8;
        if (((ArrayList) yVar.f1486f) == null) {
            yVar.f1486f = new ArrayList();
        }
        ((ArrayList) yVar.f1486f).add(this);
        this.f9790g = interpolator;
        this.f9784a = i10;
        this.f9785b = i11;
        if (i9 == 3) {
            this.f9796m = true;
        }
        this.f9793j = i7 == 0 ? Float.MAX_VALUE : 1.0f / i7;
        a();
    }

    public final void a() {
        boolean z6 = this.f9791h;
        androidx.appcompat.widget.y yVar = this.f9789f;
        Interpolator interpolator = this.f9790g;
        n nVar = this.f9786c;
        int i7 = this.f9785b;
        int i8 = this.f9784a;
        if (!z6) {
            long nanoTime = System.nanoTime();
            long j7 = nanoTime - this.f9794k;
            this.f9794k = nanoTime;
            float f7 = (((float) (j7 * 1.0E-6d)) * this.f9793j) + this.f9792i;
            this.f9792i = f7;
            if (f7 >= 1.0f) {
                this.f9792i = 1.0f;
            }
            boolean e7 = nVar.e(interpolator == null ? this.f9792i : interpolator.getInterpolation(this.f9792i), nanoTime, nVar.f9867b, this.f9788e);
            if (this.f9792i >= 1.0f) {
                if (i8 != -1) {
                    nVar.f9867b.setTag(i8, Long.valueOf(System.nanoTime()));
                }
                if (i7 != -1) {
                    nVar.f9867b.setTag(i7, null);
                }
                if (!this.f9796m) {
                    ((ArrayList) yVar.f1487g).add(this);
                }
            }
            if (this.f9792i < 1.0f || e7) {
                ((MotionLayout) yVar.f1482b).invalidate();
                return;
            }
            return;
        }
        long nanoTime2 = System.nanoTime();
        long j8 = nanoTime2 - this.f9794k;
        this.f9794k = nanoTime2;
        float f8 = this.f9792i - (((float) (j8 * 1.0E-6d)) * this.f9793j);
        this.f9792i = f8;
        if (f8 < CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f9792i = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float f9 = this.f9792i;
        if (interpolator != null) {
            f9 = interpolator.getInterpolation(f9);
        }
        boolean e8 = nVar.e(f9, nanoTime2, nVar.f9867b, this.f9788e);
        if (this.f9792i <= CropImageView.DEFAULT_ASPECT_RATIO) {
            if (i8 != -1) {
                nVar.f9867b.setTag(i8, Long.valueOf(System.nanoTime()));
            }
            if (i7 != -1) {
                nVar.f9867b.setTag(i7, null);
            }
            ((ArrayList) yVar.f1487g).add(this);
        }
        if (this.f9792i > CropImageView.DEFAULT_ASPECT_RATIO || e8) {
            ((MotionLayout) yVar.f1482b).invalidate();
        }
    }

    public final void b() {
        this.f9791h = true;
        int i7 = this.f9787d;
        if (i7 != -1) {
            this.f9793j = i7 == 0 ? Float.MAX_VALUE : 1.0f / i7;
        }
        ((MotionLayout) this.f9789f.f1482b).invalidate();
        this.f9794k = System.nanoTime();
    }
}
