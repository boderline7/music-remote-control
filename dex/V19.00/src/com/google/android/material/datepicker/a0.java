package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 extends BaseAdapter {

    /* renamed from: g, reason: collision with root package name */
    public static final int f3592g = j0.g(null).getMaximum(4);

    /* renamed from: h, reason: collision with root package name */
    public static final int f3593h = (j0.g(null).getMaximum(7) + j0.g(null).getMaximum(5)) - 1;

    /* renamed from: a, reason: collision with root package name */
    public final Month f3594a;

    /* renamed from: b, reason: collision with root package name */
    public final DateSelector f3595b;

    /* renamed from: c, reason: collision with root package name */
    public Collection f3596c;

    /* renamed from: d, reason: collision with root package name */
    public android.support.v4.media.p f3597d;

    /* renamed from: e, reason: collision with root package name */
    public final CalendarConstraints f3598e;

    /* renamed from: f, reason: collision with root package name */
    public final DayViewDecorator f3599f;

    public a0(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.f3594a = month;
        this.f3595b = dateSelector;
        this.f3598e = calendarConstraints;
        this.f3599f = dayViewDecorator;
        this.f3596c = dateSelector.k();
    }

    public final int a() {
        int i7 = this.f3598e.f3568e;
        Month month = this.f3594a;
        Calendar calendar = month.f3579a;
        int i8 = calendar.get(7);
        if (i7 <= 0) {
            i7 = calendar.getFirstDayOfWeek();
        }
        int i9 = i8 - i7;
        return i9 < 0 ? i9 + month.f3582d : i9;
    }

    @Override // android.widget.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Long getItem(int i7) {
        if (i7 < a() || i7 > c()) {
            return null;
        }
        int a7 = (i7 - a()) + 1;
        Calendar c7 = j0.c(this.f3594a.f3579a);
        c7.set(5, a7);
        return Long.valueOf(c7.getTimeInMillis());
    }

    public final int c() {
        return (a() + this.f3594a.f3583e) - 1;
    }

    public final void d(TextView textView, long j7, int i7) {
        boolean z6;
        boolean z7;
        androidx.appcompat.widget.w wVar;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        boolean z8 = false;
        boolean z9 = j0.f().getTimeInMillis() == j7;
        DateSelector dateSelector = this.f3595b;
        Iterator it = dateSelector.e().iterator();
        while (true) {
            if (!it.hasNext()) {
                z6 = false;
                break;
            }
            Object obj = ((o0.c) it.next()).f7788a;
            if (obj != null && ((Long) obj).longValue() == j7) {
                z6 = true;
                break;
            }
        }
        Iterator it2 = dateSelector.e().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z7 = false;
                break;
            }
            Object obj2 = ((o0.c) it2.next()).f7789b;
            if (obj2 != null && ((Long) obj2).longValue() == j7) {
                z7 = true;
                break;
            }
        }
        Calendar f7 = j0.f();
        Calendar g7 = j0.g(null);
        g7.setTimeInMillis(j7);
        String format = f7.get(1) == g7.get(1) ? j0.b("MMMMEEEEd", Locale.getDefault()).format(new Date(j7)) : j0.b("yMMMMEEEEd", Locale.getDefault()).format(new Date(j7));
        if (z9) {
            format = String.format(context.getString(q2.k.mtrl_picker_today_description), format);
        }
        if (z6) {
            format = String.format(context.getString(q2.k.mtrl_picker_start_date_description), format);
        } else if (z7) {
            format = String.format(context.getString(q2.k.mtrl_picker_end_date_description), format);
        }
        textView.setContentDescription(format);
        if (this.f3598e.f3566c.f(j7)) {
            textView.setEnabled(true);
            Iterator it3 = dateSelector.k().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                if (j0.a(j7) == j0.a(((Long) it3.next()).longValue())) {
                    z8 = true;
                    break;
                }
            }
            textView.setSelected(z8);
            wVar = z8 ? (androidx.appcompat.widget.w) this.f3597d.f553b : j0.f().getTimeInMillis() == j7 ? (androidx.appcompat.widget.w) this.f3597d.f554c : (androidx.appcompat.widget.w) this.f3597d.f552a;
        } else {
            textView.setEnabled(false);
            wVar = (androidx.appcompat.widget.w) this.f3597d.f558g;
        }
        if (this.f3599f == null || i7 == -1) {
            wVar.n(textView);
            return;
        }
        int i8 = this.f3594a.f3581c;
        wVar.n(textView);
        textView.setCompoundDrawables(null, null, null, null);
        textView.setContentDescription(format);
    }

    public final void e(MaterialCalendarGridView materialCalendarGridView, long j7) {
        Month m6 = Month.m(j7);
        Month month = this.f3594a;
        if (m6.equals(month)) {
            Calendar c7 = j0.c(month.f3579a);
            c7.setTimeInMillis(j7);
            int i7 = c7.get(5);
            d((TextView) materialCalendarGridView.getChildAt((materialCalendarGridView.a().a() + (i7 - 1)) - materialCalendarGridView.getFirstVisiblePosition()), j7, i7);
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return f3593h;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return i7 / this.f3594a.f3582d;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            android.support.v4.media.p r1 = r5.f3597d
            if (r1 != 0) goto Lf
            android.support.v4.media.p r1 = new android.support.v4.media.p
            r1.<init>(r0)
            r5.f3597d = r1
        Lf:
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L26
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = q2.i.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L26:
            int r7 = r5.a()
            int r7 = r6 - r7
            if (r7 < 0) goto L5c
            com.google.android.material.datepicker.Month r8 = r5.f3594a
            int r2 = r8.f3583e
            if (r7 < r2) goto L35
            goto L5c
        L35:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L65
        L5c:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L65:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L6c
            goto L73
        L6c:
            long r1 = r6.longValue()
            r5.d(r0, r1, r7)
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.a0.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
