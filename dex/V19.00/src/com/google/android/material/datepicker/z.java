package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z implements Parcelable.Creator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3706a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f3706a) {
            case 0:
                return Month.l(parcel.readInt(), parcel.readInt());
            case 1:
                return new DateValidatorPointBackward(parcel.readLong());
            case 2:
                return new DateValidatorPointForward(parcel.readLong());
            case 3:
                RangeDateSelector rangeDateSelector = new RangeDateSelector();
                rangeDateSelector.f3587b = null;
                rangeDateSelector.f3588c = null;
                rangeDateSelector.f3589d = null;
                rangeDateSelector.f3590e = null;
                rangeDateSelector.f3587b = (Long) parcel.readValue(Long.class.getClassLoader());
                rangeDateSelector.f3588c = (Long) parcel.readValue(Long.class.getClassLoader());
                return rangeDateSelector;
            default:
                SingleDateSelector singleDateSelector = new SingleDateSelector();
                singleDateSelector.f3591a = (Long) parcel.readValue(Long.class.getClassLoader());
                return singleDateSelector;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f3706a) {
            case 0:
                return new Month[i7];
            case 1:
                return new DateValidatorPointBackward[i7];
            case 2:
                return new DateValidatorPointForward[i7];
            case 3:
                return new RangeDateSelector[i7];
            default:
                return new SingleDateSelector[i7];
        }
    }
}
