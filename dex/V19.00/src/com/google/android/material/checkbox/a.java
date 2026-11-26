package com.google.android.material.checkbox;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.checkbox.MaterialCheckBox;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        MaterialCheckBox.SavedState savedState = new MaterialCheckBox.SavedState(parcel);
        savedState.f3528a = ((Integer) parcel.readValue(MaterialCheckBox.SavedState.class.getClassLoader())).intValue();
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new MaterialCheckBox.SavedState[i7];
    }
}
