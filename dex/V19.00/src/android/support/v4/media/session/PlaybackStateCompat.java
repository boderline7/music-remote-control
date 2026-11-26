package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_FROM_URI = 8192;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_PREPARE = 16384;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768;
    public static final long ACTION_PREPARE_FROM_SEARCH = 65536;
    public static final long ACTION_PREPARE_FROM_URI = 131072;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_CAPTIONING_ENABLED = 1048576;
    public static final long ACTION_SET_PLAYBACK_SPEED = 4194304;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SET_REPEAT_MODE = 262144;
    public static final long ACTION_SET_SHUFFLE_MODE = 2097152;

    @Deprecated
    public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 524288;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new t(4);
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_INVALID = -1;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_INVALID = -1;
    public static final int SHUFFLE_MODE_NONE = 0;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;

    /* renamed from: a, reason: collision with root package name */
    public final int f573a;

    /* renamed from: b, reason: collision with root package name */
    public final long f574b;

    /* renamed from: c, reason: collision with root package name */
    public final long f575c;

    /* renamed from: d, reason: collision with root package name */
    public final float f576d;

    /* renamed from: e, reason: collision with root package name */
    public final long f577e;

    /* renamed from: f, reason: collision with root package name */
    public final int f578f;

    /* renamed from: g, reason: collision with root package name */
    public final CharSequence f579g;

    /* renamed from: h, reason: collision with root package name */
    public final long f580h;

    /* renamed from: i, reason: collision with root package name */
    public final ArrayList f581i;

    /* renamed from: j, reason: collision with root package name */
    public final long f582j;

    /* renamed from: k, reason: collision with root package name */
    public final Bundle f583k;

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new x();

        /* renamed from: a, reason: collision with root package name */
        public final String f584a;

        /* renamed from: b, reason: collision with root package name */
        public final CharSequence f585b;

        /* renamed from: c, reason: collision with root package name */
        public final int f586c;

        /* renamed from: d, reason: collision with root package name */
        public final Bundle f587d;

        public CustomAction(Parcel parcel) {
            this.f584a = parcel.readString();
            this.f585b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f586c = parcel.readInt();
            this.f587d = parcel.readBundle(u.class.getClassLoader());
        }

        public CustomAction(String str, CharSequence charSequence, int i7, Bundle bundle) {
            this.f584a = str;
            this.f585b = charSequence;
            this.f586c = i7;
            this.f587d = bundle;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.f585b) + ", mIcon=" + this.f586c + ", mExtras=" + this.f587d;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeString(this.f584a);
            TextUtils.writeToParcel(this.f585b, parcel, i7);
            parcel.writeInt(this.f586c);
            parcel.writeBundle(this.f587d);
        }
    }

    public PlaybackStateCompat(int i7, long j7, long j8, float f7, long j9, CharSequence charSequence, long j10, ArrayList arrayList, long j11, Bundle bundle) {
        this.f573a = i7;
        this.f574b = j7;
        this.f575c = j8;
        this.f576d = f7;
        this.f577e = j9;
        this.f578f = 0;
        this.f579g = charSequence;
        this.f580h = j10;
        this.f581i = new ArrayList(arrayList);
        this.f582j = j11;
        this.f583k = bundle;
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f573a = parcel.readInt();
        this.f574b = parcel.readLong();
        this.f576d = parcel.readFloat();
        this.f580h = parcel.readLong();
        this.f575c = parcel.readLong();
        this.f577e = parcel.readLong();
        this.f579g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f581i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f582j = parcel.readLong();
        this.f583k = parcel.readBundle(u.class.getClassLoader());
        this.f578f = parcel.readInt();
    }

    public static PlaybackStateCompat l(Object obj) {
        ArrayList arrayList;
        CustomAction customAction;
        if (obj == null) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> j7 = v.j(playbackState);
        if (j7 != null) {
            ArrayList arrayList2 = new ArrayList(j7.size());
            for (PlaybackState.CustomAction customAction2 : j7) {
                if (customAction2 != null) {
                    PlaybackState.CustomAction customAction3 = customAction2;
                    Bundle l7 = v.l(customAction3);
                    u.a(l7);
                    customAction = new CustomAction(v.f(customAction3), v.o(customAction3), v.m(customAction3), l7);
                } else {
                    customAction = null;
                }
                arrayList2.add(customAction);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Bundle a7 = w.a(playbackState);
        u.a(a7);
        return new PlaybackStateCompat(v.r(playbackState), v.q(playbackState), v.i(playbackState), v.p(playbackState), v.g(playbackState), v.k(playbackState), v.n(playbackState), arrayList, v.h(playbackState), a7);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "PlaybackState {state=" + this.f573a + ", position=" + this.f574b + ", buffered position=" + this.f575c + ", speed=" + this.f576d + ", updated=" + this.f580h + ", actions=" + this.f577e + ", error code=" + this.f578f + ", error message=" + this.f579g + ", custom actions=" + this.f581i + ", active item id=" + this.f582j + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f573a);
        parcel.writeLong(this.f574b);
        parcel.writeFloat(this.f576d);
        parcel.writeLong(this.f580h);
        parcel.writeLong(this.f575c);
        parcel.writeLong(this.f577e);
        TextUtils.writeToParcel(this.f579g, parcel, i7);
        parcel.writeTypedList(this.f581i);
        parcel.writeLong(this.f582j);
        parcel.writeBundle(this.f583k);
        parcel.writeInt(this.f578f);
    }
}
