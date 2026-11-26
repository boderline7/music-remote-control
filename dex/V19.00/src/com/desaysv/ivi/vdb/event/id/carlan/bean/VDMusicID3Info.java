package com.desaysv.ivi.vdb.event.id.carlan.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class VDMusicID3Info implements Parcelable {
    public static final Parcelable.Creator<VDMusicID3Info> CREATOR = new l(11);

    /* renamed from: a, reason: collision with root package name */
    public int f3193a;

    /* renamed from: b, reason: collision with root package name */
    public int f3194b;

    /* renamed from: c, reason: collision with root package name */
    public String f3195c;

    /* renamed from: d, reason: collision with root package name */
    public String f3196d;

    /* renamed from: e, reason: collision with root package name */
    public String f3197e;

    /* renamed from: f, reason: collision with root package name */
    public String f3198f;

    /* renamed from: g, reason: collision with root package name */
    public String f3199g;

    /* renamed from: h, reason: collision with root package name */
    public String f3200h;

    /* renamed from: i, reason: collision with root package name */
    public String f3201i;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f3193a);
        parcel.writeInt(this.f3194b);
        parcel.writeString(this.f3195c);
        parcel.writeString(this.f3196d);
        parcel.writeString(this.f3197e);
        parcel.writeString(this.f3198f);
        parcel.writeString(this.f3199g);
        parcel.writeString(this.f3200h);
        parcel.writeString(this.f3201i);
    }
}
