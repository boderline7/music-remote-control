package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class e2 implements k.g0 {
    public static final Method A;
    public static final Method B;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: a, reason: collision with root package name */
    public final Context f1212a;

    /* renamed from: b, reason: collision with root package name */
    public ListAdapter f1213b;

    /* renamed from: c, reason: collision with root package name */
    public s1 f1214c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1215d;

    /* renamed from: e, reason: collision with root package name */
    public int f1216e;

    /* renamed from: f, reason: collision with root package name */
    public int f1217f;

    /* renamed from: g, reason: collision with root package name */
    public int f1218g;

    /* renamed from: h, reason: collision with root package name */
    public final int f1219h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1220i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1221j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f1222k;

    /* renamed from: l, reason: collision with root package name */
    public int f1223l;

    /* renamed from: m, reason: collision with root package name */
    public final int f1224m;

    /* renamed from: n, reason: collision with root package name */
    public b2 f1225n;

    /* renamed from: o, reason: collision with root package name */
    public View f1226o;

    /* renamed from: p, reason: collision with root package name */
    public AdapterView.OnItemClickListener f1227p;

    /* renamed from: q, reason: collision with root package name */
    public AdapterView.OnItemSelectedListener f1228q;

    /* renamed from: r, reason: collision with root package name */
    public final x1 f1229r;

    /* renamed from: s, reason: collision with root package name */
    public final d2 f1230s;

    /* renamed from: t, reason: collision with root package name */
    public final c2 f1231t;

    /* renamed from: u, reason: collision with root package name */
    public final x1 f1232u;

    /* renamed from: v, reason: collision with root package name */
    public final Handler f1233v;

    /* renamed from: w, reason: collision with root package name */
    public final Rect f1234w;

    /* renamed from: x, reason: collision with root package name */
    public Rect f1235x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f1236y;

    /* renamed from: z, reason: collision with root package name */
    public final d0 f1237z;

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                A = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                B = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
    }

    public e2(Context context) {
        this(context, null, f.a.listPopupWindowStyle, 0);
    }

    public e2(Context context, AttributeSet attributeSet, int i7, int i8) {
        int resourceId;
        this.f1215d = -2;
        this.f1216e = -2;
        this.f1219h = 1002;
        this.f1223l = 0;
        this.f1224m = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1229r = new x1(this, 2);
        this.f1230s = new d2(this);
        this.f1231t = new c2(this);
        this.f1232u = new x1(this, 1);
        this.f1234w = new Rect();
        this.f1212a = context;
        this.f1233v = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ListPopupWindow, i7, i8);
        this.f1217f = obtainStyledAttributes.getDimensionPixelOffset(f.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(f.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f1218g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1220i = true;
        }
        obtainStyledAttributes.recycle();
        d0 d0Var = new d0(context, attributeSet, i7, i8);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f.j.PopupWindow, i7, i8);
        if (obtainStyledAttributes2.hasValue(f.j.PopupWindow_overlapAnchor)) {
            androidx.core.widget.p.c(d0Var, obtainStyledAttributes2.getBoolean(f.j.PopupWindow_overlapAnchor, false));
        }
        int i9 = f.j.PopupWindow_android_popupBackground;
        d0Var.setBackgroundDrawable((!obtainStyledAttributes2.hasValue(i9) || (resourceId = obtainStyledAttributes2.getResourceId(i9, 0)) == 0) ? obtainStyledAttributes2.getDrawable(i9) : l6.f.A(context, resourceId));
        obtainStyledAttributes2.recycle();
        this.f1237z = d0Var;
        d0Var.setInputMethodMode(1);
    }

    public s1 a(Context context, boolean z6) {
        return new s1(context, z6);
    }

    @Override // k.g0
    public final boolean b() {
        return this.f1237z.isShowing();
    }

    public final void c(int i7) {
        this.f1217f = i7;
    }

    @Override // k.g0
    public final void dismiss() {
        d0 d0Var = this.f1237z;
        d0Var.dismiss();
        d0Var.setContentView(null);
        this.f1214c = null;
        this.f1233v.removeCallbacks(this.f1229r);
    }

    public final int e() {
        return this.f1217f;
    }

    @Override // k.g0
    public final void g() {
        int i7;
        int paddingBottom;
        s1 s1Var;
        s1 s1Var2 = this.f1214c;
        d0 d0Var = this.f1237z;
        Context context = this.f1212a;
        if (s1Var2 == null) {
            s1 a7 = a(context, !this.f1236y);
            this.f1214c = a7;
            a7.setAdapter(this.f1213b);
            this.f1214c.setOnItemClickListener(this.f1227p);
            this.f1214c.setFocusable(true);
            this.f1214c.setFocusableInTouchMode(true);
            this.f1214c.setOnItemSelectedListener(new y1(0, this));
            this.f1214c.setOnScrollListener(this.f1231t);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f1228q;
            if (onItemSelectedListener != null) {
                this.f1214c.setOnItemSelectedListener(onItemSelectedListener);
            }
            d0Var.setContentView(this.f1214c);
        }
        Drawable background = d0Var.getBackground();
        Rect rect = this.f1234w;
        if (background != null) {
            background.getPadding(rect);
            int i8 = rect.top;
            i7 = rect.bottom + i8;
            if (!this.f1220i) {
                this.f1218g = -i8;
            }
        } else {
            rect.setEmpty();
            i7 = 0;
        }
        int a8 = z1.a(d0Var, this.f1226o, this.f1218g, d0Var.getInputMethodMode() == 2);
        int i9 = this.f1215d;
        if (i9 == -1) {
            paddingBottom = a8 + i7;
        } else {
            int i10 = this.f1216e;
            int a9 = this.f1214c.a(i10 != -2 ? i10 != -1 ? View.MeasureSpec.makeMeasureSpec(i10, v.c.EXACTLY) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), v.c.EXACTLY) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), a8);
            paddingBottom = a9 + (a9 > 0 ? this.f1214c.getPaddingBottom() + this.f1214c.getPaddingTop() + i7 : 0);
        }
        boolean z6 = this.f1237z.getInputMethodMode() == 2;
        androidx.core.widget.p.d(d0Var, this.f1219h);
        if (d0Var.isShowing()) {
            View view = this.f1226o;
            int i11 = p0.d1.OVER_SCROLL_ALWAYS;
            if (p0.o0.b(view)) {
                int i12 = this.f1216e;
                if (i12 == -1) {
                    i12 = -1;
                } else if (i12 == -2) {
                    i12 = this.f1226o.getWidth();
                }
                if (i9 == -1) {
                    i9 = z6 ? paddingBottom : -1;
                    if (z6) {
                        d0Var.setWidth(this.f1216e == -1 ? -1 : 0);
                        d0Var.setHeight(0);
                    } else {
                        d0Var.setWidth(this.f1216e == -1 ? -1 : 0);
                        d0Var.setHeight(-1);
                    }
                } else if (i9 == -2) {
                    i9 = paddingBottom;
                }
                d0Var.setOutsideTouchable(true);
                View view2 = this.f1226o;
                int i13 = this.f1217f;
                int i14 = this.f1218g;
                if (i12 < 0) {
                    i12 = -1;
                }
                d0Var.update(view2, i13, i14, i12, i9 < 0 ? -1 : i9);
                return;
            }
            return;
        }
        int i15 = this.f1216e;
        if (i15 == -1) {
            i15 = -1;
        } else if (i15 == -2) {
            i15 = this.f1226o.getWidth();
        }
        if (i9 == -1) {
            i9 = -1;
        } else if (i9 == -2) {
            i9 = paddingBottom;
        }
        d0Var.setWidth(i15);
        d0Var.setHeight(i9);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = A;
            if (method != null) {
                try {
                    method.invoke(d0Var, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        } else {
            a2.b(d0Var, true);
        }
        d0Var.setOutsideTouchable(true);
        d0Var.setTouchInterceptor(this.f1230s);
        if (this.f1222k) {
            androidx.core.widget.p.c(d0Var, this.f1221j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = B;
            if (method2 != null) {
                try {
                    method2.invoke(d0Var, this.f1235x);
                } catch (Exception unused2) {
                }
            }
        } else {
            a2.a(d0Var, this.f1235x);
        }
        androidx.core.widget.o.a(d0Var, this.f1226o, this.f1217f, this.f1218g, this.f1223l);
        this.f1214c.setSelection(-1);
        if ((!this.f1236y || this.f1214c.isInTouchMode()) && (s1Var = this.f1214c) != null) {
            s1Var.setListSelectionHidden(true);
            s1Var.requestLayout();
        }
        if (this.f1236y) {
            return;
        }
        this.f1233v.post(this.f1232u);
    }

    public final int h() {
        if (this.f1220i) {
            return this.f1218g;
        }
        return 0;
    }

    public final Drawable i() {
        return this.f1237z.getBackground();
    }

    @Override // k.g0
    public final s1 l() {
        return this.f1214c;
    }

    public final void n(Drawable drawable) {
        this.f1237z.setBackgroundDrawable(drawable);
    }

    public final void o(int i7) {
        this.f1218g = i7;
        this.f1220i = true;
    }

    public void p(ListAdapter listAdapter) {
        b2 b2Var = this.f1225n;
        if (b2Var == null) {
            this.f1225n = new b2(0, this);
        } else {
            ListAdapter listAdapter2 = this.f1213b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(b2Var);
            }
        }
        this.f1213b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1225n);
        }
        s1 s1Var = this.f1214c;
        if (s1Var != null) {
            s1Var.setAdapter(this.f1213b);
        }
    }

    public final void r(int i7) {
        Drawable background = this.f1237z.getBackground();
        if (background == null) {
            this.f1216e = i7;
            return;
        }
        Rect rect = this.f1234w;
        background.getPadding(rect);
        this.f1216e = rect.left + rect.right + i7;
    }
}
