package com.google.android.material.datepicker;

import android.view.View;
import java.util.Calendar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k0 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3641a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ m0 f3642b;

    public k0(m0 m0Var, int i7) {
        this.f3642b = m0Var;
        this.f3641a = i7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        m0 m0Var = this.f3642b;
        Month l7 = Month.l(this.f3641a, m0Var.f3648a.f3662f.f3580b);
        t tVar = m0Var.f3648a;
        CalendarConstraints calendarConstraints = tVar.f3660d;
        Month month = calendarConstraints.f3564a;
        Calendar calendar = month.f3579a;
        Calendar calendar2 = l7.f3579a;
        if (calendar2.compareTo(calendar) < 0) {
            l7 = month;
        } else {
            Month month2 = calendarConstraints.f3565b;
            if (calendar2.compareTo(month2.f3579a) > 0) {
                l7 = month2;
            }
        }
        tVar.e(l7);
        tVar.f(r.DAY);
    }
}
