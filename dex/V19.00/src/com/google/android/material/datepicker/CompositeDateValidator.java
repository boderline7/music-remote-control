package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {

    /* renamed from: a, reason: collision with root package name */
    public final f f3573a;

    /* renamed from: b, reason: collision with root package name */
    public final List f3574b;

    /* renamed from: c, reason: collision with root package name */
    public static final c f3571c = new c();

    /* renamed from: d, reason: collision with root package name */
    public static final d f3572d = new d();
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new e();

    public CompositeDateValidator(ArrayList arrayList, f fVar) {
        this.f3574b = arrayList;
        this.f3573a = fVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator compositeDateValidator = (CompositeDateValidator) obj;
        return this.f3574b.equals(compositeDateValidator.f3574b) && this.f3573a.getId() == compositeDateValidator.f3573a.getId();
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public final boolean f(long j7) {
        return this.f3573a.a(this.f3574b, j7);
    }

    public final int hashCode() {
        return this.f3574b.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeList(this.f3574b);
        parcel.writeInt(this.f3573a.getId());
    }
}
