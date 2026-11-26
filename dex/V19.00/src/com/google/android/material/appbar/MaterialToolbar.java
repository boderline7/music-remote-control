package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import c0.g;
import com.google.android.material.internal.f0;
import java.util.ArrayList;
import java.util.Collections;
import k.o;
import l6.f;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialToolbar extends Toolbar {

    /* renamed from: c0, reason: collision with root package name */
    public static final int f3266c0 = l.Widget_MaterialComponents_Toolbar;

    /* renamed from: d0, reason: collision with root package name */
    public static final ImageView.ScaleType[] f3267d0 = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public Integer U;
    public boolean V;
    public boolean W;

    /* renamed from: a0, reason: collision with root package name */
    public ImageView.ScaleType f3268a0;

    /* renamed from: b0, reason: collision with root package name */
    public Boolean f3269b0;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.toolbarStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialToolbar(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.appbar.MaterialToolbar.f3266c0
            android.content.Context r8 = t3.a.a(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            android.content.Context r8 = r7.getContext()
            int[] r2 = q2.m.MaterialToolbar
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r8
            r1 = r9
            r3 = r10
            android.content.res.TypedArray r9 = com.google.android.material.internal.e0.d(r0, r1, r2, r3, r4, r5)
            int r10 = q2.m.MaterialToolbar_navigationIconTint
            boolean r10 = r9.hasValue(r10)
            r0 = -1
            if (r10 == 0) goto L2b
            int r10 = q2.m.MaterialToolbar_navigationIconTint
            int r10 = r9.getColor(r10, r0)
            r7.setNavigationIconTint(r10)
        L2b:
            int r10 = q2.m.MaterialToolbar_titleCentered
            boolean r10 = r9.getBoolean(r10, r6)
            r7.V = r10
            int r10 = q2.m.MaterialToolbar_subtitleCentered
            boolean r10 = r9.getBoolean(r10, r6)
            r7.W = r10
            int r10 = q2.m.MaterialToolbar_logoScaleType
            int r10 = r9.getInt(r10, r0)
            if (r10 < 0) goto L4c
            android.widget.ImageView$ScaleType[] r0 = com.google.android.material.appbar.MaterialToolbar.f3267d0
            int r1 = r0.length
            if (r10 >= r1) goto L4c
            r10 = r0[r10]
            r7.f3268a0 = r10
        L4c:
            int r10 = q2.m.MaterialToolbar_logoAdjustViewBounds
            boolean r10 = r9.hasValue(r10)
            if (r10 == 0) goto L60
            int r10 = q2.m.MaterialToolbar_logoAdjustViewBounds
            boolean r10 = r9.getBoolean(r10, r6)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r7.f3269b0 = r10
        L60:
            r9.recycle()
            android.graphics.drawable.Drawable r9 = r7.getBackground()
            if (r9 != 0) goto L6e
            android.content.res.ColorStateList r9 = android.content.res.ColorStateList.valueOf(r6)
            goto L72
        L6e:
            android.content.res.ColorStateList r9 = d3.a.d(r9)
        L72:
            if (r9 == 0) goto L8b
            p3.j r10 = new p3.j
            r10.<init>()
            r10.o(r9)
            r10.l(r8)
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            float r8 = p0.r0.i(r7)
            r10.n(r8)
            p0.l0.q(r7, r10)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.MaterialToolbar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.f3268a0;
    }

    public Integer getNavigationIconTint() {
        return this.U;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void n(int i7) {
        Menu menu = getMenu();
        boolean z6 = menu instanceof o;
        if (z6) {
            ((o) menu).y();
        }
        super.n(i7);
        if (z6) {
            ((o) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        f.A0(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        ImageView imageView;
        Drawable drawable;
        super.onLayout(z6, i7, i8, i9, i10);
        int i11 = 0;
        ImageView imageView2 = null;
        if (this.V || this.W) {
            ArrayList c7 = f0.c(this, getTitle());
            boolean isEmpty = c7.isEmpty();
            g gVar = f0.f3856a;
            TextView textView = isEmpty ? null : (TextView) Collections.min(c7, gVar);
            ArrayList c8 = f0.c(this, getSubtitle());
            TextView textView2 = c8.isEmpty() ? null : (TextView) Collections.max(c8, gVar);
            if (textView != null || textView2 != null) {
                int measuredWidth = getMeasuredWidth();
                int i12 = measuredWidth / 2;
                int paddingLeft = getPaddingLeft();
                int paddingRight = measuredWidth - getPaddingRight();
                for (int i13 = 0; i13 < getChildCount(); i13++) {
                    View childAt = getChildAt(i13);
                    if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                        if (childAt.getRight() < i12 && childAt.getRight() > paddingLeft) {
                            paddingLeft = childAt.getRight();
                        }
                        if (childAt.getLeft() > i12 && childAt.getLeft() < paddingRight) {
                            paddingRight = childAt.getLeft();
                        }
                    }
                }
                Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
                if (this.V && textView != null) {
                    y(textView, pair);
                }
                if (this.W && textView2 != null) {
                    y(textView2, pair);
                }
            }
        }
        Drawable logo = getLogo();
        if (logo != null) {
            while (true) {
                if (i11 >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(i11);
                if ((childAt2 instanceof ImageView) && (drawable = (imageView = (ImageView) childAt2).getDrawable()) != null && drawable.getConstantState() != null && drawable.getConstantState().equals(logo.getConstantState())) {
                    imageView2 = imageView;
                    break;
                }
                i11++;
            }
        }
        if (imageView2 != null) {
            Boolean bool = this.f3269b0;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.f3268a0;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        f.x0(this, f7);
    }

    public void setLogoAdjustViewBounds(boolean z6) {
        Boolean bool = this.f3269b0;
        if (bool == null || bool.booleanValue() != z6) {
            this.f3269b0 = Boolean.valueOf(z6);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.f3268a0 != scaleType) {
            this.f3268a0 = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.U != null) {
            drawable = drawable.mutate();
            j0.b.g(drawable, this.U.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i7) {
        this.U = Integer.valueOf(i7);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z6) {
        if (this.W != z6) {
            this.W = z6;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z6) {
        if (this.V != z6) {
            this.V = z6;
            requestLayout();
        }
    }

    public final void y(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i7 = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i8 = measuredWidth2 + i7;
        int max = Math.max(Math.max(((Integer) pair.first).intValue() - i7, 0), Math.max(i8 - ((Integer) pair.second).intValue(), 0));
        if (max > 0) {
            i7 += max;
            i8 -= max;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i8 - i7, v.c.EXACTLY), textView.getMeasuredHeightAndState());
        }
        textView.layout(i7, textView.getTop(), i8, textView.getBottom());
    }
}
