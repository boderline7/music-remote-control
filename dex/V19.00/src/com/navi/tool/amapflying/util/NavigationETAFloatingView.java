package com.navi.tool.amapflying.util;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.app.p0;
import c0.f;
import com.navi.tool.utils.SharedPreferencesUtils;
import h4.b;
import h4.e;
import h4.j;
import j4.k;
import k0.a;
import org.greenrobot.eventbus.ThreadMode;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NavigationETAFloatingView extends LinearLayout {
    public static final /* synthetic */ int P = 0;
    public int A;
    public boolean B;
    public boolean C;
    public boolean D;
    public Handler E;
    public int F;
    public boolean G;
    public boolean H;
    public int I;
    public boolean J;
    public boolean K;
    public int L;
    public String M;
    public String N;
    public String O;

    /* renamed from: a, reason: collision with root package name */
    public LinearLayout f4564a;

    /* renamed from: b, reason: collision with root package name */
    public LinearLayout f4565b;

    /* renamed from: c, reason: collision with root package name */
    public TextView f4566c;

    /* renamed from: d, reason: collision with root package name */
    public ImageView f4567d;

    /* renamed from: e, reason: collision with root package name */
    public TextView f4568e;

    /* renamed from: f, reason: collision with root package name */
    public TextView f4569f;

    /* renamed from: g, reason: collision with root package name */
    public TextView f4570g;

    /* renamed from: h, reason: collision with root package name */
    public LinearLayout f4571h;

    /* renamed from: i, reason: collision with root package name */
    public Space f4572i;

    /* renamed from: j, reason: collision with root package name */
    public LinearLayout f4573j;

    /* renamed from: k, reason: collision with root package name */
    public TextView f4574k;

    /* renamed from: l, reason: collision with root package name */
    public TextView f4575l;

    /* renamed from: m, reason: collision with root package name */
    public TextView f4576m;

    /* renamed from: n, reason: collision with root package name */
    public LinearLayout f4577n;

    /* renamed from: o, reason: collision with root package name */
    public LinearLayout f4578o;

    /* renamed from: p, reason: collision with root package name */
    public LinearLayout f4579p;

    /* renamed from: q, reason: collision with root package name */
    public TextView f4580q;

    /* renamed from: r, reason: collision with root package name */
    public TextView f4581r;

    /* renamed from: s, reason: collision with root package name */
    public TextView f4582s;

    /* renamed from: t, reason: collision with root package name */
    public TextView f4583t;

    /* renamed from: u, reason: collision with root package name */
    public View f4584u;

    /* renamed from: v, reason: collision with root package name */
    public View f4585v;

    /* renamed from: w, reason: collision with root package name */
    public View f4586w;

    /* renamed from: x, reason: collision with root package name */
    public WindowManager.LayoutParams f4587x;

    /* renamed from: y, reason: collision with root package name */
    public DisplayMetrics f4588y;

    /* renamed from: z, reason: collision with root package name */
    public int f4589z;

    public static int a(NavigationETAFloatingView navigationETAFloatingView, LinearLayout linearLayout) {
        navigationETAFloatingView.getClass();
        if (linearLayout == null) {
            return 0;
        }
        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return linearLayout.getMeasuredWidth();
    }

    public static int e(LinearLayout linearLayout) {
        if (linearLayout == null) {
            return 0;
        }
        int width = linearLayout.getWidth();
        return width > 0 ? width : linearLayout.getMeasuredWidth();
    }

    public static boolean f(String str) {
        if (str == null) {
            return true;
        }
        String trim = str.trim();
        return trim.isEmpty() || "null".equalsIgnoreCase(trim) || "-1".equals(trim);
    }

    public static boolean g(String str) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        return (trim.isEmpty() || "null".equalsIgnoreCase(trim) || "-1".equals(trim) || "0".equals(trim) || "0米".equals(trim) || "0m".equalsIgnoreCase(trim)) ? false : true;
    }

    public static Integer h(String str) {
        if (str == null) {
            return null;
        }
        try {
            String replaceAll = str.replaceAll("[^0-9]", h0.FRAGMENT_ENCODE_SET);
            if (replaceAll.isEmpty()) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(replaceAll));
        } catch (Exception unused) {
            return null;
        }
    }

    public final void b(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.setTextSize(this.F);
        textView.setTypeface(null, this.G ? 1 : 0);
    }

    public final void c() {
        try {
            b(this.f4566c);
            b(this.f4568e);
            b(this.f4569f);
            b(this.f4570g);
            b(this.f4580q);
            b(this.f4581r);
            b(this.f4582s);
            b(this.f4583t);
            b(this.f4574k);
            b(this.f4575l);
            b(this.f4576m);
        } catch (Exception unused) {
        }
    }

    public final int d(int i7) {
        return Math.round(i7 * this.f4588y.density);
    }

    public final void i() {
        if (this.H) {
            return;
        }
        this.H = true;
        this.I = 0;
        LinearLayout linearLayout = this.f4564a;
        if (linearLayout != null) {
            linearLayout.getViewTreeObserver().addOnPreDrawListener(new f(2, this));
            this.f4564a.requestLayout();
        } else {
            post(new k(this, 1));
            this.H = false;
            this.I = 0;
        }
    }

    public final void j() {
        int d7 = d(16);
        LinearLayout linearLayout = this.f4565b;
        int i7 = 0;
        int e7 = (linearLayout == null || linearLayout.getVisibility() != 0) ? 0 : e(this.f4565b);
        int e8 = e(this.f4571h) + d7;
        int e9 = e(this.f4573j) + d7;
        LinearLayout linearLayout2 = this.f4578o;
        int e10 = (linearLayout2 == null || linearLayout2.getVisibility() != 0) ? 0 : e(this.f4578o) + d7;
        LinearLayout linearLayout3 = this.f4579p;
        if (linearLayout3 != null && linearLayout3.getVisibility() == 0) {
            i7 = e(this.f4579p) + d7;
        }
        int max = Math.max(e7, Math.max(e8, Math.max(e9, Math.max(e10, i7))));
        if (max <= 0) {
            return;
        }
        LinearLayout linearLayout4 = this.f4565b;
        if (linearLayout4 != null) {
            ViewGroup.LayoutParams layoutParams = linearLayout4.getLayoutParams();
            layoutParams.width = max;
            this.f4565b.setLayoutParams(layoutParams);
        }
        LinearLayout linearLayout5 = this.f4571h;
        if (linearLayout5 != null) {
            ViewGroup.LayoutParams layoutParams2 = linearLayout5.getLayoutParams();
            layoutParams2.width = max;
            this.f4571h.setLayoutParams(layoutParams2);
        }
        LinearLayout linearLayout6 = this.f4573j;
        if (linearLayout6 != null) {
            ViewGroup.LayoutParams layoutParams3 = linearLayout6.getLayoutParams();
            layoutParams3.width = max;
            this.f4573j.setLayoutParams(layoutParams3);
        }
        LinearLayout linearLayout7 = this.f4578o;
        if (linearLayout7 != null) {
            ViewGroup.LayoutParams layoutParams4 = linearLayout7.getLayoutParams();
            layoutParams4.width = max;
            this.f4578o.setLayoutParams(layoutParams4);
        }
        LinearLayout linearLayout8 = this.f4579p;
        if (linearLayout8 != null) {
            ViewGroup.LayoutParams layoutParams5 = linearLayout8.getLayoutParams();
            layoutParams5.width = max;
            this.f4579p.setLayoutParams(layoutParams5);
        }
    }

    public final void k() {
        if (f(this.N) || f(this.O)) {
            LinearLayout linearLayout = this.f4573j;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            View view = this.f4585v;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        String str = f(this.M) ? "--" : this.M;
        String str2 = this.N;
        if (str2 == null) {
            str2 = "--";
        }
        String str3 = this.O;
        String str4 = str3 != null ? str3 : "--";
        TextView textView = this.f4574k;
        if (textView != null) {
            textView.setText("限: " + str);
        }
        TextView textView2 = this.f4575l;
        if (textView2 != null) {
            textView2.setText("均: ".concat(str2));
        }
        TextView textView3 = this.f4576m;
        if (textView3 != null) {
            textView3.setText("剩: ".concat(str4));
        }
        LinearLayout linearLayout2 = this.f4573j;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(0);
        }
        View view2 = this.f4585v;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        Integer h7 = h(this.N);
        Integer h8 = h(this.M);
        if (h7 == null || h8 == null || h7.intValue() <= h8.intValue()) {
            TextView textView4 = this.f4575l;
            if (textView4 != null) {
                textView4.setTextColor(-1);
                return;
            }
            return;
        }
        TextView textView5 = this.f4575l;
        if (textView5 != null) {
            textView5.setTextColor(a.CATEGORY_MASK);
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onGuideInfoEvent(b bVar) {
        if (bVar == null) {
            return;
        }
        bVar.toString();
        Handler handler = this.E;
        if (handler == null) {
            return;
        }
        handler.post(new p0(10, this, bVar));
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onNavigationEndEvent(e eVar) {
        if (eVar == null) {
            return;
        }
        this.B = false;
        this.K = true;
        Handler handler = this.E;
        if (handler != null) {
            handler.post(new k(this, 0));
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onZoneSpeedEvent(j jVar) {
        if (jVar == null) {
            return;
        }
        this.N = jVar.f6751a;
        this.O = jVar.f6752b;
        k();
    }

    public void setBackgroundAlphaPercent(int i7) {
        int max = Math.max(0, Math.min(100, i7));
        if (max == this.L) {
            return;
        }
        this.L = max;
        SharedPreferencesUtils.getAppPrefs(getContext()).edit().putInt("navigation_eta_bg_alpha_percent", this.L).apply();
        try {
            LinearLayout linearLayout = this.f4564a;
            if (linearLayout != null) {
                Drawable background = linearLayout.getBackground();
                if (background instanceof GradientDrawable) {
                    int parseColor = Color.parseColor("#0F223E");
                    ((GradientDrawable) background).setColor(Color.argb((int) Math.round((this.L * 255.0d) / 100.0d), Color.red(parseColor), Color.green(parseColor), Color.blue(parseColor)));
                }
            }
        } catch (Exception unused) {
        }
    }

    public void setGlobalBoldEnabled(boolean z6) {
        if (z6 == this.G) {
            return;
        }
        this.G = z6;
        SharedPreferencesUtils.getAppPrefs(getContext()).edit().putBoolean("navigation_eta_bold_enabled", this.G).apply();
        c();
        i();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0008, code lost:
    
        if (r3 > 40) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setGlobalFontSizeSp(int r3) {
        /*
            r2 = this;
            r0 = 25
            if (r3 >= r0) goto L6
        L4:
            r3 = r0
            goto Lb
        L6:
            r0 = 40
            if (r3 <= r0) goto Lb
            goto L4
        Lb:
            int r0 = r2.F
            if (r3 != r0) goto L10
            return
        L10:
            r2.F = r3
            android.content.Context r3 = r2.getContext()
            android.content.SharedPreferences r3 = com.navi.tool.utils.SharedPreferencesUtils.getAppPrefs(r3)
            android.content.SharedPreferences$Editor r3 = r3.edit()
            java.lang.String r0 = "navigation_eta_font_size_sp"
            int r1 = r2.F
            android.content.SharedPreferences$Editor r3 = r3.putInt(r0, r1)
            r3.apply()
            r2.c()
            r2.i()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.util.NavigationETAFloatingView.setGlobalFontSizeSp(int):void");
    }

    public void setServiceInfoEnabled(boolean z6) {
        if (z6 == this.J) {
            return;
        }
        this.J = z6;
        SharedPreferencesUtils.getAppPrefs(getContext()).edit().putBoolean("navigation_eta_service_info_enabled", this.J).apply();
        if (!this.J) {
            LinearLayout linearLayout = this.f4578o;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            LinearLayout linearLayout2 = this.f4579p;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            View view = this.f4586w;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        i();
    }

    public void setTopInfoVisible(boolean z6) {
        LinearLayout linearLayout;
        this.C = z6;
        LinearLayout linearLayout2 = this.f4565b;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(z6 ? 0 : 8);
        }
        LinearLayout linearLayout3 = this.f4564a;
        if (linearLayout3 != null && linearLayout3.getChildCount() > 1 && (linearLayout = (LinearLayout) this.f4564a.getChildAt(1)) != null) {
            linearLayout.setPadding(0, z6 ? d(4) : d(0), 0, 0);
        }
        i();
    }
}
