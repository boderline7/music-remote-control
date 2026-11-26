package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class g extends Binder implements a {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f593a;

    public g(h hVar) {
        attachInterface(this, a.DESCRIPTOR);
        this.f593a = new WeakReference(hVar);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface(a.DESCRIPTOR);
        }
        if (i7 == 1598968902) {
            parcel2.writeString(a.DESCRIPTOR);
            return true;
        }
        WeakReference weakReference = this.f593a;
        switch (i7) {
            case 1:
                String readString = parcel.readString();
                Bundle bundle = (Bundle) l6.f.d(parcel, Bundle.CREATOR);
                h hVar = (h) weakReference.get();
                if (hVar != null) {
                    hVar.postToHandler(1, readString, bundle);
                }
                return true;
            case 2:
                b();
                return true;
            case 3:
                PlaybackStateCompat playbackStateCompat = (PlaybackStateCompat) l6.f.d(parcel, PlaybackStateCompat.CREATOR);
                h hVar2 = (h) weakReference.get();
                if (hVar2 != null) {
                    hVar2.postToHandler(2, playbackStateCompat, null);
                }
                return true;
            case 4:
                c((MediaMetadataCompat) l6.f.d(parcel, MediaMetadataCompat.CREATOR));
                return true;
            case 5:
                e(parcel.createTypedArrayList(MediaSessionCompat$QueueItem.CREATOR));
                return true;
            case 6:
                a((CharSequence) l6.f.d(parcel, TextUtils.CHAR_SEQUENCE_CREATOR));
                return true;
            case 7:
                d((Bundle) l6.f.d(parcel, Bundle.CREATOR));
                return true;
            case 8:
                g((ParcelableVolumeInfo) l6.f.d(parcel, ParcelableVolumeInfo.CREATOR));
                return true;
            case 9:
                int readInt = parcel.readInt();
                h hVar3 = (h) weakReference.get();
                if (hVar3 != null) {
                    hVar3.postToHandler(9, Integer.valueOf(readInt), null);
                }
                return true;
            case 10:
                parcel.readInt();
                return true;
            case 11:
                boolean z6 = parcel.readInt() != 0;
                h hVar4 = (h) weakReference.get();
                if (hVar4 != null) {
                    hVar4.postToHandler(11, Boolean.valueOf(z6), null);
                }
                return true;
            case 12:
                int readInt2 = parcel.readInt();
                h hVar5 = (h) weakReference.get();
                if (hVar5 != null) {
                    hVar5.postToHandler(12, Integer.valueOf(readInt2), null);
                }
                return true;
            case 13:
                h hVar6 = (h) weakReference.get();
                if (hVar6 != null) {
                    hVar6.postToHandler(13, null, null);
                }
                return true;
            default:
                return super.onTransact(i7, parcel, parcel2, i8);
        }
    }
}
