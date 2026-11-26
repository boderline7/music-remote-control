package com.google.android.material.slider;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        BaseSlider$SliderState baseSlider$SliderState = new BaseSlider$SliderState(parcel);
        baseSlider$SliderState.f4142a = parcel.readFloat();
        baseSlider$SliderState.f4143b = parcel.readFloat();
        ArrayList arrayList = new ArrayList();
        baseSlider$SliderState.f4144c = arrayList;
        parcel.readList(arrayList, Float.class.getClassLoader());
        baseSlider$SliderState.f4145d = parcel.readFloat();
        baseSlider$SliderState.f4146e = parcel.createBooleanArray()[0];
        return baseSlider$SliderState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new BaseSlider$SliderState[i7];
    }
}
