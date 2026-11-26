package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* renamed from: a, reason: collision with root package name */
    public final Month f3564a;

    /* renamed from: b, reason: collision with root package name */
    public final Month f3565b;

    /* renamed from: c, reason: collision with root package name */
    public final DateValidator f3566c;

    /* renamed from: d, reason: collision with root package name */
    public final Month f3567d;

    /* renamed from: e, reason: collision with root package name */
    public final int f3568e;

    /* renamed from: f, reason: collision with root package name */
    public final int f3569f;

    /* renamed from: g, reason: collision with root package name */
    public final int f3570g;

    public interface DateValidator extends Parcelable {
        boolean f(long j7);
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i7) {
        Objects.requireNonNull(month, "start cannot be null");
        Objects.requireNonNull(month2, "end cannot be null");
        Objects.requireNonNull(dateValidator, "validator cannot be null");
        this.f3564a = month;
        this.f3565b = month2;
        this.f3567d = month3;
        this.f3568e = i7;
        this.f3566c = dateValidator;
        if (month3 != null && month.f3579a.compareTo(month3.f3579a) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.f3579a.compareTo(month2.f3579a) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i7 < 0 || i7 > j0.g(null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.f3570g = month.o(month2) + 1;
        this.f3569f = (month2.f3581c - month.f3581c) + 1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.f3564a.equals(calendarConstraints.f3564a) && this.f3565b.equals(calendarConstraints.f3565b) && o0.b.a(this.f3567d, calendarConstraints.f3567d) && this.f3568e == calendarConstraints.f3568e && this.f3566c.equals(calendarConstraints.f3566c);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3564a, this.f3565b, this.f3567d, Integer.valueOf(this.f3568e), this.f3566c});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.f3564a, 0);
        parcel.writeParcelable(this.f3565b, 0);
        parcel.writeParcelable(this.f3567d, 0);
        parcel.writeParcelable(this.f3566c, 0);
        parcel.writeInt(this.f3568e);
    }
}
