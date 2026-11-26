package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.MediaSession;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new t(2);

    /* renamed from: a, reason: collision with root package name */
    public final Object f564a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public final Object f565b;

    /* renamed from: c, reason: collision with root package name */
    public d f566c;

    /* renamed from: d, reason: collision with root package name */
    public p1.c f567d;

    public MediaSessionCompat$Token(Parcelable parcelable, d dVar) {
        this.f565b = parcelable;
        this.f566c = dVar;
    }

    public static MediaSessionCompat$Token l(MediaSession.Token token, d dVar) {
        if (token != null) {
            return new MediaSessionCompat$Token(token, dVar);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Object obj2 = this.f565b;
        if (obj2 == null) {
            return mediaSessionCompat$Token.f565b == null;
        }
        Object obj3 = mediaSessionCompat$Token.f565b;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        Object obj = this.f565b;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final d m() {
        d dVar;
        synchronized (this.f564a) {
            dVar = this.f566c;
        }
        return dVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable((Parcelable) this.f565b, i7);
    }
}
