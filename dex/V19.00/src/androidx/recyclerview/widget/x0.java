package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class x0 extends f2 {
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    private final DisplayMetrics mDisplayMetrics;
    private float mMillisPerPixel;
    protected PointF mTargetVector;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    private boolean mHasCalculatedMillisPerPixel = false;
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;

    public x0(Context context) {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    public int calculateDtToFit(int i7, int i8, int i9, int i10, int i11) {
        if (i11 == -1) {
            return i9 - i7;
        }
        if (i11 != 0) {
            if (i11 == 1) {
                return i10 - i8;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i12 = i9 - i7;
        if (i12 > 0) {
            return i12;
        }
        int i13 = i10 - i8;
        if (i13 < 0) {
            return i13;
        }
        return 0;
    }

    public int calculateDxToMakeVisible(View view, int i7) {
        r1 layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.e()) {
            return 0;
        }
        s1 s1Var = (s1) view.getLayoutParams();
        return calculateDtToFit(r1.B(view) - ((ViewGroup.MarginLayoutParams) s1Var).leftMargin, r1.C(view) + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin, layoutManager.H(), layoutManager.f2634n - layoutManager.I(), i7);
    }

    public int calculateDyToMakeVisible(View view, int i7) {
        r1 layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.f()) {
            return 0;
        }
        s1 s1Var = (s1) view.getLayoutParams();
        return calculateDtToFit(r1.D(view) - ((ViewGroup.MarginLayoutParams) s1Var).topMargin, r1.z(view) + ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin, layoutManager.J(), layoutManager.f2635o - layoutManager.G(), i7);
    }

    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
    }

    public int calculateTimeForDeceleration(int i7) {
        return (int) Math.ceil(calculateTimeForScrolling(i7) / 0.3356d);
    }

    public int calculateTimeForScrolling(int i7) {
        float abs = Math.abs(i7);
        if (!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return (int) Math.ceil(abs * this.mMillisPerPixel);
    }

    public int getHorizontalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF != null) {
            float f7 = pointF.x;
            if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
                return f7 > CropImageView.DEFAULT_ASPECT_RATIO ? 1 : -1;
            }
        }
        return 0;
    }

    public int getVerticalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF != null) {
            float f7 = pointF.y;
            if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
                return f7 > CropImageView.DEFAULT_ASPECT_RATIO ? 1 : -1;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.f2
    public void onSeekTargetStep(int i7, int i8, g2 g2Var, d2 d2Var) {
        if (getChildCount() == 0) {
            stop();
            return;
        }
        int i9 = this.mInterimTargetDx;
        int i10 = i9 - i7;
        if (i9 * i10 <= 0) {
            i10 = 0;
        }
        this.mInterimTargetDx = i10;
        int i11 = this.mInterimTargetDy;
        int i12 = i11 - i8;
        int i13 = i11 * i12 > 0 ? i12 : 0;
        this.mInterimTargetDy = i13;
        if (i10 == 0 && i13 == 0) {
            updateActionForInterimTarget(d2Var);
        }
    }

    @Override // androidx.recyclerview.widget.f2
    public void onStart() {
    }

    @Override // androidx.recyclerview.widget.f2
    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    @Override // androidx.recyclerview.widget.f2
    public void onTargetFound(View view, g2 g2Var, d2 d2Var) {
        int calculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
        int calculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
        int calculateTimeForDeceleration = calculateTimeForDeceleration((int) Math.sqrt((calculateDyToMakeVisible * calculateDyToMakeVisible) + (calculateDxToMakeVisible * calculateDxToMakeVisible)));
        if (calculateTimeForDeceleration > 0) {
            DecelerateInterpolator decelerateInterpolator = this.mDecelerateInterpolator;
            d2Var.f2428a = -calculateDxToMakeVisible;
            d2Var.f2429b = -calculateDyToMakeVisible;
            d2Var.f2430c = calculateTimeForDeceleration;
            d2Var.f2432e = decelerateInterpolator;
            d2Var.f2433f = true;
        }
    }

    public void updateActionForInterimTarget(d2 d2Var) {
        PointF computeScrollVectorForPosition = computeScrollVectorForPosition(getTargetPosition());
        if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == CropImageView.DEFAULT_ASPECT_RATIO && computeScrollVectorForPosition.y == CropImageView.DEFAULT_ASPECT_RATIO)) {
            d2Var.f2431d = getTargetPosition();
            stop();
            return;
        }
        normalize(computeScrollVectorForPosition);
        this.mTargetVector = computeScrollVectorForPosition;
        this.mInterimTargetDx = (int) (computeScrollVectorForPosition.x * 10000.0f);
        this.mInterimTargetDy = (int) (computeScrollVectorForPosition.y * 10000.0f);
        int calculateTimeForScrolling = calculateTimeForScrolling(TARGET_SEEK_SCROLL_DISTANCE_PX);
        int i7 = (int) (this.mInterimTargetDx * TARGET_SEEK_EXTRA_SCROLL_RATIO);
        int i8 = (int) (this.mInterimTargetDy * TARGET_SEEK_EXTRA_SCROLL_RATIO);
        int i9 = (int) (calculateTimeForScrolling * TARGET_SEEK_EXTRA_SCROLL_RATIO);
        LinearInterpolator linearInterpolator = this.mLinearInterpolator;
        d2Var.f2428a = i7;
        d2Var.f2429b = i8;
        d2Var.f2430c = i9;
        d2Var.f2432e = linearInterpolator;
        d2Var.f2433f = true;
    }
}
