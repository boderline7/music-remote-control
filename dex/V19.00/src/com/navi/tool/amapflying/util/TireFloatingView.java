package com.navi.tool.amapflying.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.e;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.utils.SharedPreferencesUtils;
import e0.f;
import g4.p;
import g4.q;
import java.util.Locale;
import o4.c;
import o4.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TireFloatingView extends FrameLayout {

    /* renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ int f4596q = 0;

    /* renamed from: a, reason: collision with root package name */
    public final TextView f4597a;

    /* renamed from: b, reason: collision with root package name */
    public final TextView f4598b;

    /* renamed from: c, reason: collision with root package name */
    public final TextView f4599c;

    /* renamed from: d, reason: collision with root package name */
    public final TextView f4600d;

    /* renamed from: e, reason: collision with root package name */
    public final TextView f4601e;

    /* renamed from: f, reason: collision with root package name */
    public final TextView f4602f;

    /* renamed from: g, reason: collision with root package name */
    public final TextView f4603g;

    /* renamed from: h, reason: collision with root package name */
    public final TextView f4604h;

    /* renamed from: i, reason: collision with root package name */
    public final TextView f4605i;

    /* renamed from: j, reason: collision with root package name */
    public final ImageView f4606j;

    /* renamed from: k, reason: collision with root package name */
    public final Handler f4607k;

    /* renamed from: l, reason: collision with root package name */
    public long f4608l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f4609m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f4610n;

    /* renamed from: o, reason: collision with root package name */
    public final e f4611o;

    /* renamed from: p, reason: collision with root package name */
    public final f f4612p;

    public TireFloatingView(Context context) {
        this(context, null);
    }

    public TireFloatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4607k = new Handler(Looper.getMainLooper());
        this.f4608l = 0L;
        this.f4609m = false;
        this.f4610n = false;
        this.f4611o = new e(15, this);
        f fVar = new f(8, this);
        this.f4612p = fVar;
        LayoutInflater.from(context).inflate(q.view_dashboard_tire_card, (ViewGroup) this, true);
        this.f4597a = (TextView) findViewById(p.tv_tire_fl_value);
        this.f4598b = (TextView) findViewById(p.tv_tire_fr_value);
        this.f4599c = (TextView) findViewById(p.tv_tire_rl_value);
        this.f4600d = (TextView) findViewById(p.tv_tire_rr_value);
        this.f4601e = (TextView) findViewById(p.tv_tire_fl_temp);
        this.f4602f = (TextView) findViewById(p.tv_tire_fr_temp);
        this.f4603g = (TextView) findViewById(p.tv_tire_rl_temp);
        this.f4604h = (TextView) findViewById(p.tv_tire_rr_temp);
        this.f4605i = (TextView) findViewById(p.tv_tire_meta);
        this.f4606j = (ImageView) findViewById(p.img_tire_card);
        setVisibility(8);
        c c7 = c.c();
        c7.f7948p.f(fVar);
        this.f4610n = true;
        c7.k(context);
    }

    public static void b(TextView textView, TextView textView2, int i7, int i8) {
        int i9;
        String str = "--";
        if (textView != null) {
            textView.setText(i7 > 0 ? String.format(Locale.US, "%.2f", Float.valueOf(i7 / 100.0f)) : "--");
            if (i7 <= 0) {
                i9 = -6381922;
            } else {
                float f7 = i7 / 100.0f;
                i9 = f7 < 2.0f ? -1754827 : f7 > 2.8f ? -291840 : -1;
            }
            textView.setTextColor(i9);
        }
        if (textView2 != null) {
            if (i8 != Integer.MIN_VALUE) {
                str = i8 + "℃";
            }
            textView2.setText(str);
        }
    }

    public final void a() {
        this.f4607k.removeCallbacks(this.f4611o);
        if (this.f4610n) {
            c.c().f7948p.g(this.f4612p);
            this.f4610n = false;
        }
        setVisibility(8);
    }

    public final void c() {
        d((s) c.c().f7948p.d());
        setVisibility(0);
        Handler handler = this.f4607k;
        e eVar = this.f4611o;
        handler.removeCallbacks(eVar);
        handler.postDelayed(eVar, Math.max(10, Math.min(60, SharedPreferencesUtils.getAppPrefs(getContext()).getInt(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_SECONDS, 30))) * 1000);
    }

    public final void d(s sVar) {
        if (sVar == null) {
            b(this.f4597a, this.f4601e, -1, Integer.MIN_VALUE);
            b(this.f4598b, this.f4602f, -1, Integer.MIN_VALUE);
            b(this.f4599c, this.f4603g, -1, Integer.MIN_VALUE);
            b(this.f4600d, this.f4604h, -1, Integer.MIN_VALUE);
            TextView textView = this.f4605i;
            if (textView != null) {
                textView.setText("胎压未上报");
            }
            ImageView imageView = this.f4606j;
            if (imageView != null) {
                imageView.setAlpha(1.0f);
                return;
            }
            return;
        }
        b(this.f4597a, this.f4601e, sVar.f8019a, sVar.f8023e);
        b(this.f4598b, this.f4602f, sVar.f8020b, sVar.f8024f);
        b(this.f4599c, this.f4603g, sVar.f8021c, sVar.f8025g);
        b(this.f4600d, this.f4604h, sVar.f8022d, sVar.f8026h);
        TextView textView2 = this.f4605i;
        if (textView2 != null) {
            textView2.setText(sVar.d());
        }
        ImageView imageView2 = this.f4606j;
        if (imageView2 != null) {
            imageView2.setAlpha((sVar.c() || !sVar.f8027i) ? 0.65f : 1.0f);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        a();
        super.onDetachedFromWindow();
    }
}
