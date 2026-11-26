package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new b(2);

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f1883a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f1884b;

    /* renamed from: c, reason: collision with root package name */
    public BackStackState[] f1885c;

    /* renamed from: d, reason: collision with root package name */
    public int f1886d;

    /* renamed from: e, reason: collision with root package name */
    public String f1887e;

    /* renamed from: f, reason: collision with root package name */
    public ArrayList f1888f;

    /* renamed from: g, reason: collision with root package name */
    public ArrayList f1889g;

    /* renamed from: h, reason: collision with root package name */
    public ArrayList f1890h;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeTypedList(this.f1883a);
        parcel.writeStringList(this.f1884b);
        parcel.writeTypedArray(this.f1885c, i7);
        parcel.writeInt(this.f1886d);
        parcel.writeString(this.f1887e);
        parcel.writeStringList(this.f1888f);
        parcel.writeTypedList(this.f1889g);
        parcel.writeTypedList(this.f1890h);
    }
}
