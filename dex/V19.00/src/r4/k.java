package r4;

import android.graphics.Matrix;
import android.view.ScaleGestureDetector;
import androidx.appcompat.widget.AppCompatImageView;
import com.navi.tool.view.ZoomableImageView;
import com.yalantis.ucrop.view.GestureCropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8592a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppCompatImageView f8593b;

    public /* synthetic */ k(AppCompatImageView appCompatImageView, int i7) {
        this.f8592a = i7;
        this.f8593b = appCompatImageView;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(ZoomableImageView zoomableImageView) {
        this(zoomableImageView, 0);
        this.f8592a = 0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(GestureCropImageView gestureCropImageView) {
        this(gestureCropImageView, 1);
        this.f8592a = 1;
    }

    @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float f7;
        float f8;
        Matrix matrix;
        int i7 = this.f8592a;
        AppCompatImageView appCompatImageView = this.f8593b;
        switch (i7) {
            case 0:
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                ZoomableImageView zoomableImageView = (ZoomableImageView) appCompatImageView;
                float f9 = zoomableImageView.f6097l;
                float f10 = f9 * scaleFactor;
                zoomableImageView.f6097l = f10;
                float f11 = zoomableImageView.f6099n;
                if (f10 <= f11) {
                    f11 = zoomableImageView.f6098m;
                    if (f10 < f11) {
                        zoomableImageView.f6097l = f11;
                    }
                    float f12 = zoomableImageView.f6100o;
                    float f13 = zoomableImageView.f6097l;
                    f7 = f12 * f13;
                    f8 = zoomableImageView.f6095j;
                    matrix = zoomableImageView.f6089d;
                    if (f7 > f8 || zoomableImageView.f6101p * f13 <= zoomableImageView.f6096k) {
                        matrix.postScale(scaleFactor, scaleFactor, f8 / 2.0f, zoomableImageView.f6096k / 2.0f);
                    } else {
                        matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    }
                    zoomableImageView.d();
                    break;
                } else {
                    zoomableImageView.f6097l = f11;
                }
                scaleFactor = f11 / f9;
                float f122 = zoomableImageView.f6100o;
                float f132 = zoomableImageView.f6097l;
                f7 = f122 * f132;
                f8 = zoomableImageView.f6095j;
                matrix = zoomableImageView.f6089d;
                if (f7 > f8) {
                }
                matrix.postScale(scaleFactor, scaleFactor, f8 / 2.0f, zoomableImageView.f6096k / 2.0f);
                zoomableImageView.d();
            default:
                GestureCropImageView gestureCropImageView = (GestureCropImageView) appCompatImageView;
                gestureCropImageView.j(scaleGestureDetector.getScaleFactor(), gestureCropImageView.J, gestureCropImageView.K);
                break;
        }
        return true;
    }

    @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        switch (this.f8592a) {
            case 0:
                ((ZoomableImageView) this.f8593b).f6102q = 2;
                return true;
            default:
                return super.onScaleBegin(scaleGestureDetector);
        }
    }
}
