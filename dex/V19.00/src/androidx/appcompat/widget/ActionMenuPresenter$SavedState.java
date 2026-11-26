package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class ActionMenuPresenter$SavedState implements Parcelable {
    public static final Parcelable.Creator<ActionMenuPresenter$SavedState> CREATOR = new l();

    /* renamed from: a, reason: collision with root package name */
    public int f980a;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f980a);
    }
}
