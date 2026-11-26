package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new z(0);

    /* renamed from: a, reason: collision with root package name */
    public final Calendar f3579a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3580b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3581c;

    /* renamed from: d, reason: collision with root package name */
    public final int f3582d;

    /* renamed from: e, reason: collision with root package name */
    public final int f3583e;

    /* renamed from: f, reason: collision with root package name */
    public final long f3584f;

    /* renamed from: g, reason: collision with root package name */
    public String f3585g;

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar c7 = j0.c(calendar);
        this.f3579a = c7;
        this.f3580b = c7.get(2);
        this.f3581c = c7.get(1);
        this.f3582d = c7.getMaximum(7);
        this.f3583e = c7.getActualMaximum(5);
        this.f3584f = c7.getTimeInMillis();
    }

    public static Month l(int i7, int i8) {
        Calendar g7 = j0.g(null);
        g7.set(1, i7);
        g7.set(2, i8);
        return new Month(g7);
    }

    public static Month m(long j7) {
        Calendar g7 = j0.g(null);
        g7.setTimeInMillis(j7);
        return new Month(g7);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Month month) {
        return this.f3579a.compareTo(month.f3579a);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.f3580b == month.f3580b && this.f3581c == month.f3581c;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3580b), Integer.valueOf(this.f3581c)});
    }

    public final String n() {
        if (this.f3585g == null) {
            this.f3585g = j0.b("yMMMM", Locale.getDefault()).format(new Date(this.f3579a.getTimeInMillis()));
        }
        return this.f3585g;
    }

    public final int o(Month month) {
        if (!(this.f3579a instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (month.f3580b - this.f3580b) + ((month.f3581c - this.f3581c) * 12);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f3581c);
        parcel.writeInt(this.f3580b);
    }
}
