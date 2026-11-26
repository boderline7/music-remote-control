package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AspectRatio implements Parcelable {
    public static final Parcelable.Creator<AspectRatio> CREATOR = new l(17);

    /* renamed from: a, reason: collision with root package name */
    public final String f6166a;

    /* renamed from: b, reason: collision with root package name */
    public final float f6167b;

    /* renamed from: c, reason: collision with root package name */
    public final float f6168c;

    public AspectRatio(Parcel parcel) {
        this.f6166a = parcel.readString();
        this.f6167b = parcel.readFloat();
        this.f6168c = parcel.readFloat();
    }

    public AspectRatio(String str, float f7, float f8) {
        this.f6166a = str;
        this.f6167b = f7;
        this.f6168c = f8;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f6166a);
        parcel.writeFloat(this.f6167b);
        parcel.writeFloat(this.f6168c);
    }
}
