package androidx.swiperefreshlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new SwipeRefreshLayout.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new SwipeRefreshLayout.SavedState[i7];
    }
}
