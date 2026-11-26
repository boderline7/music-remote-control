package com.google.android.material.timepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new g();
    public static final String NUMBER_FORMAT = "%d";
    public static final String ZERO_LEADING_NUMBER_FORMAT = "%02d";

    /* renamed from: a, reason: collision with root package name */
    public final int f4452a;

    /* renamed from: b, reason: collision with root package name */
    public final int f4453b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4454c;

    /* renamed from: d, reason: collision with root package name */
    public final int f4455d;

    public TimeModel(Parcel parcel) {
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        int readInt3 = parcel.readInt();
        int readInt4 = parcel.readInt();
        this.f4453b = readInt;
        this.f4454c = readInt2;
        this.f4455d = readInt3;
        this.f4452a = readInt4;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeModel)) {
            return false;
        }
        TimeModel timeModel = (TimeModel) obj;
        return this.f4453b == timeModel.f4453b && this.f4454c == timeModel.f4454c && this.f4452a == timeModel.f4452a && this.f4455d == timeModel.f4455d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4452a), Integer.valueOf(this.f4453b), Integer.valueOf(this.f4454c), Integer.valueOf(this.f4455d)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f4453b);
        parcel.writeInt(this.f4454c);
        parcel.writeInt(this.f4455d);
        parcel.writeInt(this.f4452a);
    }
}
