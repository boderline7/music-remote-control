package com.navi.tool.amapflying.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.yalantis.ucrop.view.CropImageView;
import h4.e;
import h4.g;
import i6.k;
import j4.l;
import j4.m;
import j4.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import s.f;
import z1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TmcBarFloatingView extends FrameLayout {
    public static final int POSITION_BOTTOM = 2;
    public static final int POSITION_RIGHT = 0;
    public static final int POSITION_TOP = 1;

    /* renamed from: a, reason: collision with root package name */
    public final Context f4613a;

    /* renamed from: b, reason: collision with root package name */
    public final WindowManager f4614b;

    /* renamed from: c, reason: collision with root package name */
    public WindowManager.LayoutParams f4615c;

    /* renamed from: d, reason: collision with root package name */
    public final n f4616d;

    /* renamed from: e, reason: collision with root package name */
    public final l f4617e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f4618f;

    /* renamed from: g, reason: collision with root package name */
    public int f4619g;

    /* renamed from: h, reason: collision with root package name */
    public int f4620h;

    /* renamed from: i, reason: collision with root package name */
    public final int f4621i;

    /* renamed from: j, reason: collision with root package name */
    public int f4622j;

    /* renamed from: k, reason: collision with root package name */
    public g f4623k;

    public TmcBarFloatingView(Context context, WindowManager windowManager) {
        super(context);
        this.f4618f = false;
        this.f4619g = 0;
        this.f4620h = -1;
        this.f4613a = context;
        this.f4614b = windowManager;
        new Handler(Looper.getMainLooper());
        this.f4621i = (context.getResources().getDisplayMetrics().heightPixels * 2) / 3;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(View.generateViewId());
        linearLayout.setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        float a7 = a(25) / 2.0f;
        gradientDrawable.setCornerRadius(a7);
        linearLayout.setBackground(gradientDrawable);
        int a8 = a(2);
        linearLayout.setPadding(a8, a8, a8, a8);
        this.f4616d = new n(context);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(0);
        gradientDrawable2.setCornerRadius(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, a7 - a8));
        this.f4616d.setBackground(gradientDrawable2);
        this.f4616d.setClipToOutline(true);
        linearLayout.addView(this.f4616d, new LinearLayout.LayoutParams(-1, -1));
        addView(linearLayout);
        l lVar = new l(this, context);
        this.f4617e = lVar;
        addView(lVar);
        b();
    }

    public final int a(int i7) {
        return Math.round(i7 * this.f4613a.getResources().getDisplayMetrics().density);
    }

    public final void b() {
        int i7;
        int a7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this.f4613a);
        linearLayout.setOrientation(this.f4619g == 0 ? 1 : 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        float a8 = a(25) / 2.0f;
        gradientDrawable.setCornerRadius(a8);
        linearLayout.setBackground(gradientDrawable);
        int a9 = a(2);
        linearLayout.setPadding(a9, a9, a9, a9);
        n nVar = this.f4616d;
        int i14 = this.f4619g == 0 ? 0 : 1;
        if (nVar.f7021d != i14) {
            nVar.f7021d = i14;
            nVar.invalidate();
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(0);
        gradientDrawable2.setCornerRadius(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, a8 - a9));
        this.f4616d.setBackground(gradientDrawable2);
        this.f4616d.setClipToOutline(true);
        if (this.f4616d.getParent() != null) {
            ((LinearLayout) this.f4616d.getParent()).removeView(this.f4616d);
        }
        linearLayout.addView(this.f4616d, new LinearLayout.LayoutParams(-1, -1));
        int a10 = a(25);
        int i15 = this.f4619g;
        int i16 = this.f4621i;
        if (i15 == 0) {
            this.f4622j = i16;
        } else if (i15 == 1) {
            this.f4622j = 480;
        } else {
            this.f4622j = 480;
        }
        int i17 = i15 == 0 ? a10 : this.f4622j;
        if (i15 == 0) {
            a10 = this.f4622j;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i17, a10);
        layoutParams.gravity = 17;
        addView(linearLayout, layoutParams);
        if (this.f4617e.getParent() != null) {
            ((FrameLayout) this.f4617e.getParent()).removeView(this.f4617e);
        }
        int a11 = a(29);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(a11, a11);
        if (this.f4619g == 0) {
            layoutParams2.gravity = 81;
            layoutParams2.bottomMargin = 0;
            l lVar = this.f4617e;
            if (lVar.f7014c != 0) {
                lVar.f7014c = 0;
                lVar.invalidate();
            }
        } else {
            layoutParams2.gravity = 19;
            layoutParams2.leftMargin = 0;
            l lVar2 = this.f4617e;
            if (lVar2.f7014c != 1) {
                lVar2.f7014c = 1;
                lVar2.invalidate();
            }
        }
        addView(this.f4617e, layoutParams2);
        int a12 = a(10);
        int i18 = this.f4619g;
        if (i18 == 0) {
            int a13 = a(25) + a12;
            int i19 = i16 + a12;
            i11 = a(30);
            i12 = 21;
            i9 = 0;
            i13 = i19;
            i10 = a13;
        } else {
            if (i18 == 1) {
                i7 = a12 + 480;
                a7 = a(25) + a12;
                i8 = c.OR_INT;
            } else {
                i7 = a12 + 480;
                a7 = a(25) + a12;
                i8 = 580;
            }
            i9 = i8;
            i10 = i7;
            i11 = 1350;
            i12 = 51;
            i13 = a7;
        }
        WindowManager.LayoutParams layoutParams3 = this.f4615c;
        if (layoutParams3 == null) {
            this.f4615c = new WindowManager.LayoutParams(i10, i13, 2038, 24, -3);
        } else {
            layoutParams3.width = i10;
            layoutParams3.height = i13;
        }
        WindowManager.LayoutParams layoutParams4 = this.f4615c;
        layoutParams4.gravity = i12;
        layoutParams4.x = i11;
        layoutParams4.y = i9;
    }

    public final void c(g gVar) {
        List list = gVar.f6745a;
        if (list == null || list.isEmpty()) {
            n nVar = this.f4616d;
            if (nVar != null) {
                nVar.a(Collections.emptyList());
            }
            setVisibility(8);
            return;
        }
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new f(6, this));
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            h4.f fVar = (h4.f) it.next();
            int i9 = fVar.f6744c;
            if (i9 > 0) {
                int i10 = fVar.f6743b;
                if (i10 == 10) {
                    i8 += i9;
                }
                arrayList2.add(new m(i9, i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 10 ? Color.parseColor("#2F6657") : Color.parseColor("#EAEAEA") : Color.parseColor("#B71C1C") : Color.parseColor("#F44336") : Color.parseColor("#FFC600") : Color.parseColor("#00BA77")));
                i7 += i9;
            }
        }
        n nVar2 = this.f4616d;
        if (nVar2 != null) {
            nVar2.a(arrayList2);
        }
        int round = i7 > 0 ? Math.round((i8 * 100.0f) / i7) : 0;
        int i11 = round >= 0 ? round : 0;
        if (i11 > 100) {
            i11 = 100;
        }
        int i12 = this.f4622j;
        int i13 = (int) (i12 * (i11 / 100.0f));
        if (i13 <= i12) {
            i12 = i13;
        }
        l lVar = this.f4617e;
        if (lVar != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) lVar.getLayoutParams();
            if (this.f4619g == 0) {
                if (Math.abs(layoutParams.bottomMargin - i12) > 2) {
                    layoutParams.bottomMargin = i12;
                    this.f4617e.setLayoutParams(layoutParams);
                    return;
                }
                return;
            }
            if (Math.abs(layoutParams.leftMargin - i12) > 2) {
                layoutParams.leftMargin = i12;
                this.f4617e.setLayoutParams(layoutParams);
            }
        }
    }

    public WindowManager.LayoutParams getWindowParams() {
        return this.f4615c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        if (r4.toLowerCase().contains("km") != false) goto L19;
     */
    @i6.k(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onGuideInfoEvent(h4.b r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.lang.String r4 = r4.f6728c
            if (r4 != 0) goto L8
            return
        L8:
            boolean r0 = r4.isEmpty()
            r1 = -1
            if (r0 == 0) goto L10
            goto L3e
        L10:
            java.lang.String r0 = "[^0-9.]"
            java.lang.String r2 = ""
            java.lang.String r0 = r4.replaceAll(r0, r2)     // Catch: java.lang.Exception -> L3e
            boolean r2 = r0.isEmpty()     // Catch: java.lang.Exception -> L3e
            if (r2 == 0) goto L1f
            goto L3e
        L1f:
            float r0 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.Exception -> L3e
            java.lang.String r2 = "公里"
            boolean r2 = r4.contains(r2)     // Catch: java.lang.Exception -> L3e
            if (r2 != 0) goto L3a
            java.lang.String r4 = r4.toLowerCase()     // Catch: java.lang.Exception -> L3e
            java.lang.String r2 = "km"
            boolean r4 = r4.contains(r2)     // Catch: java.lang.Exception -> L3e
            if (r4 == 0) goto L38
            goto L3a
        L38:
            int r1 = (int) r0
            goto L3e
        L3a:
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r0 = r0 * r4
            goto L38
        L3e:
            if (r1 >= 0) goto L41
            return
        L41:
            int r4 = r3.f4620h
            if (r4 <= 0) goto L47
            if (r1 <= r4) goto L49
        L47:
            r3.f4620h = r1
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.util.TmcBarFloatingView.onGuideInfoEvent(h4.b):void");
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onNavigationEnd(e eVar) {
        setVisibility(8);
        this.f4620h = -1;
        this.f4623k = null;
        l lVar = this.f4617e;
        if (lVar != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) lVar.getLayoutParams();
            if (this.f4619g == 0) {
                layoutParams.bottomMargin = 0;
            } else {
                layoutParams.leftMargin = 0;
            }
            this.f4617e.setLayoutParams(layoutParams);
        }
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onTmcInfoEvent(g gVar) {
        if (gVar != null) {
            this.f4623k = gVar;
            c(gVar);
        } else {
            n nVar = this.f4616d;
            if (nVar != null) {
                nVar.a(Collections.emptyList());
            }
            setVisibility(8);
        }
    }

    public void setPositionMode(int i7) {
        WindowManager windowManager;
        if (this.f4619g != i7) {
            this.f4619g = i7;
            b();
            if (this.f4618f && (windowManager = this.f4614b) != null) {
                windowManager.updateViewLayout(this, this.f4615c);
            }
            g gVar = this.f4623k;
            if (gVar != null) {
                c(gVar);
            }
        }
    }
}
