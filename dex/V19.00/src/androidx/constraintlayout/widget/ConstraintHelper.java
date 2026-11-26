package androidx.constraintlayout.widget;

import a0.f;
import a0.k;
import a0.l;
import a0.q;
import a0.t;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;
import java.util.HashMap;
import u.i;
import u.o;
import u.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class ConstraintHelper extends View {

    /* renamed from: a, reason: collision with root package name */
    public int[] f1677a;

    /* renamed from: b, reason: collision with root package name */
    public int f1678b;

    /* renamed from: c, reason: collision with root package name */
    public final Context f1679c;

    /* renamed from: d, reason: collision with root package name */
    public p f1680d;

    /* renamed from: e, reason: collision with root package name */
    public String f1681e;

    /* renamed from: f, reason: collision with root package name */
    public String f1682f;

    /* renamed from: g, reason: collision with root package name */
    public View[] f1683g;

    /* renamed from: h, reason: collision with root package name */
    public final HashMap f1684h;

    public ConstraintHelper(Context context) {
        super(context);
        this.f1677a = new int[32];
        this.f1683g = null;
        this.f1684h = new HashMap();
        this.f1679c = context;
        l(null);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1677a = new int[32];
        this.f1683g = null;
        this.f1684h = new HashMap();
        this.f1679c = context;
        l(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1677a = new int[32];
        this.f1683g = null;
        this.f1684h = new HashMap();
        this.f1679c = context;
        l(attributeSet);
    }

    public final void c(String str) {
        if (str == null || str.length() == 0 || this.f1679c == null) {
            return;
        }
        String trim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int j7 = j(trim);
        if (j7 != 0) {
            this.f1684h.put(Integer.valueOf(j7), trim);
            d(j7);
        }
    }

    public final void d(int i7) {
        if (i7 == getId()) {
            return;
        }
        int i8 = this.f1678b + 1;
        int[] iArr = this.f1677a;
        if (i8 > iArr.length) {
            this.f1677a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1677a;
        int i9 = this.f1678b;
        iArr2[i9] = i7;
        this.f1678b = i9 + 1;
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.f1679c == null) {
            return;
        }
        String trim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof f) && trim.equals(((f) layoutParams).Y) && childAt.getId() != -1) {
                d(childAt.getId());
            }
        }
    }

    public final void f() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        g((ConstraintLayout) parent);
    }

    public final void g(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i7 = 0; i7 < this.f1678b; i7++) {
            View c7 = constraintLayout.c(this.f1677a[i7]);
            if (c7 != null) {
                c7.setVisibility(visibility);
                if (elevation > CropImageView.DEFAULT_ASPECT_RATIO) {
                    c7.setTranslationZ(c7.getTranslationZ() + elevation);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1677a, this.f1678b);
    }

    public void h(ConstraintLayout constraintLayout) {
    }

    public final int i(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String str2;
        if (str == null || constraintLayout == null || (resources = this.f1679c.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            if (childAt.getId() != -1) {
                try {
                    str2 = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    str2 = null;
                }
                if (str.equals(str2)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int j(java.lang.String r5) {
        /*
            r4 = this;
            android.view.ViewParent r0 = r4.getParent()
            boolean r0 = r0 instanceof androidx.constraintlayout.widget.ConstraintLayout
            r1 = 0
            if (r0 == 0) goto L10
            android.view.ViewParent r0 = r4.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
            goto L11
        L10:
            r0 = r1
        L11:
            boolean r2 = r4.isInEditMode()
            if (r2 == 0) goto L3a
            if (r0 == 0) goto L3a
            boolean r2 = r5 instanceof java.lang.String
            if (r2 == 0) goto L2e
            java.util.HashMap r2 = r0.f1698m
            if (r2 == 0) goto L2e
            boolean r2 = r2.containsKey(r5)
            if (r2 == 0) goto L2e
            java.util.HashMap r2 = r0.f1698m
            java.lang.Object r2 = r2.get(r5)
            goto L2f
        L2e:
            r2 = r1
        L2f:
            boolean r3 = r2 instanceof java.lang.Integer
            if (r3 == 0) goto L3a
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L3b
        L3a:
            r2 = 0
        L3b:
            if (r2 != 0) goto L43
            if (r0 == 0) goto L43
            int r2 = r4.i(r0, r5)
        L43:
            if (r2 != 0) goto L4f
            java.lang.Class<a0.s> r0 = a0.s.class
            java.lang.reflect.Field r0 = r0.getField(r5)     // Catch: java.lang.Exception -> L4f
            int r2 = r0.getInt(r1)     // Catch: java.lang.Exception -> L4f
        L4f:
            if (r2 != 0) goto L61
            android.content.Context r0 = r4.f1679c
            android.content.res.Resources r1 = r0.getResources()
            java.lang.String r2 = "id"
            java.lang.String r0 = r0.getPackageName()
            int r2 = r1.getIdentifier(r5, r2, r0)
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintHelper.j(java.lang.String):int");
    }

    public final View[] k(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f1683g;
        if (viewArr == null || viewArr.length != this.f1678b) {
            this.f1683g = new View[this.f1678b];
        }
        for (int i7 = 0; i7 < this.f1678b; i7++) {
            this.f1683g[i7] = constraintLayout.c(this.f1677a[i7]);
        }
        return this.f1683g;
    }

    public void l(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = obtainStyledAttributes.getString(index);
                    this.f1681e = string;
                    setIds(string);
                } else if (index == t.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.f1682f = string2;
                    setReferenceTags(string2);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void m(k kVar, p pVar, q qVar, SparseArray sparseArray) {
        l lVar = kVar.f85e;
        int[] iArr = lVar.f109j0;
        int i7 = 0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = lVar.f111k0;
            if (str != null) {
                if (str.length() > 0) {
                    String[] split = lVar.f111k0.split(",");
                    getContext();
                    int[] iArr2 = new int[split.length];
                    int i8 = 0;
                    for (String str2 : split) {
                        int j7 = j(str2.trim());
                        if (j7 != 0) {
                            iArr2[i8] = j7;
                            i8++;
                        }
                    }
                    if (i8 != split.length) {
                        iArr2 = Arrays.copyOf(iArr2, i8);
                    }
                    lVar.f109j0 = iArr2;
                } else {
                    lVar.f109j0 = null;
                }
            }
        }
        pVar.f9211w0 = 0;
        Arrays.fill(pVar.f9210v0, (Object) null);
        if (lVar.f109j0 == null) {
            return;
        }
        while (true) {
            int[] iArr3 = lVar.f109j0;
            if (i7 >= iArr3.length) {
                return;
            }
            i iVar = (i) sparseArray.get(iArr3[i7]);
            if (iVar != null) {
                pVar.T(iVar);
            }
            i7++;
        }
    }

    public void n(i iVar, boolean z6) {
    }

    public void o() {
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f1681e;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.f1682f;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void p(ConstraintLayout constraintLayout) {
    }

    public void q(o oVar, SparseArray sparseArray) {
        p pVar = (p) oVar;
        pVar.f9211w0 = 0;
        Arrays.fill(pVar.f9210v0, (Object) null);
        for (int i7 = 0; i7 < this.f1678b; i7++) {
            pVar.T((i) sparseArray.get(this.f1677a[i7]));
        }
    }

    public final void r() {
        if (this.f1680d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof f) {
            ((f) layoutParams).f44q0 = this.f1680d;
        }
    }

    public void setIds(String str) {
        this.f1681e = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1678b = 0;
        while (true) {
            int indexOf = str.indexOf(44, i7);
            if (indexOf == -1) {
                c(str.substring(i7));
                return;
            } else {
                c(str.substring(i7, indexOf));
                i7 = indexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.f1682f = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1678b = 0;
        while (true) {
            int indexOf = str.indexOf(44, i7);
            if (indexOf == -1) {
                e(str.substring(i7));
                return;
            } else {
                e(str.substring(i7, indexOf));
                i7 = indexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f1681e = null;
        this.f1678b = 0;
        for (int i7 : iArr) {
            d(i7);
        }
    }

    @Override // android.view.View
    public void setTag(int i7, Object obj) {
        super.setTag(i7, obj);
        if (obj == null && this.f1681e == null) {
            d(i7);
        }
    }
}
