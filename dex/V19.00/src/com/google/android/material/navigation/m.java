package com.google.android.material.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.navigation.NavigationView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        NavigationView.SavedState savedState = new NavigationView.SavedState(parcel, null);
        savedState.f4007b = parcel.readBundle(null);
        return savedState;
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        NavigationView.SavedState savedState = new NavigationView.SavedState(parcel, classLoader);
        savedState.f4007b = parcel.readBundle(classLoader);
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new NavigationView.SavedState[i7];
    }
}
