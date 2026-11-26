package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w3 implements j1 {

    /* renamed from: a, reason: collision with root package name */
    public final Toolbar f1442a;

    /* renamed from: b, reason: collision with root package name */
    public int f1443b;

    /* renamed from: c, reason: collision with root package name */
    public ScrollingTabContainerView f1444c;

    /* renamed from: d, reason: collision with root package name */
    public final View f1445d;

    /* renamed from: e, reason: collision with root package name */
    public Drawable f1446e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f1447f;

    /* renamed from: g, reason: collision with root package name */
    public Drawable f1448g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1449h;

    /* renamed from: i, reason: collision with root package name */
    public CharSequence f1450i;

    /* renamed from: j, reason: collision with root package name */
    public final CharSequence f1451j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f1452k;

    /* renamed from: l, reason: collision with root package name */
    public Window.Callback f1453l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1454m;

    /* renamed from: n, reason: collision with root package name */
    public m f1455n;

    /* renamed from: o, reason: collision with root package name */
    public final int f1456o;

    /* renamed from: p, reason: collision with root package name */
    public final Drawable f1457p;

    public w3(Toolbar toolbar, boolean z6) {
        int i7;
        Drawable drawable;
        int i8 = f.h.abc_action_bar_up_description;
        this.f1456o = 0;
        this.f1442a = toolbar;
        this.f1450i = toolbar.getTitle();
        this.f1451j = toolbar.getSubtitle();
        this.f1449h = this.f1450i != null;
        this.f1448g = toolbar.getNavigationIcon();
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(toolbar.getContext(), null, f.j.ActionBar, f.a.actionBarStyle);
        this.f1457p = S.I(f.j.ActionBar_homeAsUpIndicator);
        if (z6) {
            CharSequence Q = S.Q(f.j.ActionBar_title);
            if (!TextUtils.isEmpty(Q)) {
                this.f1449h = true;
                this.f1450i = Q;
                if ((this.f1443b & 8) != 0) {
                    Toolbar toolbar2 = this.f1442a;
                    toolbar2.setTitle(Q);
                    if (this.f1449h) {
                        p0.d1.p(toolbar2.getRootView(), Q);
                    }
                }
            }
            CharSequence Q2 = S.Q(f.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(Q2)) {
                this.f1451j = Q2;
                if ((this.f1443b & 8) != 0) {
                    toolbar.setSubtitle(Q2);
                }
            }
            Drawable I = S.I(f.j.ActionBar_logo);
            if (I != null) {
                this.f1447f = I;
                d();
            }
            Drawable I2 = S.I(f.j.ActionBar_icon);
            if (I2 != null) {
                this.f1446e = I2;
                d();
            }
            if (this.f1448g == null && (drawable = this.f1457p) != null) {
                this.f1448g = drawable;
                int i9 = this.f1443b & 4;
                Toolbar toolbar3 = this.f1442a;
                if (i9 != 0) {
                    toolbar3.setNavigationIcon(drawable);
                } else {
                    toolbar3.setNavigationIcon((Drawable) null);
                }
            }
            a(S.L(f.j.ActionBar_displayOptions, 0));
            int N = S.N(f.j.ActionBar_customNavigationLayout, 0);
            if (N != 0) {
                View inflate = LayoutInflater.from(toolbar.getContext()).inflate(N, (ViewGroup) toolbar, false);
                View view = this.f1445d;
                if (view != null && (this.f1443b & 16) != 0) {
                    toolbar.removeView(view);
                }
                this.f1445d = inflate;
                if (inflate != null && (this.f1443b & 16) != 0) {
                    toolbar.addView(inflate);
                }
                a(this.f1443b | 16);
            }
            int layoutDimension = ((TypedArray) S.f715c).getLayoutDimension(f.j.ActionBar_height, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height = layoutDimension;
                toolbar.setLayoutParams(layoutParams);
            }
            int G = S.G(f.j.ActionBar_contentInsetStart, -1);
            int G2 = S.G(f.j.ActionBar_contentInsetEnd, -1);
            if (G >= 0 || G2 >= 0) {
                toolbar.setContentInsetsRelative(Math.max(G, 0), Math.max(G2, 0));
            }
            int N2 = S.N(f.j.ActionBar_titleTextStyle, 0);
            if (N2 != 0) {
                toolbar.setTitleTextAppearance(toolbar.getContext(), N2);
            }
            int N3 = S.N(f.j.ActionBar_subtitleTextStyle, 0);
            if (N3 != 0) {
                toolbar.setSubtitleTextAppearance(toolbar.getContext(), N3);
            }
            int N4 = S.N(f.j.ActionBar_popupTheme, 0);
            if (N4 != 0) {
                toolbar.setPopupTheme(N4);
            }
        } else {
            if (toolbar.getNavigationIcon() != null) {
                this.f1457p = toolbar.getNavigationIcon();
                i7 = 15;
            } else {
                i7 = 11;
            }
            this.f1443b = i7;
        }
        S.V();
        if (i8 != this.f1456o) {
            this.f1456o = i8;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                b(this.f1456o);
            }
        }
        this.f1452k = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new c(this));
    }

    public final void a(int i7) {
        View view;
        int i8 = this.f1443b ^ i7;
        this.f1443b = i7;
        if (i8 != 0) {
            if ((i8 & 4) != 0) {
                if ((i7 & 4) != 0) {
                    c();
                }
                int i9 = this.f1443b & 4;
                Toolbar toolbar = this.f1442a;
                if (i9 != 0) {
                    Drawable drawable = this.f1448g;
                    if (drawable == null) {
                        drawable = this.f1457p;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i8 & 3) != 0) {
                d();
            }
            int i10 = i8 & 8;
            Toolbar toolbar2 = this.f1442a;
            if (i10 != 0) {
                if ((i7 & 8) != 0) {
                    toolbar2.setTitle(this.f1450i);
                    toolbar2.setSubtitle(this.f1451j);
                } else {
                    toolbar2.setTitle((CharSequence) null);
                    toolbar2.setSubtitle((CharSequence) null);
                }
            }
            if ((i8 & 16) == 0 || (view = this.f1445d) == null) {
                return;
            }
            if ((i7 & 16) != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    public final void b(int i7) {
        this.f1452k = i7 == 0 ? null : this.f1442a.getContext().getString(i7);
        c();
    }

    public final void c() {
        if ((this.f1443b & 4) != 0) {
            boolean isEmpty = TextUtils.isEmpty(this.f1452k);
            Toolbar toolbar = this.f1442a;
            if (isEmpty) {
                toolbar.setNavigationContentDescription(this.f1456o);
            } else {
                toolbar.setNavigationContentDescription(this.f1452k);
            }
        }
    }

    public final void d() {
        Drawable drawable;
        int i7 = this.f1443b;
        if ((i7 & 2) == 0) {
            drawable = null;
        } else if ((i7 & 1) != 0) {
            drawable = this.f1447f;
            if (drawable == null) {
                drawable = this.f1446e;
            }
        } else {
            drawable = this.f1446e;
        }
        this.f1442a.setLogo(drawable);
    }
}
