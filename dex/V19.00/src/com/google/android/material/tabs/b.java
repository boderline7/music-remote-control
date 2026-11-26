package com.google.android.material.tabs;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b {
    public static final int INVALID_POSITION = -1;

    /* renamed from: a, reason: collision with root package name */
    public Drawable f4249a;

    /* renamed from: b, reason: collision with root package name */
    public CharSequence f4250b;

    /* renamed from: c, reason: collision with root package name */
    public CharSequence f4251c;

    /* renamed from: d, reason: collision with root package name */
    public int f4252d;

    /* renamed from: e, reason: collision with root package name */
    public View f4253e;

    /* renamed from: f, reason: collision with root package name */
    public TabLayout f4254f;

    /* renamed from: g, reason: collision with root package name */
    public TabLayout.TabView f4255g;

    /* renamed from: h, reason: collision with root package name */
    public int f4256h;

    public final void a(int i7) {
        TabLayout tabLayout = this.f4254f;
        if (tabLayout == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        b(f.A(tabLayout.getContext(), i7));
    }

    public final void b(Drawable drawable) {
        this.f4249a = drawable;
        TabLayout tabLayout = this.f4254f;
        if (tabLayout.f4236z == 1 || tabLayout.C == 2) {
            tabLayout.n(true);
        }
        TabLayout.TabView tabView = this.f4255g;
        if (tabView != null) {
            tabView.d();
        }
        if (t2.c.USE_COMPAT_PARENT) {
            TabLayout.TabView tabView2 = this.f4255g;
            int i7 = TabLayout.TabView.f4237l;
            t2.a aVar = tabView2.f4242e;
            if (aVar == null || !aVar.isVisible()) {
                return;
            }
            this.f4255g.invalidate();
        }
    }

    public final void c(int i7) {
        TabLayout tabLayout = this.f4254f;
        if (tabLayout == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        CharSequence text = tabLayout.getResources().getText(i7);
        if (TextUtils.isEmpty(this.f4251c) && !TextUtils.isEmpty(text)) {
            this.f4255g.setContentDescription(text);
        }
        this.f4250b = text;
        TabLayout.TabView tabView = this.f4255g;
        if (tabView != null) {
            tabView.d();
        }
    }
}
