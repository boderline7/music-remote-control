package android.support.v4.media;

import android.content.Intent;
import android.content.IntentSender;
import android.media.MediaDescription;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;
import android.support.v4.os.ResultReceiver;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.versionedparcelable.ParcelImpl;
import cn.kuwo.autosdk.utils.ProcessManager$ProcessInfo;
import cn.kuwo.base.bean.Music;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carlan.bean.VDMusicID3Info;
import com.desaysv.ivi.vdb.event.id.carlan.bean.VDMusicPlayStatus;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.ParcelableSparseBooleanArray;
import com.google.android.material.internal.ParcelableSparseIntArray;
import com.lion.api.bus.sync.SyncBusEvent;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.Locale;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l implements Parcelable.Creator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f549a;

    public /* synthetic */ l(int i7) {
        this.f549a = i7;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(final Parcel parcel) {
        switch (this.f549a) {
            case 0:
                return new Parcelable(parcel) { // from class: android.support.v4.media.MediaBrowserCompat$MediaItem
                    public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = new l(0);
                    public static final int FLAG_BROWSABLE = 1;
                    public static final int FLAG_PLAYABLE = 2;

                    /* renamed from: a, reason: collision with root package name */
                    public final int f511a;

                    /* renamed from: b, reason: collision with root package name */
                    public final MediaDescriptionCompat f512b;

                    {
                        this.f511a = parcel.readInt();
                        this.f512b = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                    }

                    @Override // android.os.Parcelable
                    public final int describeContents() {
                        return 0;
                    }

                    public final String toString() {
                        return "MediaItem{mFlags=" + this.f511a + ", mDescription=" + this.f512b + '}';
                    }

                    @Override // android.os.Parcelable
                    public final void writeToParcel(Parcel parcel2, int i7) {
                        parcel2.writeInt(this.f511a);
                        this.f512b.writeToParcel(parcel2, i7);
                    }
                };
            case 1:
                return MediaDescriptionCompat.l(MediaDescription.CREATOR.createFromParcel(parcel));
            case 2:
                return new MediaMetadataCompat(parcel);
            case 3:
                return new RatingCompat(parcel.readFloat(), parcel.readInt());
            case 4:
                return new ResultReceiver(parcel);
            case 5:
                return new ActivityResult(parcel);
            case 6:
                i5.a.g("inParcel", parcel);
                Parcelable readParcelable = parcel.readParcelable(IntentSender.class.getClassLoader());
                i5.a.d(readParcelable);
                return new IntentSenderRequest((IntentSender) readParcelable, (Intent) parcel.readParcelable(Intent.class.getClassLoader()), parcel.readInt(), parcel.readInt());
            case 7:
                return new ParcelImpl(parcel);
            case 8:
                return new Parcelable(parcel) { // from class: cn.kuwo.autosdk.utils.ProcessManager$ProcessInfo
                    public static final Parcelable.Creator<ProcessManager$ProcessInfo> CREATOR = new l(8);

                    /* renamed from: a, reason: collision with root package name */
                    public final String f3133a;

                    /* renamed from: b, reason: collision with root package name */
                    public final int f3134b;

                    /* renamed from: c, reason: collision with root package name */
                    public final int f3135c;

                    /* renamed from: d, reason: collision with root package name */
                    public final int f3136d;

                    /* renamed from: e, reason: collision with root package name */
                    public final String f3137e;

                    {
                        this.f3133a = parcel.readString();
                        this.f3134b = parcel.readInt();
                        this.f3135c = parcel.readInt();
                        this.f3136d = parcel.readInt();
                        this.f3137e = parcel.readString();
                    }

                    @Override // android.os.Parcelable
                    public final int describeContents() {
                        return 0;
                    }

                    @Override // android.os.Parcelable
                    public final void writeToParcel(Parcel parcel2, int i7) {
                        parcel2.writeString(this.f3133a);
                        parcel2.writeInt(this.f3134b);
                        parcel2.writeInt(this.f3135c);
                        parcel2.writeInt(this.f3136d);
                        parcel2.writeString(this.f3137e);
                    }
                };
            case 9:
                Music music = new Music();
                music.f3164c = h0.FRAGMENT_ENCODE_SET;
                music.f3165d = h0.FRAGMENT_ENCODE_SET;
                music.f3167f = h0.FRAGMENT_ENCODE_SET;
                music.f3169h = h0.FRAGMENT_ENCODE_SET;
                music.f3171j = h0.FRAGMENT_ENCODE_SET;
                music.f3172k = h0.FRAGMENT_ENCODE_SET;
                music.f3179r = h0.FRAGMENT_ENCODE_SET;
                new t1.f();
                music.f3182u = h0.FRAGMENT_ENCODE_SET;
                music.f3183v = h0.FRAGMENT_ENCODE_SET;
                music.f3187z = true;
                music.f3162a = parcel.readLong();
                music.f3163b = parcel.readLong();
                music.f3164c = parcel.readString();
                music.f3165d = parcel.readString();
                music.f3166e = parcel.readLong();
                music.f3167f = parcel.readString();
                music.f3168g = parcel.readInt();
                music.f3169h = parcel.readString();
                music.f3170i = parcel.readByte() != 0;
                music.f3171j = parcel.readString();
                music.f3172k = parcel.readString();
                music.f3173l = parcel.readInt();
                music.f3174m = parcel.readInt();
                music.f3175n = parcel.readByte() != 0;
                music.f3176o = parcel.readByte() != 0;
                music.f3177p = parcel.readString();
                music.f3178q = parcel.readString();
                music.f3179r = parcel.readString();
                music.f3182u = parcel.readString();
                music.f3183v = parcel.readString();
                music.f3184w = parcel.readLong();
                music.f3185x = parcel.readLong();
                music.f3186y = parcel.readByte() != 0;
                music.f3187z = parcel.readByte() != 0;
                music.A = parcel.readInt();
                return music;
            case 10:
                VDEvent vDEvent = new VDEvent();
                vDEvent.f3189a = parcel.readInt();
                try {
                    vDEvent.f3190b = parcel.readBundle(VDEvent.class.getClassLoader());
                } catch (Exception unused) {
                    vDEvent.f3190b = new Bundle();
                }
                vDEvent.f3191c = parcel.readInt();
                vDEvent.f3192d = parcel.readLong();
                return vDEvent;
            case 11:
                VDMusicID3Info vDMusicID3Info = new VDMusicID3Info();
                vDMusicID3Info.f3193a = 0;
                vDMusicID3Info.f3193a = parcel.readInt();
                vDMusicID3Info.f3194b = parcel.readInt();
                vDMusicID3Info.f3195c = parcel.readString();
                vDMusicID3Info.f3196d = parcel.readString();
                vDMusicID3Info.f3197e = parcel.readString();
                vDMusicID3Info.f3198f = parcel.readString();
                vDMusicID3Info.f3199g = parcel.readString();
                vDMusicID3Info.f3200h = parcel.readString();
                vDMusicID3Info.f3201i = parcel.readString();
                return vDMusicID3Info;
            case 12:
                VDMusicPlayStatus vDMusicPlayStatus = new VDMusicPlayStatus();
                vDMusicPlayStatus.f3202a = parcel.readString();
                return vDMusicPlayStatus;
            case 13:
                BadgeState$State badgeState$State = new BadgeState$State();
                badgeState$State.f3289i = 255;
                badgeState$State.f3291k = -2;
                badgeState$State.f3292l = -2;
                badgeState$State.f3293m = -2;
                badgeState$State.f3300t = Boolean.TRUE;
                badgeState$State.f3281a = parcel.readInt();
                badgeState$State.f3282b = (Integer) parcel.readSerializable();
                badgeState$State.f3283c = (Integer) parcel.readSerializable();
                badgeState$State.f3284d = (Integer) parcel.readSerializable();
                badgeState$State.f3285e = (Integer) parcel.readSerializable();
                badgeState$State.f3286f = (Integer) parcel.readSerializable();
                badgeState$State.f3287g = (Integer) parcel.readSerializable();
                badgeState$State.f3288h = (Integer) parcel.readSerializable();
                badgeState$State.f3289i = parcel.readInt();
                badgeState$State.f3290j = parcel.readString();
                badgeState$State.f3291k = parcel.readInt();
                badgeState$State.f3292l = parcel.readInt();
                badgeState$State.f3293m = parcel.readInt();
                badgeState$State.f3295o = parcel.readString();
                badgeState$State.f3296p = parcel.readString();
                badgeState$State.f3297q = parcel.readInt();
                badgeState$State.f3299s = (Integer) parcel.readSerializable();
                badgeState$State.f3301u = (Integer) parcel.readSerializable();
                badgeState$State.f3302v = (Integer) parcel.readSerializable();
                badgeState$State.f3303w = (Integer) parcel.readSerializable();
                badgeState$State.f3304x = (Integer) parcel.readSerializable();
                badgeState$State.f3305y = (Integer) parcel.readSerializable();
                badgeState$State.f3306z = (Integer) parcel.readSerializable();
                badgeState$State.C = (Integer) parcel.readSerializable();
                badgeState$State.A = (Integer) parcel.readSerializable();
                badgeState$State.B = (Integer) parcel.readSerializable();
                badgeState$State.f3300t = (Boolean) parcel.readSerializable();
                badgeState$State.f3294n = (Locale) parcel.readSerializable();
                badgeState$State.D = (Boolean) parcel.readSerializable();
                return badgeState$State;
            case 14:
                int readInt = parcel.readInt();
                ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(readInt);
                int[] iArr = new int[readInt];
                boolean[] zArr = new boolean[readInt];
                parcel.readIntArray(iArr);
                parcel.readBooleanArray(zArr);
                for (int i7 = 0; i7 < readInt; i7++) {
                    parcelableSparseBooleanArray.put(iArr[i7], zArr[i7]);
                }
                return parcelableSparseBooleanArray;
            case 15:
                int readInt2 = parcel.readInt();
                ParcelableSparseIntArray parcelableSparseIntArray = new ParcelableSparseIntArray(readInt2);
                int[] iArr2 = new int[readInt2];
                int[] iArr3 = new int[readInt2];
                parcel.readIntArray(iArr2);
                parcel.readIntArray(iArr3);
                for (int i8 = 0; i8 < readInt2; i8++) {
                    parcelableSparseIntArray.put(iArr2[i8], iArr3[i8]);
                }
                return parcelableSparseIntArray;
            case 16:
                SyncBusEvent syncBusEvent = new SyncBusEvent();
                syncBusEvent.f4483a = 0;
                syncBusEvent.f4484b = 0;
                syncBusEvent.f4484b = parcel.readInt();
                syncBusEvent.f4483a = parcel.readInt();
                syncBusEvent.f4485c = parcel.readBundle(SyncBusEvent.class.getClassLoader());
                return syncBusEvent;
            default:
                return new AspectRatio(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f549a) {
            case 0:
                return new MediaBrowserCompat$MediaItem[i7];
            case 1:
                return new MediaDescriptionCompat[i7];
            case 2:
                return new MediaMetadataCompat[i7];
            case 3:
                return new RatingCompat[i7];
            case 4:
                return new ResultReceiver[i7];
            case 5:
                return new ActivityResult[i7];
            case 6:
                return new IntentSenderRequest[i7];
            case 7:
                return new ParcelImpl[i7];
            case 8:
                return new ProcessManager$ProcessInfo[i7];
            case 9:
                return new Music[i7];
            case 10:
                return new VDEvent[i7];
            case 11:
                return new VDMusicID3Info[i7];
            case 12:
                return new VDMusicPlayStatus[i7];
            case 13:
                return new BadgeState$State[i7];
            case 14:
                return new ParcelableSparseBooleanArray[i7];
            case 15:
                return new ParcelableSparseIntArray[i7];
            case 16:
                return new SyncBusEvent[i7];
            default:
                return new AspectRatio[i7];
        }
    }
}
