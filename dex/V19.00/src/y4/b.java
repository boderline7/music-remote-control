package y4;

import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import java.lang.ref.WeakReference;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f10201a;

    /* renamed from: d, reason: collision with root package name */
    public final float f10204d;

    /* renamed from: e, reason: collision with root package name */
    public final float f10205e;

    /* renamed from: f, reason: collision with root package name */
    public final float f10206f;

    /* renamed from: g, reason: collision with root package name */
    public final float f10207g;

    /* renamed from: c, reason: collision with root package name */
    public final long f10203c = System.currentTimeMillis();

    /* renamed from: b, reason: collision with root package name */
    public final long f10202b = 200;

    public b(GestureCropImageView gestureCropImageView, float f7, float f8, float f9, float f10) {
        this.f10201a = new WeakReference(gestureCropImageView);
        this.f10204d = f7;
        this.f10205e = f8;
        this.f10206f = f9;
        this.f10207g = f10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CropImageView cropImageView = (CropImageView) this.f10201a.get();
        if (cropImageView == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f10203c;
        long j7 = this.f10202b;
        float min = Math.min(j7, currentTimeMillis);
        float f7 = j7;
        float Y = z.Y(min, this.f10205e, f7);
        if (min >= f7) {
            cropImageView.setImageToWrapCropBounds();
        } else {
            cropImageView.k(this.f10204d + Y, this.f10206f, this.f10207g);
            cropImageView.post(this);
        }
    }
}
