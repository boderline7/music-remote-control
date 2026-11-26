package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final Calendar f3634a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3635b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3636c;

    public j() {
        Calendar g7 = j0.g(null);
        this.f3634a = g7;
        this.f3635b = g7.getMaximum(7);
        this.f3636c = g7.getFirstDayOfWeek();
    }

    public j(int i7) {
        Calendar g7 = j0.g(null);
        this.f3634a = g7;
        this.f3635b = g7.getMaximum(7);
        this.f3636c = i7;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f3635b;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i7) {
        int i8 = this.f3635b;
        if (i7 >= i8) {
            return null;
        }
        int i9 = i7 + this.f3636c;
        if (i9 > i8) {
            i9 -= i8;
        }
        return Integer.valueOf(i9);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(q2.i.mtrl_calendar_day_of_week, viewGroup, false);
        }
        int i8 = i7 + this.f3636c;
        int i9 = this.f3635b;
        if (i8 > i9) {
            i8 -= i9;
        }
        Calendar calendar = this.f3634a;
        calendar.set(7, i8);
        textView.setText(calendar.getDisplayName(7, 4, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(q2.k.mtrl_picker_day_of_week_column_header), calendar.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
