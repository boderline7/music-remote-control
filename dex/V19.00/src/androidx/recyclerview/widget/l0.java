package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class l0 {
    private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
    public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
    public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
    private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
    static final int RELATIVE_DIR_FLAGS = 3158064;
    private static final Interpolator sDragScrollInterpolator = new k0(0);
    private static final Interpolator sDragViewScrollCapInterpolator = new k0(1);
    private int mCachedMaxScrollSpeed = -1;

    public static int convertToRelativeDirection(int i7, int i8) {
        int i9;
        int i10 = i7 & ABS_HORIZONTAL_DIR_FLAGS;
        if (i10 == 0) {
            return i7;
        }
        int i11 = i7 & (~i10);
        if (i8 == 0) {
            i9 = i10 << 2;
        } else {
            int i12 = i10 << 1;
            i11 |= (-789517) & i12;
            i9 = (i12 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
        }
        return i11 | i9;
    }

    public static q0 getDefaultUIUtil() {
        return r0.f2620a;
    }

    public static int makeFlag(int i7, int i8) {
        return i8 << (i7 * 8);
    }

    public static int makeMovementFlags(int i7, int i8) {
        return makeFlag(2, i7) | makeFlag(1, i8) | makeFlag(0, i8 | i7);
    }

    public boolean canDropOver(RecyclerView recyclerView, j2 j2Var, j2 j2Var2) {
        return true;
    }

    public j2 chooseDropTarget(j2 j2Var, List<j2> list, int i7, int i8) {
        int bottom;
        int abs;
        int top;
        int abs2;
        int left;
        int abs3;
        int right;
        int abs4;
        int width = j2Var.itemView.getWidth() + i7;
        int height = j2Var.itemView.getHeight() + i8;
        int left2 = i7 - j2Var.itemView.getLeft();
        int top2 = i8 - j2Var.itemView.getTop();
        int size = list.size();
        j2 j2Var2 = null;
        int i9 = -1;
        for (int i10 = 0; i10 < size; i10++) {
            j2 j2Var3 = list.get(i10);
            if (left2 > 0 && (right = j2Var3.itemView.getRight() - width) < 0 && j2Var3.itemView.getRight() > j2Var.itemView.getRight() && (abs4 = Math.abs(right)) > i9) {
                j2Var2 = j2Var3;
                i9 = abs4;
            }
            if (left2 < 0 && (left = j2Var3.itemView.getLeft() - i7) > 0 && j2Var3.itemView.getLeft() < j2Var.itemView.getLeft() && (abs3 = Math.abs(left)) > i9) {
                j2Var2 = j2Var3;
                i9 = abs3;
            }
            if (top2 < 0 && (top = j2Var3.itemView.getTop() - i8) > 0 && j2Var3.itemView.getTop() < j2Var.itemView.getTop() && (abs2 = Math.abs(top)) > i9) {
                j2Var2 = j2Var3;
                i9 = abs2;
            }
            if (top2 > 0 && (bottom = j2Var3.itemView.getBottom() - height) < 0 && j2Var3.itemView.getBottom() > j2Var.itemView.getBottom() && (abs = Math.abs(bottom)) > i9) {
                j2Var2 = j2Var3;
                i9 = abs;
            }
        }
        return j2Var2;
    }

    public void clearView(RecyclerView recyclerView, j2 j2Var) {
        View view = j2Var.itemView;
        Object tag = view.getTag(i1.c.item_touch_helper_previous_elevation);
        if (tag instanceof Float) {
            float floatValue = ((Float) tag).floatValue();
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.r0.s(view, floatValue);
        }
        view.setTag(i1.c.item_touch_helper_previous_elevation, null);
        view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public int convertToAbsoluteDirection(int i7, int i8) {
        int i9;
        int i10 = i7 & RELATIVE_DIR_FLAGS;
        if (i10 == 0) {
            return i7;
        }
        int i11 = i7 & (~i10);
        if (i8 == 0) {
            i9 = i10 >> 2;
        } else {
            int i12 = i10 >> 1;
            i11 |= (-3158065) & i12;
            i9 = (i12 & RELATIVE_DIR_FLAGS) >> 2;
        }
        return i11 | i9;
    }

    public final int getAbsoluteMovementFlags(RecyclerView recyclerView, j2 j2Var) {
        int movementFlags = getMovementFlags(recyclerView, j2Var);
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        return convertToAbsoluteDirection(movementFlags, p0.m0.d(recyclerView));
    }

    public long getAnimationDuration(RecyclerView recyclerView, int i7, float f7, float f8) {
        m1 itemAnimator = recyclerView.getItemAnimator();
        return itemAnimator == null ? i7 == 8 ? 200L : 250L : i7 == 8 ? itemAnimator.f2542e : itemAnimator.f2541d;
    }

    public int getBoundingBoxMargin() {
        return 0;
    }

    public float getMoveThreshold(j2 j2Var) {
        return 0.5f;
    }

    public abstract int getMovementFlags(RecyclerView recyclerView, j2 j2Var);

    public float getSwipeEscapeVelocity(float f7) {
        return f7;
    }

    public float getSwipeThreshold(j2 j2Var) {
        return 0.5f;
    }

    public float getSwipeVelocityThreshold(float f7) {
        return f7;
    }

    public boolean hasDragFlag(RecyclerView recyclerView, j2 j2Var) {
        return (getAbsoluteMovementFlags(recyclerView, j2Var) & 16711680) != 0;
    }

    public boolean hasSwipeFlag(RecyclerView recyclerView, j2 j2Var) {
        return (getAbsoluteMovementFlags(recyclerView, j2Var) & p0.v.ACTION_POINTER_INDEX_MASK) != 0;
    }

    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i7, int i8, int i9, long j7) {
        if (this.mCachedMaxScrollSpeed == -1) {
            this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(i1.b.item_touch_helper_max_drag_scroll_per_frame);
        }
        int interpolation = (int) (sDragScrollInterpolator.getInterpolation(j7 <= DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS ? j7 / 2000.0f : 1.0f) * ((int) (sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i8) * 1.0f) / i7)) * ((int) Math.signum(i8)) * this.mCachedMaxScrollSpeed)));
        return interpolation == 0 ? i8 > 0 ? 1 : -1 : interpolation;
    }

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, j2 j2Var, float f7, float f8, int i7, boolean z6) {
        View view = j2Var.itemView;
        if (z6 && view.getTag(i1.c.item_touch_helper_previous_elevation) == null) {
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            Float valueOf = Float.valueOf(p0.r0.i(view));
            int childCount = recyclerView.getChildCount();
            float f9 = CropImageView.DEFAULT_ASPECT_RATIO;
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = recyclerView.getChildAt(i9);
                if (childAt != view) {
                    int i10 = p0.d1.OVER_SCROLL_ALWAYS;
                    float i11 = p0.r0.i(childAt);
                    if (i11 > f9) {
                        f9 = i11;
                    }
                }
            }
            p0.r0.s(view, f9 + 1.0f);
            view.setTag(i1.c.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f7);
        view.setTranslationY(f8);
    }

    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, j2 j2Var, float f7, float f8, int i7, boolean z6) {
        View view = j2Var.itemView;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, j2 j2Var, List<j0> list, int i7, float f7, float f8) {
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            j0 j0Var = list.get(i8);
            float f9 = j0Var.f2501a;
            float f10 = j0Var.f2503c;
            j2 j2Var2 = j0Var.f2505e;
            if (f9 == f10) {
                j0Var.f2509i = j2Var2.itemView.getTranslationX();
            } else {
                j0Var.f2509i = a.a.a(f10, f9, j0Var.f2513m, f9);
            }
            float f11 = j0Var.f2502b;
            float f12 = j0Var.f2504d;
            if (f11 == f12) {
                j0Var.f2510j = j2Var2.itemView.getTranslationY();
            } else {
                j0Var.f2510j = a.a.a(f12, f11, j0Var.f2513m, f11);
            }
            int save = canvas.save();
            onChildDraw(canvas, recyclerView, j0Var.f2505e, j0Var.f2509i, j0Var.f2510j, j0Var.f2506f, false);
            canvas.restoreToCount(save);
        }
        if (j2Var != null) {
            int save2 = canvas.save();
            onChildDraw(canvas, recyclerView, j2Var, f7, f8, i7, true);
            canvas.restoreToCount(save2);
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, j2 j2Var, List<j0> list, int i7, float f7, float f8) {
        int size = list.size();
        boolean z6 = false;
        for (int i8 = 0; i8 < size; i8++) {
            j0 j0Var = list.get(i8);
            int save = canvas.save();
            onChildDrawOver(canvas, recyclerView, j0Var.f2505e, j0Var.f2509i, j0Var.f2510j, j0Var.f2506f, false);
            canvas.restoreToCount(save);
        }
        if (j2Var != null) {
            int save2 = canvas.save();
            onChildDrawOver(canvas, recyclerView, j2Var, f7, f8, i7, true);
            canvas.restoreToCount(save2);
        }
        for (int i9 = size - 1; i9 >= 0; i9--) {
            j0 j0Var2 = list.get(i9);
            boolean z7 = j0Var2.f2512l;
            if (z7 && !j0Var2.f2508h) {
                list.remove(i9);
            } else if (!z7) {
                z6 = true;
            }
        }
        if (z6) {
            recyclerView.invalidate();
        }
    }

    public abstract boolean onMove(RecyclerView recyclerView, j2 j2Var, j2 j2Var2);

    /* JADX WARN: Multi-variable type inference failed */
    public void onMoved(RecyclerView recyclerView, j2 j2Var, int i7, j2 j2Var2, int i8, int i9, int i10) {
        r1 layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof o0)) {
            if (layoutManager.e()) {
                if (r1.B(j2Var2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.e0(i8);
                }
                if (r1.C(j2Var2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.e0(i8);
                }
            }
            if (layoutManager.f()) {
                if (r1.D(j2Var2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.e0(i8);
                }
                if (r1.z(j2Var2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.e0(i8);
                    return;
                }
                return;
            }
            return;
        }
        View view = j2Var.itemView;
        View view2 = j2Var2.itemView;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((o0) layoutManager);
        linearLayoutManager.c("Cannot drop a view during a scroll or layout calculation");
        linearLayoutManager.L0();
        linearLayoutManager.d1();
        int K = r1.K(view);
        int K2 = r1.K(view2);
        char c7 = K < K2 ? (char) 1 : (char) 65535;
        if (linearLayoutManager.f2285u) {
            if (c7 == 1) {
                linearLayoutManager.f1(K2, linearLayoutManager.f2282r.e() - (linearLayoutManager.f2282r.c(view) + linearLayoutManager.f2282r.d(view2)));
                return;
            } else {
                linearLayoutManager.f1(K2, linearLayoutManager.f2282r.e() - linearLayoutManager.f2282r.b(view2));
                return;
            }
        }
        if (c7 == 65535) {
            linearLayoutManager.f1(K2, linearLayoutManager.f2282r.d(view2));
        } else {
            linearLayoutManager.f1(K2, linearLayoutManager.f2282r.b(view2) - linearLayoutManager.f2282r.c(view));
        }
    }

    public void onSelectedChanged(j2 j2Var, int i7) {
    }

    public abstract void onSwiped(j2 j2Var, int i7);
}
