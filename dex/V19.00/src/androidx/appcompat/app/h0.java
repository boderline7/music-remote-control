package androidx.appcompat.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h0 implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return AppCompatDelegateImpl$PanelFeatureState$SavedState.l(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return AppCompatDelegateImpl$PanelFeatureState$SavedState.l(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new AppCompatDelegateImpl$PanelFeatureState$SavedState[i7];
    }
}
