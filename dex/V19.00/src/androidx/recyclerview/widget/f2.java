package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f2 {
    private r1 mLayoutManager;
    private boolean mPendingInitialRun;
    private RecyclerView mRecyclerView;
    private final d2 mRecyclingAction;
    private boolean mRunning;
    private boolean mStarted;
    private int mTargetPosition = -1;
    private View mTargetView;

    public f2() {
        d2 d2Var = new d2();
        d2Var.f2431d = -1;
        d2Var.f2433f = false;
        d2Var.f2428a = 0;
        d2Var.f2429b = 0;
        d2Var.f2430c = Integer.MIN_VALUE;
        d2Var.f2432e = null;
        this.mRecyclingAction = d2Var;
    }

    public PointF computeScrollVectorForPosition(int i7) {
        Object layoutManager = getLayoutManager();
        if (layoutManager instanceof e2) {
            return ((e2) layoutManager).a(i7);
        }
        return null;
    }

    public View findViewByPosition(int i7) {
        return this.mRecyclerView.f2321m.r(i7);
    }

    public int getChildCount() {
        return this.mRecyclerView.f2321m.w();
    }

    public int getChildPosition(View view) {
        this.mRecyclerView.getClass();
        j2 K = RecyclerView.K(view);
        if (K != null) {
            return K.getLayoutPosition();
        }
        return -1;
    }

    public r1 getLayoutManager() {
        return this.mLayoutManager;
    }

    public int getTargetPosition() {
        return this.mTargetPosition;
    }

    @Deprecated
    public void instantScrollToPosition(int i7) {
        this.mRecyclerView.e0(i7);
    }

    public boolean isPendingInitialRun() {
        return this.mPendingInitialRun;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void normalize(PointF pointF) {
        float f7 = pointF.x;
        float f8 = pointF.y;
        float sqrt = (float) Math.sqrt((f8 * f8) + (f7 * f7));
        pointF.x /= sqrt;
        pointF.y /= sqrt;
    }

    public void onAnimation(int i7, int i8) {
        PointF computeScrollVectorForPosition;
        RecyclerView recyclerView = this.mRecyclerView;
        if (this.mTargetPosition == -1 || recyclerView == null) {
            stop();
        }
        if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
            float f7 = computeScrollVectorForPosition.x;
            if (f7 != CropImageView.DEFAULT_ASPECT_RATIO || computeScrollVectorForPosition.y != CropImageView.DEFAULT_ASPECT_RATIO) {
                recyclerView.d0((int) Math.signum(f7), (int) Math.signum(computeScrollVectorForPosition.y), null);
            }
        }
        this.mPendingInitialRun = false;
        View view = this.mTargetView;
        if (view != null) {
            if (getChildPosition(view) == this.mTargetPosition) {
                onTargetFound(this.mTargetView, recyclerView.f2308f0, this.mRecyclingAction);
                this.mRecyclingAction.a(recyclerView);
                stop();
            } else {
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            onSeekTargetStep(i7, i8, recyclerView.f2308f0, this.mRecyclingAction);
            d2 d2Var = this.mRecyclingAction;
            boolean z6 = d2Var.f2431d >= 0;
            d2Var.a(recyclerView);
            if (z6 && this.mRunning) {
                this.mPendingInitialRun = true;
                recyclerView.f2302c0.a();
            }
        }
    }

    public void onChildAttachedToWindow(View view) {
        if (getChildPosition(view) == getTargetPosition()) {
            this.mTargetView = view;
        }
    }

    public abstract void onSeekTargetStep(int i7, int i8, g2 g2Var, d2 d2Var);

    public abstract void onStart();

    public abstract void onStop();

    public abstract void onTargetFound(View view, g2 g2Var, d2 d2Var);

    public void setTargetPosition(int i7) {
        this.mTargetPosition = i7;
    }

    public void start(RecyclerView recyclerView, r1 r1Var) {
        i2 i2Var = recyclerView.f2302c0;
        i2Var.f2496g.removeCallbacks(i2Var);
        i2Var.f2492c.abortAnimation();
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = r1Var;
        int i7 = this.mTargetPosition;
        if (i7 == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.f2308f0.f2465a = i7;
        this.mRunning = true;
        this.mPendingInitialRun = true;
        this.mTargetView = findViewByPosition(getTargetPosition());
        onStart();
        this.mRecyclerView.f2302c0.a();
        this.mStarted = true;
    }

    public final void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            onStop();
            this.mRecyclerView.f2308f0.f2465a = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            r1 r1Var = this.mLayoutManager;
            if (r1Var.f2625e == this) {
                r1Var.f2625e = null;
            }
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }
}
