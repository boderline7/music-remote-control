package com.google.android.material.slider;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class BaseSlider$SliderState extends View.BaseSavedState {
    public static final Parcelable.Creator<BaseSlider$SliderState> CREATOR = new b();

    /* renamed from: a, reason: collision with root package name */
    public float f4142a;

    /* renamed from: b, reason: collision with root package name */
    public float f4143b;

    /* renamed from: c, reason: collision with root package name */
    public ArrayList f4144c;

    /* renamed from: d, reason: collision with root package name */
    public float f4145d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f4146e;

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        super.writeToParcel(parcel, i7);
        parcel.writeFloat(this.f4142a);
        parcel.writeFloat(this.f4143b);
        parcel.writeList(this.f4144c);
        parcel.writeFloat(this.f4145d);
        parcel.writeBooleanArray(new boolean[]{this.f4146e});
    }
}
