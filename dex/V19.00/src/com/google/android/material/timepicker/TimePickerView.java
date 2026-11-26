package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class TimePickerView extends ConstraintLayout {

    /* renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ int f4456t = 0;

    /* renamed from: s, reason: collision with root package name */
    public final Chip f4457s;

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, 0);
        i iVar = new i(this);
        LayoutInflater.from(context).inflate(q2.i.material_timepicker, this);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(q2.g.material_clock_period_toggle);
        materialButtonToggleGroup.f3448c.add(new h(this));
        Chip chip = (Chip) findViewById(q2.g.material_minute_tv);
        Chip chip2 = (Chip) findViewById(q2.g.material_hour_tv);
        this.f4457s = chip2;
        k kVar = new k(new GestureDetector(getContext(), new j(this)));
        chip.setOnTouchListener(kVar);
        chip2.setOnTouchListener(kVar);
        chip.setTag(q2.g.selection_type, 12);
        chip2.setTag(q2.g.selection_type, 10);
        chip.setOnClickListener(iVar);
        chip2.setOnClickListener(iVar);
        chip.setAccessibilityClassName("android.view.View");
        chip2.setAccessibilityClassName("android.view.View");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i7) {
        super.onVisibilityChanged(view, i7);
        if (view == this && i7 == 0) {
            this.f4457s.sendAccessibilityEvent(8);
        }
    }
}
