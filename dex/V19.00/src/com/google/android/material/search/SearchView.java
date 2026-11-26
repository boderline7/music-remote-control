package com.google.android.material.search;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.u3;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.f0;
import com.google.android.material.internal.k0;
import com.google.android.material.internal.x;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import n1.z;
import p0.d1;
import p0.l0;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SearchView extends FrameLayout implements c0.a, j3.b {
    public static final int D = q2.l.Widget_Material3_SearchView;
    public boolean A;
    public j B;
    public HashMap C;

    /* renamed from: a, reason: collision with root package name */
    public final View f4054a;

    /* renamed from: b, reason: collision with root package name */
    public final ClippableRoundedCornerLayout f4055b;

    /* renamed from: c, reason: collision with root package name */
    public final View f4056c;

    /* renamed from: d, reason: collision with root package name */
    public final View f4057d;

    /* renamed from: e, reason: collision with root package name */
    public final FrameLayout f4058e;

    /* renamed from: f, reason: collision with root package name */
    public final FrameLayout f4059f;

    /* renamed from: g, reason: collision with root package name */
    public final MaterialToolbar f4060g;

    /* renamed from: h, reason: collision with root package name */
    public final Toolbar f4061h;

    /* renamed from: i, reason: collision with root package name */
    public final TextView f4062i;

    /* renamed from: j, reason: collision with root package name */
    public final EditText f4063j;

    /* renamed from: k, reason: collision with root package name */
    public final ImageButton f4064k;

    /* renamed from: l, reason: collision with root package name */
    public final View f4065l;

    /* renamed from: m, reason: collision with root package name */
    public final TouchObserverFrameLayout f4066m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f4067n;

    /* renamed from: o, reason: collision with root package name */
    public final n f4068o;

    /* renamed from: p, reason: collision with root package name */
    public final j3.f f4069p;

    /* renamed from: q, reason: collision with root package name */
    public final boolean f4070q;

    /* renamed from: r, reason: collision with root package name */
    public final e3.a f4071r;

    /* renamed from: s, reason: collision with root package name */
    public final LinkedHashSet f4072s;

    /* renamed from: t, reason: collision with root package name */
    public SearchBar f4073t;

    /* renamed from: u, reason: collision with root package name */
    public int f4074u;

    /* renamed from: v, reason: collision with root package name */
    public boolean f4075v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f4076w;

    /* renamed from: x, reason: collision with root package name */
    public boolean f4077x;

    /* renamed from: y, reason: collision with root package name */
    public final int f4078y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f4079z;

    public static class Behavior extends c0.b {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }

        @Override // c0.b
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            SearchView searchView = (SearchView) view;
            if (searchView.f4073t != null || !(view2 instanceof SearchBar)) {
                return false;
            }
            searchView.setupWithSearchBar((SearchBar) view2);
            return false;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new i();

        /* renamed from: b, reason: collision with root package name */
        public String f4080b;

        /* renamed from: c, reason: collision with root package name */
        public int f4081c;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4080b = parcel.readString();
            this.f4081c = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeString(this.f4080b);
            parcel.writeInt(this.f4081c);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.materialSearchViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SearchView(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 445
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static /* synthetic */ void e(SearchView searchView, l2 l2Var) {
        searchView.getClass();
        int d7 = l2Var.d();
        searchView.setUpStatusBarSpacer(d7);
        if (searchView.A) {
            return;
        }
        searchView.setStatusBarSpacerEnabledInternal(d7 > 0);
    }

    private Window getActivityWindow() {
        Activity activity;
        Context context = getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            }
            if (context instanceof Activity) {
                activity = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (activity == null) {
            return null;
        }
        return activity.getWindow();
    }

    private float getOverlayElevation() {
        SearchBar searchBar = this.f4073t;
        return searchBar != null ? searchBar.getCompatElevation() : getResources().getDimension(q2.e.m3_searchview_elevation);
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void setStatusBarSpacerEnabledInternal(boolean z6) {
        this.f4057d.setVisibility(z6 ? 0 : 8);
    }

    private void setUpBackgroundViewElevationOverlay(float f7) {
        View view;
        e3.a aVar = this.f4071r;
        if (aVar == null || (view = this.f4056c) == null) {
            return;
        }
        view.setBackgroundColor(aVar.a(f7, this.f4078y));
    }

    private void setUpHeaderLayout(int i7) {
        if (i7 != -1) {
            LayoutInflater from = LayoutInflater.from(getContext());
            FrameLayout frameLayout = this.f4058e;
            frameLayout.addView(from.inflate(i7, (ViewGroup) frameLayout, false));
            frameLayout.setVisibility(0);
        }
    }

    private void setUpStatusBarSpacer(int i7) {
        View view = this.f4057d;
        if (view.getLayoutParams().height != i7) {
            view.getLayoutParams().height = i7;
            view.requestLayout();
        }
    }

    @Override // j3.b
    public final void a() {
        if (i()) {
            return;
        }
        n nVar = this.f4068o;
        j3.h hVar = nVar.f4113m;
        androidx.activity.c cVar = hVar.f6958f;
        hVar.f6958f = null;
        if (Build.VERSION.SDK_INT < 34 || this.f4073t == null || cVar == null) {
            g();
            return;
        }
        long totalDuration = nVar.j().getTotalDuration();
        SearchBar searchBar = nVar.f4115o;
        j3.h hVar2 = nVar.f4113m;
        AnimatorSet a7 = hVar2.a(searchBar);
        a7.setDuration(totalDuration);
        a7.start();
        hVar2.f6969i = CropImageView.DEFAULT_ASPECT_RATIO;
        hVar2.f6970j = null;
        hVar2.f6971k = null;
        if (nVar.f4114n != null) {
            nVar.c(false).start();
            nVar.f4114n.resume();
        }
        nVar.f4114n = null;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (this.f4067n) {
            this.f4066m.addView(view, i7, layoutParams);
        } else {
            super.addView(view, i7, layoutParams);
        }
    }

    @Override // j3.b
    public final void b(androidx.activity.c cVar) {
        if (i() || this.f4073t == null) {
            return;
        }
        n nVar = this.f4068o;
        SearchBar searchBar = nVar.f4115o;
        j3.h hVar = nVar.f4113m;
        hVar.f6958f = cVar;
        View view = hVar.f6954b;
        hVar.f6970j = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (searchBar != null) {
            hVar.f6971k = k0.a(view, searchBar);
        }
        hVar.f6969i = cVar.f609b;
    }

    @Override // j3.b
    public final void c(androidx.activity.c cVar) {
        if (i() || this.f4073t == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        n nVar = this.f4068o;
        nVar.getClass();
        float f7 = cVar.f610c;
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        SearchBar searchBar = nVar.f4115o;
        float cornerSize = searchBar.getCornerSize();
        j3.h hVar = nVar.f4113m;
        androidx.activity.c cVar2 = hVar.f6958f;
        hVar.f6958f = cVar;
        if (cVar2 != null) {
            if (searchBar.getVisibility() != 4) {
                searchBar.setVisibility(4);
            }
            boolean z6 = cVar.f611d == 0;
            float interpolation = hVar.f6953a.getInterpolation(f7);
            View view = hVar.f6954b;
            float width = view.getWidth();
            float height = view.getHeight();
            if (width > CropImageView.DEFAULT_ASPECT_RATIO && height > CropImageView.DEFAULT_ASPECT_RATIO) {
                float a7 = r2.a.a(1.0f, 0.9f, interpolation);
                float f8 = hVar.f6967g;
                float a8 = r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, Math.max(CropImageView.DEFAULT_ASPECT_RATIO, ((width - (0.9f * width)) / 2.0f) - f8), interpolation) * (z6 ? 1 : -1);
                float min = Math.min(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, ((height - (a7 * height)) / 2.0f) - f8), hVar.f6968h);
                float f9 = cVar.f609b - hVar.f6969i;
                float a9 = r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, min, Math.abs(f9) / height) * Math.signum(f9);
                view.setScaleX(a7);
                view.setScaleY(a7);
                view.setTranslationX(a8);
                view.setTranslationY(a9);
                if (view instanceof ClippableRoundedCornerLayout) {
                    ((ClippableRoundedCornerLayout) view).a(r12.getLeft(), r12.getTop(), r12.getRight(), r12.getBottom(), r2.a.a(hVar.b(), cornerSize, interpolation));
                }
            }
        }
        AnimatorSet animatorSet = nVar.f4114n;
        if (animatorSet != null) {
            animatorSet.setCurrentPlayTime((long) (f7 * animatorSet.getDuration()));
            return;
        }
        SearchView searchView = nVar.f4101a;
        if (searchView.h()) {
            searchView.f();
        }
        if (searchView.f4075v) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            nVar.b(animatorSet2);
            animatorSet2.setDuration(250L);
            animatorSet2.setInterpolator(x.a(false, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
            nVar.f4114n = animatorSet2;
            animatorSet2.start();
            nVar.f4114n.pause();
        }
    }

    @Override // j3.b
    public final void d() {
        if (i() || this.f4073t == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        n nVar = this.f4068o;
        SearchBar searchBar = nVar.f4115o;
        j3.h hVar = nVar.f4113m;
        androidx.activity.c cVar = hVar.f6958f;
        hVar.f6958f = null;
        if (cVar != null) {
            AnimatorSet a7 = hVar.a(searchBar);
            View view = hVar.f6954b;
            if (view instanceof ClippableRoundedCornerLayout) {
                ClippableRoundedCornerLayout clippableRoundedCornerLayout = (ClippableRoundedCornerLayout) view;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getCornerRadius(), hVar.b());
                ofFloat.addUpdateListener(new x2.b(1, clippableRoundedCornerLayout));
                a7.playTogether(ofFloat);
            }
            a7.setDuration(hVar.f6957e);
            a7.start();
            hVar.f6969i = CropImageView.DEFAULT_ASPECT_RATIO;
            hVar.f6970j = null;
            hVar.f6971k = null;
        }
        AnimatorSet animatorSet = nVar.f4114n;
        if (animatorSet != null) {
            animatorSet.reverse();
        }
        nVar.f4114n = null;
    }

    public final void f() {
        this.f4063j.post(new h(this, 2));
    }

    public final void g() {
        if (this.B.equals(j.HIDDEN) || this.B.equals(j.HIDING)) {
            return;
        }
        this.f4068o.j();
    }

    public j3.h getBackHelper() {
        return this.f4068o.f4113m;
    }

    @Override // c0.a
    public c0.b getBehavior() {
        return new Behavior();
    }

    public j getCurrentTransitionState() {
        return this.B;
    }

    public int getDefaultNavigationIconResource() {
        return q2.f.ic_arrow_back_black_24;
    }

    public EditText getEditText() {
        return this.f4063j;
    }

    public CharSequence getHint() {
        return this.f4063j.getHint();
    }

    public TextView getSearchPrefix() {
        return this.f4062i;
    }

    public CharSequence getSearchPrefixText() {
        return this.f4062i.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.f4074u;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.f4063j.getText();
    }

    public Toolbar getToolbar() {
        return this.f4060g;
    }

    public final boolean h() {
        return this.f4074u == 48;
    }

    public final boolean i() {
        return this.B.equals(j.HIDDEN) || this.B.equals(j.HIDING);
    }

    public final void j() {
        if (this.f4077x) {
            this.f4063j.postDelayed(new h(this, 1), 100L);
        }
    }

    public final void k(j jVar, boolean z6) {
        if (this.B.equals(jVar)) {
            return;
        }
        if (z6) {
            if (jVar == j.SHOWN) {
                setModalForAccessibility(true);
            } else if (jVar == j.HIDDEN) {
                setModalForAccessibility(false);
            }
        }
        this.B = jVar;
        Iterator it = new LinkedHashSet(this.f4072s).iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        n(jVar);
    }

    public final void l() {
        if (this.B.equals(j.SHOWN)) {
            return;
        }
        j jVar = this.B;
        j jVar2 = j.SHOWING;
        if (jVar.equals(jVar2)) {
            return;
        }
        final n nVar = this.f4068o;
        SearchBar searchBar = nVar.f4115o;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = nVar.f4103c;
        SearchView searchView = nVar.f4101a;
        if (searchBar == null) {
            if (searchView.h()) {
                searchView.postDelayed(new h(searchView, 3), 150L);
            }
            clippableRoundedCornerLayout.setVisibility(4);
            final int i7 = 1;
            clippableRoundedCornerLayout.post(new Runnable() { // from class: com.google.android.material.search.k
                @Override // java.lang.Runnable
                public final void run() {
                    int i8 = i7;
                    n nVar2 = nVar;
                    switch (i8) {
                        case 0:
                            AnimatorSet d7 = nVar2.d(true);
                            d7.addListener(new m(nVar2, 0));
                            d7.start();
                            break;
                        default:
                            nVar2.f4103c.setTranslationY(r0.getHeight());
                            AnimatorSet h7 = nVar2.h(true);
                            h7.addListener(new m(nVar2, 2));
                            h7.start();
                            break;
                    }
                }
            });
            return;
        }
        if (searchView.h()) {
            searchView.j();
        }
        searchView.setTransitionState(jVar2);
        Toolbar toolbar = nVar.f4107g;
        Menu menu = toolbar.getMenu();
        if (menu != null) {
            menu.clear();
        }
        final int i8 = 0;
        if (nVar.f4115o.getMenuResId() == -1 || !searchView.f4076w) {
            toolbar.setVisibility(8);
        } else {
            toolbar.n(nVar.f4115o.getMenuResId());
            ActionMenuView a7 = f0.a(toolbar);
            if (a7 != null) {
                for (int i9 = 0; i9 < a7.getChildCount(); i9++) {
                    View childAt = a7.getChildAt(i9);
                    childAt.setClickable(false);
                    childAt.setFocusable(false);
                    childAt.setFocusableInTouchMode(false);
                }
            }
            toolbar.setVisibility(0);
        }
        CharSequence text = nVar.f4115o.getText();
        EditText editText = nVar.f4109i;
        editText.setText(text);
        editText.setSelection(editText.getText().length());
        clippableRoundedCornerLayout.setVisibility(4);
        clippableRoundedCornerLayout.post(new Runnable() { // from class: com.google.android.material.search.k
            @Override // java.lang.Runnable
            public final void run() {
                int i82 = i8;
                n nVar2 = nVar;
                switch (i82) {
                    case 0:
                        AnimatorSet d7 = nVar2.d(true);
                        d7.addListener(new m(nVar2, 0));
                        d7.start();
                        break;
                    default:
                        nVar2.f4103c.setTranslationY(r0.getHeight());
                        AnimatorSet h7 = nVar2.h(true);
                        h7.addListener(new m(nVar2, 2));
                        h7.start();
                        break;
                }
            }
        });
    }

    public final void m(ViewGroup viewGroup, boolean z6) {
        for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if (childAt != this) {
                if (childAt.findViewById(this.f4055b.getId()) != null) {
                    m((ViewGroup) childAt, z6);
                } else if (z6) {
                    this.C.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    int i8 = d1.OVER_SCROLL_ALWAYS;
                    l0.s(childAt, 4);
                } else {
                    HashMap hashMap = this.C;
                    if (hashMap != null && hashMap.containsKey(childAt)) {
                        int intValue = ((Integer) this.C.get(childAt)).intValue();
                        int i9 = d1.OVER_SCROLL_ALWAYS;
                        l0.s(childAt, intValue);
                    }
                }
            }
        }
    }

    public final void n(j jVar) {
        j3.c cVar;
        if (this.f4073t == null || !this.f4070q) {
            return;
        }
        boolean equals = jVar.equals(j.SHOWN);
        j3.f fVar = this.f4069p;
        if (equals) {
            j3.c cVar2 = fVar.f6962a;
            if (cVar2 != null) {
                cVar2.b(fVar.f6963b, fVar.f6964c, false);
                return;
            }
            return;
        }
        if (!jVar.equals(j.HIDDEN) || (cVar = fVar.f6962a) == null) {
            return;
        }
        cVar.c(fVar.f6964c);
    }

    public final void o() {
        ImageButton b7 = f0.b(this.f4060g);
        if (b7 == null) {
            return;
        }
        int i7 = this.f4055b.getVisibility() == 0 ? 1 : 0;
        Drawable p12 = z.p1(b7.getDrawable());
        if (p12 instanceof g.d) {
            ((g.d) p12).b(i7);
        }
        if (p12 instanceof com.google.android.material.internal.f) {
            ((com.google.android.material.internal.f) p12).a(i7);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.A0(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.f4074u = activityWindow.getAttributes().softInputMode;
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        setText(savedState.f4080b);
        setVisible(savedState.f4081c == 0);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Editable text = getText();
        savedState.f4080b = text == null ? null : text.toString();
        savedState.f4081c = this.f4055b.getVisibility();
        return savedState;
    }

    public void setAnimatedNavigationIcon(boolean z6) {
        this.f4075v = z6;
    }

    public void setAutoShowKeyboard(boolean z6) {
        this.f4077x = z6;
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        setUpBackgroundViewElevationOverlay(f7);
    }

    public void setHint(int i7) {
        this.f4063j.setHint(i7);
    }

    public void setHint(CharSequence charSequence) {
        this.f4063j.setHint(charSequence);
    }

    public void setMenuItemsAnimated(boolean z6) {
        this.f4076w = z6;
    }

    public void setModalForAccessibility(boolean z6) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z6) {
            this.C = new HashMap(viewGroup.getChildCount());
        }
        m(viewGroup, z6);
        if (z6) {
            return;
        }
        this.C = null;
    }

    public void setOnMenuItemClickListener(u3 u3Var) {
        this.f4060g.setOnMenuItemClickListener(u3Var);
    }

    public void setSearchPrefixText(CharSequence charSequence) {
        TextView textView = this.f4062i;
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public void setStatusBarSpacerEnabled(boolean z6) {
        this.A = true;
        setStatusBarSpacerEnabledInternal(z6);
    }

    public void setText(int i7) {
        this.f4063j.setText(i7);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(CharSequence charSequence) {
        this.f4063j.setText(charSequence);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z6) {
        this.f4060g.setTouchscreenBlocksFocus(z6);
    }

    public void setTransitionState(j jVar) {
        k(jVar, true);
    }

    public void setUseWindowInsetsController(boolean z6) {
        this.f4079z = z6;
    }

    public void setVisible(boolean z6) {
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.f4055b;
        boolean z7 = clippableRoundedCornerLayout.getVisibility() == 0;
        clippableRoundedCornerLayout.setVisibility(z6 ? 0 : 8);
        o();
        k(z6 ? j.SHOWN : j.HIDDEN, z7 != z6);
    }

    public void setupWithSearchBar(SearchBar searchBar) {
        this.f4073t = searchBar;
        this.f4068o.f4115o = searchBar;
        if (searchBar != null) {
            searchBar.setOnClickListener(new e(this, 1));
            if (Build.VERSION.SDK_INT >= 34) {
                try {
                    searchBar.setHandwritingDelegatorCallback(new h(this, 0));
                    this.f4063j.setIsHandwritingDelegate(true);
                } catch (LinkageError unused) {
                }
            }
        }
        MaterialToolbar materialToolbar = this.f4060g;
        if (materialToolbar != null && !(z.p1(materialToolbar.getNavigationIcon()) instanceof g.d)) {
            int defaultNavigationIconResource = getDefaultNavigationIconResource();
            if (this.f4073t == null) {
                materialToolbar.setNavigationIcon(defaultNavigationIconResource);
            } else {
                Drawable mutate = l6.f.A(getContext(), defaultNavigationIconResource).mutate();
                if (materialToolbar.getNavigationIconTint() != null) {
                    j0.b.g(mutate, materialToolbar.getNavigationIconTint().intValue());
                }
                materialToolbar.setNavigationIcon(new com.google.android.material.internal.f(this.f4073t.getNavigationIcon(), mutate));
                o();
            }
        }
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
        n(getCurrentTransitionState());
    }
}
