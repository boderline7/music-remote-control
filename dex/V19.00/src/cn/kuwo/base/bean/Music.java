package cn.kuwo.base.bean;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import s5.h0;
import t1.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class Music implements Parcelable, Serializable, Cloneable {
    public static final Parcelable.Creator<Music> CREATOR;
    public int A;

    /* renamed from: a, reason: collision with root package name */
    public long f3162a;

    /* renamed from: b, reason: collision with root package name */
    public long f3163b;

    /* renamed from: e, reason: collision with root package name */
    public long f3166e;

    /* renamed from: g, reason: collision with root package name */
    public int f3168g;

    /* renamed from: i, reason: collision with root package name */
    public boolean f3170i;

    /* renamed from: l, reason: collision with root package name */
    public int f3173l;

    /* renamed from: m, reason: collision with root package name */
    public int f3174m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3175n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3176o;

    /* renamed from: p, reason: collision with root package name */
    public String f3177p;

    /* renamed from: q, reason: collision with root package name */
    public String f3178q;

    /* renamed from: s, reason: collision with root package name */
    public String f3180s;

    /* renamed from: t, reason: collision with root package name */
    public ArrayList f3181t;

    /* renamed from: u, reason: collision with root package name */
    public String f3182u;

    /* renamed from: v, reason: collision with root package name */
    public String f3183v;

    /* renamed from: w, reason: collision with root package name */
    public long f3184w;

    /* renamed from: x, reason: collision with root package name */
    public long f3185x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f3186y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f3187z;

    /* renamed from: c, reason: collision with root package name */
    public String f3164c = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: d, reason: collision with root package name */
    public String f3165d = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: f, reason: collision with root package name */
    public String f3167f = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: h, reason: collision with root package name */
    public String f3169h = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: j, reason: collision with root package name */
    public String f3171j = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: k, reason: collision with root package name */
    public String f3172k = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: r, reason: collision with root package name */
    public String f3179r = h0.FRAGMENT_ENCODE_SET;

    static {
        Collator.getInstance(Locale.CHINA);
        CREATOR = new l(9);
    }

    public Music() {
        new f();
        this.f3182u = h0.FRAGMENT_ENCODE_SET;
        this.f3183v = h0.FRAGMENT_ENCODE_SET;
        this.f3187z = true;
    }

    public final Object clone() {
        try {
            Music music = (Music) super.clone();
            if (this.f3181t != null) {
                music.f3181t = new ArrayList();
                Iterator it = this.f3181t.iterator();
                if (it.hasNext()) {
                    a.u(it.next());
                    throw null;
                }
            }
            return music;
        } catch (CloneNotSupportedException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Music)) {
            return false;
        }
        Music music = (Music) obj;
        long j7 = music.f3163b;
        if (j7 <= 0) {
            String str = music.f3182u;
            String str2 = this.f3182u;
            if (str2 != null && str != null) {
                return str2.equals(str);
            }
            if (str2 != null || str != null) {
                return false;
            }
        } else if (j7 != this.f3163b) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        long j7 = this.f3163b;
        if (j7 > 0) {
            return (int) j7;
        }
        String str = this.f3182u;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.f3162a);
        parcel.writeLong(this.f3163b);
        parcel.writeString(this.f3164c);
        parcel.writeString(this.f3165d);
        parcel.writeLong(this.f3166e);
        parcel.writeString(this.f3167f);
        parcel.writeInt(this.f3168g);
        parcel.writeString(this.f3169h);
        parcel.writeByte(this.f3170i ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3171j);
        parcel.writeString(this.f3172k);
        parcel.writeInt(this.f3173l);
        parcel.writeInt(this.f3174m);
        parcel.writeByte(this.f3175n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3176o ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3177p);
        parcel.writeString(this.f3178q);
        parcel.writeString(this.f3179r);
        parcel.writeString(this.f3182u);
        parcel.writeString(this.f3183v);
        parcel.writeLong(this.f3184w);
        parcel.writeLong(this.f3185x);
        parcel.writeByte(this.f3186y ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3187z ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.A);
    }
}
