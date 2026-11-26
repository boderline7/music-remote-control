package com.lion.api.bus.sync;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SyncBusEvent implements Parcelable {
    public static final Parcelable.Creator<SyncBusEvent> CREATOR = new l(16);

    /* renamed from: a, reason: collision with root package name */
    public int f4483a = 6;

    /* renamed from: b, reason: collision with root package name */
    public int f4484b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f4485c;

    public SyncBusEvent(int i7) {
        this.f4484b = i7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "SyncBusEvent{mModuleType=" + this.f4483a + ", mEventName=" + this.f4484b + ", mBundle=" + this.f4485c + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f4484b);
        parcel.writeInt(this.f4483a);
        parcel.writeBundle(this.f4485c);
    }
}
