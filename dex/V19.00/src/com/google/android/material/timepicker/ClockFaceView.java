package com.google.android.material.timepicker;

import a0.p;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p0.d1;
import q2.l;
import q2.m;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class ClockFaceView extends f implements d {
    public final c A;
    public final int[] B;
    public final float[] C;
    public final int D;
    public final int E;
    public final int F;
    public final int G;
    public final String[] H;
    public float I;
    public final ColorStateList J;

    /* renamed from: v, reason: collision with root package name */
    public final ClockHandView f4434v;

    /* renamed from: w, reason: collision with root package name */
    public final Rect f4435w;

    /* renamed from: x, reason: collision with root package name */
    public final RectF f4436x;

    /* renamed from: y, reason: collision with root package name */
    public final Rect f4437y;

    /* renamed from: z, reason: collision with root package name */
    public final SparseArray f4438z;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.materialClockStyle);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f4435w = new Rect();
        this.f4436x = new RectF();
        this.f4437y = new Rect();
        SparseArray sparseArray = new SparseArray();
        this.f4438z = sparseArray;
        this.C = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.ClockFaceView, i7, l.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList w6 = l6.f.w(context, obtainStyledAttributes, m.ClockFaceView_clockNumberTextColor);
        this.J = w6;
        LayoutInflater.from(context).inflate(q2.i.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(q2.g.material_clock_hand);
        this.f4434v = clockHandView;
        this.D = resources.getDimensionPixelSize(q2.e.material_clock_hand_padding);
        int colorForState = w6.getColorForState(new int[]{R.attr.state_selected}, w6.getDefaultColor());
        this.B = new int[]{colorForState, colorForState, w6.getDefaultColor()};
        clockHandView.f4441c.add(this);
        int defaultColor = g0.g.b(context, q2.d.material_timepicker_clockface).getDefaultColor();
        ColorStateList w7 = l6.f.w(context, obtainStyledAttributes, m.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(w7 != null ? w7.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.A = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, h0.FRAGMENT_ENCODE_SET);
        this.H = strArr;
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        boolean z6 = false;
        for (int i8 = 0; i8 < Math.max(this.H.length, size); i8++) {
            TextView textView = (TextView) sparseArray.get(i8);
            if (i8 >= this.H.length) {
                removeView(textView);
                sparseArray.remove(i8);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(q2.i.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i8, textView);
                    addView(textView);
                }
                textView.setText(this.H[i8]);
                textView.setTag(q2.g.material_value_index, Integer.valueOf(i8));
                int i9 = (i8 / 12) + 1;
                textView.setTag(q2.g.material_clock_level, Integer.valueOf(i9));
                z6 = i9 > 1 ? true : z6;
                d1.o(textView, this.A);
                textView.setTextColor(this.J);
            }
        }
        ClockHandView clockHandView2 = this.f4434v;
        if (clockHandView2.f4440b && !z6) {
            clockHandView2.f4451m = 1;
        }
        clockHandView2.f4440b = z6;
        clockHandView2.invalidate();
        this.E = resources.getDimensionPixelSize(q2.e.material_time_picker_minimum_screen_height);
        this.F = resources.getDimensionPixelSize(q2.e.material_time_picker_minimum_screen_width);
        this.G = resources.getDimensionPixelSize(q2.e.material_clock_size);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) q0.h.a(1, this.H.length, 1).f8425a);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        q();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int max = (int) (this.G / Math.max(Math.max(this.E / displayMetrics.heightPixels, this.F / displayMetrics.widthPixels), 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max, v.c.EXACTLY);
        setMeasuredDimension(max, max);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    @Override // com.google.android.material.timepicker.f
    public final void p() {
        p pVar = new p();
        pVar.e(this);
        HashMap hashMap = new HashMap();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getId() != q2.g.circle_center && !"skip".equals(childAt.getTag())) {
                int i8 = (Integer) childAt.getTag(q2.g.material_clock_level);
                if (i8 == null) {
                    i8 = 1;
                }
                if (!hashMap.containsKey(i8)) {
                    hashMap.put(i8, new ArrayList());
                }
                ((List) hashMap.get(i8)).add(childAt);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            List list = (List) entry.getValue();
            int round = ((Integer) entry.getKey()).intValue() == 2 ? Math.round(this.f4463t * 0.66f) : this.f4463t;
            Iterator it = list.iterator();
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
            while (it.hasNext()) {
                int id = ((View) it.next()).getId();
                int i9 = q2.g.circle_center;
                a0.l lVar = pVar.h(id).f85e;
                lVar.A = i9;
                lVar.B = round;
                lVar.C = f7;
                f7 += 360.0f / list.size();
            }
        }
        pVar.b(this);
        int i10 = 0;
        while (true) {
            SparseArray sparseArray = this.f4438z;
            if (i10 >= sparseArray.size()) {
                return;
            }
            ((TextView) sparseArray.get(i10)).setVisibility(0);
            i10++;
        }
    }

    public final void q() {
        SparseArray sparseArray;
        RectF rectF;
        Rect rect;
        RectF rectF2 = this.f4434v.f4445g;
        float f7 = Float.MAX_VALUE;
        TextView textView = null;
        int i7 = 0;
        while (true) {
            sparseArray = this.f4438z;
            int size = sparseArray.size();
            rectF = this.f4436x;
            rect = this.f4435w;
            if (i7 >= size) {
                break;
            }
            TextView textView2 = (TextView) sparseArray.get(i7);
            if (textView2 != null) {
                textView2.getHitRect(rect);
                rectF.set(rect);
                rectF.union(rectF2);
                float height = rectF.height() * rectF.width();
                if (height < f7) {
                    textView = textView2;
                    f7 = height;
                }
            }
            i7++;
        }
        for (int i8 = 0; i8 < sparseArray.size(); i8++) {
            TextView textView3 = (TextView) sparseArray.get(i8);
            if (textView3 != null) {
                textView3.setSelected(textView3 == textView);
                textView3.getHitRect(rect);
                rectF.set(rect);
                textView3.getLineBounds(0, this.f4437y);
                rectF.inset(r8.left, r8.top);
                textView3.getPaint().setShader(!RectF.intersects(rectF2, rectF) ? null : new RadialGradient(rectF2.centerX() - rectF.left, rectF2.centerY() - rectF.top, 0.5f * rectF2.width(), this.B, this.C, Shader.TileMode.CLAMP));
                textView3.invalidate();
            }
        }
    }
}
