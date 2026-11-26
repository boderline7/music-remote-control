package com.google.android.material.datepicker;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.j2;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 extends j2 {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f3605a;

    /* renamed from: b, reason: collision with root package name */
    public final MaterialCalendarGridView f3606b;

    public c0(LinearLayout linearLayout, boolean z6) {
        super(linearLayout);
        TextView textView = (TextView) linearLayout.findViewById(q2.g.month_title);
        this.f3605a = textView;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        new p0.h0(d0.c.tag_accessibility_heading, 3).b(textView, Boolean.TRUE);
        this.f3606b = (MaterialCalendarGridView) linearLayout.findViewById(q2.g.month_grid);
        if (z6) {
            return;
        }
        textView.setVisibility(8);
    }
}
