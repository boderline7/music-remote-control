package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class FragmentManager$LaunchedFragmentInfo implements Parcelable {
    public static final Parcelable.Creator<FragmentManager$LaunchedFragmentInfo> CREATOR = new b(1);

    /* renamed from: a, reason: collision with root package name */
    public String f1881a;

    /* renamed from: b, reason: collision with root package name */
    public int f1882b;

    public FragmentManager$LaunchedFragmentInfo(String str, int i7) {
        this.f1881a = str;
        this.f1882b = i7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f1881a);
        parcel.writeInt(this.f1882b);
    }
}
