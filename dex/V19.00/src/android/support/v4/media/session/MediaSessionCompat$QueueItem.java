package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new t(1);
    public static final int UNKNOWN_ID = -1;

    /* renamed from: a, reason: collision with root package name */
    public final MediaDescriptionCompat f561a;

    /* renamed from: b, reason: collision with root package name */
    public final long f562b;

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.f561a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.f562b = parcel.readLong();
    }

    public MediaSessionCompat$QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j7) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (j7 == -1) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
        this.f561a = mediaDescriptionCompat;
        this.f562b = j7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaSession.QueueItem {Description=" + this.f561a + ", Id=" + this.f562b + " }";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        this.f561a.writeToParcel(parcel, i7);
        parcel.writeLong(this.f562b);
    }
}
