package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> CREATOR = new p2();

    /* renamed from: a, reason: collision with root package name */
    public int f2357a;

    /* renamed from: b, reason: collision with root package name */
    public int f2358b;

    /* renamed from: c, reason: collision with root package name */
    public int[] f2359c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2360d;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.f2357a + ", mGapDir=" + this.f2358b + ", mHasUnwantedGapAfter=" + this.f2360d + ", mGapPerSpan=" + Arrays.toString(this.f2359c) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f2357a);
        parcel.writeInt(this.f2358b);
        parcel.writeInt(this.f2360d ? 1 : 0);
        int[] iArr = this.f2359c;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.f2359c);
        }
    }
}
