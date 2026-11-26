package com.navi.tool.amapflying.util;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.app.p0;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.o;
import h4.d;
import i6.e;
import i6.k;
import j4.i;
import j4.j;
import java.util.ArrayList;
import java.util.Iterator;
import n1.z;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;
import p0.d1;
import s5.h0;
import z1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LaneLinesFloatingView extends LinearLayout {

    /* renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f4547i = 0;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f4548a;

    /* renamed from: b, reason: collision with root package name */
    public final LinearLayout f4549b;

    /* renamed from: c, reason: collision with root package name */
    public int f4550c;

    /* renamed from: d, reason: collision with root package name */
    public final Handler f4551d;

    /* renamed from: e, reason: collision with root package name */
    public final DisplayMetrics f4552e;

    /* renamed from: f, reason: collision with root package name */
    public String f4553f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f4554g;

    /* renamed from: h, reason: collision with root package name */
    public int f4555h;

    public LaneLinesFloatingView(Context context) {
        super(context);
        this.f4548a = new ArrayList();
        this.f4550c = 80;
        this.f4555h = -1;
        this.f4551d = new Handler(Looper.getMainLooper());
        this.f4552e = getContext().getResources().getDisplayMetrics();
        try {
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(getContext());
            int i7 = appPrefs.getInt("lane_lines_height", 80);
            this.f4550c = e(Math.max(50, Math.min(c.INVOKE_INTERFACE_RANGE, i7 > 120 ? Math.round(i7 / this.f4552e.density) : i7)));
            this.f4554g = appPrefs.getBoolean("lane_lines_transparent_bg", false);
            this.f4555h = g() ? appPrefs.getInt("lane_lines_arrow_color_night", -1) : appPrefs.getInt("lane_lines_arrow_color_day", -1);
        } catch (Exception unused) {
        }
        setOrientation(1);
        setBackgroundColor(0);
        setGravity(51);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f4549b = linearLayout;
        linearLayout.setOrientation(0);
        this.f4549b.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor("#0250ED"));
        gradientDrawable.setCornerRadius(e(10));
        this.f4549b.setBackground(gradientDrawable);
        this.f4549b.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f4550c));
        this.f4549b.setVisibility(8);
        addView(this.f4549b);
        b();
        setLayoutParams(new LinearLayout.LayoutParams(-2, this.f4550c));
        setVisibility(8);
        if (e.b().e(this)) {
            return;
        }
        e.b().j(this);
    }

    public static ArrayList h(JSONArray jSONArray) {
        int r02;
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < jSONArray.length(); i7++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i7);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("lane_id", optJSONObject.optInt("lane", optJSONObject.optInt("icon_id", optJSONObject.optInt("id", -1))));
                if (optInt == -1) {
                    String optString = optJSONObject.optString("drive_way_lane_Back_icon", h0.FRAGMENT_ENCODE_SET);
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            optInt = Integer.parseInt(optString);
                        } catch (NumberFormatException unused) {
                        }
                    }
                    optInt = -1;
                }
                if (optInt >= 0 && optInt <= 100) {
                    if (optInt >= 0 && optInt <= 14) {
                        r02 = z.r0("amap_lane_back_" + optInt);
                    } else if (optInt < 15 || optInt > 100) {
                        r02 = z.r0("amap_lane_back_0");
                    } else {
                        r02 = z.r0("amap_lane_front_" + optInt);
                    }
                    if (r02 != 0) {
                        boolean optBoolean = optJSONObject.optBoolean("trafficLaneAdvised", false);
                        int optInt2 = optJSONObject.optInt("trafficLaneType", -1);
                        arrayList.add(new i(r02, optInt2, optInt2 == 1 ? (optInt < 0 || optInt > 14) ? o.amap_lane_front_bus : o.amap_lane_back_bus : 0, optBoolean));
                    }
                }
            }
        }
        return arrayList;
    }

    public final void a(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        int i7 = this.f4555h;
        if (i7 == -1) {
            imageView.clearColorFilter();
        } else {
            imageView.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
        }
    }

    public final void b() {
        if (this.f4549b == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(e(10));
        gradientDrawable.setColor(this.f4554g ? 0 : Color.parseColor("#0250ED"));
        this.f4549b.setBackground(gradientDrawable);
    }

    public final void c() {
        try {
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(getContext());
            this.f4554g = appPrefs.getBoolean("lane_lines_transparent_bg", false);
            int i7 = appPrefs.getInt("lane_lines_arrow_color_day", -1);
            int i8 = appPrefs.getInt("lane_lines_arrow_color_night", -1);
            if (g()) {
                i7 = i8;
            }
            this.f4555h = i7;
            b();
            if (TextUtils.isEmpty(this.f4553f)) {
                requestLayout();
            } else {
                i(this.f4553f, true);
            }
        } catch (Exception unused) {
        }
    }

    public final void d(ArrayList arrayList) {
        ArrayList arrayList2;
        char c7;
        char c8;
        int i7;
        ArrayList arrayList3;
        char c9;
        char c10;
        int i8;
        int size = arrayList.size();
        while (true) {
            arrayList2 = this.f4548a;
            c7 = 52429;
            c8 = '\n';
            i7 = 2;
            if (arrayList2.size() >= size) {
                break;
            }
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            linearLayout2.setOrientation(1);
            linearLayout2.setGravity(17);
            linearLayout2.setPadding(0, e(10), 0, e(10));
            LinearLayout linearLayout3 = new LinearLayout(getContext());
            linearLayout3.setOrientation(1);
            linearLayout3.setGravity(1);
            ImageView imageView = new ImageView(getContext());
            ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
            imageView.setScaleType(scaleType);
            Space space = new Space(getContext());
            ImageView imageView2 = new ImageView(getContext());
            imageView2.setScaleType(scaleType);
            linearLayout3.addView(imageView, new LinearLayout.LayoutParams(0, 0, 0.6f));
            linearLayout3.addView(space, new LinearLayout.LayoutParams(-1, e(2)));
            linearLayout3.addView(imageView2, new LinearLayout.LayoutParams(0, 0, 0.4f));
            ImageView imageView3 = new ImageView(getContext());
            imageView3.setScaleType(scaleType);
            TextView textView = new TextView(getContext());
            textView.setText("推荐");
            textView.setTextColor(-1);
            textView.setTextSize(2, 16.0f);
            textView.setGravity(17);
            textView.setSingleLine(true);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setShadowLayer(3.0f, 1.0f, 1.0f, d1.MEASURED_STATE_MASK);
            textView.setVisibility(8);
            linearLayout2.addView(linearLayout3);
            linearLayout2.addView(imageView3);
            linearLayout2.addView(textView);
            View view = new View(getContext());
            view.setBackgroundColor(Color.parseColor("#66FFFFFF"));
            linearLayout.addView(linearLayout2);
            linearLayout.addView(view);
            linearLayout.setVisibility(8);
            arrayList2.add(new j(linearLayout, linearLayout2, linearLayout3, imageView, space, imageView2, imageView3, textView, view));
            this.f4549b.addView(linearLayout);
        }
        int size2 = arrayList.size();
        int max = Math.max(e(24), this.f4550c - e(20));
        int e7 = e(2);
        int i9 = 0;
        while (i9 < arrayList2.size()) {
            j jVar = (j) arrayList2.get(i9);
            if (i9 < size2) {
                i iVar = (i) arrayList.get(i9);
                boolean z6 = i9 == size2 + (-1);
                jVar.f7001a.setVisibility(0);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(max, this.f4550c);
                layoutParams.setMargins(e7, 0, e7, 0);
                jVar.f7002b.setLayoutParams(layoutParams);
                int i10 = iVar.f6999c;
                LinearLayout linearLayout4 = jVar.f7003c;
                TextView textView2 = jVar.f7008h;
                ImageView imageView4 = jVar.f7007g;
                int i11 = iVar.f6997a;
                arrayList3 = arrayList2;
                if (i10 == 1) {
                    linearLayout4.setVisibility(0);
                    imageView4.setVisibility(8);
                    textView2.setVisibility(8);
                    ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(max, 0, 0.6f);
                    ImageView imageView5 = jVar.f7004d;
                    imageView5.setLayoutParams(layoutParams2);
                    ViewGroup.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(max, 0, 0.4f);
                    ImageView imageView6 = jVar.f7006f;
                    imageView6.setLayoutParams(layoutParams3);
                    jVar.f7005e.setLayoutParams(new LinearLayout.LayoutParams(max, e(2)));
                    imageView5.setImageResource(i11);
                    a(imageView5);
                    imageView6.setImageResource(iVar.f7000d);
                    a(imageView6);
                    c9 = 52429;
                    i8 = 8;
                } else {
                    c9 = 52429;
                    linearLayout4.setVisibility(8);
                    imageView4.setVisibility(0);
                    imageView4.setImageResource(i11);
                    a(imageView4);
                    if (iVar.f6998b) {
                        imageView4.setLayoutParams(new LinearLayout.LayoutParams(max, 0, 1.0f));
                        textView2.setVisibility(0);
                        textView2.setTextColor(this.f4555h);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams4.topMargin = e(-8);
                        textView2.setLayoutParams(layoutParams4);
                        i8 = 8;
                    } else {
                        imageView4.setLayoutParams(new LinearLayout.LayoutParams(max, max));
                        i8 = 8;
                        textView2.setVisibility(8);
                    }
                }
                View view2 = jVar.f7009i;
                if (z6) {
                    view2.setVisibility(i8);
                    c10 = '\n';
                } else {
                    view2.setVisibility(0);
                    c10 = '\n';
                    LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(e(1), this.f4550c - e(10));
                    layoutParams5.setMargins(0, e(5), 0, e(5));
                    view2.setLayoutParams(layoutParams5);
                }
            } else {
                arrayList3 = arrayList2;
                c9 = c7;
                c10 = c8;
                jVar.f7001a.setVisibility(8);
            }
            i9++;
            c7 = c9;
            c8 = c10;
            arrayList2 = arrayList3;
            i7 = 2;
        }
        int min = Math.min(e(8) + (e(1) * Math.max(0, size2 - 1)) + (((e7 * i7) + max) * size2), (int) (getResources().getDisplayMetrics().widthPixels * 0.8f));
        ViewGroup.LayoutParams layoutParams6 = getLayoutParams();
        if (layoutParams6 instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) layoutParams6;
            layoutParams7.width = min;
            layoutParams7.height = this.f4550c;
            setLayoutParams(layoutParams7);
        } else {
            setLayoutParams(new LinearLayout.LayoutParams(min, this.f4550c));
        }
        ViewGroup.LayoutParams layoutParams8 = this.f4549b.getLayoutParams();
        if (layoutParams8 instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams9 = (LinearLayout.LayoutParams) layoutParams8;
            layoutParams9.height = this.f4550c;
            this.f4549b.setLayoutParams(layoutParams9);
        } else {
            this.f4549b.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f4550c));
        }
        this.f4549b.setVisibility(0);
        setVisibility(0);
    }

    public final int e(int i7) {
        return Math.round(i7 * this.f4552e.density);
    }

    public final void f() {
        LinearLayout linearLayout = this.f4549b;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        Iterator it = this.f4548a.iterator();
        while (it.hasNext()) {
            ((j) it.next()).f7001a.setVisibility(8);
        }
        setVisibility(8);
    }

    public final boolean g() {
        try {
            UiModeManager uiModeManager = (UiModeManager) getContext().getSystemService("uimode");
            if (uiModeManager != null) {
                int nightMode = uiModeManager.getNightMode();
                if (nightMode == 2) {
                    return true;
                }
                if (nightMode == 1) {
                    return false;
                }
            }
        } catch (Exception unused) {
        }
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    public final void i(String str, boolean z6) {
        String trim = str == null ? h0.FRAGMENT_ENCODE_SET : str.trim();
        if (TextUtils.isEmpty(trim)) {
            this.f4553f = null;
            f();
            return;
        }
        if (z6 || !TextUtils.equals(this.f4553f, trim)) {
            try {
                JSONObject jSONObject = new JSONObject(trim);
                boolean optBoolean = jSONObject.optBoolean("drive_way_enabled", true);
                JSONArray optJSONArray = jSONObject.optJSONArray("drive_way_info");
                if (optBoolean && optJSONArray != null && optJSONArray.length() != 0) {
                    ArrayList h7 = h(optJSONArray);
                    if (h7.isEmpty()) {
                        this.f4553f = null;
                        f();
                        return;
                    } else {
                        d(h7);
                        this.f4553f = trim;
                        return;
                    }
                }
                this.f4553f = null;
                f();
            } catch (Exception unused) {
                this.f4553f = null;
                f();
            }
        }
    }

    public final void j(int i7) {
        this.f4550c = e(Math.max(50, Math.min(c.INVOKE_INTERFACE_RANGE, i7)));
        try {
            ViewGroup.LayoutParams layoutParams = this.f4549b.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).height = this.f4550c;
                this.f4549b.setLayoutParams(layoutParams);
            } else {
                this.f4549b.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f4550c));
            }
            ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
            if (layoutParams2 instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams2).height = this.f4550c;
                setLayoutParams(layoutParams2);
            } else {
                setLayoutParams(new LinearLayout.LayoutParams(-2, this.f4550c));
            }
            if (TextUtils.isEmpty(this.f4553f)) {
                requestLayout();
            } else {
                i(this.f4553f, true);
            }
        } catch (Exception unused) {
        }
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onLaneInfoEvent(d dVar) {
        if (dVar == null || this.f4549b == null) {
            return;
        }
        p0 p0Var = new p0(9, this, dVar);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            p0Var.run();
            return;
        }
        Handler handler = this.f4551d;
        if (handler != null) {
            handler.post(p0Var);
        }
    }
}
