package com.google.android.material.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.search.SearchBar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        SearchBar.SavedState savedState = new SearchBar.SavedState(parcel, null);
        savedState.f4052b = parcel.readString();
        return savedState;
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        SearchBar.SavedState savedState = new SearchBar.SavedState(parcel, classLoader);
        savedState.f4052b = parcel.readString();
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new SearchBar.SavedState[i7];
    }
}
