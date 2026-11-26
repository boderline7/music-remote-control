package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.customview.view.AbsSavedState;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class RecyclerView extends ViewGroup implements p0.w {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_TYPE = -1;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;

    /* renamed from: w0, reason: collision with root package name */
    public static final int[] f2294w0 = {R.attr.nestedScrollingEnabled};

    /* renamed from: x0, reason: collision with root package name */
    public static final Class[] f2295x0;

    /* renamed from: y0, reason: collision with root package name */
    public static final k0 f2296y0;
    public ArrayList A;
    public boolean B;
    public boolean C;
    public int D;
    public int E;
    public l1 F;
    public EdgeEffect G;
    public EdgeEffect H;
    public EdgeEffect I;
    public EdgeEffect J;
    public m1 K;
    public int L;
    public int M;
    public VelocityTracker N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public u1 T;
    public final int U;
    public final int V;
    public final float W;

    /* renamed from: a, reason: collision with root package name */
    public final b2 f2297a;

    /* renamed from: a0, reason: collision with root package name */
    public final float f2298a0;

    /* renamed from: b, reason: collision with root package name */
    public final z1 f2299b;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f2300b0;

    /* renamed from: c, reason: collision with root package name */
    public SavedState f2301c;

    /* renamed from: c0, reason: collision with root package name */
    public final i2 f2302c0;

    /* renamed from: d, reason: collision with root package name */
    public final b f2303d;

    /* renamed from: d0, reason: collision with root package name */
    public g0 f2304d0;

    /* renamed from: e, reason: collision with root package name */
    public final j f2305e;

    /* renamed from: e0, reason: collision with root package name */
    public final e0 f2306e0;

    /* renamed from: f, reason: collision with root package name */
    public final q2 f2307f;

    /* renamed from: f0, reason: collision with root package name */
    public final g2 f2308f0;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2309g;

    /* renamed from: g0, reason: collision with root package name */
    public w1 f2310g0;

    /* renamed from: h, reason: collision with root package name */
    public final g1 f2311h;

    /* renamed from: h0, reason: collision with root package name */
    public ArrayList f2312h0;

    /* renamed from: i, reason: collision with root package name */
    public final Rect f2313i;

    /* renamed from: i0, reason: collision with root package name */
    public boolean f2314i0;

    /* renamed from: j, reason: collision with root package name */
    public final Rect f2315j;

    /* renamed from: j0, reason: collision with root package name */
    public boolean f2316j0;

    /* renamed from: k, reason: collision with root package name */
    public final RectF f2317k;

    /* renamed from: k0, reason: collision with root package name */
    public final n1 f2318k0;

    /* renamed from: l, reason: collision with root package name */
    public h1 f2319l;

    /* renamed from: l0, reason: collision with root package name */
    public boolean f2320l0;

    /* renamed from: m, reason: collision with root package name */
    public r1 f2321m;

    /* renamed from: m0, reason: collision with root package name */
    public l2 f2322m0;

    /* renamed from: n, reason: collision with root package name */
    public final ArrayList f2323n;

    /* renamed from: n0, reason: collision with root package name */
    public k1 f2324n0;

    /* renamed from: o, reason: collision with root package name */
    public final ArrayList f2325o;

    /* renamed from: o0, reason: collision with root package name */
    public final int[] f2326o0;

    /* renamed from: p, reason: collision with root package name */
    public v1 f2327p;

    /* renamed from: p0, reason: collision with root package name */
    public p0.x f2328p0;

    /* renamed from: q, reason: collision with root package name */
    public boolean f2329q;

    /* renamed from: q0, reason: collision with root package name */
    public final int[] f2330q0;

    /* renamed from: r, reason: collision with root package name */
    public boolean f2331r;

    /* renamed from: r0, reason: collision with root package name */
    public final int[] f2332r0;

    /* renamed from: s, reason: collision with root package name */
    public boolean f2333s;

    /* renamed from: s0, reason: collision with root package name */
    public final int[] f2334s0;

    /* renamed from: t, reason: collision with root package name */
    public int f2335t;

    /* renamed from: t0, reason: collision with root package name */
    public final ArrayList f2336t0;

    /* renamed from: u, reason: collision with root package name */
    public boolean f2337u;

    /* renamed from: u0, reason: collision with root package name */
    public final g1 f2338u0;

    /* renamed from: v, reason: collision with root package name */
    public boolean f2339v;

    /* renamed from: v0, reason: collision with root package name */
    public final n1 f2340v0;

    /* renamed from: w, reason: collision with root package name */
    public boolean f2341w;

    /* renamed from: x, reason: collision with root package name */
    public int f2342x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f2343y;

    /* renamed from: z, reason: collision with root package name */
    public final AccessibilityManager f2344z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c2();

        /* renamed from: b, reason: collision with root package name */
        public Parcelable f2345b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2345b = parcel.readParcelable(classLoader == null ? r1.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeParcelable(this.f2345b, 0);
        }
    }

    static {
        Class cls = Integer.TYPE;
        f2295x0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        f2296y0 = new k0(2);
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i1.a.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        TypedArray typedArray;
        char c7;
        Object[] objArr;
        Constructor constructor;
        Object[] objArr2;
        int i8 = 0;
        this.f2297a = new b2(i8, this);
        this.f2299b = new z1(this);
        this.f2307f = new q2(1);
        this.f2311h = new g1(i8, this);
        this.f2313i = new Rect();
        this.f2315j = new Rect();
        this.f2317k = new RectF();
        this.f2323n = new ArrayList();
        this.f2325o = new ArrayList();
        this.f2335t = 0;
        this.B = false;
        this.C = false;
        this.D = 0;
        this.E = 0;
        this.F = new l1();
        q qVar = new q();
        qVar.f2538a = null;
        qVar.f2539b = new ArrayList();
        qVar.f2540c = 120L;
        qVar.f2541d = 120L;
        qVar.f2542e = 250L;
        qVar.f2543f = 250L;
        qVar.f2600g = true;
        qVar.f2601h = new ArrayList();
        qVar.f2602i = new ArrayList();
        qVar.f2603j = new ArrayList();
        qVar.f2604k = new ArrayList();
        qVar.f2605l = new ArrayList();
        qVar.f2606m = new ArrayList();
        qVar.f2607n = new ArrayList();
        qVar.f2608o = new ArrayList();
        qVar.f2609p = new ArrayList();
        qVar.f2610q = new ArrayList();
        qVar.f2611r = new ArrayList();
        this.K = qVar;
        this.L = 0;
        this.M = -1;
        this.W = Float.MIN_VALUE;
        this.f2298a0 = Float.MIN_VALUE;
        this.f2300b0 = true;
        this.f2302c0 = new i2(this);
        this.f2306e0 = new e0();
        g2 g2Var = new g2();
        g2Var.f2465a = -1;
        g2Var.f2466b = 0;
        g2Var.f2467c = 0;
        g2Var.f2468d = 1;
        g2Var.f2469e = 0;
        g2Var.f2470f = false;
        g2Var.f2471g = false;
        g2Var.f2472h = false;
        g2Var.f2473i = false;
        g2Var.f2474j = false;
        g2Var.f2475k = false;
        this.f2308f0 = g2Var;
        this.f2314i0 = false;
        this.f2316j0 = false;
        n1 n1Var = new n1(this);
        this.f2318k0 = n1Var;
        this.f2320l0 = false;
        this.f2326o0 = new int[2];
        this.f2330q0 = new int[2];
        this.f2332r0 = new int[2];
        this.f2334s0 = new int[2];
        this.f2336t0 = new ArrayList();
        this.f2338u0 = new g1(1, this);
        this.f2340v0 = new n1(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.S = viewConfiguration.getScaledTouchSlop();
        this.W = p0.e1.a(viewConfiguration);
        this.f2298a0 = p0.e1.b(viewConfiguration);
        this.U = viewConfiguration.getScaledMinimumFlingVelocity();
        this.V = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.K.f2538a = n1Var;
        this.f2303d = new b(new n1(this));
        this.f2305e = new j(new n1(this));
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        if (p0.u0.b(this) == 0) {
            p0.u0.l(this, 8);
        }
        if (p0.l0.c(this) == 0) {
            p0.l0.s(this, 1);
        }
        this.f2344z = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new l2(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i1.d.RecyclerView, i7, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, i1.d.RecyclerView, attributeSet, obtainStyledAttributes, i7, 0);
        }
        String string = obtainStyledAttributes.getString(i1.d.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(i1.d.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.f2309g = obtainStyledAttributes.getBoolean(i1.d.RecyclerView_android_clipToPadding, true);
        if (obtainStyledAttributes.getBoolean(i1.d.RecyclerView_fastScrollEnabled, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) obtainStyledAttributes.getDrawable(i1.d.RecyclerView_fastScrollVerticalThumbDrawable);
            Drawable drawable = obtainStyledAttributes.getDrawable(i1.d.RecyclerView_fastScrollVerticalTrackDrawable);
            StateListDrawable stateListDrawable2 = (StateListDrawable) obtainStyledAttributes.getDrawable(i1.d.RecyclerView_fastScrollHorizontalThumbDrawable);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(i1.d.RecyclerView_fastScrollHorizontalTrackDrawable);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + z());
            }
            Resources resources = getContext().getResources();
            typedArray = obtainStyledAttributes;
            c7 = 2;
            objArr = null;
            new d0(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(i1.b.fastscroll_default_thickness), resources.getDimensionPixelSize(i1.b.fastscroll_minimum_range), resources.getDimensionPixelOffset(i1.b.fastscroll_margin));
        } else {
            typedArray = obtainStyledAttributes;
            c7 = 2;
            objArr = null;
        }
        typedArray.recycle();
        if (string != null) {
            String trim = string.trim();
            if (!trim.isEmpty()) {
                if (trim.charAt(0) == '.') {
                    trim = context.getPackageName() + trim;
                } else if (!trim.contains(".")) {
                    trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                }
                String str = trim;
                try {
                    Class<? extends U> asSubclass = Class.forName(str, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(r1.class);
                    try {
                        constructor = asSubclass.getConstructor(f2295x0);
                        objArr2 = new Object[4];
                        objArr2[0] = context;
                        objArr2[1] = attributeSet;
                        objArr2[c7] = Integer.valueOf(i7);
                        objArr2[3] = 0;
                    } catch (NoSuchMethodException e7) {
                        try {
                            constructor = asSubclass.getConstructor(new Class[0]);
                            objArr2 = objArr;
                        } catch (NoSuchMethodException e8) {
                            e8.initCause(e7);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e8);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((r1) constructor.newInstance(objArr2));
                } catch (ClassCastException e9) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e9);
                } catch (ClassNotFoundException e10) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e10);
                } catch (IllegalAccessException e11) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e11);
                } catch (InstantiationException e12) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e12);
                } catch (InvocationTargetException e13) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e13);
                }
            }
        }
        int i10 = Build.VERSION.SDK_INT;
        int[] iArr = f2294w0;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i7, 0);
        if (i10 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes2, i7, 0);
        }
        boolean z6 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z6);
    }

    public static RecyclerView E(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            RecyclerView E = E(viewGroup.getChildAt(i7));
            if (E != null) {
                return E;
            }
        }
        return null;
    }

    public static int I(View view) {
        j2 K = K(view);
        if (K != null) {
            return K.getAdapterPosition();
        }
        return -1;
    }

    public static j2 K(View view) {
        if (view == null) {
            return null;
        }
        return ((s1) view.getLayoutParams()).f2652a;
    }

    public static void L(View view, Rect rect) {
        s1 s1Var = (s1) view.getLayoutParams();
        Rect rect2 = s1Var.f2653b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) s1Var).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) s1Var).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin);
    }

    private p0.x getScrollingChildHelper() {
        if (this.f2328p0 == null) {
            this.f2328p0 = new p0.x(this);
        }
        return this.f2328p0;
    }

    public static void j(j2 j2Var) {
        WeakReference<RecyclerView> weakReference = j2Var.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == j2Var.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            j2Var.mNestedRecyclerView = null;
        }
    }

    public final void A(g2 g2Var) {
        if (getScrollState() != 2) {
            g2Var.getClass();
            return;
        }
        OverScroller overScroller = this.f2302c0.f2492c;
        overScroller.getFinalX();
        overScroller.getCurrX();
        g2Var.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View B(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            goto L18
        L17:
            r3 = 0
        L18:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.B(android.view.View):android.view.View");
    }

    public final boolean C(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        ArrayList arrayList = this.f2325o;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            v1 v1Var = (v1) arrayList.get(i7);
            if (v1Var.a(motionEvent) && action != 3) {
                this.f2327p = v1Var;
                return true;
            }
        }
        return false;
    }

    public final void D(int[] iArr) {
        int e7 = this.f2305e.e();
        if (e7 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i7 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i8 = Integer.MIN_VALUE;
        for (int i9 = 0; i9 < e7; i9++) {
            j2 K = K(this.f2305e.d(i9));
            if (!K.shouldIgnore()) {
                int layoutPosition = K.getLayoutPosition();
                if (layoutPosition < i7) {
                    i7 = layoutPosition;
                }
                if (layoutPosition > i8) {
                    i8 = layoutPosition;
                }
            }
        }
        iArr[0] = i7;
        iArr[1] = i8;
    }

    public final j2 F(int i7) {
        j2 j2Var = null;
        if (this.B) {
            return null;
        }
        int h7 = this.f2305e.h();
        for (int i8 = 0; i8 < h7; i8++) {
            j2 K = K(this.f2305e.g(i8));
            if (K != null && !K.isRemoved() && G(K) == i7) {
                if (!this.f2305e.k(K.itemView)) {
                    return K;
                }
                j2Var = K;
            }
        }
        return j2Var;
    }

    public final int G(j2 j2Var) {
        if (j2Var.hasAnyOfTheFlags(524) || !j2Var.isBound()) {
            return -1;
        }
        b bVar = this.f2303d;
        int i7 = j2Var.mPosition;
        ArrayList arrayList = bVar.f2377b;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            a aVar = (a) arrayList.get(i8);
            int i9 = aVar.f2371a;
            if (i9 != 1) {
                if (i9 == 2) {
                    int i10 = aVar.f2372b;
                    if (i10 <= i7) {
                        int i11 = aVar.f2374d;
                        if (i10 + i11 > i7) {
                            return -1;
                        }
                        i7 -= i11;
                    } else {
                        continue;
                    }
                } else if (i9 == 8) {
                    int i12 = aVar.f2372b;
                    if (i12 == i7) {
                        i7 = aVar.f2374d;
                    } else {
                        if (i12 < i7) {
                            i7--;
                        }
                        if (aVar.f2374d <= i7) {
                            i7++;
                        }
                    }
                }
            } else if (aVar.f2372b <= i7) {
                i7 += aVar.f2374d;
            }
        }
        return i7;
    }

    public final long H(j2 j2Var) {
        return this.f2319l.hasStableIds() ? j2Var.getItemId() : j2Var.mPosition;
    }

    public final j2 J(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return K(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect M(View view) {
        s1 s1Var = (s1) view.getLayoutParams();
        boolean z6 = s1Var.f2654c;
        Rect rect = s1Var.f2653b;
        if (!z6) {
            return rect;
        }
        g2 g2Var = this.f2308f0;
        if (g2Var.f2471g && (s1Var.f2652a.isUpdated() || s1Var.f2652a.isInvalid())) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.f2323n;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Rect rect2 = this.f2313i;
            rect2.set(0, 0, 0, 0);
            ((o1) arrayList.get(i7)).getItemOffsets(rect2, view, this, g2Var);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        s1Var.f2654c = false;
        return rect;
    }

    public final boolean N() {
        return !this.f2333s || this.B || this.f2303d.g();
    }

    public final boolean O() {
        return this.D > 0;
    }

    public final void P(int i7) {
        if (this.f2321m == null) {
            return;
        }
        setScrollState(2);
        this.f2321m.s0(i7);
        awakenScrollBars();
    }

    public final void Q() {
        int h7 = this.f2305e.h();
        for (int i7 = 0; i7 < h7; i7++) {
            ((s1) this.f2305e.g(i7).getLayoutParams()).f2654c = true;
        }
        ArrayList arrayList = this.f2299b.f2720c;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            s1 s1Var = (s1) ((j2) arrayList.get(i8)).itemView.getLayoutParams();
            if (s1Var != null) {
                s1Var.f2654c = true;
            }
        }
    }

    public final void R(int i7, int i8, boolean z6) {
        int i9 = i7 + i8;
        int h7 = this.f2305e.h();
        for (int i10 = 0; i10 < h7; i10++) {
            j2 K = K(this.f2305e.g(i10));
            if (K != null && !K.shouldIgnore()) {
                int i11 = K.mPosition;
                g2 g2Var = this.f2308f0;
                if (i11 >= i9) {
                    K.offsetPosition(-i8, z6);
                    g2Var.f2470f = true;
                } else if (i11 >= i7) {
                    K.flagRemovedAndOffsetPosition(i7 - 1, -i8, z6);
                    g2Var.f2470f = true;
                }
            }
        }
        z1 z1Var = this.f2299b;
        ArrayList arrayList = z1Var.f2720c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            j2 j2Var = (j2) arrayList.get(size);
            if (j2Var != null) {
                int i12 = j2Var.mPosition;
                if (i12 >= i9) {
                    j2Var.offsetPosition(-i8, z6);
                } else if (i12 >= i7) {
                    j2Var.addFlags(8);
                    z1Var.e(size);
                }
            }
        }
        requestLayout();
    }

    public final void S() {
        this.D++;
    }

    public final void T(boolean z6) {
        int i7;
        AccessibilityManager accessibilityManager;
        int i8 = this.D - 1;
        this.D = i8;
        if (i8 < 1) {
            this.D = 0;
            if (z6) {
                int i9 = this.f2342x;
                this.f2342x = 0;
                if (i9 != 0 && (accessibilityManager = this.f2344z) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    obtain.setEventType(2048);
                    q0.b.b(obtain, i9);
                    sendAccessibilityEventUnchecked(obtain);
                }
                ArrayList arrayList = this.f2336t0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    j2 j2Var = (j2) arrayList.get(size);
                    if (j2Var.itemView.getParent() == this && !j2Var.shouldIgnore() && (i7 = j2Var.mPendingAccessibilityState) != -1) {
                        View view = j2Var.itemView;
                        int i10 = p0.d1.OVER_SCROLL_ALWAYS;
                        p0.l0.s(view, i7);
                        j2Var.mPendingAccessibilityState = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void U(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.M) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.M = motionEvent.getPointerId(i7);
            int x6 = (int) (motionEvent.getX(i7) + 0.5f);
            this.Q = x6;
            this.O = x6;
            int y6 = (int) (motionEvent.getY(i7) + 0.5f);
            this.R = y6;
            this.P = y6;
        }
    }

    public final void V() {
        if (this.f2320l0 || !this.f2329q) {
            return;
        }
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.m(this, this.f2338u0);
        this.f2320l0 = true;
    }

    public final void W() {
        boolean z6;
        boolean z7 = false;
        if (this.B) {
            b bVar = this.f2303d;
            bVar.l(bVar.f2377b);
            bVar.l(bVar.f2378c);
            bVar.f2381f = 0;
            if (this.C) {
                this.f2321m.b0();
            }
        }
        if (this.K == null || !this.f2321m.E0()) {
            this.f2303d.c();
        } else {
            this.f2303d.j();
        }
        boolean z8 = this.f2314i0 || this.f2316j0;
        boolean z9 = this.f2333s && this.K != null && ((z6 = this.B) || z8 || this.f2321m.f2626f) && (!z6 || this.f2319l.hasStableIds());
        g2 g2Var = this.f2308f0;
        g2Var.f2474j = z9;
        if (z9 && z8 && !this.B && this.K != null && this.f2321m.E0()) {
            z7 = true;
        }
        g2Var.f2475k = z7;
    }

    public final void X(boolean z6) {
        this.C = z6 | this.C;
        this.B = true;
        int h7 = this.f2305e.h();
        for (int i7 = 0; i7 < h7; i7++) {
            j2 K = K(this.f2305e.g(i7));
            if (K != null && !K.shouldIgnore()) {
                K.addFlags(6);
            }
        }
        Q();
        z1 z1Var = this.f2299b;
        ArrayList arrayList = z1Var.f2720c;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            j2 j2Var = (j2) arrayList.get(i8);
            if (j2Var != null) {
                j2Var.addFlags(6);
                j2Var.addChangePayload(null);
            }
        }
        h1 h1Var = z1Var.f2725h.f2319l;
        if (h1Var == null || !h1Var.hasStableIds()) {
            z1Var.d();
        }
    }

    public final void Y(j2 j2Var, androidx.media.a aVar) {
        j2Var.setFlags(0, 8192);
        boolean z6 = this.f2308f0.f2472h;
        q2 q2Var = this.f2307f;
        if (z6 && j2Var.isUpdated() && !j2Var.isRemoved() && !j2Var.shouldIgnore()) {
            ((q.e) q2Var.f2618c).f(H(j2Var), j2Var);
        }
        q2Var.c(j2Var, aVar);
    }

    public final void Z(o1 o1Var) {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            r1Var.c("Cannot remove item decoration during a scroll  or layout");
        }
        ArrayList arrayList = this.f2323n;
        arrayList.remove(o1Var);
        if (arrayList.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        Q();
        requestLayout();
    }

    public final void a0(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.f2313i;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof s1) {
            s1 s1Var = (s1) layoutParams;
            if (!s1Var.f2654c) {
                int i7 = rect.left;
                Rect rect2 = s1Var.f2653b;
                rect.left = i7 - rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.f2321m.p0(this, view, this.f2313i, !this.f2333s, view2 == null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i7, int i8) {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            r1Var.getClass();
        }
        super.addFocusables(arrayList, i7, i8);
    }

    public final void b0() {
        VelocityTracker velocityTracker = this.N;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean z6 = false;
        i0(0);
        EdgeEffect edgeEffect = this.G;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z6 = this.G.isFinished();
        }
        EdgeEffect edgeEffect2 = this.H;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z6 |= this.H.isFinished();
        }
        EdgeEffect edgeEffect3 = this.I;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z6 |= this.I.isFinished();
        }
        EdgeEffect edgeEffect4 = this.J;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z6 |= this.J.isFinished();
        }
        if (z6) {
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.k(this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0113, code lost:
    
        if (r3 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean c0(int r22, int r23, android.view.MotionEvent r24) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.c0(int, int, android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof s1) && this.f2321m.g((s1) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.e()) {
            return this.f2321m.k(this.f2308f0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.e()) {
            return this.f2321m.l(this.f2308f0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.e()) {
            return this.f2321m.m(this.f2308f0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.f()) {
            return this.f2321m.n(this.f2308f0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.f()) {
            return this.f2321m.o(this.f2308f0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        r1 r1Var = this.f2321m;
        if (r1Var != null && r1Var.f()) {
            return this.f2321m.p(this.f2308f0);
        }
        return 0;
    }

    public final void d0(int i7, int i8, int[] iArr) {
        j2 j2Var;
        g0();
        S();
        int i9 = l0.k.f7276a;
        l0.j.a("RV Scroll");
        g2 g2Var = this.f2308f0;
        A(g2Var);
        z1 z1Var = this.f2299b;
        int r02 = i7 != 0 ? this.f2321m.r0(i7, z1Var, g2Var) : 0;
        int t02 = i8 != 0 ? this.f2321m.t0(i8, z1Var, g2Var) : 0;
        l0.j.b();
        int e7 = this.f2305e.e();
        for (int i10 = 0; i10 < e7; i10++) {
            View d7 = this.f2305e.d(i10);
            j2 J = J(d7);
            if (J != null && (j2Var = J.mShadowingHolder) != null) {
                View view = j2Var.itemView;
                int left = d7.getLeft();
                int top = d7.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        T(true);
        h0(false);
        if (iArr != null) {
            iArr[0] = r02;
            iArr[1] = t02;
        }
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return getScrollingChildHelper().a(f7, f8, z6);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f7, float f8) {
        return getScrollingChildHelper().b(f7, f8);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i7, i8, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return getScrollingChildHelper().e(i7, i8, i9, i10, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z6;
        super.draw(canvas);
        ArrayList arrayList = this.f2323n;
        int size = arrayList.size();
        boolean z7 = false;
        for (int i7 = 0; i7 < size; i7++) {
            ((o1) arrayList.get(i7)).onDrawOver(canvas, this, this.f2308f0);
        }
        EdgeEffect edgeEffect = this.G;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z6 = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.f2309g ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, CropImageView.DEFAULT_ASPECT_RATIO);
            EdgeEffect edgeEffect2 = this.G;
            z6 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.H;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.f2309g) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.H;
            z6 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.I;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f2309g ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.I;
            z6 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.J;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f2309g) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.J;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z7 = true;
            }
            z6 |= z7;
            canvas.restoreToCount(save4);
        }
        if ((z6 || this.K == null || arrayList.size() <= 0 || !this.K.f()) && !z6) {
            return;
        }
        int i8 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.k(this);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j7) {
        return super.drawChild(canvas, view, j7);
    }

    public final void e0(int i7) {
        if (this.f2339v) {
            return;
        }
        j0();
        r1 r1Var = this.f2321m;
        if (r1Var == null) {
            return;
        }
        r1Var.s0(i7);
        awakenScrollBars();
    }

    public final void f(j2 j2Var) {
        View view = j2Var.itemView;
        boolean z6 = view.getParent() == this;
        this.f2299b.j(J(view));
        if (j2Var.isTmpDetached()) {
            this.f2305e.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z6) {
            this.f2305e.a(view, -1, true);
            return;
        }
        j jVar = this.f2305e;
        int indexOfChild = ((n1) jVar.f2498b).f2551a.indexOfChild(view);
        if (indexOfChild >= 0) {
            ((i) jVar.f2499c).i(indexOfChild);
            jVar.i(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void f0(int i7, int i8, boolean z6) {
        r1 r1Var = this.f2321m;
        if (r1Var == null || this.f2339v) {
            return;
        }
        if (!r1Var.e()) {
            i7 = 0;
        }
        if (!this.f2321m.f()) {
            i8 = 0;
        }
        if (i7 == 0 && i8 == 0) {
            return;
        }
        if (z6) {
            int i9 = i7 != 0 ? 1 : 0;
            if (i8 != 0) {
                i9 |= 2;
            }
            getScrollingChildHelper().h(i9, 1);
        }
        this.f2302c0.b(i7, i8, Integer.MIN_VALUE, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r18, int r19) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(o1 o1Var) {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            r1Var.c("Cannot add item decoration during a scroll  or layout");
        }
        ArrayList arrayList = this.f2323n;
        if (arrayList.isEmpty()) {
            setWillNotDraw(false);
        }
        arrayList.add(o1Var);
        Q();
        requestLayout();
    }

    public final void g0() {
        int i7 = this.f2335t + 1;
        this.f2335t = i7;
        if (i7 != 1 || this.f2339v) {
            return;
        }
        this.f2337u = false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            return r1Var.s();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            return r1Var.t(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            return r1Var.u(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + z());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public h1 getAdapter() {
        return this.f2319l;
    }

    @Override // android.view.View
    public int getBaseline() {
        r1 r1Var = this.f2321m;
        if (r1Var == null) {
            return super.getBaseline();
        }
        r1Var.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i8) {
        k1 k1Var = this.f2324n0;
        if (k1Var == null) {
            return super.getChildDrawingOrder(i7, i8);
        }
        p0 p0Var = (p0) ((b1) k1Var).f2385a;
        View view = p0Var.f2593w;
        if (view == null) {
            return i8;
        }
        int i9 = p0Var.f2594x;
        if (i9 == -1) {
            i9 = p0Var.f2588r.indexOfChild(view);
            p0Var.f2594x = i9;
        }
        return i8 == i7 + (-1) ? i9 : i8 < i9 ? i8 : i8 + 1;
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f2309g;
    }

    public l2 getCompatAccessibilityDelegate() {
        return this.f2322m0;
    }

    public l1 getEdgeEffectFactory() {
        return this.F;
    }

    public m1 getItemAnimator() {
        return this.K;
    }

    public int getItemDecorationCount() {
        return this.f2323n.size();
    }

    public r1 getLayoutManager() {
        return this.f2321m;
    }

    public int getMaxFlingVelocity() {
        return this.V;
    }

    public int getMinFlingVelocity() {
        return this.U;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public u1 getOnFlingListener() {
        return this.T;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f2300b0;
    }

    public y1 getRecycledViewPool() {
        return this.f2299b.c();
    }

    public int getScrollState() {
        return this.L;
    }

    public final void h(w1 w1Var) {
        if (this.f2312h0 == null) {
            this.f2312h0 = new ArrayList();
        }
        this.f2312h0.add(w1Var);
    }

    public final void h0(boolean z6) {
        if (this.f2335t < 1) {
            this.f2335t = 1;
        }
        if (!z6 && !this.f2339v) {
            this.f2337u = false;
        }
        if (this.f2335t == 1) {
            if (z6 && this.f2337u && !this.f2339v && this.f2321m != null && this.f2319l != null) {
                p();
            }
            if (!this.f2339v) {
                this.f2337u = false;
            }
        }
        this.f2335t--;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().g(0);
    }

    public final void i(String str) {
        if (O()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + z());
        }
        if (this.E > 0) {
            new IllegalStateException(s5.h0.FRAGMENT_ENCODE_SET + z());
        }
    }

    public final void i0(int i7) {
        getScrollingChildHelper().i(i7);
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.f2329q;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.f2339v;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f8201d;
    }

    public final void j0() {
        f2 f2Var;
        setScrollState(0);
        i2 i2Var = this.f2302c0;
        i2Var.f2496g.removeCallbacks(i2Var);
        i2Var.f2492c.abortAnimation();
        r1 r1Var = this.f2321m;
        if (r1Var == null || (f2Var = r1Var.f2625e) == null) {
            return;
        }
        f2Var.stop();
    }

    public final void k() {
        int h7 = this.f2305e.h();
        for (int i7 = 0; i7 < h7; i7++) {
            j2 K = K(this.f2305e.g(i7));
            if (!K.shouldIgnore()) {
                K.clearOldPosition();
            }
        }
        z1 z1Var = this.f2299b;
        ArrayList arrayList = z1Var.f2720c;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            ((j2) arrayList.get(i8)).clearOldPosition();
        }
        ArrayList arrayList2 = z1Var.f2718a;
        int size2 = arrayList2.size();
        for (int i9 = 0; i9 < size2; i9++) {
            ((j2) arrayList2.get(i9)).clearOldPosition();
        }
        ArrayList arrayList3 = z1Var.f2719b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i10 = 0; i10 < size3; i10++) {
                ((j2) z1Var.f2719b.get(i10)).clearOldPosition();
            }
        }
    }

    public final void l(int i7, int i8) {
        boolean z6;
        EdgeEffect edgeEffect = this.G;
        if (edgeEffect == null || edgeEffect.isFinished() || i7 <= 0) {
            z6 = false;
        } else {
            this.G.onRelease();
            z6 = this.G.isFinished();
        }
        EdgeEffect edgeEffect2 = this.I;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i7 < 0) {
            this.I.onRelease();
            z6 |= this.I.isFinished();
        }
        EdgeEffect edgeEffect3 = this.H;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i8 > 0) {
            this.H.onRelease();
            z6 |= this.H.isFinished();
        }
        EdgeEffect edgeEffect4 = this.J;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i8 < 0) {
            this.J.onRelease();
            z6 |= this.J.isFinished();
        }
        if (z6) {
            int i9 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.k(this);
        }
    }

    public final void m() {
        if (!this.f2333s || this.B) {
            int i7 = l0.k.f7276a;
            l0.j.a("RV FullInvalidate");
            p();
            l0.j.b();
            return;
        }
        if (this.f2303d.g()) {
            b bVar = this.f2303d;
            int i8 = bVar.f2381f;
            if ((i8 & 4) == 0 || (i8 & 11) != 0) {
                if (bVar.g()) {
                    int i9 = l0.k.f7276a;
                    l0.j.a("RV FullInvalidate");
                    p();
                    l0.j.b();
                    return;
                }
                return;
            }
            int i10 = l0.k.f7276a;
            l0.j.a("RV PartialInvalidate");
            g0();
            S();
            this.f2303d.j();
            if (!this.f2337u) {
                int e7 = this.f2305e.e();
                int i11 = 0;
                while (true) {
                    if (i11 < e7) {
                        j2 K = K(this.f2305e.d(i11));
                        if (K != null && !K.shouldIgnore() && K.isUpdated()) {
                            p();
                            break;
                        }
                        i11++;
                    } else {
                        this.f2303d.b();
                        break;
                    }
                }
            }
            h0(true);
            T(true);
            l0.j.b();
        }
    }

    public final void n(int i7, int i8) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        setMeasuredDimension(r1.h(i7, paddingRight, p0.l0.e(this)), r1.h(i8, getPaddingBottom() + getPaddingTop(), p0.l0.d(this)));
    }

    public final void o(View view) {
        j2 K = K(view);
        h1 h1Var = this.f2319l;
        if (h1Var != null && K != null) {
            h1Var.onViewDetachedFromWindow(K);
        }
        ArrayList arrayList = this.A;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((t1) this.A.get(size)).d(view);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005a, code lost:
    
        if (r1 >= 30.0f) goto L20;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.D = r0
            r1 = 1
            r5.f2329q = r1
            boolean r2 = r5.f2333s
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.f2333s = r2
            androidx.recyclerview.widget.r1 r2 = r5.f2321m
            if (r2 == 0) goto L21
            r2.f2627g = r1
            r2.T(r5)
        L21:
            r5.f2320l0 = r0
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.g0.f2457e
            java.lang.Object r1 = r0.get()
            androidx.recyclerview.widget.g0 r1 = (androidx.recyclerview.widget.g0) r1
            r5.f2304d0 = r1
            if (r1 != 0) goto L6b
            androidx.recyclerview.widget.g0 r1 = new androidx.recyclerview.widget.g0
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.f2459a = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.f2462d = r2
            r5.f2304d0 = r1
            int r1 = p0.d1.OVER_SCROLL_ALWAYS
            android.view.Display r1 = p0.m0.b(r5)
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L5d
            if (r1 == 0) goto L5d
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L5d
            goto L5f
        L5d:
            r1 = 1114636288(0x42700000, float:60.0)
        L5f:
            androidx.recyclerview.widget.g0 r2 = r5.f2304d0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.f2461c = r3
            r0.set(r2)
        L6b:
            androidx.recyclerview.widget.g0 r0 = r5.f2304d0
            java.util.ArrayList r0 = r0.f2459a
            r0.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m1 m1Var = this.K;
        if (m1Var != null) {
            m1Var.e();
        }
        j0();
        this.f2329q = false;
        r1 r1Var = this.f2321m;
        if (r1Var != null) {
            r1Var.f2627g = false;
            r1Var.U(this);
        }
        this.f2336t0.clear();
        removeCallbacks(this.f2338u0);
        this.f2307f.getClass();
        while (v2.f2695d.a() != null) {
        }
        g0 g0Var = this.f2304d0;
        if (g0Var != null) {
            g0Var.f2459a.remove(this);
            this.f2304d0 = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.f2323n;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((o1) arrayList.get(i7)).onDraw(canvas, this, this.f2308f0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.r1 r0 = r5.f2321m
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.f2339v
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3e
            androidx.recyclerview.widget.r1 r0 = r5.f2321m
            boolean r0 = r0.f()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.r1 r3 = r5.f2321m
            boolean r3 = r3.e()
            if (r3 == 0) goto L3c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L64
        L3c:
            r3 = r2
            goto L64
        L3e:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.r1 r3 = r5.f2321m
            boolean r3 = r3.f()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.r1 r3 = r5.f2321m
            boolean r3 = r3.e()
            if (r3 == 0) goto L62
            r3 = r0
            r0 = r2
            goto L64
        L62:
            r0 = r2
            r3 = r0
        L64:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6c
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6c:
            float r2 = r5.W
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.f2298a0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.c0(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b7, code lost:
    
        if (r0 != false) goto L45;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int i11 = l0.k.f7276a;
        l0.j.a("RV OnLayout");
        p();
        l0.j.b();
        this.f2333s = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        r1 r1Var = this.f2321m;
        if (r1Var == null) {
            n(i7, i8);
            return;
        }
        boolean O = r1Var.O();
        g2 g2Var = this.f2308f0;
        if (O) {
            int mode = View.MeasureSpec.getMode(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            this.f2321m.f2622b.n(i7, i8);
            if ((mode == 1073741824 && mode2 == 1073741824) || this.f2319l == null) {
                return;
            }
            if (g2Var.f2468d == 1) {
                q();
            }
            this.f2321m.v0(i7, i8);
            g2Var.f2473i = true;
            r();
            this.f2321m.x0(i7, i8);
            if (this.f2321m.A0()) {
                this.f2321m.v0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), v.c.EXACTLY));
                g2Var.f2473i = true;
                r();
                this.f2321m.x0(i7, i8);
                return;
            }
            return;
        }
        if (this.f2331r) {
            this.f2321m.f2622b.n(i7, i8);
            return;
        }
        if (this.f2343y) {
            g0();
            S();
            W();
            T(true);
            if (g2Var.f2475k) {
                g2Var.f2471g = true;
            } else {
                this.f2303d.c();
                g2Var.f2471g = false;
            }
            this.f2343y = false;
            h0(false);
        } else if (g2Var.f2475k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        h1 h1Var = this.f2319l;
        if (h1Var != null) {
            g2Var.f2469e = h1Var.getItemCount();
        } else {
            g2Var.f2469e = 0;
        }
        g0();
        this.f2321m.f2622b.n(i7, i8);
        h0(false);
        g2Var.f2471g = false;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (O()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i7, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.f2301c = savedState;
        super.onRestoreInstanceState(savedState.f1829a);
        r1 r1Var = this.f2321m;
        if (r1Var == null || (parcelable2 = this.f2301c.f2345b) == null) {
            return;
        }
        r1Var.h0(parcelable2);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.f2301c;
        if (savedState2 != null) {
            savedState.f2345b = savedState2.f2345b;
        } else {
            r1 r1Var = this.f2321m;
            if (r1Var != null) {
                savedState.f2345b = r1Var.i0();
            } else {
                savedState.f2345b = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 == i9 && i8 == i10) {
            return;
        }
        this.J = null;
        this.H = null;
        this.I = null;
        this.G = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:196:0x02dd, code lost:
    
        if (r0 < r8) goto L146;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e9  */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v6 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r24) {
        /*
            Method dump skipped, instructions count: 928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x02eb, code lost:
    
        if (r18.f2305e.k(getFocusedChild()) == false) goto L212;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:176:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0360  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p() {
        /*
            Method dump skipped, instructions count: 959
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        View B;
        v2 v2Var;
        g2 g2Var = this.f2308f0;
        g2Var.a(1);
        A(g2Var);
        g2Var.f2473i = false;
        g0();
        q2 q2Var = this.f2307f;
        q2Var.d();
        S();
        W();
        View focusedChild = (this.f2300b0 && hasFocus() && this.f2319l != null) ? getFocusedChild() : null;
        j2 J = (focusedChild == null || (B = B(focusedChild)) == null) ? null : J(B);
        if (J == null) {
            g2Var.f2477m = -1L;
            g2Var.f2476l = -1;
            g2Var.f2478n = -1;
        } else {
            g2Var.f2477m = this.f2319l.hasStableIds() ? J.getItemId() : -1L;
            g2Var.f2476l = this.B ? -1 : J.isRemoved() ? J.mOldPosition : J.getAdapterPosition();
            View view = J.itemView;
            int id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    id = view.getId();
                }
            }
            g2Var.f2478n = id;
        }
        g2Var.f2472h = g2Var.f2474j && this.f2316j0;
        this.f2316j0 = false;
        this.f2314i0 = false;
        g2Var.f2471g = g2Var.f2475k;
        g2Var.f2469e = this.f2319l.getItemCount();
        D(this.f2326o0);
        if (g2Var.f2474j) {
            int e7 = this.f2305e.e();
            for (int i7 = 0; i7 < e7; i7++) {
                j2 K = K(this.f2305e.d(i7));
                if (!K.shouldIgnore() && (!K.isInvalid() || this.f2319l.hasStableIds())) {
                    m1 m1Var = this.K;
                    m1.b(K);
                    K.getUnmodifiedPayloads();
                    m1Var.getClass();
                    androidx.media.a aVar = new androidx.media.a();
                    aVar.a(K);
                    q2Var.c(K, aVar);
                    if (g2Var.f2472h && K.isUpdated() && !K.isRemoved() && !K.shouldIgnore() && !K.isInvalid()) {
                        ((q.e) q2Var.f2618c).f(H(K), K);
                    }
                }
            }
        }
        if (g2Var.f2475k) {
            int h7 = this.f2305e.h();
            for (int i8 = 0; i8 < h7; i8++) {
                j2 K2 = K(this.f2305e.g(i8));
                if (!K2.shouldIgnore()) {
                    K2.saveOldPosition();
                }
            }
            boolean z6 = g2Var.f2470f;
            g2Var.f2470f = false;
            this.f2321m.f0(this.f2299b, g2Var);
            g2Var.f2470f = z6;
            for (int i9 = 0; i9 < this.f2305e.e(); i9++) {
                j2 K3 = K(this.f2305e.d(i9));
                if (!K3.shouldIgnore() && ((v2Var = (v2) ((q.k) q2Var.f2617b).getOrDefault(K3, null)) == null || (v2Var.f2696a & 4) == 0)) {
                    m1.b(K3);
                    boolean hasAnyOfTheFlags = K3.hasAnyOfTheFlags(8192);
                    m1 m1Var2 = this.K;
                    K3.getUnmodifiedPayloads();
                    m1Var2.getClass();
                    androidx.media.a aVar2 = new androidx.media.a();
                    aVar2.a(K3);
                    if (hasAnyOfTheFlags) {
                        Y(K3, aVar2);
                    } else {
                        v2 v2Var2 = (v2) ((q.k) q2Var.f2617b).getOrDefault(K3, null);
                        if (v2Var2 == null) {
                            v2Var2 = v2.a();
                            ((q.k) q2Var.f2617b).put(K3, v2Var2);
                        }
                        v2Var2.f2696a |= 2;
                        v2Var2.f2697b = aVar2;
                    }
                }
            }
            k();
        } else {
            k();
        }
        T(true);
        h0(false);
        g2Var.f2468d = 2;
    }

    public final void r() {
        g0();
        S();
        g2 g2Var = this.f2308f0;
        g2Var.a(6);
        this.f2303d.c();
        g2Var.f2469e = this.f2319l.getItemCount();
        g2Var.f2467c = 0;
        g2Var.f2471g = false;
        this.f2321m.f0(this.f2299b, g2Var);
        g2Var.f2470f = false;
        this.f2301c = null;
        g2Var.f2474j = g2Var.f2474j && this.K != null;
        g2Var.f2468d = 4;
        T(true);
        h0(false);
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z6) {
        j2 K = K(view);
        if (K != null) {
            if (K.isTmpDetached()) {
                K.clearTmpDetachFlag();
            } else if (!K.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + K + z());
            }
        }
        view.clearAnimation();
        o(view);
        super.removeDetachedView(view, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        f2 f2Var = this.f2321m.f2625e;
        if ((f2Var == null || !f2Var.isRunning()) && !O() && view2 != null) {
            a0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        return this.f2321m.p0(this, view, rect, z6, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        ArrayList arrayList = this.f2325o;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((v1) arrayList.get(i7)).e(z6);
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.f2335t != 0 || this.f2339v) {
            this.f2337u = true;
        } else {
            super.requestLayout();
        }
    }

    public final boolean s(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        return getScrollingChildHelper().c(i7, i8, iArr, iArr2, i9);
    }

    @Override // android.view.View
    public final void scrollBy(int i7, int i8) {
        r1 r1Var = this.f2321m;
        if (r1Var == null || this.f2339v) {
            return;
        }
        boolean e7 = r1Var.e();
        boolean f7 = this.f2321m.f();
        if (e7 || f7) {
            if (!e7) {
                i7 = 0;
            }
            if (!f7) {
                i8 = 0;
            }
            c0(i7, i8, null);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i7, int i8) {
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!O()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int a7 = accessibilityEvent != null ? q0.b.a(accessibilityEvent) : 0;
            this.f2342x |= a7 != 0 ? a7 : 0;
        }
    }

    public void setAccessibilityDelegateCompat(l2 l2Var) {
        this.f2322m0 = l2Var;
        p0.d1.o(this, l2Var);
    }

    public void setAdapter(h1 h1Var) {
        setLayoutFrozen(false);
        h1 h1Var2 = this.f2319l;
        b2 b2Var = this.f2297a;
        if (h1Var2 != null) {
            h1Var2.unregisterAdapterDataObserver(b2Var);
            this.f2319l.onDetachedFromRecyclerView(this);
        }
        m1 m1Var = this.K;
        if (m1Var != null) {
            m1Var.e();
        }
        r1 r1Var = this.f2321m;
        z1 z1Var = this.f2299b;
        if (r1Var != null) {
            r1Var.l0(z1Var);
            this.f2321m.m0(z1Var);
        }
        z1Var.f2718a.clear();
        z1Var.d();
        b bVar = this.f2303d;
        bVar.l(bVar.f2377b);
        bVar.l(bVar.f2378c);
        bVar.f2381f = 0;
        h1 h1Var3 = this.f2319l;
        this.f2319l = h1Var;
        if (h1Var != null) {
            h1Var.registerAdapterDataObserver(b2Var);
            h1Var.onAttachedToRecyclerView(this);
        }
        h1 h1Var4 = this.f2319l;
        z1Var.f2718a.clear();
        z1Var.d();
        y1 c7 = z1Var.c();
        if (h1Var3 != null) {
            c7.f2715b--;
        }
        if (c7.f2715b == 0) {
            int i7 = 0;
            while (true) {
                SparseArray sparseArray = c7.f2714a;
                if (i7 >= sparseArray.size()) {
                    break;
                }
                ((x1) sparseArray.valueAt(i7)).f2705a.clear();
                i7++;
            }
        }
        if (h1Var4 != null) {
            c7.f2715b++;
        }
        this.f2308f0.f2470f = true;
        X(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(k1 k1Var) {
        if (k1Var == this.f2324n0) {
            return;
        }
        this.f2324n0 = k1Var;
        setChildrenDrawingOrderEnabled(k1Var != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z6) {
        if (z6 != this.f2309g) {
            this.J = null;
            this.H = null;
            this.I = null;
            this.G = null;
        }
        this.f2309g = z6;
        super.setClipToPadding(z6);
        if (this.f2333s) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(l1 l1Var) {
        l1Var.getClass();
        this.F = l1Var;
        this.J = null;
        this.H = null;
        this.I = null;
        this.G = null;
    }

    public void setHasFixedSize(boolean z6) {
        this.f2331r = z6;
    }

    public void setItemAnimator(m1 m1Var) {
        m1 m1Var2 = this.K;
        if (m1Var2 != null) {
            m1Var2.e();
            this.K.f2538a = null;
        }
        this.K = m1Var;
        if (m1Var != null) {
            m1Var.f2538a = this.f2318k0;
        }
    }

    public void setItemViewCacheSize(int i7) {
        z1 z1Var = this.f2299b;
        z1Var.f2722e = i7;
        z1Var.k();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z6) {
        suppressLayout(z6);
    }

    public void setLayoutManager(r1 r1Var) {
        Object obj;
        if (r1Var == this.f2321m) {
            return;
        }
        j0();
        r1 r1Var2 = this.f2321m;
        z1 z1Var = this.f2299b;
        if (r1Var2 != null) {
            m1 m1Var = this.K;
            if (m1Var != null) {
                m1Var.e();
            }
            this.f2321m.l0(z1Var);
            this.f2321m.m0(z1Var);
            z1Var.f2718a.clear();
            z1Var.d();
            if (this.f2329q) {
                r1 r1Var3 = this.f2321m;
                r1Var3.f2627g = false;
                r1Var3.U(this);
            }
            this.f2321m.y0(null);
            this.f2321m = null;
        } else {
            z1Var.f2718a.clear();
            z1Var.d();
        }
        j jVar = this.f2305e;
        ((i) jVar.f2499c).h();
        List list = (List) jVar.f2500d;
        int size = list.size() - 1;
        while (true) {
            obj = jVar.f2498b;
            if (size < 0) {
                break;
            }
            n1 n1Var = (n1) obj;
            View view = (View) list.get(size);
            n1Var.getClass();
            j2 K = K(view);
            if (K != null) {
                K.onLeftHiddenState(n1Var.f2551a);
            }
            list.remove(size);
            size--;
        }
        RecyclerView recyclerView = ((n1) obj).f2551a;
        int childCount = recyclerView.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = recyclerView.getChildAt(i7);
            recyclerView.o(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.f2321m = r1Var;
        if (r1Var != null) {
            if (r1Var.f2622b != null) {
                throw new IllegalArgumentException("LayoutManager " + r1Var + " is already attached to a RecyclerView:" + r1Var.f2622b.z());
            }
            r1Var.y0(this);
            if (this.f2329q) {
                r1 r1Var4 = this.f2321m;
                r1Var4.f2627g = true;
                r1Var4.T(this);
            }
        }
        z1Var.k();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z6) {
        p0.x scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.f8201d) {
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.r0.z(scrollingChildHelper.f8200c);
        }
        scrollingChildHelper.f8201d = z6;
    }

    public void setOnFlingListener(u1 u1Var) {
        this.T = u1Var;
    }

    @Deprecated
    public void setOnScrollListener(w1 w1Var) {
        this.f2310g0 = w1Var;
    }

    public void setPreserveFocusAfterLayout(boolean z6) {
        this.f2300b0 = z6;
    }

    public void setRecycledViewPool(y1 y1Var) {
        z1 z1Var = this.f2299b;
        if (z1Var.f2724g != null) {
            r1.f2715b--;
        }
        z1Var.f2724g = y1Var;
        if (y1Var == null || z1Var.f2725h.getAdapter() == null) {
            return;
        }
        z1Var.f2724g.f2715b++;
    }

    public void setRecyclerListener(a2 a2Var) {
    }

    public void setScrollState(int i7) {
        f2 f2Var;
        if (i7 == this.L) {
            return;
        }
        this.L = i7;
        if (i7 != 2) {
            i2 i2Var = this.f2302c0;
            i2Var.f2496g.removeCallbacks(i2Var);
            i2Var.f2492c.abortAnimation();
            r1 r1Var = this.f2321m;
            if (r1Var != null && (f2Var = r1Var.f2625e) != null) {
                f2Var.stop();
            }
        }
        r1 r1Var2 = this.f2321m;
        if (r1Var2 != null) {
            r1Var2.j0(i7);
        }
        w1 w1Var = this.f2310g0;
        if (w1Var != null) {
            w1Var.a(i7, this);
        }
        ArrayList arrayList = this.f2312h0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((w1) this.f2312h0.get(size)).a(i7, this);
            }
        }
    }

    public void setScrollingTouchSlop(int i7) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i7 != 1) {
            this.S = viewConfiguration.getScaledTouchSlop();
        } else {
            this.S = viewConfiguration.getScaledPagingTouchSlop();
        }
    }

    public void setViewCacheExtension(h2 h2Var) {
        this.f2299b.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i7) {
        return getScrollingChildHelper().h(i7, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().i(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z6) {
        if (z6 != this.f2339v) {
            i("Do not suppressLayout in layout or scroll");
            if (z6) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0));
                this.f2339v = true;
                this.f2341w = true;
                j0();
                return;
            }
            this.f2339v = false;
            if (this.f2337u && this.f2321m != null && this.f2319l != null) {
                requestLayout();
            }
            this.f2337u = false;
        }
    }

    public final void t(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        getScrollingChildHelper().e(i7, i8, i9, i10, iArr, i11, iArr2);
    }

    public final void u(int i7, int i8) {
        this.E++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i7, scrollY - i8);
        w1 w1Var = this.f2310g0;
        if (w1Var != null) {
            w1Var.b(this, i7, i8);
        }
        ArrayList arrayList = this.f2312h0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((w1) this.f2312h0.get(size)).b(this, i7, i8);
            }
        }
        this.E--;
    }

    public final void v() {
        if (this.J != null) {
            return;
        }
        this.F.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.J = edgeEffect;
        if (this.f2309g) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void w() {
        if (this.G != null) {
            return;
        }
        this.F.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.G = edgeEffect;
        if (this.f2309g) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.I != null) {
            return;
        }
        this.F.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.I = edgeEffect;
        if (this.f2309g) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void y() {
        if (this.H != null) {
            return;
        }
        this.F.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.H = edgeEffect;
        if (this.f2309g) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String z() {
        return " " + super.toString() + ", adapter:" + this.f2319l + ", layout:" + this.f2321m + ", context:" + getContext();
    }
}
