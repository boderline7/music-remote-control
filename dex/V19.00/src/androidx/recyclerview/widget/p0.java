package androidx.recyclerview.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p0 extends o1 implements t1 {
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int START = 16;
    public static final int UP = 1;
    public Rect B;
    public long C;

    /* renamed from: d, reason: collision with root package name */
    public float f2574d;

    /* renamed from: e, reason: collision with root package name */
    public float f2575e;

    /* renamed from: f, reason: collision with root package name */
    public float f2576f;

    /* renamed from: g, reason: collision with root package name */
    public float f2577g;

    /* renamed from: h, reason: collision with root package name */
    public float f2578h;

    /* renamed from: i, reason: collision with root package name */
    public float f2579i;

    /* renamed from: j, reason: collision with root package name */
    public float f2580j;

    /* renamed from: k, reason: collision with root package name */
    public float f2581k;

    /* renamed from: m, reason: collision with root package name */
    public final l0 f2583m;

    /* renamed from: o, reason: collision with root package name */
    public int f2585o;

    /* renamed from: q, reason: collision with root package name */
    public int f2587q;

    /* renamed from: r, reason: collision with root package name */
    public RecyclerView f2588r;

    /* renamed from: t, reason: collision with root package name */
    public VelocityTracker f2590t;

    /* renamed from: u, reason: collision with root package name */
    public ArrayList f2591u;

    /* renamed from: v, reason: collision with root package name */
    public ArrayList f2592v;

    /* renamed from: y, reason: collision with root package name */
    public e.a f2595y;

    /* renamed from: z, reason: collision with root package name */
    public m0 f2596z;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2571a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final float[] f2572b = new float[2];

    /* renamed from: c, reason: collision with root package name */
    public j2 f2573c = null;

    /* renamed from: l, reason: collision with root package name */
    public int f2582l = -1;

    /* renamed from: n, reason: collision with root package name */
    public int f2584n = 0;

    /* renamed from: p, reason: collision with root package name */
    public final ArrayList f2586p = new ArrayList();

    /* renamed from: s, reason: collision with root package name */
    public final z f2589s = new z(1, this);

    /* renamed from: w, reason: collision with root package name */
    public View f2593w = null;

    /* renamed from: x, reason: collision with root package name */
    public int f2594x = -1;
    public final i0 A = new i0(this);

    public p0(l0 l0Var) {
        this.f2583m = l0Var;
    }

    public static boolean m(View view, float f7, float f8, float f9, float f10) {
        return f7 >= f9 && f7 <= f9 + ((float) view.getWidth()) && f8 >= f10 && f8 <= f10 + ((float) view.getHeight());
    }

    @Override // androidx.recyclerview.widget.t1
    public final void b(View view) {
    }

    @Override // androidx.recyclerview.widget.t1
    public final void d(View view) {
        o(view);
        j2 J = this.f2588r.J(view);
        if (J == null) {
            return;
        }
        j2 j2Var = this.f2573c;
        if (j2Var != null && J == j2Var) {
            p(null, 0);
            return;
        }
        j(J, false);
        if (this.f2571a.remove(J.itemView)) {
            this.f2583m.clearView(this.f2588r, J);
        }
    }

    public final void f(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2588r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        i0 i0Var = this.A;
        if (recyclerView2 != null) {
            recyclerView2.Z(this);
            RecyclerView recyclerView3 = this.f2588r;
            recyclerView3.f2325o.remove(i0Var);
            if (recyclerView3.f2327p == i0Var) {
                recyclerView3.f2327p = null;
            }
            ArrayList arrayList = this.f2588r.A;
            if (arrayList != null) {
                arrayList.remove(this);
            }
            ArrayList arrayList2 = this.f2586p;
            int size = arrayList2.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                this.f2583m.clearView(this.f2588r, ((j0) arrayList2.get(0)).f2505e);
            }
            arrayList2.clear();
            this.f2593w = null;
            this.f2594x = -1;
            VelocityTracker velocityTracker = this.f2590t;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f2590t = null;
            }
            m0 m0Var = this.f2596z;
            if (m0Var != null) {
                m0Var.f2536a = false;
                this.f2596z = null;
            }
            if (this.f2595y != null) {
                this.f2595y = null;
            }
        }
        this.f2588r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f2576f = resources.getDimension(i1.b.item_touch_helper_swipe_escape_velocity);
            this.f2577g = resources.getDimension(i1.b.item_touch_helper_swipe_escape_max_velocity);
            this.f2587q = ViewConfiguration.get(this.f2588r.getContext()).getScaledTouchSlop();
            this.f2588r.g(this);
            this.f2588r.f2325o.add(i0Var);
            RecyclerView recyclerView4 = this.f2588r;
            if (recyclerView4.A == null) {
                recyclerView4.A = new ArrayList();
            }
            recyclerView4.A.add(this);
            this.f2596z = new m0(this);
            this.f2595y = new e.a(this.f2588r.getContext(), this.f2596z);
        }
    }

    public final int g(j2 j2Var, int i7) {
        if ((i7 & 12) == 0) {
            return 0;
        }
        int i8 = this.f2578h > CropImageView.DEFAULT_ASPECT_RATIO ? 8 : 4;
        VelocityTracker velocityTracker = this.f2590t;
        l0 l0Var = this.f2583m;
        if (velocityTracker != null && this.f2582l > -1) {
            velocityTracker.computeCurrentVelocity(1000, l0Var.getSwipeVelocityThreshold(this.f2577g));
            float xVelocity = this.f2590t.getXVelocity(this.f2582l);
            float yVelocity = this.f2590t.getYVelocity(this.f2582l);
            int i9 = xVelocity > CropImageView.DEFAULT_ASPECT_RATIO ? 8 : 4;
            float abs = Math.abs(xVelocity);
            if ((i9 & i7) != 0 && i8 == i9 && abs >= l0Var.getSwipeEscapeVelocity(this.f2576f) && abs > Math.abs(yVelocity)) {
                return i9;
            }
        }
        float swipeThreshold = l0Var.getSwipeThreshold(j2Var) * this.f2588r.getWidth();
        if ((i7 & i8) == 0 || Math.abs(this.f2578h) <= swipeThreshold) {
            return 0;
        }
        return i8;
    }

    @Override // androidx.recyclerview.widget.o1
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, g2 g2Var) {
        rect.setEmpty();
    }

    public final void h(int i7, int i8, MotionEvent motionEvent) {
        int absoluteMovementFlags;
        View k7;
        if (this.f2573c == null && i7 == 2 && this.f2584n != 2) {
            l0 l0Var = this.f2583m;
            if (l0Var.isItemViewSwipeEnabled() && this.f2588r.getScrollState() != 1) {
                r1 layoutManager = this.f2588r.getLayoutManager();
                int i9 = this.f2582l;
                j2 j2Var = null;
                if (i9 != -1) {
                    int findPointerIndex = motionEvent.findPointerIndex(i9);
                    float x6 = motionEvent.getX(findPointerIndex) - this.f2574d;
                    float y6 = motionEvent.getY(findPointerIndex) - this.f2575e;
                    float abs = Math.abs(x6);
                    float abs2 = Math.abs(y6);
                    float f7 = this.f2587q;
                    if ((abs >= f7 || abs2 >= f7) && ((abs <= abs2 || !layoutManager.e()) && ((abs2 <= abs || !layoutManager.f()) && (k7 = k(motionEvent)) != null))) {
                        j2Var = this.f2588r.J(k7);
                    }
                }
                if (j2Var == null || (absoluteMovementFlags = (l0Var.getAbsoluteMovementFlags(this.f2588r, j2Var) & p0.v.ACTION_POINTER_INDEX_MASK) >> 8) == 0) {
                    return;
                }
                float x7 = motionEvent.getX(i8);
                float y7 = motionEvent.getY(i8);
                float f8 = x7 - this.f2574d;
                float f9 = y7 - this.f2575e;
                float abs3 = Math.abs(f8);
                float abs4 = Math.abs(f9);
                float f10 = this.f2587q;
                if (abs3 >= f10 || abs4 >= f10) {
                    if (abs3 > abs4) {
                        if (f8 < CropImageView.DEFAULT_ASPECT_RATIO && (absoluteMovementFlags & 4) == 0) {
                            return;
                        }
                        if (f8 > CropImageView.DEFAULT_ASPECT_RATIO && (absoluteMovementFlags & 8) == 0) {
                            return;
                        }
                    } else {
                        if (f9 < CropImageView.DEFAULT_ASPECT_RATIO && (absoluteMovementFlags & 1) == 0) {
                            return;
                        }
                        if (f9 > CropImageView.DEFAULT_ASPECT_RATIO && (absoluteMovementFlags & 2) == 0) {
                            return;
                        }
                    }
                    this.f2579i = CropImageView.DEFAULT_ASPECT_RATIO;
                    this.f2578h = CropImageView.DEFAULT_ASPECT_RATIO;
                    this.f2582l = motionEvent.getPointerId(0);
                    p(j2Var, 1);
                }
            }
        }
    }

    public final int i(j2 j2Var, int i7) {
        if ((i7 & 3) == 0) {
            return 0;
        }
        int i8 = this.f2579i > CropImageView.DEFAULT_ASPECT_RATIO ? 2 : 1;
        VelocityTracker velocityTracker = this.f2590t;
        l0 l0Var = this.f2583m;
        if (velocityTracker != null && this.f2582l > -1) {
            velocityTracker.computeCurrentVelocity(1000, l0Var.getSwipeVelocityThreshold(this.f2577g));
            float xVelocity = this.f2590t.getXVelocity(this.f2582l);
            float yVelocity = this.f2590t.getYVelocity(this.f2582l);
            int i9 = yVelocity > CropImageView.DEFAULT_ASPECT_RATIO ? 2 : 1;
            float abs = Math.abs(yVelocity);
            if ((i9 & i7) != 0 && i9 == i8 && abs >= l0Var.getSwipeEscapeVelocity(this.f2576f) && abs > Math.abs(xVelocity)) {
                return i9;
            }
        }
        float swipeThreshold = l0Var.getSwipeThreshold(j2Var) * this.f2588r.getHeight();
        if ((i7 & i8) == 0 || Math.abs(this.f2579i) <= swipeThreshold) {
            return 0;
        }
        return i8;
    }

    public final void j(j2 j2Var, boolean z6) {
        ArrayList arrayList = this.f2586p;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            j0 j0Var = (j0) arrayList.get(size);
            if (j0Var.f2505e == j2Var) {
                j0Var.f2511k |= z6;
                if (!j0Var.f2512l) {
                    j0Var.f2507g.cancel();
                }
                arrayList.remove(size);
                return;
            }
        }
    }

    public final View k(MotionEvent motionEvent) {
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        j2 j2Var = this.f2573c;
        if (j2Var != null) {
            View view = j2Var.itemView;
            if (m(view, x6, y6, this.f2580j + this.f2578h, this.f2581k + this.f2579i)) {
                return view;
            }
        }
        ArrayList arrayList = this.f2586p;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            j0 j0Var = (j0) arrayList.get(size);
            View view2 = j0Var.f2505e.itemView;
            if (m(view2, x6, y6, j0Var.f2509i, j0Var.f2510j)) {
                return view2;
            }
        }
        RecyclerView recyclerView = this.f2588r;
        for (int e7 = recyclerView.f2305e.e() - 1; e7 >= 0; e7--) {
            View d7 = recyclerView.f2305e.d(e7);
            float translationX = d7.getTranslationX();
            float translationY = d7.getTranslationY();
            if (x6 >= d7.getLeft() + translationX && x6 <= d7.getRight() + translationX && y6 >= d7.getTop() + translationY && y6 <= d7.getBottom() + translationY) {
                return d7;
            }
        }
        return null;
    }

    public final void l(float[] fArr) {
        if ((this.f2585o & 12) != 0) {
            fArr[0] = (this.f2580j + this.f2578h) - this.f2573c.itemView.getLeft();
        } else {
            fArr[0] = this.f2573c.itemView.getTranslationX();
        }
        if ((this.f2585o & 3) != 0) {
            fArr[1] = (this.f2581k + this.f2579i) - this.f2573c.itemView.getTop();
        } else {
            fArr[1] = this.f2573c.itemView.getTranslationY();
        }
    }

    public final void n(j2 j2Var) {
        int i7;
        int i8;
        int i9;
        if (!this.f2588r.isLayoutRequested() && this.f2584n == 2) {
            l0 l0Var = this.f2583m;
            float moveThreshold = l0Var.getMoveThreshold(j2Var);
            int i10 = (int) (this.f2580j + this.f2578h);
            int i11 = (int) (this.f2581k + this.f2579i);
            if (Math.abs(i11 - j2Var.itemView.getTop()) >= j2Var.itemView.getHeight() * moveThreshold || Math.abs(i10 - j2Var.itemView.getLeft()) >= j2Var.itemView.getWidth() * moveThreshold) {
                ArrayList arrayList = this.f2591u;
                if (arrayList == null) {
                    this.f2591u = new ArrayList();
                    this.f2592v = new ArrayList();
                } else {
                    arrayList.clear();
                    this.f2592v.clear();
                }
                int boundingBoxMargin = l0Var.getBoundingBoxMargin();
                int round = Math.round(this.f2580j + this.f2578h) - boundingBoxMargin;
                int round2 = Math.round(this.f2581k + this.f2579i) - boundingBoxMargin;
                int i12 = boundingBoxMargin * 2;
                int width = j2Var.itemView.getWidth() + round + i12;
                int height = j2Var.itemView.getHeight() + round2 + i12;
                int i13 = (round + width) / 2;
                int i14 = (round2 + height) / 2;
                r1 layoutManager = this.f2588r.getLayoutManager();
                int w6 = layoutManager.w();
                int i15 = 0;
                while (i15 < w6) {
                    View v6 = layoutManager.v(i15);
                    if (v6 != j2Var.itemView && v6.getBottom() >= round2 && v6.getTop() <= height && v6.getRight() >= round && v6.getLeft() <= width) {
                        j2 J = this.f2588r.J(v6);
                        i7 = round;
                        i8 = round2;
                        if (l0Var.canDropOver(this.f2588r, this.f2573c, J)) {
                            int abs = Math.abs(i13 - ((v6.getRight() + v6.getLeft()) / 2));
                            int abs2 = Math.abs(i14 - ((v6.getBottom() + v6.getTop()) / 2));
                            int i16 = (abs2 * abs2) + (abs * abs);
                            int size = this.f2591u.size();
                            i9 = i13;
                            int i17 = 0;
                            int i18 = 0;
                            while (i18 < size) {
                                int i19 = size;
                                if (i16 <= ((Integer) this.f2592v.get(i18)).intValue()) {
                                    break;
                                }
                                i17++;
                                i18++;
                                size = i19;
                            }
                            this.f2591u.add(i17, J);
                            this.f2592v.add(i17, Integer.valueOf(i16));
                        } else {
                            i9 = i13;
                        }
                    } else {
                        i9 = i13;
                        i7 = round;
                        i8 = round2;
                    }
                    i15++;
                    round = i7;
                    round2 = i8;
                    i13 = i9;
                }
                ArrayList arrayList2 = this.f2591u;
                if (arrayList2.size() == 0) {
                    return;
                }
                j2 chooseDropTarget = l0Var.chooseDropTarget(j2Var, arrayList2, i10, i11);
                if (chooseDropTarget == null) {
                    this.f2591u.clear();
                    this.f2592v.clear();
                    return;
                }
                int adapterPosition = chooseDropTarget.getAdapterPosition();
                int adapterPosition2 = j2Var.getAdapterPosition();
                if (l0Var.onMove(this.f2588r, j2Var, chooseDropTarget)) {
                    this.f2583m.onMoved(this.f2588r, j2Var, adapterPosition2, chooseDropTarget, adapterPosition, i10, i11);
                }
            }
        }
    }

    public final void o(View view) {
        if (view == this.f2593w) {
            this.f2593w = null;
        }
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        float f7;
        float f8;
        this.f2594x = -1;
        if (this.f2573c != null) {
            float[] fArr = this.f2572b;
            l(fArr);
            float f9 = fArr[0];
            f8 = fArr[1];
            f7 = f9;
        } else {
            f7 = 0.0f;
            f8 = 0.0f;
        }
        this.f2583m.onDraw(canvas, recyclerView, this.f2573c, this.f2586p, this.f2584n, f7, f8);
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        float f7;
        float f8;
        if (this.f2573c != null) {
            float[] fArr = this.f2572b;
            l(fArr);
            float f9 = fArr[0];
            f8 = fArr[1];
            f7 = f9;
        } else {
            f7 = 0.0f;
            f8 = 0.0f;
        }
        this.f2583m.onDrawOver(canvas, recyclerView, this.f2573c, this.f2586p, this.f2584n, f7, f8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0095, code lost:
    
        if (r2 > 0) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p(androidx.recyclerview.widget.j2 r23, int r24) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.p0.p(androidx.recyclerview.widget.j2, int):void");
    }

    public final void q(int i7, int i8, MotionEvent motionEvent) {
        float x6 = motionEvent.getX(i8);
        float y6 = motionEvent.getY(i8);
        float f7 = x6 - this.f2574d;
        this.f2578h = f7;
        this.f2579i = y6 - this.f2575e;
        if ((i7 & 4) == 0) {
            this.f2578h = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f7);
        }
        if ((i7 & 8) == 0) {
            this.f2578h = Math.min(CropImageView.DEFAULT_ASPECT_RATIO, this.f2578h);
        }
        if ((i7 & 1) == 0) {
            this.f2579i = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, this.f2579i);
        }
        if ((i7 & 2) == 0) {
            this.f2579i = Math.min(CropImageView.DEFAULT_ASPECT_RATIO, this.f2579i);
        }
    }
}
