package androidx.coordinatorlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new CoordinatorLayout.SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new CoordinatorLayout.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new CoordinatorLayout.SavedState[i7];
    }
}
