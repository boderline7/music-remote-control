package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new l(1);
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
    public static final long STATUS_DOWNLOADED = 2;
    public static final long STATUS_DOWNLOADING = 1;
    public static final long STATUS_NOT_DOWNLOADED = 0;

    /* renamed from: a, reason: collision with root package name */
    public final String f513a;

    /* renamed from: b, reason: collision with root package name */
    public final CharSequence f514b;

    /* renamed from: c, reason: collision with root package name */
    public final CharSequence f515c;

    /* renamed from: d, reason: collision with root package name */
    public final CharSequence f516d;

    /* renamed from: e, reason: collision with root package name */
    public final Bitmap f517e;

    /* renamed from: f, reason: collision with root package name */
    public final Uri f518f;

    /* renamed from: g, reason: collision with root package name */
    public final Bundle f519g;

    /* renamed from: h, reason: collision with root package name */
    public final Uri f520h;

    /* renamed from: i, reason: collision with root package name */
    public MediaDescription f521i;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f513a = str;
        this.f514b = charSequence;
        this.f515c = charSequence2;
        this.f516d = charSequence3;
        this.f517e = bitmap;
        this.f518f = uri;
        this.f519g = bundle;
        this.f520h = uri2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.support.v4.media.MediaDescriptionCompat l(java.lang.Object r13) {
        /*
            r0 = 0
            if (r13 == 0) goto L61
            android.media.MediaDescription r13 = (android.media.MediaDescription) r13
            java.lang.String r2 = android.support.v4.media.n.g(r13)
            java.lang.CharSequence r3 = android.support.v4.media.n.i(r13)
            java.lang.CharSequence r4 = android.support.v4.media.n.h(r13)
            java.lang.CharSequence r5 = android.support.v4.media.n.c(r13)
            android.graphics.Bitmap r6 = android.support.v4.media.n.e(r13)
            android.net.Uri r7 = android.support.v4.media.n.f(r13)
            android.os.Bundle r1 = android.support.v4.media.n.d(r13)
            if (r1 == 0) goto L2b
            android.support.v4.media.session.u.a(r1)
            r1.isEmpty()     // Catch: android.os.BadParcelableException -> L2a
            goto L2b
        L2a:
            r1 = r0
        L2b:
            java.lang.String r8 = "android.support.v4.media.description.MEDIA_URI"
            if (r1 == 0) goto L36
            android.os.Parcelable r9 = r1.getParcelable(r8)
            android.net.Uri r9 = (android.net.Uri) r9
            goto L37
        L36:
            r9 = r0
        L37:
            if (r9 == 0) goto L50
            java.lang.String r10 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r11 = r1.containsKey(r10)
            if (r11 == 0) goto L4a
            int r11 = r1.size()
            r12 = 2
            if (r11 != r12) goto L4a
            r8 = r0
            goto L51
        L4a:
            r1.remove(r8)
            r1.remove(r10)
        L50:
            r8 = r1
        L51:
            if (r9 == 0) goto L54
            goto L59
        L54:
            android.net.Uri r0 = android.support.v4.media.o.a(r13)
            r9 = r0
        L59:
            android.support.v4.media.MediaDescriptionCompat r0 = new android.support.v4.media.MediaDescriptionCompat
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.f521i = r13
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.l(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f514b) + ", " + ((Object) this.f515c) + ", " + ((Object) this.f516d);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        MediaDescription mediaDescription = this.f521i;
        if (mediaDescription == null) {
            MediaDescription.Builder b7 = n.b();
            n.n(b7, this.f513a);
            n.p(b7, this.f514b);
            n.o(b7, this.f515c);
            n.j(b7, this.f516d);
            n.l(b7, this.f517e);
            n.m(b7, this.f518f);
            n.k(b7, this.f519g);
            o.b(b7, this.f520h);
            mediaDescription = n.a(b7);
            this.f521i = mediaDescription;
        }
        mediaDescription.writeToParcel(parcel, i7);
    }
}
