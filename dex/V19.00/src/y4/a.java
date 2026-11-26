package y4;

import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f10191a;

    /* renamed from: b, reason: collision with root package name */
    public final long f10192b;

    /* renamed from: c, reason: collision with root package name */
    public final long f10193c = System.currentTimeMillis();

    /* renamed from: d, reason: collision with root package name */
    public final float f10194d;

    /* renamed from: e, reason: collision with root package name */
    public final float f10195e;

    /* renamed from: f, reason: collision with root package name */
    public final float f10196f;

    /* renamed from: g, reason: collision with root package name */
    public final float f10197g;

    /* renamed from: h, reason: collision with root package name */
    public final float f10198h;

    /* renamed from: i, reason: collision with root package name */
    public final float f10199i;

    /* renamed from: j, reason: collision with root package name */
    public final boolean f10200j;

    public a(CropImageView cropImageView, long j7, float f7, float f8, float f9, float f10, float f11, float f12, boolean z6) {
        this.f10191a = new WeakReference(cropImageView);
        this.f10192b = j7;
        this.f10194d = f7;
        this.f10195e = f8;
        this.f10196f = f9;
        this.f10197g = f10;
        this.f10198h = f11;
        this.f10199i = f12;
        this.f10200j = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CropImageView cropImageView = (CropImageView) this.f10191a.get();
        if (cropImageView == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f10193c;
        long j7 = this.f10192b;
        float min = Math.min(j7, currentTimeMillis);
        float f7 = j7;
        float f8 = (min / f7) - 1.0f;
        float A = a.a.A(f8, f8, f8, 1.0f);
        float f9 = (this.f10196f * A) + CropImageView.DEFAULT_ASPECT_RATIO;
        float f10 = (A * this.f10197g) + CropImageView.DEFAULT_ASPECT_RATIO;
        float Y = z.Y(min, this.f10199i, f7);
        if (min < f7) {
            float[] fArr = cropImageView.f6202e;
            cropImageView.f(f9 - (fArr[0] - this.f10194d), f10 - (fArr[1] - this.f10195e));
            if (!this.f10200j) {
                float f11 = this.f10198h + Y;
                RectF rectF = cropImageView.f6169u;
                cropImageView.k(f11, rectF.centerX(), rectF.centerY());
            }
            if (cropImageView.i(cropImageView.f6201d)) {
                return;
            }
            cropImageView.post(this);
        }
    }
}
