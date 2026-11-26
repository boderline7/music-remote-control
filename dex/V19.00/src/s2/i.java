package s2;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.search.SearchBar;
import com.yalantis.ucrop.view.CropImageView;
import n1.z;
import p0.d1;
import p0.l0;
import p0.l2;
import p0.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i extends j {

    /* renamed from: c, reason: collision with root package name */
    public final Rect f8682c;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f8683d;

    /* renamed from: e, reason: collision with root package name */
    public int f8684e;

    /* renamed from: f, reason: collision with root package name */
    public int f8685f;

    public i() {
        this.f8682c = new Rect();
        this.f8683d = new Rect();
        this.f8684e = 0;
    }

    public i(int i7) {
        super(0);
        this.f8682c = new Rect();
        this.f8683d = new Rect();
        this.f8684e = 0;
    }

    @Override // c0.b
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9) {
        AppBarLayout z6;
        l2 lastWindowInsets;
        int i10 = view.getLayoutParams().height;
        if ((i10 != -1 && i10 != -2) || (z6 = AppBarLayout.ScrollingViewBehavior.z(coordinatorLayout.o(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i9);
        if (size > 0) {
            int i11 = d1.OVER_SCROLL_ALWAYS;
            if (l0.b(z6) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.a() + lastWindowInsets.d();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        int totalScrollRange = z6.getTotalScrollRange() + size;
        int measuredHeight = z6.getMeasuredHeight();
        if (this instanceof SearchBar.ScrollingViewBehavior) {
            view.setTranslationY(-measuredHeight);
        } else {
            view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            totalScrollRange -= measuredHeight;
        }
        coordinatorLayout.v(view, i7, i8, View.MeasureSpec.makeMeasureSpec(totalScrollRange, i10 == -1 ? v.c.EXACTLY : Integer.MIN_VALUE));
        return true;
    }

    @Override // s2.j
    public final void x(CoordinatorLayout coordinatorLayout, View view, int i7) {
        AppBarLayout z6 = AppBarLayout.ScrollingViewBehavior.z(coordinatorLayout.o(view));
        if (z6 == null) {
            coordinatorLayout.u(i7, view);
            this.f8684e = 0;
            return;
        }
        c0.e eVar = (c0.e) view.getLayoutParams();
        int paddingLeft = coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
        int bottom = z6.getBottom() + ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
        int bottom2 = ((z6.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
        Rect rect = this.f8682c;
        rect.set(paddingLeft, bottom, width, bottom2);
        l2 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            if (l0.b(coordinatorLayout) && !l0.b(view)) {
                rect.left = lastWindowInsets.b() + rect.left;
                rect.right -= lastWindowInsets.c();
            }
        }
        Rect rect2 = this.f8683d;
        int i9 = eVar.f3009c;
        if (i9 == 0) {
            i9 = 8388659;
        }
        m.b(i9, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i7);
        int y6 = y(z6);
        view.layout(rect2.left, rect2.top - y6, rect2.right, rect2.bottom - y6);
        this.f8684e = rect2.top - z6.getBottom();
    }

    public final int y(View view) {
        int i7;
        if (this.f8685f == 0) {
            return 0;
        }
        boolean z6 = view instanceof AppBarLayout;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (z6) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            c0.b bVar = ((c0.e) appBarLayout.getLayoutParams()).f3007a;
            int y6 = bVar instanceof AppBarLayout.BaseBehavior ? ((AppBarLayout.BaseBehavior) bVar).y() : 0;
            if ((downNestedPreScrollRange == 0 || totalScrollRange + y6 > downNestedPreScrollRange) && (i7 = totalScrollRange - downNestedPreScrollRange) != 0) {
                f7 = (y6 / i7) + 1.0f;
            }
        }
        int i8 = this.f8685f;
        return z.y((int) (f7 * i8), 0, i8);
    }
}
