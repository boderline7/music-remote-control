package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = new android.support.v4.media.l(14);

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        int[] iArr = new int[size()];
        boolean[] zArr = new boolean[size()];
        for (int i8 = 0; i8 < size(); i8++) {
            iArr[i8] = keyAt(i8);
            zArr[i8] = valueAt(i8);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeBooleanArray(zArr);
    }
}
