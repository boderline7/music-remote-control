package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import java.util.ArrayList;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class FragmentContainerView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f1877a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f1878b;

    /* renamed from: c, reason: collision with root package name */
    public View.OnApplyWindowInsetsListener f1879c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1880d;

    public FragmentContainerView(Context context) {
        super(context);
        this.f1880d = true;
    }

    public FragmentContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FragmentContainerView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        String str;
        this.f1880d = true;
        if (attributeSet != null) {
            String classAttribute = attributeSet.getClassAttribute();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b1.c.FragmentContainerView);
            if (classAttribute == null) {
                classAttribute = obtainStyledAttributes.getString(b1.c.FragmentContainerView_android_name);
                str = "android:name";
            } else {
                str = "class";
            }
            obtainStyledAttributes.recycle();
            if (classAttribute == null || isInEditMode()) {
                return;
            }
            throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + str + "=\"" + classAttribute + "\"");
        }
    }

    public final void a(View view) {
        ArrayList arrayList = this.f1878b;
        if (arrayList == null || !arrayList.contains(view)) {
            return;
        }
        if (this.f1877a == null) {
            this.f1877a = new ArrayList();
        }
        this.f1877a.add(view);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        Object tag = view.getTag(b1.b.fragment_container_view_tag);
        if ((tag instanceof z ? (z) tag : null) != null) {
            super.addView(view, i7, layoutParams);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        Object tag = view.getTag(b1.b.fragment_container_view_tag);
        if ((tag instanceof z ? (z) tag : null) != null) {
            return super.addViewInLayout(view, i7, layoutParams, z6);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        l2 h7 = l2.h(null, windowInsets);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.f1879c;
        l2 h8 = onApplyWindowInsetsListener != null ? l2.h(null, onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets)) : p0.d1.j(this, h7);
        if (!h8.f8161a.m()) {
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                p0.d1.b(getChildAt(i7), h8);
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.f1880d && this.f1877a != null) {
            for (int i7 = 0; i7 < this.f1877a.size(); i7++) {
                super.drawChild(canvas, (View) this.f1877a.get(i7), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j7) {
        ArrayList arrayList;
        if (!this.f1880d || (arrayList = this.f1877a) == null || arrayList.size() <= 0 || !this.f1877a.contains(view)) {
            return super.drawChild(canvas, view, j7);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        ArrayList arrayList = this.f1878b;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList arrayList2 = this.f1877a;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.f1880d = true;
            }
        }
        super.endViewTransition(view);
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z6) {
        if (z6) {
            a(view);
        }
        super.removeDetachedView(view, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i7) {
        a(getChildAt(i7));
        super.removeViewAt(i7);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i7, int i8) {
        for (int i9 = i7; i9 < i7 + i8; i9++) {
            a(getChildAt(i9));
        }
        super.removeViews(i7, i8);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i7, int i8) {
        for (int i9 = i7; i9 < i7 + i8; i9++) {
            a(getChildAt(i9));
        }
        super.removeViewsInLayout(i7, i8);
    }

    public void setDrawDisappearingViewsLast(boolean z6) {
        this.f1880d = z6;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.f1879c = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.f1878b == null) {
                this.f1878b = new ArrayList();
            }
            this.f1878b.add(view);
        }
        super.startViewTransition(view);
    }
}
