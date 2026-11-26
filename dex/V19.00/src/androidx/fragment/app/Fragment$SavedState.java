package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Fragment$SavedState implements Parcelable {
    public static final Parcelable.Creator<Fragment$SavedState> CREATOR = new y();

    /* renamed from: a, reason: collision with root package name */
    public final Bundle f1876a;

    public Fragment$SavedState(Bundle bundle) {
        this.f1876a = bundle;
    }

    public Fragment$SavedState(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle();
        this.f1876a = readBundle;
        if (classLoader == null || readBundle == null) {
            return;
        }
        readBundle.setClassLoader(classLoader);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeBundle(this.f1876a);
    }
}
