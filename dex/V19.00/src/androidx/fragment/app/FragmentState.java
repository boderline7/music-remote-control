package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new b(3);

    /* renamed from: a, reason: collision with root package name */
    public final String f1891a;

    /* renamed from: b, reason: collision with root package name */
    public final String f1892b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f1893c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1894d;

    /* renamed from: e, reason: collision with root package name */
    public final int f1895e;

    /* renamed from: f, reason: collision with root package name */
    public final String f1896f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f1897g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f1898h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f1899i;

    /* renamed from: j, reason: collision with root package name */
    public final Bundle f1900j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f1901k;

    /* renamed from: l, reason: collision with root package name */
    public final int f1902l;

    /* renamed from: m, reason: collision with root package name */
    public Bundle f1903m;

    public FragmentState(Parcel parcel) {
        this.f1891a = parcel.readString();
        this.f1892b = parcel.readString();
        this.f1893c = parcel.readInt() != 0;
        this.f1894d = parcel.readInt();
        this.f1895e = parcel.readInt();
        this.f1896f = parcel.readString();
        this.f1897g = parcel.readInt() != 0;
        this.f1898h = parcel.readInt() != 0;
        this.f1899i = parcel.readInt() != 0;
        this.f1900j = parcel.readBundle();
        this.f1901k = parcel.readInt() != 0;
        this.f1903m = parcel.readBundle();
        this.f1902l = parcel.readInt();
    }

    public FragmentState(z zVar) {
        this.f1891a = zVar.getClass().getName();
        this.f1892b = zVar.mWho;
        this.f1893c = zVar.mFromLayout;
        this.f1894d = zVar.mFragmentId;
        this.f1895e = zVar.mContainerId;
        this.f1896f = zVar.mTag;
        this.f1897g = zVar.mRetainInstance;
        this.f1898h = zVar.mRemoving;
        this.f1899i = zVar.mDetached;
        this.f1900j = zVar.mArguments;
        this.f1901k = zVar.mHidden;
        this.f1902l = zVar.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1891a);
        sb.append(" (");
        sb.append(this.f1892b);
        sb.append(")}:");
        if (this.f1893c) {
            sb.append(" fromLayout");
        }
        int i7 = this.f1895e;
        if (i7 != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i7));
        }
        String str = this.f1896f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(str);
        }
        if (this.f1897g) {
            sb.append(" retainInstance");
        }
        if (this.f1898h) {
            sb.append(" removing");
        }
        if (this.f1899i) {
            sb.append(" detached");
        }
        if (this.f1901k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f1891a);
        parcel.writeString(this.f1892b);
        parcel.writeInt(this.f1893c ? 1 : 0);
        parcel.writeInt(this.f1894d);
        parcel.writeInt(this.f1895e);
        parcel.writeString(this.f1896f);
        parcel.writeInt(this.f1897g ? 1 : 0);
        parcel.writeInt(this.f1898h ? 1 : 0);
        parcel.writeInt(this.f1899i ? 1 : 0);
        parcel.writeBundle(this.f1900j);
        parcel.writeInt(this.f1901k ? 1 : 0);
        parcel.writeBundle(this.f1903m);
        parcel.writeInt(this.f1902l);
    }
}
