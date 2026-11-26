package android.support.v4.media;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.yalantis.ucrop.view.CropImageView;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new l(3);
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;

    /* renamed from: a, reason: collision with root package name */
    public final int f527a;

    /* renamed from: b, reason: collision with root package name */
    public final float f528b;

    public RatingCompat(float f7, int i7) {
        this.f527a = i7;
        this.f528b = f7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.f527a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.f527a);
        sb.append(" rating=");
        float f7 = this.f528b;
        sb.append(f7 < CropImageView.DEFAULT_ASPECT_RATIO ? "unrated" : String.valueOf(f7));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f527a);
        parcel.writeFloat(this.f528b);
    }
}
