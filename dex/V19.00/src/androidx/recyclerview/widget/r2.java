package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r2 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        StaggeredGridLayoutManager.SavedState savedState = new StaggeredGridLayoutManager.SavedState();
        savedState.f2361a = parcel.readInt();
        savedState.f2362b = parcel.readInt();
        int readInt = parcel.readInt();
        savedState.f2363c = readInt;
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            savedState.f2364d = iArr;
            parcel.readIntArray(iArr);
        }
        int readInt2 = parcel.readInt();
        savedState.f2365e = readInt2;
        if (readInt2 > 0) {
            int[] iArr2 = new int[readInt2];
            savedState.f2366f = iArr2;
            parcel.readIntArray(iArr2);
        }
        savedState.f2368h = parcel.readInt() == 1;
        savedState.f2369i = parcel.readInt() == 1;
        savedState.f2370j = parcel.readInt() == 1;
        savedState.f2367g = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        return savedState;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        return new StaggeredGridLayoutManager.SavedState[i7];
    }
}
