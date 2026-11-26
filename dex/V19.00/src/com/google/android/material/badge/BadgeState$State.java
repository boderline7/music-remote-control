package com.google.android.material.badge;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class BadgeState$State implements Parcelable {
    public static final Parcelable.Creator<BadgeState$State> CREATOR = new l(13);
    public Integer A;
    public Integer B;
    public Integer C;
    public Boolean D;

    /* renamed from: a, reason: collision with root package name */
    public int f3281a;

    /* renamed from: b, reason: collision with root package name */
    public Integer f3282b;

    /* renamed from: c, reason: collision with root package name */
    public Integer f3283c;

    /* renamed from: d, reason: collision with root package name */
    public Integer f3284d;

    /* renamed from: e, reason: collision with root package name */
    public Integer f3285e;

    /* renamed from: f, reason: collision with root package name */
    public Integer f3286f;

    /* renamed from: g, reason: collision with root package name */
    public Integer f3287g;

    /* renamed from: h, reason: collision with root package name */
    public Integer f3288h;

    /* renamed from: j, reason: collision with root package name */
    public String f3290j;

    /* renamed from: n, reason: collision with root package name */
    public Locale f3294n;

    /* renamed from: o, reason: collision with root package name */
    public CharSequence f3295o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f3296p;

    /* renamed from: q, reason: collision with root package name */
    public int f3297q;

    /* renamed from: r, reason: collision with root package name */
    public int f3298r;

    /* renamed from: s, reason: collision with root package name */
    public Integer f3299s;

    /* renamed from: u, reason: collision with root package name */
    public Integer f3301u;

    /* renamed from: v, reason: collision with root package name */
    public Integer f3302v;

    /* renamed from: w, reason: collision with root package name */
    public Integer f3303w;

    /* renamed from: x, reason: collision with root package name */
    public Integer f3304x;

    /* renamed from: y, reason: collision with root package name */
    public Integer f3305y;

    /* renamed from: z, reason: collision with root package name */
    public Integer f3306z;

    /* renamed from: i, reason: collision with root package name */
    public int f3289i = 255;

    /* renamed from: k, reason: collision with root package name */
    public int f3291k = -2;

    /* renamed from: l, reason: collision with root package name */
    public int f3292l = -2;

    /* renamed from: m, reason: collision with root package name */
    public int f3293m = -2;

    /* renamed from: t, reason: collision with root package name */
    public Boolean f3300t = Boolean.TRUE;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f3281a);
        parcel.writeSerializable(this.f3282b);
        parcel.writeSerializable(this.f3283c);
        parcel.writeSerializable(this.f3284d);
        parcel.writeSerializable(this.f3285e);
        parcel.writeSerializable(this.f3286f);
        parcel.writeSerializable(this.f3287g);
        parcel.writeSerializable(this.f3288h);
        parcel.writeInt(this.f3289i);
        parcel.writeString(this.f3290j);
        parcel.writeInt(this.f3291k);
        parcel.writeInt(this.f3292l);
        parcel.writeInt(this.f3293m);
        CharSequence charSequence = this.f3295o;
        parcel.writeString(charSequence != null ? charSequence.toString() : null);
        CharSequence charSequence2 = this.f3296p;
        parcel.writeString(charSequence2 != null ? charSequence2.toString() : null);
        parcel.writeInt(this.f3297q);
        parcel.writeSerializable(this.f3299s);
        parcel.writeSerializable(this.f3301u);
        parcel.writeSerializable(this.f3302v);
        parcel.writeSerializable(this.f3303w);
        parcel.writeSerializable(this.f3304x);
        parcel.writeSerializable(this.f3305y);
        parcel.writeSerializable(this.f3306z);
        parcel.writeSerializable(this.C);
        parcel.writeSerializable(this.A);
        parcel.writeSerializable(this.B);
        parcel.writeSerializable(this.f3300t);
        parcel.writeSerializable(this.f3294n);
        parcel.writeSerializable(this.D);
    }
}
