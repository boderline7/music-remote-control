package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.x3;
import androidx.core.widget.q;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import o0.d;
import p0.d1;
import p0.f0;
import p0.l0;
import p0.m0;
import p0.o;
import p0.o0;
import p0.t0;
import q0.h;
import q1.e;
import q2.i;
import q2.k;
import q2.l;
import s3.f;
import s3.g;

@e
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class TabLayout extends HorizontalScrollView {
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    public static final int U = l.Widget_Design_TabLayout;
    public static final d V = new d(16);
    public final int A;
    public int B;
    public int C;
    public boolean D;
    public boolean E;
    public int F;
    public int G;
    public boolean H;
    public a I;
    public final TimeInterpolator J;
    public s3.c K;
    public final ArrayList L;
    public g M;
    public ValueAnimator N;
    public ViewPager O;
    public f P;
    public s3.b Q;
    public boolean R;
    public int S;
    public final s.e T;

    /* renamed from: a, reason: collision with root package name */
    public int f4211a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f4212b;

    /* renamed from: c, reason: collision with root package name */
    public b f4213c;

    /* renamed from: d, reason: collision with root package name */
    public final s3.e f4214d;

    /* renamed from: e, reason: collision with root package name */
    public final int f4215e;

    /* renamed from: f, reason: collision with root package name */
    public final int f4216f;

    /* renamed from: g, reason: collision with root package name */
    public final int f4217g;

    /* renamed from: h, reason: collision with root package name */
    public final int f4218h;

    /* renamed from: i, reason: collision with root package name */
    public final int f4219i;

    /* renamed from: j, reason: collision with root package name */
    public final int f4220j;

    /* renamed from: k, reason: collision with root package name */
    public final int f4221k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f4222l;

    /* renamed from: m, reason: collision with root package name */
    public ColorStateList f4223m;

    /* renamed from: n, reason: collision with root package name */
    public ColorStateList f4224n;

    /* renamed from: o, reason: collision with root package name */
    public Drawable f4225o;

    /* renamed from: p, reason: collision with root package name */
    public int f4226p;

    /* renamed from: q, reason: collision with root package name */
    public final PorterDuff.Mode f4227q;

    /* renamed from: r, reason: collision with root package name */
    public final float f4228r;

    /* renamed from: s, reason: collision with root package name */
    public final float f4229s;

    /* renamed from: t, reason: collision with root package name */
    public final int f4230t;

    /* renamed from: u, reason: collision with root package name */
    public int f4231u;

    /* renamed from: v, reason: collision with root package name */
    public final int f4232v;

    /* renamed from: w, reason: collision with root package name */
    public final int f4233w;

    /* renamed from: x, reason: collision with root package name */
    public final int f4234x;

    /* renamed from: y, reason: collision with root package name */
    public final int f4235y;

    /* renamed from: z, reason: collision with root package name */
    public int f4236z;

    public final class TabView extends LinearLayout {

        /* renamed from: l, reason: collision with root package name */
        public static final /* synthetic */ int f4237l = 0;

        /* renamed from: a, reason: collision with root package name */
        public b f4238a;

        /* renamed from: b, reason: collision with root package name */
        public TextView f4239b;

        /* renamed from: c, reason: collision with root package name */
        public ImageView f4240c;

        /* renamed from: d, reason: collision with root package name */
        public View f4241d;

        /* renamed from: e, reason: collision with root package name */
        public t2.a f4242e;

        /* renamed from: f, reason: collision with root package name */
        public View f4243f;

        /* renamed from: g, reason: collision with root package name */
        public TextView f4244g;

        /* renamed from: h, reason: collision with root package name */
        public ImageView f4245h;

        /* renamed from: i, reason: collision with root package name */
        public Drawable f4246i;

        /* renamed from: j, reason: collision with root package name */
        public int f4247j;

        public TabView(Context context) {
            super(context);
            this.f4247j = 2;
            e(context);
            int i7 = TabLayout.this.f4215e;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            m0.k(this, i7, TabLayout.this.f4216f, TabLayout.this.f4217g, TabLayout.this.f4218h);
            setGravity(17);
            setOrientation(!TabLayout.this.D ? 1 : 0);
            setClickable(true);
            t0.d(this, f0.b(getContext(), 1002));
        }

        private t2.a getBadge() {
            return this.f4242e;
        }

        private t2.a getOrCreateBadge() {
            if (this.f4242e == null) {
                this.f4242e = new t2.a(getContext(), null);
            }
            b();
            t2.a aVar = this.f4242e;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void a() {
            if (this.f4242e != null) {
                setClipChildren(true);
                setClipToPadding(true);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                    viewGroup.setClipToPadding(true);
                }
                View view = this.f4241d;
                if (view != null) {
                    t2.a aVar = this.f4242e;
                    if (aVar != null) {
                        if (t2.c.USE_COMPAT_PARENT || aVar.d() != null) {
                            aVar.d().setForeground(null);
                        } else {
                            view.getOverlay().remove(aVar);
                        }
                    }
                    this.f4241d = null;
                }
            }
        }

        public final void b() {
            b bVar;
            if (this.f4242e != null) {
                if (this.f4243f != null) {
                    a();
                    return;
                }
                ImageView imageView = this.f4240c;
                FrameLayout frameLayout = null;
                if (imageView != null && (bVar = this.f4238a) != null && bVar.f4249a != null) {
                    if (this.f4241d == imageView) {
                        c(imageView);
                        return;
                    }
                    a();
                    ImageView imageView2 = this.f4240c;
                    if (this.f4242e == null || imageView2 == null) {
                        return;
                    }
                    setClipChildren(false);
                    setClipToPadding(false);
                    ViewGroup viewGroup = (ViewGroup) getParent();
                    if (viewGroup != null) {
                        viewGroup.setClipChildren(false);
                        viewGroup.setClipToPadding(false);
                    }
                    t2.a aVar = this.f4242e;
                    if ((imageView2 == this.f4240c || imageView2 == this.f4239b) && t2.c.USE_COMPAT_PARENT) {
                        frameLayout = (FrameLayout) imageView2.getParent();
                    }
                    t2.c.a(aVar, imageView2, frameLayout);
                    this.f4241d = imageView2;
                    return;
                }
                TextView textView = this.f4239b;
                if (textView == null || this.f4238a == null) {
                    a();
                    return;
                }
                if (this.f4241d == textView) {
                    c(textView);
                    return;
                }
                a();
                TextView textView2 = this.f4239b;
                if (this.f4242e == null || textView2 == null) {
                    return;
                }
                setClipChildren(false);
                setClipToPadding(false);
                ViewGroup viewGroup2 = (ViewGroup) getParent();
                if (viewGroup2 != null) {
                    viewGroup2.setClipChildren(false);
                    viewGroup2.setClipToPadding(false);
                }
                t2.a aVar2 = this.f4242e;
                if ((textView2 == this.f4240c || textView2 == this.f4239b) && t2.c.USE_COMPAT_PARENT) {
                    frameLayout = (FrameLayout) textView2.getParent();
                }
                t2.c.a(aVar2, textView2, frameLayout);
                this.f4241d = textView2;
            }
        }

        public final void c(View view) {
            t2.a aVar = this.f4242e;
            if (aVar == null || view != this.f4241d) {
                return;
            }
            FrameLayout frameLayout = null;
            if ((view == this.f4240c || view == this.f4239b) && t2.c.USE_COMPAT_PARENT) {
                frameLayout = (FrameLayout) view.getParent();
            }
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            aVar.setBounds(rect);
            aVar.i(view, frameLayout);
        }

        public final void d() {
            boolean z6;
            f();
            b bVar = this.f4238a;
            if (bVar != null) {
                TabLayout tabLayout = bVar.f4254f;
                if (tabLayout == null) {
                    throw new IllegalArgumentException("Tab not attached to a TabLayout");
                }
                int selectedTabPosition = tabLayout.getSelectedTabPosition();
                if (selectedTabPosition != -1 && selectedTabPosition == bVar.f4252d) {
                    z6 = true;
                    setSelected(z6);
                }
            }
            z6 = false;
            setSelected(z6);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.f4246i;
            if (drawable != null && drawable.isStateful() && this.f4246i.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [android.graphics.drawable.RippleDrawable] */
        public final void e(Context context) {
            TabLayout tabLayout = TabLayout.this;
            int i7 = tabLayout.f4230t;
            if (i7 != 0) {
                Drawable A = l6.f.A(context, i7);
                this.f4246i = A;
                if (A != null && A.isStateful()) {
                    this.f4246i.setState(getDrawableState());
                }
            } else {
                this.f4246i = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            if (tabLayout.f4224n != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList a7 = n3.a.a(tabLayout.f4224n);
                boolean z6 = tabLayout.H;
                if (z6) {
                    gradientDrawable = null;
                }
                gradientDrawable = new RippleDrawable(a7, gradientDrawable, z6 ? null : gradientDrawable2);
            }
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.q(this, gradientDrawable);
            tabLayout.invalidate();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void f() {
            int i7;
            FrameLayout frameLayout;
            FrameLayout frameLayout2;
            ViewParent parent;
            b bVar = this.f4238a;
            View view = bVar != null ? bVar.f4253e : null;
            if (view != null) {
                ViewParent parent2 = view.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(view);
                    }
                    View view2 = this.f4243f;
                    if (view2 != null && (parent = view2.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.f4243f);
                    }
                    addView(view);
                }
                this.f4243f = view;
                TextView textView = this.f4239b;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f4240c;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f4240c.setImageDrawable(null);
                }
                TextView textView2 = (TextView) view.findViewById(R.id.text1);
                this.f4244g = textView2;
                if (textView2 != null) {
                    this.f4247j = q.b(textView2);
                }
                this.f4245h = (ImageView) view.findViewById(R.id.icon);
            } else {
                View view3 = this.f4243f;
                if (view3 != null) {
                    removeView(view3);
                    this.f4243f = null;
                }
                this.f4244g = null;
                this.f4245h = null;
            }
            if (this.f4243f == null) {
                if (this.f4240c == null) {
                    if (t2.c.USE_COMPAT_PARENT) {
                        frameLayout2 = new FrameLayout(getContext());
                        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
                        addView(frameLayout2, 0);
                    } else {
                        frameLayout2 = this;
                    }
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(i.design_layout_tab_icon, (ViewGroup) frameLayout2, false);
                    this.f4240c = imageView2;
                    frameLayout2.addView(imageView2, 0);
                }
                if (this.f4239b == null) {
                    if (t2.c.USE_COMPAT_PARENT) {
                        frameLayout = new FrameLayout(getContext());
                        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
                        addView(frameLayout);
                    } else {
                        frameLayout = this;
                    }
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(i.design_layout_tab_text, (ViewGroup) frameLayout, false);
                    this.f4239b = textView3;
                    frameLayout.addView(textView3);
                    this.f4247j = q.b(this.f4239b);
                }
                TextView textView4 = this.f4239b;
                TabLayout tabLayout = TabLayout.this;
                textView4.setTextAppearance(tabLayout.f4219i);
                if (!isSelected() || (i7 = tabLayout.f4221k) == -1) {
                    this.f4239b.setTextAppearance(tabLayout.f4220j);
                } else {
                    this.f4239b.setTextAppearance(i7);
                }
                ColorStateList colorStateList = tabLayout.f4222l;
                if (colorStateList != null) {
                    this.f4239b.setTextColor(colorStateList);
                }
                g(this.f4239b, this.f4240c, true);
                b();
                ImageView imageView3 = this.f4240c;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new c(this, imageView3));
                }
                TextView textView5 = this.f4239b;
                if (textView5 != null) {
                    textView5.addOnLayoutChangeListener(new c(this, textView5));
                }
            } else {
                TextView textView6 = this.f4244g;
                if (textView6 != null || this.f4245h != null) {
                    g(textView6, this.f4245h, false);
                }
            }
            if (bVar == null || TextUtils.isEmpty(bVar.f4251c)) {
                return;
            }
            setContentDescription(bVar.f4251c);
        }

        public final void g(TextView textView, ImageView imageView, boolean z6) {
            Drawable drawable;
            b bVar = this.f4238a;
            Drawable mutate = (bVar == null || (drawable = bVar.f4249a) == null) ? null : drawable.mutate();
            TabLayout tabLayout = TabLayout.this;
            if (mutate != null) {
                j0.b.h(mutate, tabLayout.f4223m);
                PorterDuff.Mode mode = tabLayout.f4227q;
                if (mode != null) {
                    j0.b.i(mutate, mode);
                }
            }
            b bVar2 = this.f4238a;
            CharSequence charSequence = bVar2 != null ? bVar2.f4250b : null;
            if (imageView != null) {
                if (mutate != null) {
                    imageView.setImageDrawable(mutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z7 = true;
            boolean z8 = !TextUtils.isEmpty(charSequence);
            if (textView != null) {
                if (z8) {
                    this.f4238a.getClass();
                } else {
                    z7 = false;
                }
                textView.setText(z8 ? charSequence : null);
                textView.setVisibility(z7 ? 0 : 8);
                if (z8) {
                    setVisibility(0);
                }
            } else {
                z7 = false;
            }
            if (z6 && imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int c7 = (z7 && imageView.getVisibility() == 0) ? (int) k0.c(getContext(), 8) : 0;
                if (tabLayout.D) {
                    if (c7 != o.b(marginLayoutParams)) {
                        o.g(marginLayoutParams, c7);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (c7 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = c7;
                    o.g(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            b bVar3 = this.f4238a;
            CharSequence charSequence2 = bVar3 != null ? bVar3.f4251c : null;
            if (!z8) {
                charSequence = charSequence2;
            }
            x3.a(this, charSequence);
        }

        public int getContentHeight() {
            View[] viewArr = {this.f4239b, this.f4240c, this.f4243f};
            int i7 = 0;
            int i8 = 0;
            boolean z6 = false;
            for (int i9 = 0; i9 < 3; i9++) {
                View view = viewArr[i9];
                if (view != null && view.getVisibility() == 0) {
                    i8 = z6 ? Math.min(i8, view.getTop()) : view.getTop();
                    i7 = z6 ? Math.max(i7, view.getBottom()) : view.getBottom();
                    z6 = true;
                }
            }
            return i7 - i8;
        }

        public int getContentWidth() {
            View[] viewArr = {this.f4239b, this.f4240c, this.f4243f};
            int i7 = 0;
            int i8 = 0;
            boolean z6 = false;
            for (int i9 = 0; i9 < 3; i9++) {
                View view = viewArr[i9];
                if (view != null && view.getVisibility() == 0) {
                    i8 = z6 ? Math.min(i8, view.getLeft()) : view.getLeft();
                    i7 = z6 ? Math.max(i7, view.getRight()) : view.getRight();
                    z6 = true;
                }
            }
            return i7 - i8;
        }

        public b getTab() {
            return this.f4238a;
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            t2.a aVar = this.f4242e;
            if (aVar != null && aVar.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f4242e.c()));
            }
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) androidx.fragment.app.o.b(0, 1, this.f4238a.f4252d, 1, false, isSelected()).f2027a);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) q0.g.ACTION_CLICK.f8421a);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(k.item_view_role_description));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public final void onMeasure(int i7, int i8) {
            int size = View.MeasureSpec.getSize(i7);
            int mode = View.MeasureSpec.getMode(i7);
            TabLayout tabLayout = TabLayout.this;
            int tabMaxWidth = tabLayout.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i7 = View.MeasureSpec.makeMeasureSpec(tabLayout.f4231u, Integer.MIN_VALUE);
            }
            super.onMeasure(i7, i8);
            if (this.f4239b != null) {
                float f7 = tabLayout.f4228r;
                int i9 = this.f4247j;
                ImageView imageView = this.f4240c;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f4239b;
                    if (textView != null && textView.getLineCount() > 1) {
                        f7 = tabLayout.f4229s;
                    }
                } else {
                    i9 = 1;
                }
                float textSize = this.f4239b.getTextSize();
                int lineCount = this.f4239b.getLineCount();
                int b7 = q.b(this.f4239b);
                if (f7 != textSize || (b7 >= 0 && i9 != b7)) {
                    if (tabLayout.C == 1 && f7 > textSize && lineCount == 1) {
                        Layout layout = this.f4239b.getLayout();
                        if (layout == null) {
                            return;
                        }
                        if ((f7 / layout.getPaint().getTextSize()) * layout.getLineWidth(0) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) {
                            return;
                        }
                    }
                    this.f4239b.setTextSize(0, f7);
                    this.f4239b.setMaxLines(i9);
                    super.onMeasure(i7, i8);
                }
            }
        }

        @Override // android.view.View
        public final boolean performClick() {
            boolean performClick = super.performClick();
            if (this.f4238a == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            b bVar = this.f4238a;
            TabLayout tabLayout = bVar.f4254f;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.k(bVar, true);
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z6) {
            isSelected();
            super.setSelected(z6);
            TextView textView = this.f4239b;
            if (textView != null) {
                textView.setSelected(z6);
            }
            ImageView imageView = this.f4240c;
            if (imageView != null) {
                imageView.setSelected(z6);
            }
            View view = this.f4243f;
            if (view != null) {
                view.setSelected(z6);
            }
        }

        public void setTab(b bVar) {
            if (bVar != this.f4238a) {
                this.f4238a = bVar;
                d();
            }
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.tabStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TabLayout(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static ColorStateList f(int i7, int i8) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i8, i7});
    }

    private int getDefaultHeight() {
        ArrayList arrayList = this.f4212b;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                break;
            }
            b bVar = (b) arrayList.get(i7);
            if (bVar == null || bVar.f4249a == null || TextUtils.isEmpty(bVar.f4250b)) {
                i7++;
            } else if (!this.D) {
                return 72;
            }
        }
        return 48;
    }

    private int getTabMinWidth() {
        int i7 = this.f4232v;
        if (i7 != -1) {
            return i7;
        }
        int i8 = this.C;
        if (i8 == 0 || i8 == 2) {
            return this.f4234x;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.f4214d.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i7) {
        s3.e eVar = this.f4214d;
        int childCount = eVar.getChildCount();
        if (i7 < childCount) {
            int i8 = 0;
            while (i8 < childCount) {
                View childAt = eVar.getChildAt(i8);
                if ((i8 != i7 || childAt.isSelected()) && (i8 == i7 || !childAt.isSelected())) {
                    childAt.setSelected(i8 == i7);
                    childAt.setActivated(i8 == i7);
                } else {
                    childAt.setSelected(i8 == i7);
                    childAt.setActivated(i8 == i7);
                    if (childAt instanceof TabView) {
                        ((TabView) childAt).f();
                    }
                }
                i8++;
            }
        }
    }

    public final void a(b bVar, boolean z6) {
        ArrayList arrayList = this.f4212b;
        int size = arrayList.size();
        if (bVar.f4254f != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        bVar.f4252d = size;
        arrayList.add(size, bVar);
        int size2 = arrayList.size();
        int i7 = -1;
        for (int i8 = size + 1; i8 < size2; i8++) {
            if (((b) arrayList.get(i8)).f4252d == this.f4211a) {
                i7 = i8;
            }
            ((b) arrayList.get(i8)).f4252d = i8;
        }
        this.f4211a = i7;
        TabView tabView = bVar.f4255g;
        tabView.setSelected(false);
        tabView.setActivated(false);
        int i9 = bVar.f4252d;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        if (this.C == 1 && this.f4236z == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        this.f4214d.addView(tabView, i9, layoutParams);
        if (z6) {
            TabLayout tabLayout = bVar.f4254f;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.k(bVar, true);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view) {
        b(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i7) {
        b(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        b(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        b(view);
    }

    public final void b(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        TabItem tabItem = (TabItem) view;
        b i7 = i();
        CharSequence charSequence = tabItem.f4208a;
        if (charSequence != null) {
            if (TextUtils.isEmpty(i7.f4251c) && !TextUtils.isEmpty(charSequence)) {
                i7.f4255g.setContentDescription(charSequence);
            }
            i7.f4250b = charSequence;
            TabView tabView = i7.f4255g;
            if (tabView != null) {
                tabView.d();
            }
        }
        Drawable drawable = tabItem.f4209b;
        if (drawable != null) {
            i7.b(drawable);
        }
        int i8 = tabItem.f4210c;
        if (i8 != 0) {
            i7.f4253e = LayoutInflater.from(i7.f4255g.getContext()).inflate(i8, (ViewGroup) i7.f4255g, false);
            TabView tabView2 = i7.f4255g;
            if (tabView2 != null) {
                tabView2.d();
            }
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            i7.f4251c = tabItem.getContentDescription();
            TabView tabView3 = i7.f4255g;
            if (tabView3 != null) {
                tabView3.d();
            }
        }
        a(i7, this.f4212b.isEmpty());
    }

    public final void c(int i7) {
        if (i7 == -1) {
            return;
        }
        if (getWindowToken() != null) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            if (o0.c(this)) {
                s3.e eVar = this.f4214d;
                int childCount = eVar.getChildCount();
                for (int i9 = 0; i9 < childCount; i9++) {
                    if (eVar.getChildAt(i9).getWidth() > 0) {
                    }
                }
                int scrollX = getScrollX();
                int e7 = e(CropImageView.DEFAULT_ASPECT_RATIO, i7);
                if (scrollX != e7) {
                    g();
                    this.N.setIntValues(scrollX, e7);
                    this.N.start();
                }
                ValueAnimator valueAnimator = eVar.f8696a;
                if (valueAnimator != null && valueAnimator.isRunning() && eVar.f8697b.f4211a != i7) {
                    eVar.f8696a.cancel();
                }
                eVar.d(i7, this.A, true);
                return;
            }
        }
        setScrollPosition(i7, CropImageView.DEFAULT_ASPECT_RATIO, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r0 != 2) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d() {
        /*
            r4 = this;
            int r0 = r4.C
            r1 = 2
            r2 = 0
            if (r0 == 0) goto Lb
            if (r0 != r1) goto L9
            goto Lb
        L9:
            r0 = r2
            goto L14
        Lb:
            int r0 = r4.f4235y
            int r3 = r4.f4215e
            int r0 = r0 - r3
            int r0 = java.lang.Math.max(r2, r0)
        L14:
            int r3 = p0.d1.OVER_SCROLL_ALWAYS
            s3.e r3 = r4.f4214d
            p0.m0.k(r3, r0, r2, r2, r2)
            int r0 = r4.C
            r2 = 1
            if (r0 == 0) goto L29
            if (r0 == r2) goto L25
            if (r0 == r1) goto L25
            goto L3c
        L25:
            r3.setGravity(r2)
            goto L3c
        L29:
            int r0 = r4.f4236z
            if (r0 == 0) goto L36
            if (r0 == r2) goto L32
            if (r0 == r1) goto L36
            goto L3c
        L32:
            r3.setGravity(r2)
            goto L3c
        L36:
            r0 = 8388611(0x800003, float:1.1754948E-38)
            r3.setGravity(r0)
        L3c:
            r4.n(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.d():void");
    }

    public final int e(float f7, int i7) {
        s3.e eVar;
        View childAt;
        int i8 = this.C;
        if ((i8 != 0 && i8 != 2) || (childAt = (eVar = this.f4214d).getChildAt(i7)) == null) {
            return 0;
        }
        int i9 = i7 + 1;
        View childAt2 = i9 < eVar.getChildCount() ? eVar.getChildAt(i9) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i10 = (int) ((width + width2) * 0.5f * f7);
        int i11 = d1.OVER_SCROLL_ALWAYS;
        return m0.d(this) == 0 ? left + i10 : left - i10;
    }

    public final void g() {
        if (this.N == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.N = valueAnimator;
            valueAnimator.setInterpolator(this.J);
            this.N.setDuration(this.A);
            this.N.addUpdateListener(new s2.d(3, this));
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        b bVar = this.f4213c;
        if (bVar != null) {
            return bVar.f4252d;
        }
        return -1;
    }

    public int getTabCount() {
        return this.f4212b.size();
    }

    public int getTabGravity() {
        return this.f4236z;
    }

    public ColorStateList getTabIconTint() {
        return this.f4223m;
    }

    public int getTabIndicatorAnimationMode() {
        return this.G;
    }

    public int getTabIndicatorGravity() {
        return this.B;
    }

    public int getTabMaxWidth() {
        return this.f4231u;
    }

    public int getTabMode() {
        return this.C;
    }

    public ColorStateList getTabRippleColor() {
        return this.f4224n;
    }

    public Drawable getTabSelectedIndicator() {
        return this.f4225o;
    }

    public ColorStateList getTabTextColors() {
        return this.f4222l;
    }

    public final b h(int i7) {
        if (i7 < 0 || i7 >= getTabCount()) {
            return null;
        }
        return (b) this.f4212b.get(i7);
    }

    public final b i() {
        b bVar = (b) V.a();
        if (bVar == null) {
            bVar = new b();
            bVar.f4252d = -1;
            bVar.f4256h = -1;
        }
        bVar.f4254f = this;
        s.e eVar = this.T;
        TabView tabView = eVar != null ? (TabView) eVar.a() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        tabView.setTab(bVar);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(bVar.f4251c)) {
            tabView.setContentDescription(bVar.f4250b);
        } else {
            tabView.setContentDescription(bVar.f4251c);
        }
        bVar.f4255g = tabView;
        int i7 = bVar.f4256h;
        if (i7 != -1) {
            tabView.setId(i7);
        }
        return bVar;
    }

    public final void j() {
        s3.e eVar = this.f4214d;
        int childCount = eVar.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            TabView tabView = (TabView) eVar.getChildAt(childCount);
            eVar.removeViewAt(childCount);
            if (tabView != null) {
                tabView.setTab(null);
                tabView.setSelected(false);
                this.T.b(tabView);
            }
            requestLayout();
        }
        Iterator it = this.f4212b.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            it.remove();
            bVar.f4254f = null;
            bVar.f4255g = null;
            bVar.f4249a = null;
            bVar.f4256h = -1;
            bVar.f4250b = null;
            bVar.f4251c = null;
            bVar.f4252d = -1;
            bVar.f4253e = null;
            V.b(bVar);
        }
        this.f4213c = null;
    }

    public final void k(b bVar, boolean z6) {
        b bVar2 = this.f4213c;
        ArrayList arrayList = this.L;
        if (bVar2 == bVar) {
            if (bVar2 != null) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((s3.c) arrayList.get(size)).b();
                }
                c(bVar.f4252d);
                return;
            }
            return;
        }
        int i7 = bVar != null ? bVar.f4252d : -1;
        if (z6) {
            if ((bVar2 == null || bVar2.f4252d == -1) && i7 != -1) {
                setScrollPosition(i7, CropImageView.DEFAULT_ASPECT_RATIO, true);
            } else {
                c(i7);
            }
            if (i7 != -1) {
                setSelectedTabView(i7);
            }
        }
        this.f4213c = bVar;
        if (bVar2 != null && bVar2.f4254f != null) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                ((s3.c) arrayList.get(size2)).a();
            }
        }
        if (bVar != null) {
            for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                ((s3.c) arrayList.get(size3)).c(bVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0092, code lost:
    
        if (r10 == false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(int r6, float r7, boolean r8, boolean r9, boolean r10) {
        /*
            r5 = this;
            float r0 = (float) r6
            float r0 = r0 + r7
            int r1 = java.lang.Math.round(r0)
            if (r1 < 0) goto L9f
            s3.e r2 = r5.f4214d
            int r3 = r2.getChildCount()
            if (r1 < r3) goto L12
            goto L9f
        L12:
            if (r9 == 0) goto L3b
            r2.getClass()
            int r9 = java.lang.Math.round(r0)
            com.google.android.material.tabs.TabLayout r0 = r2.f8697b
            r0.f4211a = r9
            android.animation.ValueAnimator r9 = r2.f8696a
            if (r9 == 0) goto L2e
            boolean r9 = r9.isRunning()
            if (r9 == 0) goto L2e
            android.animation.ValueAnimator r9 = r2.f8696a
            r9.cancel()
        L2e:
            android.view.View r9 = r2.getChildAt(r6)
            int r0 = r6 + 1
            android.view.View r0 = r2.getChildAt(r0)
            r2.c(r9, r0, r7)
        L3b:
            android.animation.ValueAnimator r9 = r5.N
            if (r9 == 0) goto L4a
            boolean r9 = r9.isRunning()
            if (r9 == 0) goto L4a
            android.animation.ValueAnimator r9 = r5.N
            r9.cancel()
        L4a:
            int r7 = r5.e(r7, r6)
            int r9 = r5.getScrollX()
            int r0 = r5.getSelectedTabPosition()
            r2 = 0
            r3 = 1
            if (r6 >= r0) goto L5c
            if (r7 >= r9) goto L6a
        L5c:
            int r0 = r5.getSelectedTabPosition()
            if (r6 <= r0) goto L64
            if (r7 <= r9) goto L6a
        L64:
            int r0 = r5.getSelectedTabPosition()
            if (r6 != r0) goto L6c
        L6a:
            r0 = r3
            goto L6d
        L6c:
            r0 = r2
        L6d:
            int r4 = p0.d1.OVER_SCROLL_ALWAYS
            int r4 = p0.m0.d(r5)
            if (r4 != r3) goto L8c
            int r0 = r5.getSelectedTabPosition()
            if (r6 >= r0) goto L7d
            if (r7 <= r9) goto L94
        L7d:
            int r0 = r5.getSelectedTabPosition()
            if (r6 <= r0) goto L85
            if (r7 >= r9) goto L94
        L85:
            int r9 = r5.getSelectedTabPosition()
            if (r6 != r9) goto L8e
            goto L94
        L8c:
            if (r0 != 0) goto L94
        L8e:
            int r9 = r5.S
            if (r9 == r3) goto L94
            if (r10 == 0) goto L9a
        L94:
            if (r6 >= 0) goto L97
            r7 = r2
        L97:
            r5.scrollTo(r7, r2)
        L9a:
            if (r8 == 0) goto L9f
            r5.setSelectedTabView(r1)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.l(int, float, boolean, boolean, boolean):void");
    }

    public final void m(ViewPager viewPager, boolean z6, boolean z7) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ViewPager viewPager2 = this.O;
        if (viewPager2 != null) {
            f fVar = this.P;
            if (fVar != null && (arrayList2 = viewPager2.D) != null) {
                arrayList2.remove(fVar);
            }
            s3.b bVar = this.Q;
            if (bVar != null && (arrayList = this.O.G) != null) {
                arrayList.remove(bVar);
            }
        }
        g gVar = this.M;
        ArrayList arrayList3 = this.L;
        if (gVar != null) {
            arrayList3.remove(gVar);
            this.M = null;
        }
        if (viewPager != null) {
            this.O = viewPager;
            if (this.P == null) {
                this.P = new f(this);
            }
            f fVar2 = this.P;
            fVar2.f8700c = 0;
            fVar2.f8699b = 0;
            if (viewPager.D == null) {
                viewPager.D = new ArrayList();
            }
            viewPager.D.add(fVar2);
            g gVar2 = new g(viewPager);
            this.M = gVar2;
            if (!arrayList3.contains(gVar2)) {
                arrayList3.add(gVar2);
            }
            viewPager.getAdapter();
            if (this.Q == null) {
                this.Q = new s3.b(this);
            }
            s3.b bVar2 = this.Q;
            bVar2.getClass();
            if (viewPager.G == null) {
                viewPager.G = new ArrayList();
            }
            viewPager.G.add(bVar2);
            setScrollPosition(viewPager.getCurrentItem(), CropImageView.DEFAULT_ASPECT_RATIO, true);
        } else {
            this.O = null;
            j();
        }
        this.R = z7;
    }

    public final void n(boolean z6) {
        int i7 = 0;
        while (true) {
            s3.e eVar = this.f4214d;
            if (i7 >= eVar.getChildCount()) {
                return;
            }
            View childAt = eVar.getChildAt(i7);
            childAt.setMinimumWidth(getTabMinWidth());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (this.C == 1 && this.f4236z == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.width = -2;
                layoutParams.weight = CropImageView.DEFAULT_ASPECT_RATIO;
            }
            if (z6) {
                childAt.requestLayout();
            }
            i7++;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.A0(this);
        if (this.O == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                m((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.R) {
            setupWithViewPager(null);
            this.R = false;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        TabView tabView;
        Drawable drawable;
        int i7 = 0;
        while (true) {
            s3.e eVar = this.f4214d;
            if (i7 >= eVar.getChildCount()) {
                super.onDraw(canvas);
                return;
            }
            View childAt = eVar.getChildAt(i7);
            if ((childAt instanceof TabView) && (drawable = (tabView = (TabView) childAt).f4246i) != null) {
                drawable.setBounds(tabView.getLeft(), tabView.getTop(), tabView.getRight(), tabView.getBottom());
                tabView.f4246i.draw(canvas);
            }
            i7++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) h.a(1, getTabCount(), 1).f8425a);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (getTabMode() == 0 || getTabMode() == 2) && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int round = Math.round(k0.c(getContext(), getDefaultHeight()));
        int mode = View.MeasureSpec.getMode(i8);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                i8 = View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + round, v.c.EXACTLY);
            }
        } else if (getChildCount() == 1 && View.MeasureSpec.getSize(i8) >= round) {
            getChildAt(0).setMinimumHeight(round);
        }
        int size = View.MeasureSpec.getSize(i7);
        if (View.MeasureSpec.getMode(i7) != 0) {
            int i9 = this.f4233w;
            if (i9 <= 0) {
                i9 = (int) (size - k0.c(getContext(), 56));
            }
            this.f4231u = i9;
        }
        super.onMeasure(i7, i8);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i10 = this.C;
            if (i10 != 0) {
                if (i10 == 1) {
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        return;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), v.c.EXACTLY), ViewGroup.getChildMeasureSpec(i8, getPaddingBottom() + getPaddingTop(), childAt.getLayoutParams().height));
                }
                if (i10 != 2) {
                    return;
                }
            }
            if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), v.c.EXACTLY), ViewGroup.getChildMeasureSpec(i8, getPaddingBottom() + getPaddingTop(), childAt.getLayoutParams().height));
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || getTabMode() == 0 || getTabMode() == 2) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        l6.f.x0(this, f7);
    }

    public void setInlineLabel(boolean z6) {
        if (this.D == z6) {
            return;
        }
        this.D = z6;
        int i7 = 0;
        while (true) {
            s3.e eVar = this.f4214d;
            if (i7 >= eVar.getChildCount()) {
                d();
                return;
            }
            View childAt = eVar.getChildAt(i7);
            if (childAt instanceof TabView) {
                TabView tabView = (TabView) childAt;
                tabView.setOrientation(!TabLayout.this.D ? 1 : 0);
                TextView textView = tabView.f4244g;
                if (textView == null && tabView.f4245h == null) {
                    tabView.g(tabView.f4239b, tabView.f4240c, true);
                } else {
                    tabView.g(textView, tabView.f4245h, false);
                }
            }
            i7++;
        }
    }

    public void setInlineLabelResource(int i7) {
        setInlineLabel(getResources().getBoolean(i7));
    }

    @Deprecated
    public void setOnTabSelectedListener(s3.c cVar) {
        s3.c cVar2 = this.K;
        ArrayList arrayList = this.L;
        if (cVar2 != null) {
            arrayList.remove(cVar2);
        }
        this.K = cVar;
        if (cVar == null || arrayList.contains(cVar)) {
            return;
        }
        arrayList.add(cVar);
    }

    @Deprecated
    public void setOnTabSelectedListener(s3.d dVar) {
        setOnTabSelectedListener((s3.c) dVar);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        g();
        this.N.addListener(animatorListener);
    }

    public void setScrollPosition(int i7, float f7, boolean z6) {
        setScrollPosition(i7, f7, z6, true);
    }

    public void setScrollPosition(int i7, float f7, boolean z6, boolean z7) {
        l(i7, f7, z6, z7, true);
    }

    public void setSelectedTabIndicator(int i7) {
        if (i7 != 0) {
            setSelectedTabIndicator(l6.f.A(getContext(), i7));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable mutate = drawable.mutate();
        this.f4225o = mutate;
        int i7 = this.f4226p;
        if (i7 != 0) {
            j0.b.g(mutate, i7);
        } else {
            j0.b.h(mutate, null);
        }
        int i8 = this.F;
        if (i8 == -1) {
            i8 = this.f4225o.getIntrinsicHeight();
        }
        this.f4214d.b(i8);
    }

    public void setSelectedTabIndicatorColor(int i7) {
        this.f4226p = i7;
        Drawable drawable = this.f4225o;
        if (i7 != 0) {
            j0.b.g(drawable, i7);
        } else {
            j0.b.h(drawable, null);
        }
        n(false);
    }

    public void setSelectedTabIndicatorGravity(int i7) {
        if (this.B != i7) {
            this.B = i7;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this.f4214d);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i7) {
        this.F = i7;
        this.f4214d.b(i7);
    }

    public void setTabGravity(int i7) {
        if (this.f4236z != i7) {
            this.f4236z = i7;
            d();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.f4223m != colorStateList) {
            this.f4223m = colorStateList;
            ArrayList arrayList = this.f4212b;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                TabView tabView = ((b) arrayList.get(i7)).f4255g;
                if (tabView != null) {
                    tabView.d();
                }
            }
        }
    }

    public void setTabIconTintResource(int i7) {
        setTabIconTint(g0.g.b(getContext(), i7));
    }

    public void setTabIndicatorAnimationMode(int i7) {
        this.G = i7;
        if (i7 == 0) {
            this.I = new a();
            return;
        }
        int i8 = 1;
        if (i7 == 1) {
            this.I = new s3.a(0);
        } else {
            if (i7 == 2) {
                this.I = new s3.a(i8);
                return;
            }
            throw new IllegalArgumentException(i7 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z6) {
        this.E = z6;
        int i7 = s3.e.f8695c;
        s3.e eVar = this.f4214d;
        eVar.a(eVar.f8697b.getSelectedTabPosition());
        int i8 = d1.OVER_SCROLL_ALWAYS;
        l0.k(eVar);
    }

    public void setTabMode(int i7) {
        if (i7 != this.C) {
            this.C = i7;
            d();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.f4224n == colorStateList) {
            return;
        }
        this.f4224n = colorStateList;
        int i7 = 0;
        while (true) {
            s3.e eVar = this.f4214d;
            if (i7 >= eVar.getChildCount()) {
                return;
            }
            View childAt = eVar.getChildAt(i7);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i8 = TabView.f4237l;
                ((TabView) childAt).e(context);
            }
            i7++;
        }
    }

    public void setTabRippleColorResource(int i7) {
        setTabRippleColor(g0.g.b(getContext(), i7));
    }

    public void setTabTextColors(int i7, int i8) {
        setTabTextColors(f(i7, i8));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.f4222l != colorStateList) {
            this.f4222l = colorStateList;
            ArrayList arrayList = this.f4212b;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                TabView tabView = ((b) arrayList.get(i7)).f4255g;
                if (tabView != null) {
                    tabView.d();
                }
            }
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(q1.a aVar) {
        j();
    }

    public void setUnboundedRipple(boolean z6) {
        if (this.H == z6) {
            return;
        }
        this.H = z6;
        int i7 = 0;
        while (true) {
            s3.e eVar = this.f4214d;
            if (i7 >= eVar.getChildCount()) {
                return;
            }
            View childAt = eVar.getChildAt(i7);
            if (childAt instanceof TabView) {
                Context context = getContext();
                int i8 = TabView.f4237l;
                ((TabView) childAt).e(context);
            }
            i7++;
        }
    }

    public void setUnboundedRippleResource(int i7) {
        setUnboundedRipple(getResources().getBoolean(i7));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(ViewPager viewPager, boolean z6) {
        m(viewPager, z6, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }
}
