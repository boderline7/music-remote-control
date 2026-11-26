package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper> CREATOR = new t(0);

    /* renamed from: a, reason: collision with root package name */
    public ResultReceiver f563a;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        this.f563a.writeToParcel(parcel, i7);
    }
}
