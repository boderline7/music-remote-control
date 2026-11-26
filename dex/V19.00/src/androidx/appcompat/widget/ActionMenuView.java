package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActionMenuView extends LinearLayoutCompat implements k.n, k.e0 {
    public q A;

    /* renamed from: p, reason: collision with root package name */
    public k.o f981p;

    /* renamed from: q, reason: collision with root package name */
    public Context f982q;

    /* renamed from: r, reason: collision with root package name */
    public int f983r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f984s;

    /* renamed from: t, reason: collision with root package name */
    public m f985t;

    /* renamed from: u, reason: collision with root package name */
    public k.b0 f986u;

    /* renamed from: v, reason: collision with root package name */
    public k.m f987v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f988w;

    /* renamed from: x, reason: collision with root package name */
    public int f989x;

    /* renamed from: y, reason: collision with root package name */
    public final int f990y;

    /* renamed from: z, reason: collision with root package name */
    public final int f991z;

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.f990y = (int) (56.0f * f7);
        this.f991z = (int) (f7 * 4.0f);
        this.f982q = context;
        this.f983r = 0;
    }

    public static p l() {
        p pVar = new p(-2, -2);
        pVar.f1370a = false;
        ((LinearLayout.LayoutParams) pVar).gravity = 16;
        return pVar;
    }

    public static p m(ViewGroup.LayoutParams layoutParams) {
        p pVar;
        if (layoutParams == null) {
            return l();
        }
        if (layoutParams instanceof p) {
            p pVar2 = (p) layoutParams;
            pVar = new p(pVar2);
            pVar.f1370a = pVar2.f1370a;
        } else {
            pVar = new p(layoutParams);
        }
        if (((LinearLayout.LayoutParams) pVar).gravity <= 0) {
            ((LinearLayout.LayoutParams) pVar).gravity = 16;
        }
        return pVar;
    }

    @Override // k.n
    public final boolean a(k.q qVar) {
        return this.f981p.q(qVar, null, 0);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof p;
    }

    @Override // k.e0
    public final void d(k.o oVar) {
        this.f981p = oVar;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return l();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new p(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public Menu getMenu() {
        if (this.f981p == null) {
            Context context = getContext();
            k.o oVar = new k.o(context);
            this.f981p = oVar;
            oVar.f7143e = new e.a(6, this);
            m mVar = new m(context);
            this.f985t = mVar;
            mVar.f1335m = true;
            mVar.f1336n = true;
            k.b0 b0Var = this.f986u;
            if (b0Var == null) {
                b0Var = new o();
            }
            mVar.f1327e = b0Var;
            this.f981p.b(mVar, this.f982q);
            m mVar2 = this.f985t;
            mVar2.f1330h = this;
            this.f981p = mVar2.f1325c;
        }
        return this.f981p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        m mVar = this.f985t;
        k kVar = mVar.f1332j;
        if (kVar != null) {
            return kVar.getDrawable();
        }
        if (mVar.f1334l) {
            return mVar.f1333k;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.f983r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* renamed from: h */
    public final /* bridge */ /* synthetic */ w1 generateDefaultLayoutParams() {
        return l();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* renamed from: i */
    public final w1 generateLayoutParams(AttributeSet attributeSet) {
        return new p(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    /* renamed from: j */
    public final /* bridge */ /* synthetic */ w1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public final boolean n(int i7) {
        boolean z6 = false;
        if (i7 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i7 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i7);
        if (i7 < getChildCount() && (childAt instanceof n)) {
            z6 = ((n) childAt).a();
        }
        return (i7 <= 0 || !(childAt2 instanceof n)) ? z6 : z6 | ((n) childAt2).b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m mVar = this.f985t;
        if (mVar != null) {
            mVar.m(false);
            if (this.f985t.l()) {
                this.f985t.g();
                this.f985t.n();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.f985t;
        if (mVar != null) {
            mVar.g();
            h hVar = mVar.f1343u;
            if (hVar == null || !hVar.b()) {
                return;
            }
            hVar.f7055j.dismiss();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int width;
        int i11;
        if (!this.f988w) {
            super.onLayout(z6, i7, i8, i9, i10);
            return;
        }
        int childCount = getChildCount();
        int i12 = (i10 - i8) / 2;
        int dividerWidth = getDividerWidth();
        int i13 = i9 - i7;
        int paddingRight = (i13 - getPaddingRight()) - getPaddingLeft();
        boolean a7 = a4.a(this);
        int i14 = 0;
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                p pVar = (p) childAt.getLayoutParams();
                if (pVar.f1370a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (n(i16)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a7) {
                        i11 = getPaddingLeft() + ((LinearLayout.LayoutParams) pVar).leftMargin;
                        width = i11 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) pVar).rightMargin;
                        i11 = width - measuredWidth;
                    }
                    int i17 = i12 - (measuredHeight / 2);
                    childAt.layout(i11, i17, width, measuredHeight + i17);
                    paddingRight -= measuredWidth;
                    i14 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) pVar).leftMargin) + ((LinearLayout.LayoutParams) pVar).rightMargin;
                    n(i16);
                    i15++;
                }
            }
        }
        if (childCount == 1 && i14 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i18 = (i13 / 2) - (measuredWidth2 / 2);
            int i19 = i12 - (measuredHeight2 / 2);
            childAt2.layout(i18, i19, measuredWidth2 + i18, measuredHeight2 + i19);
            return;
        }
        int i20 = i15 - (i14 ^ 1);
        int max = Math.max(0, i20 > 0 ? paddingRight / i20 : 0);
        if (a7) {
            int width2 = getWidth() - getPaddingRight();
            for (int i21 = 0; i21 < childCount; i21++) {
                View childAt3 = getChildAt(i21);
                p pVar2 = (p) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !pVar2.f1370a) {
                    int i22 = width2 - ((LinearLayout.LayoutParams) pVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i23 = i12 - (measuredHeight3 / 2);
                    childAt3.layout(i22 - measuredWidth3, i23, i22, measuredHeight3 + i23);
                    width2 = i22 - ((measuredWidth3 + ((LinearLayout.LayoutParams) pVar2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i24 = 0; i24 < childCount; i24++) {
            View childAt4 = getChildAt(i24);
            p pVar3 = (p) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !pVar3.f1370a) {
                int i25 = paddingLeft + ((LinearLayout.LayoutParams) pVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i26 = i12 - (measuredHeight4 / 2);
                childAt4.layout(i25, i26, i25 + measuredWidth4, measuredHeight4 + i26);
                paddingLeft = measuredWidth4 + ((LinearLayout.LayoutParams) pVar3).rightMargin + max + i25;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v26 */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        boolean z6;
        int i12;
        int i13;
        int i14;
        int i15;
        ?? r12;
        int i16;
        int i17;
        int i18;
        k.o oVar;
        boolean z7 = this.f988w;
        boolean z8 = View.MeasureSpec.getMode(i7) == 1073741824;
        this.f988w = z8;
        if (z7 != z8) {
            this.f989x = 0;
        }
        int size = View.MeasureSpec.getSize(i7);
        if (this.f988w && (oVar = this.f981p) != null && size != this.f989x) {
            this.f989x = size;
            oVar.p(true);
        }
        int childCount = getChildCount();
        if (!this.f988w || childCount <= 0) {
            for (int i19 = 0; i19 < childCount; i19++) {
                p pVar = (p) getChildAt(i19).getLayoutParams();
                ((LinearLayout.LayoutParams) pVar).rightMargin = 0;
                ((LinearLayout.LayoutParams) pVar).leftMargin = 0;
            }
            super.onMeasure(i7, i8);
            return;
        }
        int mode = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i7);
        int size3 = View.MeasureSpec.getSize(i8);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, paddingBottom, -2);
        int i20 = size2 - paddingRight;
        int i21 = this.f990y;
        int i22 = i20 / i21;
        int i23 = i20 % i21;
        if (i22 == 0) {
            setMeasuredDimension(i20, 0);
            return;
        }
        int i24 = (i23 / i22) + i21;
        int childCount2 = getChildCount();
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        boolean z9 = false;
        int i28 = 0;
        int i29 = 0;
        long j7 = 0;
        while (true) {
            i9 = this.f991z;
            if (i27 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i27);
            int i30 = size3;
            if (childAt.getVisibility() == 8) {
                i16 = i20;
                i17 = paddingBottom;
            } else {
                boolean z10 = childAt instanceof ActionMenuItemView;
                int i31 = i25 + 1;
                if (z10) {
                    childAt.setPadding(i9, 0, i9, 0);
                }
                p pVar2 = (p) childAt.getLayoutParams();
                pVar2.f1375f = false;
                pVar2.f1372c = 0;
                pVar2.f1371b = 0;
                pVar2.f1373d = false;
                ((LinearLayout.LayoutParams) pVar2).leftMargin = 0;
                ((LinearLayout.LayoutParams) pVar2).rightMargin = 0;
                pVar2.f1374e = z10 && (TextUtils.isEmpty(((ActionMenuItemView) childAt).getText()) ^ true);
                int i32 = pVar2.f1370a ? 1 : i22;
                p pVar3 = (p) childAt.getLayoutParams();
                i16 = i20;
                i17 = paddingBottom;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z10 ? (ActionMenuItemView) childAt : null;
                boolean z11 = actionMenuItemView != null && (TextUtils.isEmpty(actionMenuItemView.getText()) ^ true);
                if (i32 <= 0 || (z11 && i32 < 2)) {
                    i18 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i32 * i24, Integer.MIN_VALUE), makeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i18 = measuredWidth / i24;
                    if (measuredWidth % i24 != 0) {
                        i18++;
                    }
                    if (z11 && i18 < 2) {
                        i18 = 2;
                    }
                }
                pVar3.f1373d = !pVar3.f1370a && z11;
                pVar3.f1371b = i18;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i18 * i24, v.c.EXACTLY), makeMeasureSpec);
                i26 = Math.max(i26, i18);
                if (pVar2.f1373d) {
                    i28++;
                }
                if (pVar2.f1370a) {
                    z9 = true;
                }
                i22 -= i18;
                i29 = Math.max(i29, childAt.getMeasuredHeight());
                if (i18 == 1) {
                    j7 |= 1 << i27;
                }
                i25 = i31;
            }
            i27++;
            size3 = i30;
            paddingBottom = i17;
            i20 = i16;
        }
        int i33 = i20;
        int i34 = size3;
        int i35 = i29;
        boolean z12 = z9 && i25 == 2;
        boolean z13 = false;
        while (i28 > 0 && i22 > 0) {
            int i36 = u.MAX_ACTIVITY_COUNT_UNLIMITED;
            int i37 = 0;
            int i38 = 0;
            long j8 = 0;
            while (i38 < childCount2) {
                int i39 = i35;
                p pVar4 = (p) getChildAt(i38).getLayoutParams();
                boolean z14 = z13;
                if (pVar4.f1373d) {
                    int i40 = pVar4.f1371b;
                    if (i40 < i36) {
                        j8 = 1 << i38;
                        i36 = i40;
                        i37 = 1;
                    } else if (i40 == i36) {
                        i37++;
                        j8 |= 1 << i38;
                    }
                }
                i38++;
                z13 = z14;
                i35 = i39;
            }
            i11 = i35;
            z6 = z13;
            j7 |= j8;
            if (i37 > i22) {
                i10 = mode;
                break;
            }
            int i41 = i36 + 1;
            int i42 = 0;
            while (i42 < childCount2) {
                View childAt2 = getChildAt(i42);
                p pVar5 = (p) childAt2.getLayoutParams();
                int i43 = mode;
                int i44 = childMeasureSpec;
                int i45 = childCount2;
                long j9 = 1 << i42;
                if ((j8 & j9) != 0) {
                    if (z12 && pVar5.f1374e) {
                        r12 = 1;
                        r12 = 1;
                        if (i22 == 1) {
                            childAt2.setPadding(i9 + i24, 0, i9, 0);
                        }
                    } else {
                        r12 = 1;
                    }
                    pVar5.f1371b += r12;
                    pVar5.f1375f = r12;
                    i22--;
                } else if (pVar5.f1371b == i41) {
                    j7 |= j9;
                }
                i42++;
                childMeasureSpec = i44;
                mode = i43;
                childCount2 = i45;
            }
            i35 = i11;
            z13 = true;
        }
        i10 = mode;
        i11 = i35;
        z6 = z13;
        int i46 = childMeasureSpec;
        int i47 = childCount2;
        boolean z15 = !z9 && i25 == 1;
        if (i22 <= 0 || j7 == 0 || (i22 >= i25 - 1 && !z15 && i26 <= 1)) {
            i12 = i47;
        } else {
            float bitCount = Long.bitCount(j7);
            if (!z15) {
                if ((j7 & 1) != 0 && !((p) getChildAt(0).getLayoutParams()).f1374e) {
                    bitCount -= 0.5f;
                }
                int i48 = i47 - 1;
                if ((j7 & (1 << i48)) != 0 && !((p) getChildAt(i48).getLayoutParams()).f1374e) {
                    bitCount -= 0.5f;
                }
            }
            int i49 = bitCount > CropImageView.DEFAULT_ASPECT_RATIO ? (int) ((i22 * i24) / bitCount) : 0;
            i12 = i47;
            for (int i50 = 0; i50 < i12; i50++) {
                if ((j7 & (1 << i50)) != 0) {
                    View childAt3 = getChildAt(i50);
                    p pVar6 = (p) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        pVar6.f1372c = i49;
                        pVar6.f1375f = true;
                        if (i50 == 0 && !pVar6.f1374e) {
                            ((LinearLayout.LayoutParams) pVar6).leftMargin = (-i49) / 2;
                        }
                        z6 = true;
                    } else if (pVar6.f1370a) {
                        pVar6.f1372c = i49;
                        pVar6.f1375f = true;
                        ((LinearLayout.LayoutParams) pVar6).rightMargin = (-i49) / 2;
                        z6 = true;
                    } else {
                        if (i50 != 0) {
                            ((LinearLayout.LayoutParams) pVar6).leftMargin = i49 / 2;
                        }
                        if (i50 != i12 - 1) {
                            ((LinearLayout.LayoutParams) pVar6).rightMargin = i49 / 2;
                        }
                    }
                }
            }
        }
        if (z6) {
            int i51 = 0;
            while (i51 < i12) {
                View childAt4 = getChildAt(i51);
                p pVar7 = (p) childAt4.getLayoutParams();
                if (pVar7.f1375f) {
                    i15 = i46;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((pVar7.f1371b * i24) + pVar7.f1372c, v.c.EXACTLY), i15);
                } else {
                    i15 = i46;
                }
                i51++;
                i46 = i15;
            }
        }
        if (i10 != 1073741824) {
            i14 = i33;
            i13 = i11;
        } else {
            i13 = i34;
            i14 = i33;
        }
        setMeasuredDimension(i14, i13);
    }

    public void setExpandedActionViewsExclusive(boolean z6) {
        this.f985t.f1340r = z6;
    }

    public void setMenuCallbacks(k.b0 b0Var, k.m mVar) {
        this.f986u = b0Var;
        this.f987v = mVar;
    }

    public void setOnMenuItemClickListener(q qVar) {
        this.A = qVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        m mVar = this.f985t;
        k kVar = mVar.f1332j;
        if (kVar != null) {
            kVar.setImageDrawable(drawable);
        } else {
            mVar.f1334l = true;
            mVar.f1333k = drawable;
        }
    }

    public void setOverflowReserved(boolean z6) {
        this.f984s = z6;
    }

    public void setPopupTheme(int i7) {
        if (this.f983r != i7) {
            this.f983r = i7;
            if (i7 == 0) {
                this.f982q = getContext();
            } else {
                this.f982q = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    public void setPresenter(m mVar) {
        this.f985t = mVar;
        mVar.f1330h = this;
        this.f981p = mVar.f1325c;
    }
}
