package com.google.android.material.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import androidx.customview.view.a;
import q.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ExtendableSavedState extends AbsSavedState {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR = new a(2);

    /* renamed from: b, reason: collision with root package name */
    public final k f4205b;

    public ExtendableSavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        String[] strArr = new String[readInt];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[readInt];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.f4205b = new k(readInt);
        for (int i7 = 0; i7 < readInt; i7++) {
            this.f4205b.put(strArr[i7], bundleArr[i7]);
        }
    }

    public ExtendableSavedState(Parcelable parcelable) {
        super(parcelable);
        this.f4205b = new k();
    }

    public final String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.f4205b + "}";
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.f1829a, i7);
        k kVar = this.f4205b;
        int i8 = kVar.f8412c;
        parcel.writeInt(i8);
        String[] strArr = new String[i8];
        Bundle[] bundleArr = new Bundle[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            strArr[i9] = (String) kVar.h(i9);
            bundleArr[i9] = (Bundle) kVar.j(i9);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }
}
