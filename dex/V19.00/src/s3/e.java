package s3;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.material.internal.k0;
import com.google.android.material.tabs.TabLayout;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends LinearLayout {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f8695c = 0;

    /* renamed from: a, reason: collision with root package name */
    public ValueAnimator f8696a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TabLayout f8697b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(TabLayout tabLayout, Context context) {
        super(context);
        this.f8697b = tabLayout;
        setWillNotDraw(false);
    }

    public final void a(int i7) {
        TabLayout tabLayout = this.f8697b;
        if (tabLayout.S == 0 || (tabLayout.getTabSelectedIndicator().getBounds().left == -1 && tabLayout.getTabSelectedIndicator().getBounds().right == -1)) {
            View childAt = getChildAt(i7);
            com.google.android.material.tabs.a aVar = tabLayout.I;
            Drawable drawable = tabLayout.f4225o;
            aVar.getClass();
            RectF a7 = com.google.android.material.tabs.a.a(tabLayout, childAt);
            drawable.setBounds((int) a7.left, drawable.getBounds().top, (int) a7.right, drawable.getBounds().bottom);
            tabLayout.f4211a = i7;
        }
    }

    public final void b(int i7) {
        TabLayout tabLayout = this.f8697b;
        Rect bounds = tabLayout.f4225o.getBounds();
        tabLayout.f4225o.setBounds(bounds.left, 0, bounds.right, i7);
        requestLayout();
    }

    public final void c(View view, View view2, float f7) {
        if (view == null || view.getWidth() <= 0) {
            TabLayout tabLayout = this.f8697b;
            Drawable drawable = tabLayout.f4225o;
            drawable.setBounds(-1, drawable.getBounds().top, -1, tabLayout.f4225o.getBounds().bottom);
        } else {
            TabLayout tabLayout2 = this.f8697b;
            tabLayout2.I.b(tabLayout2, view, view2, f7, tabLayout2.f4225o);
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.k(this);
    }

    public final void d(int i7, int i8, boolean z6) {
        TabLayout tabLayout = this.f8697b;
        if (tabLayout.f4211a == i7) {
            return;
        }
        View childAt = getChildAt(tabLayout.getSelectedTabPosition());
        View childAt2 = getChildAt(i7);
        if (childAt2 == null) {
            a(tabLayout.getSelectedTabPosition());
            return;
        }
        tabLayout.f4211a = i7;
        com.google.android.material.internal.g gVar = new com.google.android.material.internal.g(this, childAt, childAt2);
        if (!z6) {
            this.f8696a.removeAllUpdateListeners();
            this.f8696a.addUpdateListener(gVar);
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        this.f8696a = valueAnimator;
        valueAnimator.setInterpolator(tabLayout.J);
        valueAnimator.setDuration(i8);
        valueAnimator.setFloatValues(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        valueAnimator.addUpdateListener(gVar);
        valueAnimator.start();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int height;
        TabLayout tabLayout = this.f8697b;
        int height2 = tabLayout.f4225o.getBounds().height();
        if (height2 < 0) {
            height2 = tabLayout.f4225o.getIntrinsicHeight();
        }
        int i7 = tabLayout.B;
        if (i7 == 0) {
            height = getHeight() - height2;
            height2 = getHeight();
        } else if (i7 != 1) {
            height = 0;
            if (i7 != 2) {
                height2 = i7 != 3 ? 0 : getHeight();
            }
        } else {
            height = (getHeight() - height2) / 2;
            height2 = (getHeight() + height2) / 2;
        }
        if (tabLayout.f4225o.getBounds().width() > 0) {
            Rect bounds = tabLayout.f4225o.getBounds();
            tabLayout.f4225o.setBounds(bounds.left, height, bounds.right, height2);
            tabLayout.f4225o.draw(canvas);
        }
        super.draw(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        ValueAnimator valueAnimator = this.f8696a;
        TabLayout tabLayout = this.f8697b;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            d(tabLayout.getSelectedTabPosition(), -1, false);
            return;
        }
        if (tabLayout.f4211a == -1) {
            tabLayout.f4211a = tabLayout.getSelectedTabPosition();
        }
        a(tabLayout.f4211a);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (View.MeasureSpec.getMode(i7) != 1073741824) {
            return;
        }
        TabLayout tabLayout = this.f8697b;
        if (tabLayout.f4236z == 1 || tabLayout.C == 2) {
            int childCount = getChildCount();
            int i9 = 0;
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                if (childAt.getVisibility() == 0) {
                    i9 = Math.max(i9, childAt.getMeasuredWidth());
                }
            }
            if (i9 <= 0) {
                return;
            }
            if (i9 * childCount <= getMeasuredWidth() - (((int) k0.c(getContext(), 16)) * 2)) {
                boolean z6 = false;
                for (int i11 = 0; i11 < childCount; i11++) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i11).getLayoutParams();
                    if (layoutParams.width != i9 || layoutParams.weight != CropImageView.DEFAULT_ASPECT_RATIO) {
                        layoutParams.width = i9;
                        layoutParams.weight = CropImageView.DEFAULT_ASPECT_RATIO;
                        z6 = true;
                    }
                }
                if (!z6) {
                    return;
                }
            } else {
                tabLayout.f4236z = 0;
                tabLayout.n(false);
            }
            super.onMeasure(i7, i8);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
    }
}
