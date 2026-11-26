package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new t(3);

    /* renamed from: a, reason: collision with root package name */
    public int f568a;

    /* renamed from: b, reason: collision with root package name */
    public int f569b;

    /* renamed from: c, reason: collision with root package name */
    public int f570c;

    /* renamed from: d, reason: collision with root package name */
    public int f571d;

    /* renamed from: e, reason: collision with root package name */
    public int f572e;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f568a);
        parcel.writeInt(this.f570c);
        parcel.writeInt(this.f571d);
        parcel.writeInt(this.f572e);
        parcel.writeInt(this.f569b);
    }
}
