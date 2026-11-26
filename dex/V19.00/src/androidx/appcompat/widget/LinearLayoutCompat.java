package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;

    /* renamed from: a, reason: collision with root package name */
    public boolean f1074a;

    /* renamed from: b, reason: collision with root package name */
    public int f1075b;

    /* renamed from: c, reason: collision with root package name */
    public int f1076c;

    /* renamed from: d, reason: collision with root package name */
    public int f1077d;

    /* renamed from: e, reason: collision with root package name */
    public int f1078e;

    /* renamed from: f, reason: collision with root package name */
    public int f1079f;

    /* renamed from: g, reason: collision with root package name */
    public float f1080g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1081h;

    /* renamed from: i, reason: collision with root package name */
    public int[] f1082i;

    /* renamed from: j, reason: collision with root package name */
    public int[] f1083j;

    /* renamed from: k, reason: collision with root package name */
    public Drawable f1084k;

    /* renamed from: l, reason: collision with root package name */
    public int f1085l;

    /* renamed from: m, reason: collision with root package name */
    public int f1086m;

    /* renamed from: n, reason: collision with root package name */
    public int f1087n;

    /* renamed from: o, reason: collision with root package name */
    public int f1088o;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1074a = true;
        this.f1075b = -1;
        this.f1076c = 0;
        this.f1078e = 8388659;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.LinearLayoutCompat, i7, 0);
        androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, obtainStyledAttributes);
        p0.d1.n(this, context, f.j.LinearLayoutCompat, attributeSet, obtainStyledAttributes, i7);
        int L = gVar.L(f.j.LinearLayoutCompat_android_orientation, -1);
        if (L >= 0) {
            setOrientation(L);
        }
        int L2 = gVar.L(f.j.LinearLayoutCompat_android_gravity, -1);
        if (L2 >= 0) {
            setGravity(L2);
        }
        boolean E = gVar.E(f.j.LinearLayoutCompat_android_baselineAligned, true);
        if (!E) {
            setBaselineAligned(E);
        }
        this.f1080g = ((TypedArray) gVar.f715c).getFloat(f.j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f1075b = gVar.L(f.j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.f1081h = gVar.E(f.j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(gVar.I(f.j.LinearLayoutCompat_divider));
        this.f1087n = gVar.L(f.j.LinearLayoutCompat_showDividers, 0);
        this.f1088o = gVar.H(f.j.LinearLayoutCompat_dividerPadding, 0);
        gVar.V();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof w1;
    }

    public final void f(Canvas canvas, int i7) {
        this.f1084k.setBounds(getPaddingLeft() + this.f1088o, i7, (getWidth() - getPaddingRight()) - this.f1088o, this.f1086m + i7);
        this.f1084k.draw(canvas);
    }

    public final void g(Canvas canvas, int i7) {
        this.f1084k.setBounds(i7, getPaddingTop() + this.f1088o, this.f1085l + i7, (getHeight() - getPaddingBottom()) - this.f1088o);
        this.f1084k.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i7;
        if (this.f1075b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i8 = this.f1075b;
        if (childCount <= i8) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i8);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f1075b == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int i9 = this.f1076c;
        if (this.f1077d == 1 && (i7 = this.f1078e & z1.c.INVOKE_DIRECT) != 48) {
            if (i7 == 16) {
                i9 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1079f) / 2;
            } else if (i7 == 80) {
                i9 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1079f;
            }
        }
        return i9 + ((LinearLayout.LayoutParams) ((w1) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1075b;
    }

    public Drawable getDividerDrawable() {
        return this.f1084k;
    }

    public int getDividerPadding() {
        return this.f1088o;
    }

    public int getDividerWidth() {
        return this.f1085l;
    }

    public int getGravity() {
        return this.f1078e;
    }

    public int getOrientation() {
        return this.f1077d;
    }

    public int getShowDividers() {
        return this.f1087n;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1080g;
    }

    @Override // android.view.ViewGroup
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public w1 generateDefaultLayoutParams() {
        int i7 = this.f1077d;
        if (i7 == 0) {
            return new w1(-2, -2);
        }
        if (i7 == 1) {
            return new w1(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public w1 generateLayoutParams(AttributeSet attributeSet) {
        return new w1(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public w1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new w1(layoutParams);
    }

    public final boolean k(int i7) {
        if (i7 == 0) {
            return (this.f1087n & 1) != 0;
        }
        if (i7 == getChildCount()) {
            return (this.f1087n & 4) != 0;
        }
        if ((this.f1087n & 2) == 0) {
            return false;
        }
        for (int i8 = i7 - 1; i8 >= 0; i8--) {
            if (getChildAt(i8).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i7;
        if (this.f1084k == null) {
            return;
        }
        int i8 = 0;
        if (this.f1077d == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i8 < virtualChildCount) {
                View childAt = getChildAt(i8);
                if (childAt != null && childAt.getVisibility() != 8 && k(i8)) {
                    f(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((w1) childAt.getLayoutParams())).topMargin) - this.f1086m);
                }
                i8++;
            }
            if (k(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                f(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f1086m : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((w1) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean a7 = a4.a(this);
        while (i8 < virtualChildCount2) {
            View childAt3 = getChildAt(i8);
            if (childAt3 != null && childAt3.getVisibility() != 8 && k(i8)) {
                w1 w1Var = (w1) childAt3.getLayoutParams();
                g(canvas, a7 ? childAt3.getRight() + ((LinearLayout.LayoutParams) w1Var).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) w1Var).leftMargin) - this.f1085l);
            }
            i8++;
        }
        if (k(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                w1 w1Var2 = (w1) childAt4.getLayoutParams();
                if (a7) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) w1Var2).leftMargin;
                    i7 = this.f1085l;
                    right = left - i7;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) w1Var2).rightMargin;
                }
            } else if (a7) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i7 = this.f1085l;
                right = left - i7;
            }
            g(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0190  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:173:0x02e0, code lost:
    
        if (((android.widget.LinearLayout.LayoutParams) r13).width == (-1)) goto L148;
     */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:352:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x06b4  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r38, int r39) {
        /*
            Method dump skipped, instructions count: 2208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z6) {
        this.f1074a = z6;
    }

    public void setBaselineAlignedChildIndex(int i7) {
        if (i7 >= 0 && i7 < getChildCount()) {
            this.f1075b = i7;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f1084k) {
            return;
        }
        this.f1084k = drawable;
        if (drawable != null) {
            this.f1085l = drawable.getIntrinsicWidth();
            this.f1086m = drawable.getIntrinsicHeight();
        } else {
            this.f1085l = 0;
            this.f1086m = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i7) {
        this.f1088o = i7;
    }

    public void setGravity(int i7) {
        if (this.f1078e != i7) {
            if ((8388615 & i7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & z1.c.INVOKE_DIRECT) == 0) {
                i7 |= 48;
            }
            this.f1078e = i7;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i7) {
        int i8 = i7 & 8388615;
        int i9 = this.f1078e;
        if ((8388615 & i9) != i8) {
            this.f1078e = i8 | ((-8388616) & i9);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z6) {
        this.f1081h = z6;
    }

    public void setOrientation(int i7) {
        if (this.f1077d != i7) {
            this.f1077d = i7;
            requestLayout();
        }
    }

    public void setShowDividers(int i7) {
        if (i7 != this.f1087n) {
            requestLayout();
        }
        this.f1087n = i7;
    }

    public void setVerticalGravity(int i7) {
        int i8 = i7 & z1.c.INVOKE_DIRECT;
        int i9 = this.f1078e;
        if ((i9 & z1.c.INVOKE_DIRECT) != i8) {
            this.f1078e = i8 | (i9 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f7) {
        this.f1080g = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f7);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
