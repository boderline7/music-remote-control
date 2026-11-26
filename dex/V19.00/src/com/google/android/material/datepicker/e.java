package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        ArrayList readArrayList = parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
        int readInt = parcel.readInt();
        f fVar = CompositeDateValidator.f3572d;
        if (readInt != 2 && readInt == 1) {
            fVar = CompositeDateValidator.f3571c;
        }
        readArrayList.getClass();
        return new CompositeDateValidator(readArrayList, fVar);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new CompositeDateValidator[i7];
    }
}
