package androidx.drawerlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.core.widget.k;
import androidx.customview.view.AbsSavedState;
import com.yalantis.ucrop.view.CropImageView;
import g0.g;
import java.util.ArrayList;
import p0.d1;
import p0.l0;
import p0.l2;
import p0.m0;
import p0.r0;
import u0.e;
import w0.b;
import w0.c;
import w0.d;
import w0.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class DrawerLayout extends ViewGroup {
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    public static final boolean N;
    public static final boolean O;
    public static final boolean P;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public CharSequence A;
    public Object B;
    public boolean C;
    public Drawable D;
    public Drawable E;
    public Drawable F;
    public Drawable G;
    public final ArrayList H;
    public Rect I;
    public Matrix J;
    public final e.a K;

    /* renamed from: a, reason: collision with root package name */
    public final k f1831a;

    /* renamed from: b, reason: collision with root package name */
    public float f1832b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1833c;

    /* renamed from: d, reason: collision with root package name */
    public int f1834d;

    /* renamed from: e, reason: collision with root package name */
    public float f1835e;

    /* renamed from: f, reason: collision with root package name */
    public final Paint f1836f;

    /* renamed from: g, reason: collision with root package name */
    public final e f1837g;

    /* renamed from: h, reason: collision with root package name */
    public final e f1838h;

    /* renamed from: i, reason: collision with root package name */
    public final f f1839i;

    /* renamed from: j, reason: collision with root package name */
    public final f f1840j;

    /* renamed from: k, reason: collision with root package name */
    public int f1841k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f1842l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1843m;

    /* renamed from: n, reason: collision with root package name */
    public int f1844n;

    /* renamed from: o, reason: collision with root package name */
    public int f1845o;

    /* renamed from: p, reason: collision with root package name */
    public int f1846p;

    /* renamed from: q, reason: collision with root package name */
    public int f1847q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1848r;

    /* renamed from: s, reason: collision with root package name */
    public c f1849s;

    /* renamed from: t, reason: collision with root package name */
    public ArrayList f1850t;

    /* renamed from: u, reason: collision with root package name */
    public float f1851u;

    /* renamed from: v, reason: collision with root package name */
    public float f1852v;

    /* renamed from: w, reason: collision with root package name */
    public Drawable f1853w;

    /* renamed from: x, reason: collision with root package name */
    public Drawable f1854x;

    /* renamed from: y, reason: collision with root package name */
    public Drawable f1855y;

    /* renamed from: z, reason: collision with root package name */
    public CharSequence f1856z;
    public static final int[] L = {R.attr.colorPrimaryDark};
    public static final int[] M = {R.attr.layout_gravity};

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public int f1857b;

        /* renamed from: c, reason: collision with root package name */
        public int f1858c;

        /* renamed from: d, reason: collision with root package name */
        public int f1859d;

        /* renamed from: e, reason: collision with root package name */
        public int f1860e;

        /* renamed from: f, reason: collision with root package name */
        public int f1861f;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1857b = 0;
            this.f1857b = parcel.readInt();
            this.f1858c = parcel.readInt();
            this.f1859d = parcel.readInt();
            this.f1860e = parcel.readInt();
            this.f1861f = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f1857b);
            parcel.writeInt(this.f1858c);
            parcel.writeInt(this.f1859d);
            parcel.writeInt(this.f1860e);
            parcel.writeInt(this.f1861f);
        }
    }

    static {
        int i7 = Build.VERSION.SDK_INT;
        N = true;
        O = true;
        P = i7 >= 29;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, v0.a.drawerLayoutStyle);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1831a = new k(1);
        this.f1834d = -1728053248;
        this.f1836f = new Paint();
        this.f1843m = true;
        this.f1844n = 3;
        this.f1845o = 3;
        this.f1846p = 3;
        this.f1847q = 3;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.K = new e.a(18, this);
        setDescendantFocusability(262144);
        float f7 = getResources().getDisplayMetrics().density;
        this.f1833c = (int) ((64.0f * f7) + 0.5f);
        float f8 = f7 * 400.0f;
        f fVar = new f(this, 3);
        this.f1839i = fVar;
        f fVar2 = new f(this, 5);
        this.f1840j = fVar2;
        e eVar = new e(getContext(), this, fVar);
        eVar.f9238b = (int) (eVar.f9238b * 1.0f);
        this.f1837g = eVar;
        eVar.f9253q = 1;
        eVar.f9250n = f8;
        fVar.f9410e = eVar;
        e eVar2 = new e(getContext(), this, fVar2);
        eVar2.f9238b = (int) (1.0f * eVar2.f9238b);
        this.f1838h = eVar2;
        eVar2.f9253q = 2;
        eVar2.f9250n = f8;
        fVar2.f9410e = eVar2;
        setFocusableInTouchMode(true);
        int i8 = d1.OVER_SCROLL_ALWAYS;
        l0.s(this, 1);
        d1.o(this, new b(this));
        setMotionEventSplittingEnabled(false);
        if (l0.b(this)) {
            setOnApplyWindowInsetsListener(new w0.a());
            setSystemUiVisibility(1280);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(L);
            try {
                this.f1853w = obtainStyledAttributes.getDrawable(0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, v0.c.DrawerLayout, i7, 0);
        try {
            if (obtainStyledAttributes2.hasValue(v0.c.DrawerLayout_elevation)) {
                this.f1832b = obtainStyledAttributes2.getDimension(v0.c.DrawerLayout_elevation, CropImageView.DEFAULT_ASPECT_RATIO);
            } else {
                this.f1832b = getResources().getDimension(v0.b.def_drawer_elevation);
            }
            obtainStyledAttributes2.recycle();
            this.H = new ArrayList();
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
        }
    }

    public static String l(int i7) {
        return (i7 & 3) == 3 ? "LEFT" : (i7 & 5) == 5 ? "RIGHT" : Integer.toHexString(i7);
    }

    public static boolean m(View view) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return (l0.c(view) == 4 || l0.c(view) == 2) ? false : true;
    }

    public static boolean n(View view) {
        return ((d) view.getLayoutParams()).f9405a == 0;
    }

    public static boolean o(View view) {
        if (p(view)) {
            return (((d) view.getLayoutParams()).f9408d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public static boolean p(View view) {
        int i7 = ((d) view.getLayoutParams()).f9405a;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, m0.d(view));
        return ((absoluteGravity & 3) == 0 && (absoluteGravity & 5) == 0) ? false : true;
    }

    public static boolean q(View view) {
        if (p(view)) {
            return ((d) view.getLayoutParams()).f9406b > CropImageView.DEFAULT_ASPECT_RATIO;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final void a(c cVar) {
        if (cVar == null) {
            return;
        }
        if (this.f1850t == null) {
            this.f1850t = new ArrayList();
        }
        this.f1850t.add(cVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i7, int i8) {
        ArrayList arrayList2;
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        int i9 = 0;
        boolean z6 = false;
        while (true) {
            arrayList2 = this.H;
            if (i9 >= childCount) {
                break;
            }
            View childAt = getChildAt(i9);
            if (!p(childAt)) {
                arrayList2.add(childAt);
            } else if (o(childAt)) {
                childAt.addFocusables(arrayList, i7, i8);
                z6 = true;
            }
            i9++;
        }
        if (!z6) {
            int size = arrayList2.size();
            for (int i10 = 0; i10 < size; i10++) {
                View view = (View) arrayList2.get(i10);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i7, i8);
                }
            }
        }
        arrayList2.clear();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i7, layoutParams);
        if (g() != null || p(view)) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.s(view, 4);
        } else {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            l0.s(view, 1);
        }
        if (N) {
            return;
        }
        d1.o(view, this.f1831a);
    }

    public final boolean b(int i7, View view) {
        return (k(view) & i7) == i7;
    }

    public final void c(View view, boolean z6) {
        if (!p(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        d dVar = (d) view.getLayoutParams();
        if (this.f1843m) {
            dVar.f9406b = CropImageView.DEFAULT_ASPECT_RATIO;
            dVar.f9408d = 0;
        } else if (z6) {
            dVar.f9408d |= 4;
            if (b(3, view)) {
                this.f1837g.r(view, -view.getWidth(), view.getTop());
            } else {
                this.f1838h.r(view, getWidth(), view.getTop());
            }
        } else {
            r(view, CropImageView.DEFAULT_ASPECT_RATIO);
            y(0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        int childCount = getChildCount();
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (int i7 = 0; i7 < childCount; i7++) {
            f7 = Math.max(f7, ((d) getChildAt(i7).getLayoutParams()).f9406b);
        }
        this.f1835e = f7;
        boolean g7 = this.f1837g.g();
        boolean g8 = this.f1838h.g();
        if (g7 || g8) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public final void d() {
        View f7 = f(8388611);
        if (f7 != null) {
            c(f7, true);
        } else {
            throw new IllegalArgumentException("No drawer view found with gravity " + l(8388611));
        }
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean dispatchGenericMotionEvent;
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f1835e <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        for (int i7 = childCount - 1; i7 >= 0; i7--) {
            View childAt = getChildAt(i7);
            if (this.I == null) {
                this.I = new Rect();
            }
            childAt.getHitRect(this.I);
            if (this.I.contains((int) x6, (int) y6) && !n(childAt)) {
                if (childAt.getMatrix().isIdentity()) {
                    float scrollX = getScrollX() - childAt.getLeft();
                    float scrollY = getScrollY() - childAt.getTop();
                    motionEvent.offsetLocation(scrollX, scrollY);
                    dispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEvent);
                    motionEvent.offsetLocation(-scrollX, -scrollY);
                } else {
                    float scrollX2 = getScrollX() - childAt.getLeft();
                    float scrollY2 = getScrollY() - childAt.getTop();
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(scrollX2, scrollY2);
                    Matrix matrix = childAt.getMatrix();
                    if (!matrix.isIdentity()) {
                        if (this.J == null) {
                            this.J = new Matrix();
                        }
                        matrix.invert(this.J);
                        obtain.transform(this.J);
                    }
                    dispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(obtain);
                    obtain.recycle();
                }
                if (dispatchGenericMotionEvent) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j7) {
        Drawable background;
        int height = getHeight();
        boolean n6 = n(view);
        int width = getWidth();
        int save = canvas.save();
        int i7 = 0;
        if (n6) {
            int childCount = getChildCount();
            int i8 = 0;
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                if (childAt != view && childAt.getVisibility() == 0 && (background = childAt.getBackground()) != null && background.getOpacity() == -1 && p(childAt) && childAt.getHeight() >= height) {
                    if (b(3, childAt)) {
                        int right = childAt.getRight();
                        if (right > i8) {
                            i8 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i8, 0, width, getHeight());
            i7 = i8;
        }
        boolean drawChild = super.drawChild(canvas, view, j7);
        canvas.restoreToCount(save);
        float f7 = this.f1835e;
        if (f7 > CropImageView.DEFAULT_ASPECT_RATIO && n6) {
            int i10 = (this.f1834d & d1.MEASURED_SIZE_MASK) | (((int) ((((-16777216) & r2) >>> 24) * f7)) << 24);
            Paint paint = this.f1836f;
            paint.setColor(i10);
            canvas.drawRect(i7, CropImageView.DEFAULT_ASPECT_RATIO, width, getHeight(), paint);
        } else if (this.f1854x != null && b(3, view)) {
            int intrinsicWidth = this.f1854x.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(right2 / this.f1837g.f9251o, 1.0f));
            this.f1854x.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.f1854x.setAlpha((int) (max * 255.0f));
            this.f1854x.draw(canvas);
        } else if (this.f1855y != null && b(5, view)) {
            int intrinsicWidth2 = this.f1855y.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min((getWidth() - left2) / this.f1838h.f9251o, 1.0f));
            this.f1855y.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.f1855y.setAlpha((int) (max2 * 255.0f));
            this.f1855y.draw(canvas);
        }
        return drawChild;
    }

    public final void e(boolean z6) {
        int childCount = getChildCount();
        boolean z7 = false;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            d dVar = (d) childAt.getLayoutParams();
            if (p(childAt) && (!z6 || dVar.f9407c)) {
                z7 |= b(3, childAt) ? this.f1837g.r(childAt, -childAt.getWidth(), childAt.getTop()) : this.f1838h.r(childAt, getWidth(), childAt.getTop());
                dVar.f9407c = false;
            }
        }
        f fVar = this.f1839i;
        fVar.f9412g.removeCallbacks(fVar.f9411f);
        f fVar2 = this.f1840j;
        fVar2.f9412g.removeCallbacks(fVar2.f9411f);
        if (z7) {
            invalidate();
        }
    }

    public final View f(int i7) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, m0.d(this)) & 7;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if ((k(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    public final View g() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if ((((d) childAt.getLayoutParams()).f9408d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        d dVar = new d(-1, -1);
        dVar.f9405a = 0;
        return dVar;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        d dVar = new d(context, attributeSet);
        dVar.f9405a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, M);
        dVar.f9405a = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        return dVar;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof d) {
            d dVar = (d) layoutParams;
            d dVar2 = new d(dVar);
            dVar2.f9405a = 0;
            dVar2.f9405a = dVar.f9405a;
            return dVar2;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            d dVar3 = new d((ViewGroup.MarginLayoutParams) layoutParams);
            dVar3.f9405a = 0;
            return dVar3;
        }
        d dVar4 = new d(layoutParams);
        dVar4.f9405a = 0;
        return dVar4;
    }

    public float getDrawerElevation() {
        return O ? this.f1832b : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.f1853w;
    }

    public final View h() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (p(childAt) && q(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public final int i(int i7) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        int d7 = m0.d(this);
        if (i7 == 3) {
            int i9 = this.f1844n;
            if (i9 != 3) {
                return i9;
            }
            int i10 = d7 == 0 ? this.f1846p : this.f1847q;
            if (i10 != 3) {
                return i10;
            }
            return 0;
        }
        if (i7 == 5) {
            int i11 = this.f1845o;
            if (i11 != 3) {
                return i11;
            }
            int i12 = d7 == 0 ? this.f1847q : this.f1846p;
            if (i12 != 3) {
                return i12;
            }
            return 0;
        }
        if (i7 == 8388611) {
            int i13 = this.f1846p;
            if (i13 != 3) {
                return i13;
            }
            int i14 = d7 == 0 ? this.f1844n : this.f1845o;
            if (i14 != 3) {
                return i14;
            }
            return 0;
        }
        if (i7 != 8388613) {
            return 0;
        }
        int i15 = this.f1847q;
        if (i15 != 3) {
            return i15;
        }
        int i16 = d7 == 0 ? this.f1845o : this.f1844n;
        if (i16 != 3) {
            return i16;
        }
        return 0;
    }

    public final int j(View view) {
        if (p(view)) {
            return i(((d) view.getLayoutParams()).f9405a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final int k(View view) {
        int i7 = ((d) view.getLayoutParams()).f9405a;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        return Gravity.getAbsoluteGravity(i7, m0.d(this));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1843m = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1843m = true;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.C || this.f1853w == null) {
            return;
        }
        Object obj = this.B;
        int systemWindowInsetTop = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
        if (systemWindowInsetTop > 0) {
            this.f1853w.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.f1853w.draw(canvas);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
    
        if (r0 != 3) goto L19;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            u0.e r1 = r8.f1837g
            boolean r2 = r1.q(r9)
            u0.e r3 = r8.f1838h
            boolean r3 = r3.q(r9)
            r2 = r2 | r3
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L65
            if (r0 == r3) goto L5e
            r9 = 2
            if (r0 == r9) goto L1e
            r9 = 3
            if (r0 == r9) goto L5e
            goto L63
        L1e:
            float[] r9 = r1.f9240d
            int r9 = r9.length
            r0 = r4
        L22:
            if (r0 >= r9) goto L63
            int r5 = r1.f9247k
            int r6 = r3 << r0
            r5 = r5 & r6
            if (r5 == 0) goto L5b
            float[] r5 = r1.f9242f
            r5 = r5[r0]
            float[] r6 = r1.f9240d
            r6 = r6[r0]
            float r5 = r5 - r6
            float[] r6 = r1.f9243g
            r6 = r6[r0]
            float[] r7 = r1.f9241e
            r7 = r7[r0]
            float r6 = r6 - r7
            float r5 = r5 * r5
            float r6 = r6 * r6
            float r6 = r6 + r5
            int r5 = r1.f9238b
            int r5 = r5 * r5
            float r5 = (float) r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 <= 0) goto L5b
            w0.f r9 = r8.f1839i
            androidx.appcompat.app.s0 r0 = r9.f9411f
            androidx.drawerlayout.widget.DrawerLayout r9 = r9.f9412g
            r9.removeCallbacks(r0)
            w0.f r9 = r8.f1840j
            androidx.appcompat.app.s0 r0 = r9.f9411f
            androidx.drawerlayout.widget.DrawerLayout r9 = r9.f9412g
            r9.removeCallbacks(r0)
            goto L63
        L5b:
            int r0 = r0 + 1
            goto L22
        L5e:
            r8.e(r3)
            r8.f1848r = r4
        L63:
            r9 = r4
            goto L8b
        L65:
            float r0 = r9.getX()
            float r9 = r9.getY()
            r8.f1851u = r0
            r8.f1852v = r9
            float r5 = r8.f1835e
            r6 = 0
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L88
            int r0 = (int) r0
            int r9 = (int) r9
            android.view.View r9 = r1.h(r0, r9)
            if (r9 == 0) goto L88
            boolean r9 = n(r9)
            if (r9 == 0) goto L88
            r9 = r3
            goto L89
        L88:
            r9 = r4
        L89:
            r8.f1848r = r4
        L8b:
            if (r2 != 0) goto Lae
            if (r9 != 0) goto Lae
            int r9 = r8.getChildCount()
            r0 = r4
        L94:
            if (r0 >= r9) goto La8
            android.view.View r1 = r8.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            w0.d r1 = (w0.d) r1
            boolean r1 = r1.f9407c
            if (r1 == 0) goto La5
            goto Lae
        La5:
            int r0 = r0 + 1
            goto L94
        La8:
            boolean r9 = r8.f1848r
            if (r9 == 0) goto Lad
            goto Lae
        Lad:
            r3 = r4
        Lae:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (i7 != 4 || h() == null) {
            return super.onKeyDown(i7, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i7, KeyEvent keyEvent) {
        if (i7 != 4) {
            return super.onKeyUp(i7, keyEvent);
        }
        View h7 = h();
        if (h7 != null && j(h7) == 0) {
            e(false);
        }
        return h7 != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        WindowInsets rootWindowInsets;
        float f7;
        int i11;
        boolean z7 = true;
        this.f1842l = true;
        int i12 = i9 - i7;
        int childCount = getChildCount();
        int i13 = 0;
        while (i13 < childCount) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                if (n(childAt)) {
                    int i14 = ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    childAt.layout(i14, ((ViewGroup.MarginLayoutParams) dVar).topMargin, childAt.getMeasuredWidth() + i14, childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) dVar).topMargin);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (b(3, childAt)) {
                        float f8 = measuredWidth;
                        i11 = (-measuredWidth) + ((int) (dVar.f9406b * f8));
                        f7 = (measuredWidth + i11) / f8;
                    } else {
                        float f9 = measuredWidth;
                        f7 = (i12 - r11) / f9;
                        i11 = i12 - ((int) (dVar.f9406b * f9));
                    }
                    boolean z8 = f7 != dVar.f9406b ? z7 : false;
                    int i15 = dVar.f9405a & z1.c.INVOKE_DIRECT;
                    if (i15 == 16) {
                        int i16 = i10 - i8;
                        int i17 = (i16 - measuredHeight) / 2;
                        int i18 = ((ViewGroup.MarginLayoutParams) dVar).topMargin;
                        if (i17 < i18) {
                            i17 = i18;
                        } else {
                            int i19 = i17 + measuredHeight;
                            int i20 = i16 - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin;
                            if (i19 > i20) {
                                i17 = i20 - measuredHeight;
                            }
                        }
                        childAt.layout(i11, i17, measuredWidth + i11, measuredHeight + i17);
                    } else if (i15 != 80) {
                        int i21 = ((ViewGroup.MarginLayoutParams) dVar).topMargin;
                        childAt.layout(i11, i21, measuredWidth + i11, measuredHeight + i21);
                    } else {
                        int i22 = i10 - i8;
                        childAt.layout(i11, (i22 - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i11, i22 - ((ViewGroup.MarginLayoutParams) dVar).bottomMargin);
                    }
                    if (z8) {
                        v(childAt, f7);
                    }
                    int i23 = dVar.f9406b > CropImageView.DEFAULT_ASPECT_RATIO ? 0 : 4;
                    if (childAt.getVisibility() != i23) {
                        childAt.setVisibility(i23);
                    }
                }
            }
            i13++;
            z7 = true;
        }
        if (P && (rootWindowInsets = getRootWindowInsets()) != null) {
            i0.c i24 = l2.h(null, rootWindowInsets).f8161a.i();
            e eVar = this.f1837g;
            eVar.f9251o = Math.max(eVar.f9252p, i24.f6773a);
            e eVar2 = this.f1838h;
            eVar2.f9251o = Math.max(eVar2.f9252p, i24.f6775c);
        }
        this.f1842l = false;
        this.f1843m = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0048  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        View f7;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        int i7 = savedState.f1857b;
        if (i7 != 0 && (f7 = f(i7)) != null) {
            s(f7);
        }
        int i8 = savedState.f1858c;
        if (i8 != 3) {
            setDrawerLockMode(i8, 3);
        }
        int i9 = savedState.f1859d;
        if (i9 != 3) {
            setDrawerLockMode(i9, 5);
        }
        int i10 = savedState.f1860e;
        if (i10 != 3) {
            setDrawerLockMode(i10, 8388611);
        }
        int i11 = savedState.f1861f;
        if (i11 != 3) {
            setDrawerLockMode(i11, 8388613);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        u();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1857b = 0;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            d dVar = (d) getChildAt(i7).getLayoutParams();
            int i8 = dVar.f9408d;
            boolean z6 = i8 == 1;
            boolean z7 = i8 == 2;
            if (z6 || z7) {
                savedState.f1857b = dVar.f9405a;
                break;
            }
        }
        savedState.f1858c = this.f1844n;
        savedState.f1859d = this.f1845o;
        savedState.f1860e = this.f1846p;
        savedState.f1861f = this.f1847q;
        return savedState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
    
        if (j(r7) != 2) goto L20;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            u0.e r0 = r6.f1837g
            r0.j(r7)
            u0.e r1 = r6.f1838h
            r1.j(r7)
            int r1 = r7.getAction()
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L59
            if (r1 == r3) goto L20
            r7 = 3
            if (r1 == r7) goto L1a
            goto L67
        L1a:
            r6.e(r3)
            r6.f1848r = r2
            goto L67
        L20:
            float r1 = r7.getX()
            float r7 = r7.getY()
            int r4 = (int) r1
            int r5 = (int) r7
            android.view.View r4 = r0.h(r4, r5)
            if (r4 == 0) goto L54
            boolean r4 = n(r4)
            if (r4 == 0) goto L54
            float r4 = r6.f1851u
            float r1 = r1 - r4
            float r4 = r6.f1852v
            float r7 = r7 - r4
            int r0 = r0.f9238b
            float r1 = r1 * r1
            float r7 = r7 * r7
            float r7 = r7 + r1
            int r0 = r0 * r0
            float r0 = (float) r0
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L54
            android.view.View r7 = r6.g()
            if (r7 == 0) goto L54
            int r7 = r6.j(r7)
            r0 = 2
            if (r7 != r0) goto L55
        L54:
            r2 = r3
        L55:
            r6.e(r2)
            goto L67
        L59:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.f1851u = r0
            r6.f1852v = r7
            r6.f1848r = r2
        L67:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void r(View view, float f7) {
        float f8 = ((d) view.getLayoutParams()).f9406b;
        float width = view.getWidth();
        int i7 = ((int) (width * f7)) - ((int) (f8 * width));
        if (!b(3, view)) {
            i7 = -i7;
        }
        view.offsetLeftAndRight(i7);
        v(view, f7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        super.requestDisallowInterceptTouchEvent(z6);
        if (z6) {
            e(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.f1842l) {
            return;
        }
        super.requestLayout();
    }

    public final void s(View view) {
        if (!p(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        d dVar = (d) view.getLayoutParams();
        if (this.f1843m) {
            dVar.f9406b = 1.0f;
            dVar.f9408d = 1;
            x(view, true);
            w(view);
        } else {
            dVar.f9408d |= 2;
            if (b(3, view)) {
                this.f1837g.r(view, 0, view.getTop());
            } else {
                this.f1838h.r(view, getWidth() - view.getWidth(), view.getTop());
            }
        }
        invalidate();
    }

    public void setChildInsets(Object obj, boolean z6) {
        this.B = obj;
        this.C = z6;
        setWillNotDraw(!z6 && getBackground() == null);
        requestLayout();
    }

    public void setDrawerElevation(float f7) {
        this.f1832b = f7;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (p(childAt)) {
                float f8 = this.f1832b;
                int i8 = d1.OVER_SCROLL_ALWAYS;
                r0.s(childAt, f8);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(c cVar) {
        ArrayList arrayList;
        c cVar2 = this.f1849s;
        if (cVar2 != null && (arrayList = this.f1850t) != null) {
            arrayList.remove(cVar2);
        }
        if (cVar != null) {
            a(cVar);
        }
        this.f1849s = cVar;
    }

    public void setDrawerLockMode(int i7) {
        setDrawerLockMode(i7, 3);
        setDrawerLockMode(i7, 5);
    }

    public void setDrawerLockMode(int i7, int i8) {
        View f7;
        int i9 = d1.OVER_SCROLL_ALWAYS;
        int absoluteGravity = Gravity.getAbsoluteGravity(i8, m0.d(this));
        if (i8 == 3) {
            this.f1844n = i7;
        } else if (i8 == 5) {
            this.f1845o = i7;
        } else if (i8 == 8388611) {
            this.f1846p = i7;
        } else if (i8 == 8388613) {
            this.f1847q = i7;
        }
        if (i7 != 0) {
            (absoluteGravity == 3 ? this.f1837g : this.f1838h).a();
        }
        if (i7 != 1) {
            if (i7 == 2 && (f7 = f(absoluteGravity)) != null) {
                s(f7);
                return;
            }
            return;
        }
        View f8 = f(absoluteGravity);
        if (f8 != null) {
            c(f8, true);
        }
    }

    public void setDrawerLockMode(int i7, View view) {
        if (p(view)) {
            setDrawerLockMode(i7, ((d) view.getLayoutParams()).f9405a);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer with appropriate layout_gravity");
    }

    public void setDrawerShadow(int i7, int i8) {
        Context context = getContext();
        Object obj = g.f6423a;
        setDrawerShadow(g0.c.b(context, i7), i8);
    }

    public void setDrawerShadow(Drawable drawable, int i7) {
        if (O) {
            return;
        }
        if ((i7 & 8388611) == 8388611) {
            this.D = drawable;
        } else if ((i7 & 8388613) == 8388613) {
            this.E = drawable;
        } else if ((i7 & 3) == 3) {
            this.F = drawable;
        } else if ((i7 & 5) != 5) {
            return;
        } else {
            this.G = drawable;
        }
        u();
        invalidate();
    }

    public void setDrawerTitle(int i7, CharSequence charSequence) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, m0.d(this));
        if (absoluteGravity == 3) {
            this.f1856z = charSequence;
        } else if (absoluteGravity == 5) {
            this.A = charSequence;
        }
    }

    public void setScrimColor(int i7) {
        this.f1834d = i7;
        invalidate();
    }

    public void setStatusBarBackground(int i7) {
        Drawable drawable;
        if (i7 != 0) {
            Context context = getContext();
            Object obj = g.f6423a;
            drawable = g0.c.b(context, i7);
        } else {
            drawable = null;
        }
        this.f1853w = drawable;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.f1853w = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i7) {
        this.f1853w = new ColorDrawable(i7);
        invalidate();
    }

    public final void t() {
        View f7 = f(8388611);
        if (f7 != null) {
            s(f7);
        } else {
            throw new IllegalArgumentException("No drawer view found with gravity " + l(8388611));
        }
    }

    public final void u() {
        Drawable drawable;
        Drawable drawable2;
        if (O) {
            return;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int d7 = m0.d(this);
        if (d7 == 0) {
            Drawable drawable3 = this.D;
            if (drawable3 != null) {
                if (j0.a.d(drawable3)) {
                    j0.c.b(drawable3, d7);
                }
                drawable = this.D;
            }
            drawable = this.F;
        } else {
            Drawable drawable4 = this.E;
            if (drawable4 != null) {
                if (j0.a.d(drawable4)) {
                    j0.c.b(drawable4, d7);
                }
                drawable = this.E;
            }
            drawable = this.F;
        }
        this.f1854x = drawable;
        int d8 = m0.d(this);
        if (d8 == 0) {
            Drawable drawable5 = this.E;
            if (drawable5 != null) {
                if (j0.a.d(drawable5)) {
                    j0.c.b(drawable5, d8);
                }
                drawable2 = this.E;
            }
            drawable2 = this.G;
        } else {
            Drawable drawable6 = this.D;
            if (drawable6 != null) {
                if (j0.a.d(drawable6)) {
                    j0.c.b(drawable6, d8);
                }
                drawable2 = this.D;
            }
            drawable2 = this.G;
        }
        this.f1855y = drawable2;
    }

    public final void v(View view, float f7) {
        d dVar = (d) view.getLayoutParams();
        if (f7 == dVar.f9406b) {
            return;
        }
        dVar.f9406b = f7;
        ArrayList arrayList = this.f1850t;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((c) this.f1850t.get(size)).c(f7);
            }
        }
    }

    public final void w(View view) {
        q0.g gVar = q0.g.ACTION_DISMISS;
        d1.l(gVar.a(), view);
        d1.i(0, view);
        if (!o(view) || j(view) == 2) {
            return;
        }
        d1.m(view, gVar, null, this.K);
    }

    public final void x(View view, boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if ((z6 || p(childAt)) && !(z6 && childAt == view)) {
                int i8 = d1.OVER_SCROLL_ALWAYS;
                l0.s(childAt, 4);
            } else {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                l0.s(childAt, 1);
            }
        }
    }

    public final void y(int i7, View view) {
        int i8;
        View rootView;
        int i9 = this.f1837g.f9237a;
        int i10 = this.f1838h.f9237a;
        if (i9 == 1 || i10 == 1) {
            i8 = 1;
        } else {
            i8 = 2;
            if (i9 != 2 && i10 != 2) {
                i8 = 0;
            }
        }
        if (view != null && i7 == 0) {
            float f7 = ((d) view.getLayoutParams()).f9406b;
            if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
                d dVar = (d) view.getLayoutParams();
                if ((dVar.f9408d & 1) == 1) {
                    dVar.f9408d = 0;
                    ArrayList arrayList = this.f1850t;
                    if (arrayList != null) {
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            ((c) this.f1850t.get(size)).d(view);
                        }
                    }
                    x(view, false);
                    w(view);
                    if (hasWindowFocus() && (rootView = getRootView()) != null) {
                        rootView.sendAccessibilityEvent(32);
                    }
                }
            } else if (f7 == 1.0f) {
                d dVar2 = (d) view.getLayoutParams();
                if ((dVar2.f9408d & 1) == 0) {
                    dVar2.f9408d = 1;
                    ArrayList arrayList2 = this.f1850t;
                    if (arrayList2 != null) {
                        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                            ((c) this.f1850t.get(size2)).a(view);
                        }
                    }
                    x(view, true);
                    w(view);
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (i8 != this.f1841k) {
            this.f1841k = i8;
            ArrayList arrayList3 = this.f1850t;
            if (arrayList3 != null) {
                for (int size3 = arrayList3.size() - 1; size3 >= 0; size3--) {
                    ((c) this.f1850t.get(size3)).b();
                }
            }
        }
    }
}
