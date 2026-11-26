package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityMainBinding {

    /* renamed from: a, reason: collision with root package name */
    public final DrawerLayout f4736a;

    /* renamed from: b, reason: collision with root package name */
    public final DrawerLayout f4737b;

    /* renamed from: c, reason: collision with root package name */
    public final FloatingActionButton f4738c;

    /* renamed from: d, reason: collision with root package name */
    public final FloatingActionButton f4739d;

    /* renamed from: e, reason: collision with root package name */
    public final FloatingActionButton f4740e;

    /* renamed from: f, reason: collision with root package name */
    public final FloatingActionButton f4741f;

    /* renamed from: g, reason: collision with root package name */
    public final FloatingActionButton f4742g;

    /* renamed from: h, reason: collision with root package name */
    public final FloatingActionButton f4743h;

    /* renamed from: i, reason: collision with root package name */
    public final NavigationView f4744i;

    /* renamed from: j, reason: collision with root package name */
    public final TabLayout f4745j;

    /* renamed from: k, reason: collision with root package name */
    public final MaterialToolbar f4746k;

    /* renamed from: l, reason: collision with root package name */
    public final ViewPager2 f4747l;

    public ActivityMainBinding(DrawerLayout drawerLayout, DrawerLayout drawerLayout2, FloatingActionButton floatingActionButton, FloatingActionButton floatingActionButton2, FloatingActionButton floatingActionButton3, FloatingActionButton floatingActionButton4, FloatingActionButton floatingActionButton5, FloatingActionButton floatingActionButton6, NavigationView navigationView, TabLayout tabLayout, MaterialToolbar materialToolbar, ViewPager2 viewPager2) {
        this.f4736a = drawerLayout;
        this.f4737b = drawerLayout2;
        this.f4738c = floatingActionButton;
        this.f4739d = floatingActionButton2;
        this.f4740e = floatingActionButton3;
        this.f4741f = floatingActionButton4;
        this.f4742g = floatingActionButton5;
        this.f4743h = floatingActionButton6;
        this.f4744i = navigationView;
        this.f4745j = tabLayout;
        this.f4746k = materialToolbar;
        this.f4747l = viewPager2;
    }

    public static ActivityMainBinding bind(View view) {
        int i7 = p.btn_start_trial;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.btn_switch_version;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.container;
                if (((FrameLayout) z.c0(i7, view)) != null) {
                    DrawerLayout drawerLayout = (DrawerLayout) view;
                    i7 = p.fab_desktop_assistant;
                    FloatingActionButton floatingActionButton = (FloatingActionButton) z.c0(i7, view);
                    if (floatingActionButton != null) {
                        i7 = p.fab_file_assistant;
                        FloatingActionButton floatingActionButton2 = (FloatingActionButton) z.c0(i7, view);
                        if (floatingActionButton2 != null) {
                            i7 = p.fab_permission_assistant;
                            FloatingActionButton floatingActionButton3 = (FloatingActionButton) z.c0(i7, view);
                            if (floatingActionButton3 != null) {
                                i7 = p.fab_refresh;
                                FloatingActionButton floatingActionButton4 = (FloatingActionButton) z.c0(i7, view);
                                if (floatingActionButton4 != null) {
                                    i7 = p.fab_theme;
                                    FloatingActionButton floatingActionButton5 = (FloatingActionButton) z.c0(i7, view);
                                    if (floatingActionButton5 != null) {
                                        i7 = p.fab_tts_test;
                                        FloatingActionButton floatingActionButton6 = (FloatingActionButton) z.c0(i7, view);
                                        if (floatingActionButton6 != null) {
                                            i7 = p.nav_view;
                                            NavigationView navigationView = (NavigationView) z.c0(i7, view);
                                            if (navigationView != null) {
                                                i7 = p.tab_header;
                                                if (((LinearLayout) z.c0(i7, view)) != null) {
                                                    i7 = p.tab_layout;
                                                    TabLayout tabLayout = (TabLayout) z.c0(i7, view);
                                                    if (tabLayout != null) {
                                                        i7 = p.toolbar;
                                                        MaterialToolbar materialToolbar = (MaterialToolbar) z.c0(i7, view);
                                                        if (materialToolbar != null) {
                                                            i7 = p.view_pager;
                                                            ViewPager2 viewPager2 = (ViewPager2) z.c0(i7, view);
                                                            if (viewPager2 != null) {
                                                                return new ActivityMainBinding(drawerLayout, drawerLayout, floatingActionButton, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton6, navigationView, tabLayout, materialToolbar, viewPager2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_main, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
