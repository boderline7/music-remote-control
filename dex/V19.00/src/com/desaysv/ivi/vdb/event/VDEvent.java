package com.desaysv.ivi.vdb.event;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class VDEvent implements Parcelable {
    public static final Parcelable.Creator<VDEvent> CREATOR = new l(10);

    /* renamed from: a, reason: collision with root package name */
    public int f3189a;

    /* renamed from: b, reason: collision with root package name */
    public Bundle f3190b;

    /* renamed from: c, reason: collision with root package name */
    public int f3191c;

    /* renamed from: d, reason: collision with root package name */
    public long f3192d;

    public VDEvent(int i7) {
        this.f3189a = i7;
        this.f3190b = new Bundle();
    }

    public VDEvent(int i7, Bundle bundle) {
        this.f3189a = i7;
        this.f3190b = bundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f3189a);
        parcel.writeBundle(this.f3190b);
        parcel.writeInt(this.f3191c);
        parcel.writeLong(this.f3192d);
    }
}
