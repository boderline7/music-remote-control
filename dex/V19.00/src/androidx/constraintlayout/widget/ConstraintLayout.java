package androidx.constraintlayout.widget;

import a0.f;
import a0.i;
import a0.p;
import a0.t;
import a0.v;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.u;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import k0.a;
import p0.d1;
import s.d;
import u.e;
import u.h;
import u.j;
import u.n;
import u.q;
import v.r;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ConstraintLayout extends ViewGroup {
    public static final int DESIGN_INFO_ID = 0;
    public static final String VERSION = "ConstraintLayout-2.1.4";

    /* renamed from: r, reason: collision with root package name */
    public static v f1685r;

    /* renamed from: a, reason: collision with root package name */
    public final SparseArray f1686a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f1687b;

    /* renamed from: c, reason: collision with root package name */
    public final j f1688c;

    /* renamed from: d, reason: collision with root package name */
    public int f1689d;

    /* renamed from: e, reason: collision with root package name */
    public int f1690e;

    /* renamed from: f, reason: collision with root package name */
    public int f1691f;

    /* renamed from: g, reason: collision with root package name */
    public int f1692g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1693h;

    /* renamed from: i, reason: collision with root package name */
    public int f1694i;

    /* renamed from: j, reason: collision with root package name */
    public p f1695j;

    /* renamed from: k, reason: collision with root package name */
    public i f1696k;

    /* renamed from: l, reason: collision with root package name */
    public int f1697l;

    /* renamed from: m, reason: collision with root package name */
    public HashMap f1698m;

    /* renamed from: n, reason: collision with root package name */
    public final SparseArray f1699n;

    /* renamed from: o, reason: collision with root package name */
    public final r f1700o;

    /* renamed from: p, reason: collision with root package name */
    public int f1701p;

    /* renamed from: q, reason: collision with root package name */
    public int f1702q;

    public ConstraintLayout(Context context) {
        super(context);
        this.f1686a = new SparseArray();
        this.f1687b = new ArrayList(4);
        this.f1688c = new j();
        this.f1689d = 0;
        this.f1690e = 0;
        this.f1691f = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1692g = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1693h = true;
        this.f1694i = q.OPTIMIZATION_STANDARD;
        this.f1695j = null;
        this.f1696k = null;
        this.f1697l = -1;
        this.f1698m = new HashMap();
        this.f1699n = new SparseArray();
        this.f1700o = new r(this, this);
        this.f1701p = 0;
        this.f1702q = 0;
        h(null, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1686a = new SparseArray();
        this.f1687b = new ArrayList(4);
        this.f1688c = new j();
        this.f1689d = 0;
        this.f1690e = 0;
        this.f1691f = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1692g = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1693h = true;
        this.f1694i = q.OPTIMIZATION_STANDARD;
        this.f1695j = null;
        this.f1696k = null;
        this.f1697l = -1;
        this.f1698m = new HashMap();
        this.f1699n = new SparseArray();
        this.f1700o = new r(this, this);
        this.f1701p = 0;
        this.f1702q = 0;
        h(attributeSet, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1686a = new SparseArray();
        this.f1687b = new ArrayList(4);
        this.f1688c = new j();
        this.f1689d = 0;
        this.f1690e = 0;
        this.f1691f = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1692g = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1693h = true;
        this.f1694i = q.OPTIMIZATION_STANDARD;
        this.f1695j = null;
        this.f1696k = null;
        this.f1697l = -1;
        this.f1698m = new HashMap();
        this.f1699n = new SparseArray();
        this.f1700o = new r(this, this);
        this.f1701p = 0;
        this.f1702q = 0;
        h(attributeSet, i7);
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int max2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return max2 > 0 ? max2 : max;
    }

    public static v getSharedValues() {
        if (f1685r == null) {
            v vVar = new v();
            new SparseIntArray();
            vVar.f184a = new HashMap();
            f1685r = vVar;
        }
        return f1685r;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x02cf -> B:78:0x02d0). Please report as a decompilation issue!!! */
    public final void a(boolean z6, View view, u.i iVar, f fVar, SparseArray sparseArray) {
        float f7;
        u.i iVar2;
        u.i iVar3;
        u.i iVar4;
        u.i iVar5;
        int i7;
        int i8;
        float f8;
        int i9;
        float f9;
        fVar.a();
        iVar.f9145i0 = view.getVisibility();
        if (fVar.f22f0) {
            iVar.F = true;
            iVar.f9145i0 = 8;
        }
        iVar.f9143h0 = view;
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).n(iVar, this.f1688c.A0);
        }
        int i10 = -1;
        if (fVar.f18d0) {
            n nVar = (n) iVar;
            int i11 = fVar.f38n0;
            int i12 = fVar.f40o0;
            float f10 = fVar.f42p0;
            if (f10 != -1.0f) {
                if (f10 > -1.0f) {
                    nVar.f9205v0 = f10;
                    nVar.f9206w0 = -1;
                    nVar.f9207x0 = -1;
                    return;
                }
                return;
            }
            if (i11 != -1) {
                if (i11 > -1) {
                    nVar.f9205v0 = -1.0f;
                    nVar.f9206w0 = i11;
                    nVar.f9207x0 = -1;
                    return;
                }
                return;
            }
            if (i12 == -1 || i12 <= -1) {
                return;
            }
            nVar.f9205v0 = -1.0f;
            nVar.f9206w0 = -1;
            nVar.f9207x0 = i12;
            return;
        }
        int i13 = fVar.f24g0;
        int i14 = fVar.f26h0;
        int i15 = fVar.f28i0;
        int i16 = fVar.f30j0;
        int i17 = fVar.f32k0;
        int i18 = fVar.f34l0;
        float f11 = fVar.f36m0;
        int i19 = fVar.f41p;
        if (i19 != -1) {
            u.i iVar6 = (u.i) sparseArray.get(i19);
            if (iVar6 != null) {
                float f12 = fVar.f45r;
                int i20 = fVar.f43q;
                e eVar = e.CENTER;
                f9 = 0.0f;
                iVar.x(eVar, iVar6, eVar, i20, 0);
                iVar.D = f12;
            } else {
                f9 = 0.0f;
            }
            f7 = f9;
        } else {
            if (i13 != -1) {
                u.i iVar7 = (u.i) sparseArray.get(i13);
                if (iVar7 != null) {
                    e eVar2 = e.LEFT;
                    f7 = 0.0f;
                    iVar.x(eVar2, iVar7, eVar2, ((ViewGroup.MarginLayoutParams) fVar).leftMargin, i17);
                } else {
                    f7 = 0.0f;
                }
            } else {
                f7 = 0.0f;
                if (i14 != -1 && (iVar2 = (u.i) sparseArray.get(i14)) != null) {
                    iVar.x(e.LEFT, iVar2, e.RIGHT, ((ViewGroup.MarginLayoutParams) fVar).leftMargin, i17);
                }
            }
            if (i15 != -1) {
                u.i iVar8 = (u.i) sparseArray.get(i15);
                if (iVar8 != null) {
                    iVar.x(e.RIGHT, iVar8, e.LEFT, ((ViewGroup.MarginLayoutParams) fVar).rightMargin, i18);
                }
            } else if (i16 != -1 && (iVar3 = (u.i) sparseArray.get(i16)) != null) {
                e eVar3 = e.RIGHT;
                iVar.x(eVar3, iVar3, eVar3, ((ViewGroup.MarginLayoutParams) fVar).rightMargin, i18);
            }
            int i21 = fVar.f27i;
            if (i21 != -1) {
                u.i iVar9 = (u.i) sparseArray.get(i21);
                if (iVar9 != null) {
                    e eVar4 = e.TOP;
                    iVar.x(eVar4, iVar9, eVar4, ((ViewGroup.MarginLayoutParams) fVar).topMargin, fVar.f51x);
                }
            } else {
                int i22 = fVar.f29j;
                if (i22 != -1 && (iVar4 = (u.i) sparseArray.get(i22)) != null) {
                    iVar.x(e.TOP, iVar4, e.BOTTOM, ((ViewGroup.MarginLayoutParams) fVar).topMargin, fVar.f51x);
                }
            }
            int i23 = fVar.f31k;
            if (i23 != -1) {
                u.i iVar10 = (u.i) sparseArray.get(i23);
                if (iVar10 != null) {
                    iVar.x(e.BOTTOM, iVar10, e.TOP, ((ViewGroup.MarginLayoutParams) fVar).bottomMargin, fVar.f53z);
                }
            } else {
                int i24 = fVar.f33l;
                if (i24 != -1 && (iVar5 = (u.i) sparseArray.get(i24)) != null) {
                    e eVar5 = e.BOTTOM;
                    iVar.x(eVar5, iVar5, eVar5, ((ViewGroup.MarginLayoutParams) fVar).bottomMargin, fVar.f53z);
                }
            }
            int i25 = fVar.f35m;
            if (i25 != -1) {
                o(iVar, fVar, sparseArray, i25, e.BASELINE);
            } else {
                int i26 = fVar.f37n;
                if (i26 != -1) {
                    o(iVar, fVar, sparseArray, i26, e.TOP);
                } else {
                    int i27 = fVar.f39o;
                    if (i27 != -1) {
                        o(iVar, fVar, sparseArray, i27, e.BOTTOM);
                    }
                }
            }
            if (f11 >= f7) {
                iVar.f9139f0 = f11;
            }
            float f13 = fVar.F;
            if (f13 >= f7) {
                iVar.f9141g0 = f13;
            }
        }
        if (z6 && ((i9 = fVar.T) != -1 || fVar.U != -1)) {
            int i28 = fVar.U;
            iVar.f9129a0 = i9;
            iVar.f9131b0 = i28;
        }
        if (fVar.f12a0) {
            iVar.O(h.FIXED);
            iVar.Q(((ViewGroup.MarginLayoutParams) fVar).width);
            if (((ViewGroup.MarginLayoutParams) fVar).width == -2) {
                iVar.O(h.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) fVar).width == -1) {
            if (fVar.W) {
                iVar.O(h.MATCH_CONSTRAINT);
            } else {
                iVar.O(h.MATCH_PARENT);
            }
            iVar.k(e.LEFT).f9122g = ((ViewGroup.MarginLayoutParams) fVar).leftMargin;
            iVar.k(e.RIGHT).f9122g = ((ViewGroup.MarginLayoutParams) fVar).rightMargin;
        } else {
            iVar.O(h.MATCH_CONSTRAINT);
            iVar.Q(0);
        }
        if (fVar.f14b0) {
            iVar.P(h.FIXED);
            iVar.N(((ViewGroup.MarginLayoutParams) fVar).height);
            if (((ViewGroup.MarginLayoutParams) fVar).height == -2) {
                iVar.P(h.WRAP_CONTENT);
            }
        } else if (((ViewGroup.MarginLayoutParams) fVar).height == -1) {
            if (fVar.X) {
                iVar.P(h.MATCH_CONSTRAINT);
            } else {
                iVar.P(h.MATCH_PARENT);
            }
            iVar.k(e.TOP).f9122g = ((ViewGroup.MarginLayoutParams) fVar).topMargin;
            iVar.k(e.BOTTOM).f9122g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
        } else {
            iVar.P(h.MATCH_CONSTRAINT);
            iVar.N(0);
        }
        String str = fVar.G;
        if (str == null || str.length() == 0) {
            iVar.Y = f7;
        } else {
            int length = str.length();
            int indexOf = str.indexOf(44);
            if (indexOf <= 0 || indexOf >= length - 1) {
                i7 = 1;
                i8 = 0;
            } else {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i10 = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i7 = 1;
                    i10 = 1;
                    i8 = indexOf + i7;
                }
                i7 = 1;
                i8 = indexOf + i7;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 < 0 || indexOf2 >= length - i7) {
                String substring2 = str.substring(i8);
                if (substring2.length() > 0) {
                    f8 = Float.parseFloat(substring2);
                }
                f8 = f7;
            } else {
                String substring3 = str.substring(i8, indexOf2);
                String substring4 = str.substring(indexOf2 + i7);
                if (substring3.length() > 0 && substring4.length() > 0) {
                    float parseFloat = Float.parseFloat(substring3);
                    float parseFloat2 = Float.parseFloat(substring4);
                    if (parseFloat > f7 && parseFloat2 > f7) {
                        f8 = i10 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                    }
                }
                f8 = f7;
            }
            if (f8 > f7) {
                iVar.Y = f8;
                iVar.Z = i10;
            }
        }
        float f14 = fVar.H;
        float[] fArr = iVar.f9157o0;
        fArr[0] = f14;
        fArr[1] = fVar.I;
        iVar.f9153m0 = fVar.J;
        iVar.f9155n0 = fVar.K;
        int i29 = fVar.Z;
        if (i29 >= 0 && i29 <= 3) {
            iVar.f9160q = i29;
        }
        int i30 = fVar.L;
        int i31 = fVar.N;
        int i32 = fVar.P;
        float f15 = fVar.R;
        iVar.f9162r = i30;
        iVar.f9168u = i31;
        if (i32 == Integer.MAX_VALUE) {
            i32 = 0;
        }
        iVar.f9170v = i32;
        iVar.f9171w = f15;
        if (f15 > f7 && f15 < 1.0f && i30 == 0) {
            iVar.f9162r = 2;
        }
        int i33 = fVar.M;
        int i34 = fVar.O;
        int i35 = fVar.Q;
        float f16 = fVar.S;
        iVar.f9164s = i33;
        iVar.f9172x = i34;
        iVar.f9173y = i35 != Integer.MAX_VALUE ? i35 : 0;
        iVar.f9174z = f16;
        if (f16 <= f7 || f16 >= 1.0f || i33 != 0) {
            return;
        }
        iVar.f9164s = 2;
    }

    public final View c(int i7) {
        return (View) this.f1686a.get(i7);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.f1687b;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                ((ConstraintHelper) arrayList.get(i7)).p(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i9 = (int) ((parseInt / 1080.0f) * width);
                        int i10 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(a.CATEGORY_MASK);
                        float f7 = i9;
                        float f8 = i10;
                        float f9 = i9 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f7, f8, f9, f8, paint);
                        float parseInt4 = i10 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f9, f8, f9, parseInt4, paint);
                        canvas.drawLine(f9, parseInt4, f7, parseInt4, paint);
                        canvas.drawLine(f7, parseInt4, f7, f8, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f7, f8, f9, parseInt4, paint);
                        canvas.drawLine(f7, parseInt4, f9, f8, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.f1693h = true;
        super.forceLayout();
    }

    public final u.i g(View view) {
        if (view == this) {
            return this.f1688c;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof f) {
            return ((f) view.getLayoutParams()).f44q0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof f) {
            return ((f) view.getLayoutParams()).f44q0;
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new f();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new f(layoutParams);
    }

    public int getMaxHeight() {
        return this.f1692g;
    }

    public int getMaxWidth() {
        return this.f1691f;
    }

    public int getMinHeight() {
        return this.f1690e;
    }

    public int getMinWidth() {
        return this.f1689d;
    }

    public int getOptimizationLevel() {
        return this.f1688c.I0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        j jVar = this.f1688c;
        if (jVar.f9146j == null) {
            int id2 = getId();
            if (id2 != -1) {
                jVar.f9146j = getContext().getResources().getResourceEntryName(id2);
            } else {
                jVar.f9146j = "parent";
            }
        }
        if (jVar.f9149k0 == null) {
            jVar.f9149k0 = jVar.f9146j;
        }
        Iterator it = jVar.f9216v0.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            View view = (View) iVar.f9143h0;
            if (view != null) {
                if (iVar.f9146j == null && (id = view.getId()) != -1) {
                    iVar.f9146j = getContext().getResources().getResourceEntryName(id);
                }
                if (iVar.f9149k0 == null) {
                    iVar.f9149k0 = iVar.f9146j;
                }
            }
        }
        jVar.p(sb);
        return sb.toString();
    }

    public final void h(AttributeSet attributeSet, int i7) {
        j jVar = this.f1688c;
        jVar.f9143h0 = this;
        r rVar = this.f1700o;
        jVar.f9178z0 = rVar;
        jVar.f9176x0.f9335f = rVar;
        this.f1686a.put(getId(), this);
        this.f1695j = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout, i7, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = obtainStyledAttributes.getIndex(i8);
                if (index == t.ConstraintLayout_Layout_android_minWidth) {
                    this.f1689d = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1689d);
                } else if (index == t.ConstraintLayout_Layout_android_minHeight) {
                    this.f1690e = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1690e);
                } else if (index == t.ConstraintLayout_Layout_android_maxWidth) {
                    this.f1691f = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1691f);
                } else if (index == t.ConstraintLayout_Layout_android_maxHeight) {
                    this.f1692g = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1692g);
                } else if (index == t.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.f1694i = obtainStyledAttributes.getInt(index, this.f1694i);
                } else if (index == t.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            l(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f1696k = null;
                        }
                    }
                } else if (index == t.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        p pVar = new p();
                        this.f1695j = pVar;
                        pVar.j(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.f1695j = null;
                    }
                    this.f1697l = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        jVar.I0 = this.f1694i;
        d.f8610p = jVar.Y(512);
    }

    public final boolean k() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    public void l(int i7) {
        this.f1696k = new i(getContext(), this, i7);
    }

    public final void m(int i7, int i8, int i9, int i10, boolean z6, boolean z7) {
        r rVar = this.f1700o;
        int i11 = rVar.f9363d;
        int resolveSizeAndState = View.resolveSizeAndState(i9 + rVar.f9362c, i7, 0);
        int resolveSizeAndState2 = View.resolveSizeAndState(i10 + i11, i8, 0);
        int i12 = resolveSizeAndState & d1.MEASURED_SIZE_MASK;
        int i13 = resolveSizeAndState2 & d1.MEASURED_SIZE_MASK;
        int min = Math.min(this.f1691f, i12);
        int min2 = Math.min(this.f1692g, i13);
        if (z6) {
            min |= 16777216;
        }
        if (z7) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
    }

    /* JADX WARN: Removed duplicated region for block: B:202:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x04de A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(u.j r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 1820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.n(u.j, int, int, int):void");
    }

    public final void o(u.i iVar, f fVar, SparseArray sparseArray, int i7, e eVar) {
        View view = (View) this.f1686a.get(i7);
        u.i iVar2 = (u.i) sparseArray.get(i7);
        if (iVar2 == null || view == null || !(view.getLayoutParams() instanceof f)) {
            return;
        }
        fVar.f16c0 = true;
        e eVar2 = e.BASELINE;
        if (eVar == eVar2) {
            f fVar2 = (f) view.getLayoutParams();
            fVar2.f16c0 = true;
            fVar2.f44q0.E = true;
        }
        iVar.k(eVar2).b(iVar2.k(eVar), fVar.D, fVar.C, true);
        iVar.E = true;
        iVar.k(e.TOP).j();
        iVar.k(e.BOTTOM).j();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            f fVar = (f) childAt.getLayoutParams();
            u.i iVar = fVar.f44q0;
            if ((childAt.getVisibility() != 8 || fVar.f18d0 || fVar.f20e0 || isInEditMode) && !fVar.f22f0) {
                int t6 = iVar.t();
                int u6 = iVar.u();
                int s6 = iVar.s() + t6;
                int m6 = iVar.m() + u6;
                childAt.layout(t6, u6, s6, m6);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(t6, u6, s6, m6);
                }
            }
        }
        ArrayList arrayList = this.f1687b;
        int size = arrayList.size();
        if (size > 0) {
            for (int i12 = 0; i12 < size; i12++) {
                ((ConstraintHelper) arrayList.get(i12)).o();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        boolean z6;
        String resourceName;
        int id;
        u.i iVar;
        if (this.f1701p == i7) {
            int i9 = this.f1702q;
        }
        int i10 = 0;
        if (!this.f1693h) {
            int childCount = getChildCount();
            int i11 = 0;
            while (true) {
                if (i11 >= childCount) {
                    break;
                }
                if (getChildAt(i11).isLayoutRequested()) {
                    this.f1693h = true;
                    break;
                }
                i11++;
            }
        }
        this.f1701p = i7;
        this.f1702q = i8;
        boolean k7 = k();
        j jVar = this.f1688c;
        jVar.A0 = k7;
        if (this.f1693h) {
            this.f1693h = false;
            int childCount2 = getChildCount();
            int i12 = 0;
            while (true) {
                if (i12 >= childCount2) {
                    z6 = false;
                    break;
                } else {
                    if (getChildAt(i12).isLayoutRequested()) {
                        z6 = true;
                        break;
                    }
                    i12++;
                }
            }
            if (z6) {
                boolean isInEditMode = isInEditMode();
                int childCount3 = getChildCount();
                for (int i13 = 0; i13 < childCount3; i13++) {
                    u.i g7 = g(getChildAt(i13));
                    if (g7 != null) {
                        g7.E();
                    }
                }
                Object obj = null;
                if (isInEditMode) {
                    for (int i14 = 0; i14 < childCount3; i14++) {
                        View childAt = getChildAt(i14);
                        try {
                            resourceName = getResources().getResourceName(childAt.getId());
                            setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                            int indexOf = resourceName.indexOf(47);
                            if (indexOf != -1) {
                                resourceName = resourceName.substring(indexOf + 1);
                            }
                            id = childAt.getId();
                        } catch (Resources.NotFoundException unused) {
                        }
                        if (id != 0) {
                            View view = (View) this.f1686a.get(id);
                            if (view == null && (view = findViewById(id)) != null && view != this && view.getParent() == this) {
                                onViewAdded(view);
                            }
                            if (view != this) {
                                iVar = view == null ? null : ((f) view.getLayoutParams()).f44q0;
                                iVar.f9149k0 = resourceName;
                            }
                        }
                        iVar = jVar;
                        iVar.f9149k0 = resourceName;
                    }
                }
                if (this.f1697l != -1) {
                    for (int i15 = 0; i15 < childCount3; i15++) {
                        View childAt2 = getChildAt(i15);
                        if (childAt2.getId() == this.f1697l && (childAt2 instanceof Constraints)) {
                            this.f1695j = ((Constraints) childAt2).getConstraintSet();
                        }
                    }
                }
                p pVar = this.f1695j;
                if (pVar != null) {
                    pVar.c(this);
                }
                jVar.f9216v0.clear();
                ArrayList arrayList = this.f1687b;
                int size = arrayList.size();
                if (size > 0) {
                    int i16 = 0;
                    while (i16 < size) {
                        ConstraintHelper constraintHelper = (ConstraintHelper) arrayList.get(i16);
                        if (constraintHelper.isInEditMode()) {
                            constraintHelper.setIds(constraintHelper.f1681e);
                        }
                        u.p pVar2 = constraintHelper.f1680d;
                        if (pVar2 != null) {
                            pVar2.f9211w0 = i10;
                            Arrays.fill(pVar2.f9210v0, obj);
                            for (int i17 = i10; i17 < constraintHelper.f1678b; i17++) {
                                int i18 = constraintHelper.f1677a[i17];
                                View c7 = c(i18);
                                if (c7 == null) {
                                    Integer valueOf = Integer.valueOf(i18);
                                    HashMap hashMap = constraintHelper.f1684h;
                                    String str = (String) hashMap.get(valueOf);
                                    int i19 = constraintHelper.i(this, str);
                                    if (i19 != 0) {
                                        constraintHelper.f1677a[i17] = i19;
                                        hashMap.put(Integer.valueOf(i19), str);
                                        c7 = c(i19);
                                    }
                                }
                                View view2 = c7;
                                if (view2 != null) {
                                    constraintHelper.f1680d.T(g(view2));
                                }
                            }
                            constraintHelper.f1680d.a();
                        }
                        i16++;
                        obj = null;
                        i10 = 0;
                    }
                }
                for (int i20 = 0; i20 < childCount3; i20++) {
                    View childAt3 = getChildAt(i20);
                    if (childAt3 instanceof Placeholder) {
                        Placeholder placeholder = (Placeholder) childAt3;
                        if (placeholder.f1705a == -1 && !placeholder.isInEditMode()) {
                            placeholder.setVisibility(placeholder.f1707c);
                        }
                        View findViewById = findViewById(placeholder.f1705a);
                        placeholder.f1706b = findViewById;
                        if (findViewById != null) {
                            ((f) findViewById.getLayoutParams()).f22f0 = true;
                            placeholder.f1706b.setVisibility(0);
                            placeholder.setVisibility(0);
                        }
                    }
                }
                SparseArray sparseArray = this.f1699n;
                sparseArray.clear();
                sparseArray.put(0, jVar);
                sparseArray.put(getId(), jVar);
                for (int i21 = 0; i21 < childCount3; i21++) {
                    View childAt4 = getChildAt(i21);
                    sparseArray.put(childAt4.getId(), g(childAt4));
                }
                for (int i22 = 0; i22 < childCount3; i22++) {
                    View childAt5 = getChildAt(i22);
                    u.i g8 = g(childAt5);
                    if (g8 != null) {
                        f fVar = (f) childAt5.getLayoutParams();
                        jVar.f9216v0.add(g8);
                        u.i iVar2 = g8.V;
                        if (iVar2 != null) {
                            ((u.t) iVar2).f9216v0.remove(g8);
                            g8.E();
                        }
                        g8.V = jVar;
                        a(isInEditMode, childAt5, g8, fVar, sparseArray);
                    }
                }
            }
            if (z6) {
                jVar.f9175w0.c(jVar);
            }
        }
        n(jVar, this.f1694i, i7, i8);
        m(i7, i8, jVar.s(), jVar.m(), jVar.J0, jVar.K0);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        u.i g7 = g(view);
        if ((view instanceof Guideline) && !(g7 instanceof n)) {
            f fVar = (f) view.getLayoutParams();
            n nVar = new n();
            fVar.f44q0 = nVar;
            fVar.f18d0 = true;
            nVar.U(fVar.V);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.r();
            ((f) view.getLayoutParams()).f20e0 = true;
            ArrayList arrayList = this.f1687b;
            if (!arrayList.contains(constraintHelper)) {
                arrayList.add(constraintHelper);
            }
        }
        this.f1686a.put(view.getId(), view);
        this.f1693h = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f1686a.remove(view.getId());
        u.i g7 = g(view);
        this.f1688c.f9216v0.remove(g7);
        g7.E();
        this.f1687b.remove(view);
        this.f1693h = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f1693h = true;
        super.requestLayout();
    }

    public void setConstraintSet(p pVar) {
        this.f1695j = pVar;
    }

    public void setDesignInformation(int i7, Object obj, Object obj2) {
        if (i7 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.f1698m == null) {
                this.f1698m = new HashMap();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.f1698m.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    @Override // android.view.View
    public void setId(int i7) {
        int id = getId();
        SparseArray sparseArray = this.f1686a;
        sparseArray.remove(id);
        super.setId(i7);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i7) {
        if (i7 == this.f1692g) {
            return;
        }
        this.f1692g = i7;
        requestLayout();
    }

    public void setMaxWidth(int i7) {
        if (i7 == this.f1691f) {
            return;
        }
        this.f1691f = i7;
        requestLayout();
    }

    public void setMinHeight(int i7) {
        if (i7 == this.f1690e) {
            return;
        }
        this.f1690e = i7;
        requestLayout();
    }

    public void setMinWidth(int i7) {
        if (i7 == this.f1689d) {
            return;
        }
        this.f1689d = i7;
        requestLayout();
    }

    public void setOnConstraintsChanged(a0.r rVar) {
        i iVar = this.f1696k;
        if (iVar != null) {
            iVar.getClass();
        }
    }

    public void setOptimizationLevel(int i7) {
        this.f1694i = i7;
        j jVar = this.f1688c;
        jVar.I0 = i7;
        d.f8610p = jVar.Y(512);
    }

    public void setState(int i7, int i8, int i9) {
        i iVar = this.f1696k;
        if (iVar != null) {
            iVar.b(i8, i9, i7);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
