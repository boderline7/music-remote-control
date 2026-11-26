package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.b0;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.f0;
import com.google.android.material.internal.k0;
import com.google.android.material.internal.x;
import com.yalantis.ucrop.view.CropImageView;
import n1.z;
import p0.d1;
import p0.m0;
import p0.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    public final SearchView f4101a;

    /* renamed from: b, reason: collision with root package name */
    public final View f4102b;

    /* renamed from: c, reason: collision with root package name */
    public final ClippableRoundedCornerLayout f4103c;

    /* renamed from: d, reason: collision with root package name */
    public final FrameLayout f4104d;

    /* renamed from: e, reason: collision with root package name */
    public final FrameLayout f4105e;

    /* renamed from: f, reason: collision with root package name */
    public final MaterialToolbar f4106f;

    /* renamed from: g, reason: collision with root package name */
    public final Toolbar f4107g;

    /* renamed from: h, reason: collision with root package name */
    public final TextView f4108h;

    /* renamed from: i, reason: collision with root package name */
    public final EditText f4109i;

    /* renamed from: j, reason: collision with root package name */
    public final ImageButton f4110j;

    /* renamed from: k, reason: collision with root package name */
    public final View f4111k;

    /* renamed from: l, reason: collision with root package name */
    public final TouchObserverFrameLayout f4112l;

    /* renamed from: m, reason: collision with root package name */
    public final j3.h f4113m;

    /* renamed from: n, reason: collision with root package name */
    public AnimatorSet f4114n;

    /* renamed from: o, reason: collision with root package name */
    public SearchBar f4115o;

    public n(SearchView searchView) {
        this.f4101a = searchView;
        this.f4102b = searchView.f4054a;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = searchView.f4055b;
        this.f4103c = clippableRoundedCornerLayout;
        this.f4104d = searchView.f4058e;
        this.f4105e = searchView.f4059f;
        this.f4106f = searchView.f4060g;
        this.f4107g = searchView.f4061h;
        this.f4108h = searchView.f4062i;
        this.f4109i = searchView.f4063j;
        this.f4110j = searchView.f4064k;
        this.f4111k = searchView.f4065l;
        this.f4112l = searchView.f4066m;
        this.f4113m = new j3.h(clippableRoundedCornerLayout);
    }

    public static void a(n nVar, float f7) {
        ActionMenuView a7;
        nVar.f4110j.setAlpha(f7);
        nVar.f4111k.setAlpha(f7);
        nVar.f4112l.setAlpha(f7);
        if (!nVar.f4101a.f4076w || (a7 = f0.a(nVar.f4106f)) == null) {
            return;
        }
        a7.setAlpha(f7);
    }

    public final void b(AnimatorSet animatorSet) {
        ImageButton b7 = f0.b(this.f4106f);
        if (b7 == null) {
            return;
        }
        Drawable p12 = z.p1(b7.getDrawable());
        if (!this.f4101a.f4075v) {
            if (p12 instanceof g.d) {
                ((g.d) p12).b(1.0f);
            }
            if (p12 instanceof com.google.android.material.internal.f) {
                ((com.google.android.material.internal.f) p12).a(1.0f);
                return;
            }
            return;
        }
        if (p12 instanceof g.d) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            ofFloat.addUpdateListener(new x2.b(4, (g.d) p12));
            animatorSet.playTogether(ofFloat);
        }
        if (p12 instanceof com.google.android.material.internal.f) {
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            ofFloat2.addUpdateListener(new x2.b(3, (com.google.android.material.internal.f) p12));
            animatorSet.playTogether(ofFloat2);
        }
    }

    public final AnimatorSet c(boolean z6) {
        AnimatorSet animatorSet = new AnimatorSet();
        MaterialToolbar materialToolbar = this.f4106f;
        ImageButton b7 = f0.b(materialToolbar);
        if (b7 != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f(b7), CropImageView.DEFAULT_ASPECT_RATIO);
            ofFloat.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(2), new View[]{b7}));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(g(), CropImageView.DEFAULT_ASPECT_RATIO);
            ofFloat2.addUpdateListener(androidx.swiperefreshlayout.widget.b.a(b7));
            animatorSet.playTogether(ofFloat, ofFloat2);
        }
        ActionMenuView a7 = f0.a(materialToolbar);
        if (a7 != null) {
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(e(a7), CropImageView.DEFAULT_ASPECT_RATIO);
            ofFloat3.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(2), new View[]{a7}));
            ValueAnimator ofFloat4 = ValueAnimator.ofFloat(g(), CropImageView.DEFAULT_ASPECT_RATIO);
            ofFloat4.addUpdateListener(androidx.swiperefreshlayout.widget.b.a(a7));
            animatorSet.playTogether(ofFloat3, ofFloat4);
        }
        animatorSet.setDuration(z6 ? 300L : 250L);
        animatorSet.setInterpolator(x.a(z6, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public final AnimatorSet d(boolean z6) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.f4114n == null) {
            Animator[] animatorArr = new Animator[2];
            AnimatorSet animatorSet2 = new AnimatorSet();
            b(animatorSet2);
            animatorSet2.setDuration(z6 ? 300L : 250L);
            animatorSet2.setInterpolator(x.a(z6, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
            animatorArr[0] = animatorSet2;
            animatorArr[1] = c(z6);
            animatorSet.playTogether(animatorArr);
        }
        Animator[] animatorArr2 = new Animator[9];
        TimeInterpolator timeInterpolator = z6 ? r2.a.LINEAR_INTERPOLATOR : r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        ofFloat.setDuration(z6 ? 300L : 250L);
        ofFloat.setInterpolator(x.a(z6, timeInterpolator));
        ofFloat.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(4), new View[]{this.f4102b}));
        animatorArr2[0] = ofFloat;
        j3.h hVar = this.f4113m;
        Rect rect = hVar.f6970j;
        Rect rect2 = hVar.f6971k;
        SearchView searchView = this.f4101a;
        if (rect == null) {
            rect = new Rect(searchView.getLeft(), searchView.getTop(), searchView.getRight(), searchView.getBottom());
        }
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.f4103c;
        if (rect2 == null) {
            rect2 = k0.a(clippableRoundedCornerLayout, this.f4115o);
        }
        final Rect rect3 = new Rect(rect2);
        final float cornerSize = this.f4115o.getCornerSize();
        final float max = Math.max(clippableRoundedCornerLayout.getCornerRadius(), hVar.b());
        ValueAnimator ofObject = ValueAnimator.ofObject(new n1.n(rect3), rect2, rect);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.l
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                n nVar = n.this;
                nVar.getClass();
                float a7 = r2.a.a(cornerSize, max, valueAnimator.getAnimatedFraction());
                ClippableRoundedCornerLayout clippableRoundedCornerLayout2 = nVar.f4103c;
                clippableRoundedCornerLayout2.getClass();
                Rect rect4 = rect3;
                clippableRoundedCornerLayout2.a(rect4.left, rect4.top, rect4.right, rect4.bottom, a7);
            }
        });
        ofObject.setDuration(z6 ? 300L : 250L);
        TimeInterpolator timeInterpolator2 = r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR;
        ofObject.setInterpolator(x.a(z6, timeInterpolator2));
        animatorArr2[1] = ofObject;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        ofFloat2.setDuration(z6 ? 50L : 42L);
        ofFloat2.setStartDelay(z6 ? 250L : 0L);
        TimeInterpolator timeInterpolator3 = r2.a.LINEAR_INTERPOLATOR;
        ofFloat2.setInterpolator(x.a(z6, timeInterpolator3));
        ofFloat2.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(4), new View[]{this.f4110j}));
        animatorArr2[2] = ofFloat2;
        AnimatorSet animatorSet3 = new AnimatorSet();
        Animator[] animatorArr3 = new Animator[3];
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        ofFloat3.setDuration(z6 ? 150L : 83L);
        ofFloat3.setStartDelay(z6 ? 75L : 0L);
        ofFloat3.setInterpolator(x.a(z6, timeInterpolator3));
        View view = this.f4111k;
        TouchObserverFrameLayout touchObserverFrameLayout = this.f4112l;
        ofFloat3.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(4), new View[]{view, touchObserverFrameLayout}));
        animatorArr3[0] = ofFloat3;
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat((touchObserverFrameLayout.getHeight() * 0.050000012f) / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat4.setDuration(z6 ? 300L : 250L);
        ofFloat4.setInterpolator(x.a(z6, timeInterpolator2));
        ofFloat4.addUpdateListener(androidx.swiperefreshlayout.widget.b.a(view));
        animatorArr3[1] = ofFloat4;
        ValueAnimator ofFloat5 = ValueAnimator.ofFloat(0.95f, 1.0f);
        ofFloat5.setDuration(z6 ? 300L : 250L);
        ofFloat5.setInterpolator(x.a(z6, timeInterpolator2));
        ofFloat5.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(1), new View[]{touchObserverFrameLayout}));
        animatorArr3[2] = ofFloat5;
        animatorSet3.playTogether(animatorArr3);
        animatorArr2[3] = animatorSet3;
        animatorArr2[4] = i(this.f4104d, z6, false);
        Toolbar toolbar = this.f4107g;
        animatorArr2[5] = i(toolbar, z6, false);
        ValueAnimator ofFloat6 = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        ofFloat6.setDuration(z6 ? 300L : 250L);
        ofFloat6.setInterpolator(x.a(z6, timeInterpolator2));
        if (searchView.f4076w) {
            ofFloat6.addUpdateListener(new com.google.android.material.internal.g(f0.a(toolbar), f0.a(this.f4106f)));
        }
        animatorArr2[6] = ofFloat6;
        animatorArr2[7] = i(this.f4109i, z6, true);
        animatorArr2[8] = i(this.f4108h, z6, true);
        animatorSet.playTogether(animatorArr2);
        animatorSet.addListener(new b0(this, z6));
        return animatorSet;
    }

    public final int e(View view) {
        int b7 = o.b((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        return k0.f(this.f4115o) ? this.f4115o.getLeft() - b7 : (this.f4115o.getRight() - this.f4101a.getWidth()) + b7;
    }

    public final int f(View view) {
        int c7 = o.c((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        SearchBar searchBar = this.f4115o;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int f7 = m0.f(searchBar);
        return k0.f(this.f4115o) ? ((this.f4115o.getWidth() - this.f4115o.getRight()) + c7) - f7 : (this.f4115o.getLeft() - c7) + f7;
    }

    public final int g() {
        FrameLayout frameLayout = this.f4105e;
        return ((this.f4115o.getBottom() + this.f4115o.getTop()) / 2) - ((frameLayout.getBottom() + frameLayout.getTop()) / 2);
    }

    public final AnimatorSet h(boolean z6) {
        AnimatorSet animatorSet = new AnimatorSet();
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.f4103c;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getHeight(), CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat.addUpdateListener(androidx.swiperefreshlayout.widget.b.a(clippableRoundedCornerLayout));
        animatorSet.playTogether(ofFloat);
        b(animatorSet);
        animatorSet.setInterpolator(x.a(z6, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
        animatorSet.setDuration(z6 ? 350L : 300L);
        return animatorSet;
    }

    public final AnimatorSet i(View view, boolean z6, boolean z7) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(z7 ? f(view) : e(view), CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat.addUpdateListener(new androidx.swiperefreshlayout.widget.b(new y2.l(2), new View[]{view}));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(g(), CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat2.addUpdateListener(androidx.swiperefreshlayout.widget.b.a(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(z6 ? 300L : 250L);
        animatorSet.setInterpolator(x.a(z6, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public final AnimatorSet j() {
        SearchBar searchBar = this.f4115o;
        SearchView searchView = this.f4101a;
        if (searchBar != null) {
            if (searchView.h()) {
                searchView.f();
            }
            AnimatorSet d7 = d(false);
            d7.addListener(new m(this, 1));
            d7.start();
            return d7;
        }
        if (searchView.h()) {
            searchView.f();
        }
        AnimatorSet h7 = h(false);
        h7.addListener(new m(this, 3));
        h7.start();
        return h7;
    }
}
