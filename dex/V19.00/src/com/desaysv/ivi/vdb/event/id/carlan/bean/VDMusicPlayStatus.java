package com.desaysv.ivi.vdb.event.id.carlan.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class VDMusicPlayStatus implements Parcelable {
    public static final Parcelable.Creator<VDMusicPlayStatus> CREATOR = new l(12);

    /* renamed from: a, reason: collision with root package name */
    public String f3202a;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f3202a);
    }
}
