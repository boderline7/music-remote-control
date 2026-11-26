package com.google.android.material.datepicker;

import android.R;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.s1;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d0 extends h1 {

    /* renamed from: a, reason: collision with root package name */
    public final CalendarConstraints f3607a;

    /* renamed from: b, reason: collision with root package name */
    public final DateSelector f3608b;

    /* renamed from: c, reason: collision with root package name */
    public final DayViewDecorator f3609c;

    /* renamed from: d, reason: collision with root package name */
    public final s f3610d;

    /* renamed from: e, reason: collision with root package name */
    public final int f3611e;

    public d0(ContextThemeWrapper contextThemeWrapper, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator, o oVar) {
        Month month = calendarConstraints.f3564a;
        Month month2 = calendarConstraints.f3567d;
        if (month.f3579a.compareTo(month2.f3579a) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (month2.f3579a.compareTo(calendarConstraints.f3565b.f3579a) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int i7 = a0.f3592g;
        int i8 = t.f3657o;
        this.f3611e = (contextThemeWrapper.getResources().getDimensionPixelSize(q2.e.mtrl_calendar_day_height) * i7) + (x.g(contextThemeWrapper, R.attr.windowFullscreen) ? contextThemeWrapper.getResources().getDimensionPixelSize(q2.e.mtrl_calendar_day_height) : 0);
        this.f3607a = calendarConstraints;
        this.f3608b = dateSelector;
        this.f3609c = dayViewDecorator;
        this.f3610d = oVar;
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.h1
    public final int getItemCount() {
        return this.f3607a.f3570g;
    }

    @Override // androidx.recyclerview.widget.h1
    public final long getItemId(int i7) {
        Calendar c7 = j0.c(this.f3607a.f3564a.f3579a);
        c7.add(2, i7);
        return new Month(c7).f3579a.getTimeInMillis();
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onBindViewHolder(j2 j2Var, int i7) {
        c0 c0Var = (c0) j2Var;
        CalendarConstraints calendarConstraints = this.f3607a;
        Calendar c7 = j0.c(calendarConstraints.f3564a.f3579a);
        c7.add(2, i7);
        Month month = new Month(c7);
        c0Var.f3605a.setText(month.n());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) c0Var.f3606b.findViewById(q2.g.month_grid);
        if (materialCalendarGridView.a() == null || !month.equals(materialCalendarGridView.a().f3594a)) {
            a0 a0Var = new a0(month, this.f3608b, calendarConstraints, this.f3609c);
            materialCalendarGridView.setNumColumns(month.f3582d);
            materialCalendarGridView.setAdapter((ListAdapter) a0Var);
        } else {
            materialCalendarGridView.invalidate();
            a0 a7 = materialCalendarGridView.a();
            Iterator it = a7.f3596c.iterator();
            while (it.hasNext()) {
                a7.e(materialCalendarGridView, ((Long) it.next()).longValue());
            }
            DateSelector dateSelector = a7.f3595b;
            if (dateSelector != null) {
                Iterator it2 = dateSelector.k().iterator();
                while (it2.hasNext()) {
                    a7.e(materialCalendarGridView, ((Long) it2.next()).longValue());
                }
                a7.f3596c = dateSelector.k();
            }
        }
        materialCalendarGridView.setOnItemClickListener(new b0(this, materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.h1
    public final j2 onCreateViewHolder(ViewGroup viewGroup, int i7) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(q2.i.mtrl_calendar_month_labeled, viewGroup, false);
        if (!x.g(viewGroup.getContext(), R.attr.windowFullscreen)) {
            return new c0(linearLayout, false);
        }
        linearLayout.setLayoutParams(new s1(-1, this.f3611e));
        return new c0(linearLayout, true);
    }
}
