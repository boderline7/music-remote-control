package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class AppCompatDelegateImpl$PanelFeatureState$SavedState implements Parcelable {
    public static final Parcelable.Creator<AppCompatDelegateImpl$PanelFeatureState$SavedState> CREATOR = new h0();

    /* renamed from: a, reason: collision with root package name */
    public int f666a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f667b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f668c;

    public static AppCompatDelegateImpl$PanelFeatureState$SavedState l(Parcel parcel, ClassLoader classLoader) {
        AppCompatDelegateImpl$PanelFeatureState$SavedState appCompatDelegateImpl$PanelFeatureState$SavedState = new AppCompatDelegateImpl$PanelFeatureState$SavedState();
        appCompatDelegateImpl$PanelFeatureState$SavedState.f666a = parcel.readInt();
        boolean z6 = parcel.readInt() == 1;
        appCompatDelegateImpl$PanelFeatureState$SavedState.f667b = z6;
        if (z6) {
            appCompatDelegateImpl$PanelFeatureState$SavedState.f668c = parcel.readBundle(classLoader);
        }
        return appCompatDelegateImpl$PanelFeatureState$SavedState;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f666a);
        parcel.writeInt(this.f667b ? 1 : 0);
        if (this.f667b) {
            parcel.writeBundle(this.f668c);
        }
    }
}
