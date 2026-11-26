package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import com.yalantis.ucrop.view.CropImageView;
import p0.a0;
import p0.d1;
import p0.l0;
import p0.r0;
import p0.w;
import p0.x;
import p0.y;
import p0.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SwipeRefreshLayout extends ViewGroup implements z, y, w {
    public static final int DEFAULT = 1;
    public static final int DEFAULT_SLINGSHOT_DISTANCE = -1;
    public static final int LARGE = 0;
    public static final int[] O = {R.attr.enabled};
    public int A;
    public final e B;
    public g C;
    public g D;
    public h E;
    public h F;
    public g G;
    public boolean H;
    public int I;
    public boolean J;
    public boolean K;
    public final f L;
    public final g M;
    public final g N;

    /* renamed from: a, reason: collision with root package name */
    public View f2726a;

    /* renamed from: b, reason: collision with root package name */
    public j f2727b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2728c;

    /* renamed from: d, reason: collision with root package name */
    public final int f2729d;

    /* renamed from: e, reason: collision with root package name */
    public float f2730e;

    /* renamed from: f, reason: collision with root package name */
    public float f2731f;

    /* renamed from: g, reason: collision with root package name */
    public final a0 f2732g;

    /* renamed from: h, reason: collision with root package name */
    public final x f2733h;

    /* renamed from: i, reason: collision with root package name */
    public final int[] f2734i;

    /* renamed from: j, reason: collision with root package name */
    public final int[] f2735j;

    /* renamed from: k, reason: collision with root package name */
    public final int[] f2736k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2737l;

    /* renamed from: m, reason: collision with root package name */
    public final int f2738m;

    /* renamed from: n, reason: collision with root package name */
    public int f2739n;

    /* renamed from: o, reason: collision with root package name */
    public float f2740o;

    /* renamed from: p, reason: collision with root package name */
    public float f2741p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f2742q;

    /* renamed from: r, reason: collision with root package name */
    public int f2743r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f2744s;

    /* renamed from: t, reason: collision with root package name */
    public final DecelerateInterpolator f2745t;

    /* renamed from: u, reason: collision with root package name */
    public final a f2746u;

    /* renamed from: v, reason: collision with root package name */
    public int f2747v;

    /* renamed from: w, reason: collision with root package name */
    public int f2748w;

    /* renamed from: x, reason: collision with root package name */
    public float f2749x;

    /* renamed from: y, reason: collision with root package name */
    public int f2750y;

    /* renamed from: z, reason: collision with root package name */
    public int f2751z;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new k();

        /* renamed from: a, reason: collision with root package name */
        public final boolean f2752a;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f2752a = parcel.readByte() != 0;
        }

        public SavedState(Parcelable parcelable, boolean z6) {
            super(parcelable);
            this.f2752a = z6;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeByte(this.f2752a ? (byte) 1 : (byte) 0);
        }
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2728c = false;
        this.f2730e = -1.0f;
        this.f2734i = new int[2];
        this.f2735j = new int[2];
        this.f2736k = new int[2];
        this.f2743r = -1;
        this.f2747v = -1;
        this.L = new f(this, 0);
        this.M = new g(this, 2);
        this.N = new g(this, 3);
        this.f2729d = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f2738m = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.f2745t = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.I = (int) (displayMetrics.density * 40.0f);
        a aVar = new a(getContext());
        float f7 = aVar.getContext().getResources().getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = aVar.getContext().obtainStyledAttributes(l1.a.SwipeRefreshLayout);
        aVar.f2754b = obtainStyledAttributes.getColor(l1.a.SwipeRefreshLayout_swipeRefreshLayoutProgressSpinnerBackgroundColor, -328966);
        obtainStyledAttributes.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        int i7 = d1.OVER_SCROLL_ALWAYS;
        r0.s(aVar, f7 * 4.0f);
        shapeDrawable.getPaint().setColor(aVar.f2754b);
        l0.q(aVar, shapeDrawable);
        this.f2746u = aVar;
        e eVar = new e(getContext());
        this.B = eVar;
        eVar.c(1);
        this.f2746u.setImageDrawable(this.B);
        this.f2746u.setVisibility(8);
        addView(this.f2746u);
        setChildrenDrawingOrderEnabled(true);
        int i8 = (int) (displayMetrics.density * 64.0f);
        this.f2751z = i8;
        this.f2730e = i8;
        this.f2732g = new a0();
        this.f2733h = new x(this);
        setNestedScrollingEnabled(true);
        int i9 = -this.I;
        this.f2739n = i9;
        this.f2750y = i9;
        k(1.0f);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, O);
        setEnabled(obtainStyledAttributes2.getBoolean(0, true));
        obtainStyledAttributes2.recycle();
    }

    private void setColorViewAlpha(int i7) {
        this.f2746u.getBackground().setAlpha(i7);
        this.B.setAlpha(i7);
    }

    public final boolean a() {
        View view = this.f2726a;
        return view instanceof ListView ? androidx.core.widget.j.a((ListView) view, -1) : view.canScrollVertically(-1);
    }

    @Override // p0.y
    public final void b(View view, View view2, int i7, int i8) {
        if (i8 == 0) {
            onNestedScrollAccepted(view, view2, i7);
        }
    }

    public final void c() {
        if (this.f2726a == null) {
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                View childAt = getChildAt(i7);
                if (!childAt.equals(this.f2746u)) {
                    this.f2726a = childAt;
                    return;
                }
            }
        }
    }

    @Override // p0.y
    public final void d(View view, int i7, int i8, int[] iArr, int i9) {
        if (i9 == 0) {
            onNestedPreScroll(view, i7, i8, iArr);
        }
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return this.f2733h.a(f7, f8, z6);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f7, float f8) {
        return this.f2733h.b(f7, f8);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return this.f2733h.c(i7, i8, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return this.f2733h.e(i7, i8, i9, i10, iArr, 0, null);
    }

    @Override // p0.y
    public final void e(int i7, View view) {
        if (i7 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // p0.z
    public final void f(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        if (i11 != 0) {
            return;
        }
        int i12 = iArr[1];
        int[] iArr2 = this.f2735j;
        if (i11 == 0) {
            this.f2733h.d(i7, i8, i9, i10, iArr2, i11, iArr);
        }
        int i13 = i10 - (iArr[1] - i12);
        if ((i13 == 0 ? i10 + this.f2735j[1] : i13) >= 0 || a()) {
            return;
        }
        float abs = this.f2731f + Math.abs(r2);
        this.f2731f = abs;
        h(abs);
        iArr[1] = iArr[1] + i13;
    }

    public final void g(float f7) {
        int i7 = 1;
        if (f7 > this.f2730e) {
            m(true, true);
            return;
        }
        this.f2728c = false;
        e eVar = this.B;
        d dVar = eVar.f2784a;
        dVar.f2764e = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2765f = CropImageView.DEFAULT_ASPECT_RATIO;
        eVar.invalidateSelf();
        boolean z6 = this.f2744s;
        f fVar = !z6 ? new f(this, i7) : null;
        int i8 = this.f2739n;
        if (z6) {
            this.f2748w = i8;
            this.f2749x = this.f2746u.getScaleX();
            g gVar = new g(this, 4);
            this.G = gVar;
            gVar.setDuration(150L);
            if (fVar != null) {
                this.f2746u.f2753a = fVar;
            }
            this.f2746u.clearAnimation();
            this.f2746u.startAnimation(this.G);
        } else {
            this.f2748w = i8;
            g gVar2 = this.N;
            gVar2.reset();
            gVar2.setDuration(200L);
            gVar2.setInterpolator(this.f2745t);
            if (fVar != null) {
                this.f2746u.f2753a = fVar;
            }
            this.f2746u.clearAnimation();
            this.f2746u.startAnimation(gVar2);
        }
        e eVar2 = this.B;
        d dVar2 = eVar2.f2784a;
        if (dVar2.f2773n) {
            dVar2.f2773n = false;
        }
        eVar2.invalidateSelf();
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i8) {
        int i9 = this.f2747v;
        return i9 < 0 ? i8 : i8 == i7 + (-1) ? i9 : i8 >= i9 ? i8 + 1 : i8;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        a0 a0Var = this.f2732g;
        return a0Var.f8093b | a0Var.f8092a;
    }

    public int getProgressCircleDiameter() {
        return this.I;
    }

    public int getProgressViewEndOffset() {
        return this.f2751z;
    }

    public int getProgressViewStartOffset() {
        return this.f2750y;
    }

    public final void h(float f7) {
        h hVar;
        h hVar2;
        e eVar = this.B;
        d dVar = eVar.f2784a;
        if (!dVar.f2773n) {
            dVar.f2773n = true;
        }
        eVar.invalidateSelf();
        float min = Math.min(1.0f, Math.abs(f7 / this.f2730e));
        float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float abs = Math.abs(f7) - this.f2730e;
        int i7 = this.A;
        if (i7 <= 0) {
            i7 = this.J ? this.f2751z - this.f2750y : this.f2751z;
        }
        float f8 = i7;
        double max2 = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(abs, f8 * 2.0f) / f8) / 4.0f;
        float pow = ((float) (max2 - Math.pow(max2, 2.0d))) * 2.0f;
        int i8 = this.f2750y + ((int) ((f8 * min) + (f8 * pow * 2.0f)));
        if (this.f2746u.getVisibility() != 0) {
            this.f2746u.setVisibility(0);
        }
        if (!this.f2744s) {
            this.f2746u.setScaleX(1.0f);
            this.f2746u.setScaleY(1.0f);
        }
        if (this.f2744s) {
            setAnimationProgress(Math.min(1.0f, f7 / this.f2730e));
        }
        if (f7 < this.f2730e) {
            if (this.B.f2784a.f2779t > 76 && ((hVar2 = this.E) == null || !hVar2.hasStarted() || hVar2.hasEnded())) {
                h hVar3 = new h(this, this.B.f2784a.f2779t, 76);
                hVar3.setDuration(300L);
                a aVar = this.f2746u;
                aVar.f2753a = null;
                aVar.clearAnimation();
                this.f2746u.startAnimation(hVar3);
                this.E = hVar3;
            }
        } else if (this.B.f2784a.f2779t < 255 && ((hVar = this.F) == null || !hVar.hasStarted() || hVar.hasEnded())) {
            h hVar4 = new h(this, this.B.f2784a.f2779t, 255);
            hVar4.setDuration(300L);
            a aVar2 = this.f2746u;
            aVar2.f2753a = null;
            aVar2.clearAnimation();
            this.f2746u.startAnimation(hVar4);
            this.F = hVar4;
        }
        e eVar2 = this.B;
        float min2 = Math.min(0.8f, max * 0.8f);
        d dVar2 = eVar2.f2784a;
        dVar2.f2764e = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar2.f2765f = min2;
        eVar2.invalidateSelf();
        e eVar3 = this.B;
        float min3 = Math.min(1.0f, max);
        d dVar3 = eVar3.f2784a;
        if (min3 != dVar3.f2775p) {
            dVar3.f2775p = min3;
        }
        eVar3.invalidateSelf();
        e eVar4 = this.B;
        eVar4.f2784a.f2766g = ((pow * 2.0f) + ((max * 0.4f) - 0.25f)) * 0.5f;
        eVar4.invalidateSelf();
        setTargetOffsetTopAndBottom(i8 - this.f2739n);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f2733h.g(0);
    }

    @Override // p0.y
    public final void i(View view, int i7, int i8, int i9, int i10, int i11) {
        f(view, i7, i8, i9, i10, i11, this.f2736k);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f2733h.f8201d;
    }

    @Override // p0.y
    public final boolean j(View view, View view2, int i7, int i8) {
        if (i8 == 0) {
            return onStartNestedScroll(view, view2, i7);
        }
        return false;
    }

    public final void k(float f7) {
        setTargetOffsetTopAndBottom((this.f2748w + ((int) ((this.f2750y - r0) * f7))) - this.f2746u.getTop());
    }

    public final void l() {
        this.f2746u.clearAnimation();
        this.B.stop();
        this.f2746u.setVisibility(8);
        setColorViewAlpha(255);
        if (this.f2744s) {
            setAnimationProgress(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            setTargetOffsetTopAndBottom(this.f2750y - this.f2739n);
        }
        this.f2739n = this.f2746u.getTop();
    }

    public final void m(boolean z6, boolean z7) {
        if (this.f2728c != z6) {
            this.H = z7;
            c();
            this.f2728c = z6;
            f fVar = this.L;
            if (!z6) {
                g gVar = new g(this, 1);
                this.D = gVar;
                gVar.setDuration(150L);
                a aVar = this.f2746u;
                aVar.f2753a = fVar;
                aVar.clearAnimation();
                this.f2746u.startAnimation(this.D);
                return;
            }
            this.f2748w = this.f2739n;
            g gVar2 = this.M;
            gVar2.reset();
            gVar2.setDuration(200L);
            gVar2.setInterpolator(this.f2745t);
            if (fVar != null) {
                this.f2746u.f2753a = fVar;
            }
            this.f2746u.clearAnimation();
            this.f2746u.startAnimation(gVar2);
        }
    }

    public final void n(float f7) {
        float f8 = this.f2741p;
        float f9 = f7 - f8;
        int i7 = this.f2729d;
        if (f9 <= i7 || this.f2742q) {
            return;
        }
        this.f2740o = f8 + i7;
        this.f2742q = true;
        this.B.setAlpha(76);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        c();
        int actionMasked = motionEvent.getActionMasked();
        if (!isEnabled() || a() || this.f2728c || this.f2737l) {
            return false;
        }
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i7 = this.f2743r;
                    if (i7 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i7)) < 0) {
                        return false;
                    }
                    n(motionEvent.getY(findPointerIndex));
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        int actionIndex = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionIndex) == this.f2743r) {
                            this.f2743r = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        }
                    }
                }
            }
            this.f2742q = false;
            this.f2743r = -1;
        } else {
            setTargetOffsetTopAndBottom(this.f2750y - this.f2746u.getTop());
            int pointerId = motionEvent.getPointerId(0);
            this.f2743r = pointerId;
            this.f2742q = false;
            int findPointerIndex2 = motionEvent.findPointerIndex(pointerId);
            if (findPointerIndex2 < 0) {
                return false;
            }
            this.f2741p = motionEvent.getY(findPointerIndex2);
        }
        return this.f2742q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f2726a == null) {
            c();
        }
        View view = this.f2726a;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.f2746u.getMeasuredWidth();
        int measuredHeight2 = this.f2746u.getMeasuredHeight();
        int i11 = measuredWidth / 2;
        int i12 = measuredWidth2 / 2;
        int i13 = this.f2739n;
        this.f2746u.layout(i11 - i12, i13, i11 + i12, measuredHeight2 + i13);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.f2726a == null) {
            c();
        }
        View view = this.f2726a;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), v.c.EXACTLY));
        this.f2746u.measure(View.MeasureSpec.makeMeasureSpec(this.I, v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.I, v.c.EXACTLY));
        this.f2747v = -1;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if (getChildAt(i9) == this.f2746u) {
                this.f2747v = i9;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        return dispatchNestedFling(f7, f8, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f7, float f8) {
        return dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        if (i8 > 0) {
            float f7 = this.f2731f;
            if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                float f8 = i8;
                if (f8 > f7) {
                    iArr[1] = (int) f7;
                    this.f2731f = CropImageView.DEFAULT_ASPECT_RATIO;
                } else {
                    this.f2731f = f7 - f8;
                    iArr[1] = i8;
                }
                h(this.f2731f);
            }
        }
        if (this.J && i8 > 0 && this.f2731f == CropImageView.DEFAULT_ASPECT_RATIO && Math.abs(i8 - iArr[1]) > 0) {
            this.f2746u.setVisibility(8);
        }
        int i9 = i7 - iArr[0];
        int i10 = i8 - iArr[1];
        int[] iArr2 = this.f2734i;
        if (dispatchNestedPreScroll(i9, i10, iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        f(view, i7, i8, i9, i10, 0, this.f2736k);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        this.f2732g.f8092a = i7;
        startNestedScroll(i7 & 2);
        this.f2731f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f2737l = true;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.f2752a);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.f2728c);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        return (!isEnabled() || this.f2728c || (i7 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.f2732g.f8092a = 0;
        this.f2737l = false;
        float f7 = this.f2731f;
        if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
            g(f7);
            this.f2731f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!isEnabled() || a() || this.f2728c || this.f2737l) {
            return false;
        }
        if (actionMasked == 0) {
            this.f2743r = motionEvent.getPointerId(0);
            this.f2742q = false;
        } else {
            if (actionMasked == 1) {
                int findPointerIndex = motionEvent.findPointerIndex(this.f2743r);
                if (findPointerIndex < 0) {
                    return false;
                }
                if (this.f2742q) {
                    float y6 = (motionEvent.getY(findPointerIndex) - this.f2740o) * 0.5f;
                    this.f2742q = false;
                    g(y6);
                }
                this.f2743r = -1;
                return false;
            }
            if (actionMasked == 2) {
                int findPointerIndex2 = motionEvent.findPointerIndex(this.f2743r);
                if (findPointerIndex2 < 0) {
                    return false;
                }
                float y7 = motionEvent.getY(findPointerIndex2);
                n(y7);
                if (this.f2742q) {
                    float f7 = (y7 - this.f2740o) * 0.5f;
                    if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                        return false;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    h(f7);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        return false;
                    }
                    this.f2743r = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    int actionIndex2 = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex2) == this.f2743r) {
                        this.f2743r = motionEvent.getPointerId(actionIndex2 == 0 ? 1 : 0);
                    }
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        ViewParent parent;
        View view = this.f2726a;
        if (view != null) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            if (!r0.p(view)) {
                if (this.K || (parent = getParent()) == null) {
                    return;
                }
                parent.requestDisallowInterceptTouchEvent(z6);
                return;
            }
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    public void setAnimationProgress(float f7) {
        this.f2746u.setScaleX(f7);
        this.f2746u.setScaleY(f7);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        c();
        e eVar = this.B;
        d dVar = eVar.f2784a;
        dVar.f2768i = iArr;
        dVar.a(0);
        dVar.a(0);
        eVar.invalidateSelf();
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            int i8 = iArr[i7];
            Object obj = g0.g.f6423a;
            iArr2[i7] = g0.d.a(context, i8);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i7) {
        this.f2730e = i7;
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        if (z6) {
            return;
        }
        l();
    }

    @Deprecated
    public void setLegacyRequestDisallowInterceptTouchEventEnabled(boolean z6) {
        this.K = z6;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z6) {
        x xVar = this.f2733h;
        if (xVar.f8201d) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            r0.z(xVar.f8200c);
        }
        xVar.f8201d = z6;
    }

    public void setOnChildScrollUpCallback(i iVar) {
    }

    public void setOnRefreshListener(j jVar) {
        this.f2727b = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i7) {
        setProgressBackgroundColorSchemeResource(i7);
    }

    public void setProgressBackgroundColorSchemeColor(int i7) {
        this.f2746u.setBackgroundColor(i7);
    }

    public void setProgressBackgroundColorSchemeResource(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setProgressBackgroundColorSchemeColor(g0.d.a(context, i7));
    }

    public void setProgressViewEndTarget(boolean z6, int i7) {
        this.f2751z = i7;
        this.f2744s = z6;
        this.f2746u.invalidate();
    }

    public void setProgressViewOffset(boolean z6, int i7, int i8) {
        this.f2744s = z6;
        this.f2750y = i7;
        this.f2751z = i8;
        this.J = true;
        l();
        this.f2728c = false;
    }

    public void setRefreshing(boolean z6) {
        if (!z6 || this.f2728c == z6) {
            m(z6, false);
            return;
        }
        this.f2728c = z6;
        setTargetOffsetTopAndBottom((!this.J ? this.f2751z + this.f2750y : this.f2751z) - this.f2739n);
        this.H = false;
        f fVar = this.L;
        this.f2746u.setVisibility(0);
        this.B.setAlpha(255);
        g gVar = new g(this, 0);
        this.C = gVar;
        gVar.setDuration(this.f2738m);
        if (fVar != null) {
            this.f2746u.f2753a = fVar;
        }
        this.f2746u.clearAnimation();
        this.f2746u.startAnimation(this.C);
    }

    public void setSize(int i7) {
        if (i7 == 0 || i7 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i7 == 0) {
                this.I = (int) (displayMetrics.density * 56.0f);
            } else {
                this.I = (int) (displayMetrics.density * 40.0f);
            }
            this.f2746u.setImageDrawable(null);
            this.B.c(i7);
            this.f2746u.setImageDrawable(this.B);
        }
    }

    public void setSlingshotDistance(int i7) {
        this.A = i7;
    }

    public void setTargetOffsetTopAndBottom(int i7) {
        this.f2746u.bringToFront();
        a aVar = this.f2746u;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        aVar.offsetTopAndBottom(i7);
        this.f2739n = this.f2746u.getTop();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i7) {
        return this.f2733h.h(i7, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.f2733h.i(0);
    }
}
