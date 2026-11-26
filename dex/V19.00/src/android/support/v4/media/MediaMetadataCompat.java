package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.u;
import android.text.TextUtils;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f522c;

    /* renamed from: d, reason: collision with root package name */
    public static final String[] f523d;

    /* renamed from: e, reason: collision with root package name */
    public static final String[] f524e;

    /* renamed from: a, reason: collision with root package name */
    public final Bundle f525a;

    /* renamed from: b, reason: collision with root package name */
    public MediaDescriptionCompat f526b;

    static {
        q.b bVar = new q.b();
        bVar.put(METADATA_KEY_TITLE, 1);
        bVar.put(METADATA_KEY_ARTIST, 1);
        bVar.put(METADATA_KEY_DURATION, 0);
        bVar.put(METADATA_KEY_ALBUM, 1);
        bVar.put(METADATA_KEY_AUTHOR, 1);
        bVar.put(METADATA_KEY_WRITER, 1);
        bVar.put(METADATA_KEY_COMPOSER, 1);
        bVar.put(METADATA_KEY_COMPILATION, 1);
        bVar.put(METADATA_KEY_DATE, 1);
        bVar.put(METADATA_KEY_YEAR, 0);
        bVar.put(METADATA_KEY_GENRE, 1);
        bVar.put(METADATA_KEY_TRACK_NUMBER, 0);
        bVar.put(METADATA_KEY_NUM_TRACKS, 0);
        bVar.put(METADATA_KEY_DISC_NUMBER, 0);
        bVar.put(METADATA_KEY_ALBUM_ARTIST, 1);
        bVar.put(METADATA_KEY_ART, 2);
        bVar.put(METADATA_KEY_ART_URI, 1);
        bVar.put(METADATA_KEY_ALBUM_ART, 2);
        bVar.put(METADATA_KEY_ALBUM_ART_URI, 1);
        bVar.put(METADATA_KEY_USER_RATING, 3);
        bVar.put(METADATA_KEY_RATING, 3);
        bVar.put(METADATA_KEY_DISPLAY_TITLE, 1);
        bVar.put(METADATA_KEY_DISPLAY_SUBTITLE, 1);
        bVar.put(METADATA_KEY_DISPLAY_DESCRIPTION, 1);
        bVar.put(METADATA_KEY_DISPLAY_ICON, 2);
        bVar.put(METADATA_KEY_DISPLAY_ICON_URI, 1);
        bVar.put(METADATA_KEY_MEDIA_ID, 1);
        bVar.put(METADATA_KEY_BT_FOLDER_TYPE, 0);
        bVar.put(METADATA_KEY_MEDIA_URI, 1);
        bVar.put(METADATA_KEY_ADVERTISEMENT, 0);
        bVar.put(METADATA_KEY_DOWNLOAD_STATUS, 0);
        f522c = new String[]{METADATA_KEY_TITLE, METADATA_KEY_ARTIST, METADATA_KEY_ALBUM, METADATA_KEY_ALBUM_ARTIST, METADATA_KEY_WRITER, METADATA_KEY_AUTHOR, METADATA_KEY_COMPOSER};
        f523d = new String[]{METADATA_KEY_DISPLAY_ICON, METADATA_KEY_ART, METADATA_KEY_ALBUM_ART};
        f524e = new String[]{METADATA_KEY_DISPLAY_ICON_URI, METADATA_KEY_ART_URI, METADATA_KEY_ALBUM_ART_URI};
        CREATOR = new l(2);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.f525a = parcel.readBundle(u.class.getClassLoader());
    }

    public static MediaMetadataCompat l(Object obj) {
        if (obj == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        ((MediaMetadata) obj).writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        MediaMetadataCompat createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        createFromParcel.getClass();
        return createFromParcel;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final Bitmap m(String str) {
        try {
            return (Bitmap) this.f525a.getParcelable(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public final MediaDescriptionCompat n() {
        Bitmap bitmap;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.f526b;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String o6 = o(METADATA_KEY_MEDIA_ID);
        CharSequence[] charSequenceArr = new CharSequence[3];
        Bundle bundle = this.f525a;
        CharSequence charSequence = bundle.getCharSequence(METADATA_KEY_DISPLAY_TITLE);
        if (TextUtils.isEmpty(charSequence)) {
            int i7 = 0;
            int i8 = 0;
            while (i7 < 3) {
                String[] strArr = f522c;
                if (i8 >= strArr.length) {
                    break;
                }
                int i9 = i8 + 1;
                CharSequence charSequence2 = bundle.getCharSequence(strArr[i8]);
                if (!TextUtils.isEmpty(charSequence2)) {
                    charSequenceArr[i7] = charSequence2;
                    i7++;
                }
                i8 = i9;
            }
        } else {
            charSequenceArr[0] = charSequence;
            charSequenceArr[1] = bundle.getCharSequence(METADATA_KEY_DISPLAY_SUBTITLE);
            charSequenceArr[2] = bundle.getCharSequence(METADATA_KEY_DISPLAY_DESCRIPTION);
        }
        int i10 = 0;
        while (true) {
            String[] strArr2 = f523d;
            if (i10 >= strArr2.length) {
                bitmap = null;
                break;
            }
            Bitmap m6 = m(strArr2[i10]);
            if (m6 != null) {
                bitmap = m6;
                break;
            }
            i10++;
        }
        int i11 = 0;
        while (true) {
            String[] strArr3 = f524e;
            if (i11 >= strArr3.length) {
                uri = null;
                break;
            }
            String o7 = o(strArr3[i11]);
            if (!TextUtils.isEmpty(o7)) {
                uri = Uri.parse(o7);
                break;
            }
            i11++;
        }
        String o8 = o(METADATA_KEY_MEDIA_URI);
        Uri parse = !TextUtils.isEmpty(o8) ? Uri.parse(o8) : null;
        CharSequence charSequence3 = charSequenceArr[0];
        CharSequence charSequence4 = charSequenceArr[1];
        CharSequence charSequence5 = charSequenceArr[2];
        Bundle bundle2 = new Bundle();
        if (bundle.containsKey(METADATA_KEY_BT_FOLDER_TYPE)) {
            bundle2.putLong(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE, bundle.getLong(METADATA_KEY_BT_FOLDER_TYPE, 0L));
        }
        if (bundle.containsKey(METADATA_KEY_DOWNLOAD_STATUS)) {
            bundle2.putLong(MediaDescriptionCompat.EXTRA_DOWNLOAD_STATUS, bundle.getLong(METADATA_KEY_DOWNLOAD_STATUS, 0L));
        }
        MediaDescriptionCompat mediaDescriptionCompat2 = new MediaDescriptionCompat(o6, charSequence3, charSequence4, charSequence5, bitmap, uri, !bundle2.isEmpty() ? bundle2 : null, parse);
        this.f526b = mediaDescriptionCompat2;
        return mediaDescriptionCompat2;
    }

    public final String o(String str) {
        CharSequence charSequence = this.f525a.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeBundle(this.f525a);
    }
}
