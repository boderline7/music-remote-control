package com.navi.tool.amapflying.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import h4.b;
import h4.e;
import i6.k;
import k0.a;
import org.greenrobot.eventbus.ThreadMode;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class RoadSpeedFloatingView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    public final LinearLayout f4590a;

    /* renamed from: b, reason: collision with root package name */
    public final FrameLayout f4591b;

    /* renamed from: c, reason: collision with root package name */
    public final TextView f4592c;

    /* renamed from: d, reason: collision with root package name */
    public final TextView f4593d;

    /* renamed from: e, reason: collision with root package name */
    public final DisplayMetrics f4594e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f4595f;

    public RoadSpeedFloatingView(Context context) {
        super(context);
        this.f4595f = false;
        this.f4594e = getResources().getDisplayMetrics();
        new Handler(Looper.getMainLooper());
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f4590a = linearLayout;
        linearLayout.setOrientation(0);
        this.f4590a.setGravity(16);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor("#0F223E"));
        gradientDrawable.setCornerRadius(a(20));
        this.f4590a.setBackground(gradientDrawable);
        this.f4590a.setPadding(a(4), a(0), a(4), a(0));
        this.f4590a.setLayoutParams(new LinearLayout.LayoutParams(-2, a(55)));
        this.f4591b = new FrameLayout(getContext());
        this.f4591b.setLayoutParams(new LinearLayout.LayoutParams(a(52), a(52)));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(1);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(a(4), a.CATEGORY_MASK);
        this.f4591b.setBackground(gradientDrawable2);
        this.f4592c = new TextView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f4592c.setLayoutParams(layoutParams);
        this.f4592c.setTextColor(-1);
        this.f4592c.setTextSize(25.0f);
        this.f4592c.setTypeface(null, 1);
        this.f4592c.setSingleLine(true);
        this.f4592c.setText(h0.FRAGMENT_ENCODE_SET);
        this.f4591b.addView(this.f4592c);
        Space space = new Space(getContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(a(4), -1));
        this.f4593d = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 16;
        this.f4593d.setLayoutParams(layoutParams2);
        this.f4593d.setTextColor(-1);
        this.f4593d.setTextSize(25.0f);
        this.f4593d.setTypeface(null, 1);
        this.f4593d.setSingleLine(true);
        this.f4593d.setIncludeFontPadding(false);
        this.f4593d.setText(h0.FRAGMENT_ENCODE_SET);
        this.f4590a.addView(this.f4591b);
        this.f4590a.addView(space);
        this.f4590a.addView(this.f4593d);
        addView(this.f4590a);
    }

    public final int a(int i7) {
        return Math.round(i7 * this.f4594e.density);
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onGuideInfoEvent(b bVar) {
        if (bVar == null) {
            return;
        }
        String str = bVar.f6737l;
        String str2 = bVar.f6736k;
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty() && !"null".equalsIgnoreCase(trim)) {
                setVisibility(0);
                this.f4593d.setText(str);
                Integer num = null;
                if (str2 != null) {
                    try {
                        String replaceAll = str2.replaceAll("[^0-9]", h0.FRAGMENT_ENCODE_SET);
                        if (!replaceAll.isEmpty()) {
                            num = Integer.valueOf(Integer.parseInt(replaceAll));
                        }
                    } catch (Exception unused) {
                    }
                }
                if (num == null || num.intValue() == 0) {
                    this.f4591b.setVisibility(8);
                    return;
                } else {
                    this.f4591b.setVisibility(0);
                    this.f4592c.setText(String.valueOf(num));
                    return;
                }
            }
        }
        setVisibility(8);
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onNavigationEndEvent(e eVar) {
        setVisibility(8);
    }
}
