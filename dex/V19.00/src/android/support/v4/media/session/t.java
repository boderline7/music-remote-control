package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t implements Parcelable.Creator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f602a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f602a) {
            case 0:
                MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper = new MediaSessionCompat$ResultReceiverWrapper();
                mediaSessionCompat$ResultReceiverWrapper.f563a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
                return mediaSessionCompat$ResultReceiverWrapper;
            case 1:
                return new MediaSessionCompat$QueueItem(parcel);
            case 2:
                return new MediaSessionCompat$Token(parcel.readParcelable(null), null);
            case 3:
                ParcelableVolumeInfo parcelableVolumeInfo = new ParcelableVolumeInfo();
                parcelableVolumeInfo.f568a = parcel.readInt();
                parcelableVolumeInfo.f570c = parcel.readInt();
                parcelableVolumeInfo.f571d = parcel.readInt();
                parcelableVolumeInfo.f572e = parcel.readInt();
                parcelableVolumeInfo.f569b = parcel.readInt();
                return parcelableVolumeInfo;
            default:
                return new PlaybackStateCompat(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f602a) {
            case 0:
                return new MediaSessionCompat$ResultReceiverWrapper[i7];
            case 1:
                return new MediaSessionCompat$QueueItem[i7];
            case 2:
                return new MediaSessionCompat$Token[i7];
            case 3:
                return new ParcelableVolumeInfo[i7];
            default:
                return new PlaybackStateCompat[i7];
        }
    }
}
