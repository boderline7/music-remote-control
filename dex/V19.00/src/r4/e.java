package r4;

import android.graphics.Matrix;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.navi.tool.view.LyricsFxView;
import com.navi.tool.view.ZoomableImageView;
import com.yalantis.ucrop.view.GestureCropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8556a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f8557b;

    public /* synthetic */ e(int i7, View view) {
        this.f8556a = i7;
        this.f8557b = view;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(ZoomableImageView zoomableImageView) {
        this(1, zoomableImageView);
        this.f8556a = 1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(GestureCropImageView gestureCropImageView) {
        this(2, gestureCropImageView);
        this.f8556a = 2;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent motionEvent) {
        int i7 = this.f8556a;
        View view = this.f8557b;
        switch (i7) {
            case 0:
                LyricsFxView lyricsFxView = (LyricsFxView) view;
                int[] iArr = LyricsFxView.Z0;
                lyricsFxView.b0();
                Runnable runnable = lyricsFxView.V0;
                if (runnable != null) {
                    runnable.run();
                }
                return true;
            case 1:
                ZoomableImageView zoomableImageView = (ZoomableImageView) view;
                float f7 = zoomableImageView.f6097l;
                if (f7 > 1.05f) {
                    zoomableImageView.f6097l = 1.0f;
                    zoomableImageView.c();
                } else {
                    float f8 = 2.0f / f7;
                    zoomableImageView.f6097l = 2.0f;
                    Matrix matrix = zoomableImageView.f6089d;
                    matrix.postScale(f8, f8, motionEvent.getX(), motionEvent.getY());
                    zoomableImageView.d();
                    zoomableImageView.setImageMatrix(matrix);
                }
                return true;
            default:
                GestureCropImageView gestureCropImageView = (GestureCropImageView) view;
                float doubleTapTargetScale = gestureCropImageView.getDoubleTapTargetScale();
                float x6 = motionEvent.getX();
                float y6 = motionEvent.getY();
                if (doubleTapTargetScale > gestureCropImageView.getMaxScale()) {
                    doubleTapTargetScale = gestureCropImageView.getMaxScale();
                }
                float currentScale = gestureCropImageView.getCurrentScale();
                y4.b bVar = new y4.b(gestureCropImageView, currentScale, doubleTapTargetScale - currentScale, x6, y6);
                gestureCropImageView.A = bVar;
                gestureCropImageView.post(bVar);
                return super.onDoubleTap(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        switch (this.f8556a) {
            case 0:
                return true;
            default:
                return super.onDown(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
        switch (this.f8556a) {
            case 2:
                ((GestureCropImageView) this.f8557b).f(-f7, -f8);
                return true;
            default:
                return super.onScroll(motionEvent, motionEvent2, f7, f8);
        }
    }
}
