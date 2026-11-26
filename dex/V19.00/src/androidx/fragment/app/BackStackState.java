package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new b(0);

    /* renamed from: a, reason: collision with root package name */
    public final int[] f1862a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f1863b;

    /* renamed from: c, reason: collision with root package name */
    public final int[] f1864c;

    /* renamed from: d, reason: collision with root package name */
    public final int[] f1865d;

    /* renamed from: e, reason: collision with root package name */
    public final int f1866e;

    /* renamed from: f, reason: collision with root package name */
    public final String f1867f;

    /* renamed from: g, reason: collision with root package name */
    public final int f1868g;

    /* renamed from: h, reason: collision with root package name */
    public final int f1869h;

    /* renamed from: i, reason: collision with root package name */
    public final CharSequence f1870i;

    /* renamed from: j, reason: collision with root package name */
    public final int f1871j;

    /* renamed from: k, reason: collision with root package name */
    public final CharSequence f1872k;

    /* renamed from: l, reason: collision with root package name */
    public final ArrayList f1873l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f1874m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f1875n;

    public BackStackState(Parcel parcel) {
        this.f1862a = parcel.createIntArray();
        this.f1863b = parcel.createStringArrayList();
        this.f1864c = parcel.createIntArray();
        this.f1865d = parcel.createIntArray();
        this.f1866e = parcel.readInt();
        this.f1867f = parcel.readString();
        this.f1868g = parcel.readInt();
        this.f1869h = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f1870i = (CharSequence) creator.createFromParcel(parcel);
        this.f1871j = parcel.readInt();
        this.f1872k = (CharSequence) creator.createFromParcel(parcel);
        this.f1873l = parcel.createStringArrayList();
        this.f1874m = parcel.createStringArrayList();
        this.f1875n = parcel.readInt() != 0;
    }

    public BackStackState(a aVar) {
        int size = aVar.f1974a.size();
        this.f1862a = new int[size * 5];
        if (!aVar.f1980g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1863b = new ArrayList(size);
        this.f1864c = new int[size];
        this.f1865d = new int[size];
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            g1 g1Var = (g1) aVar.f1974a.get(i8);
            int i9 = i7 + 1;
            this.f1862a[i7] = g1Var.f1958a;
            ArrayList arrayList = this.f1863b;
            z zVar = g1Var.f1959b;
            arrayList.add(zVar != null ? zVar.mWho : null);
            int[] iArr = this.f1862a;
            iArr[i9] = g1Var.f1960c;
            iArr[i7 + 2] = g1Var.f1961d;
            int i10 = i7 + 4;
            iArr[i7 + 3] = g1Var.f1962e;
            i7 += 5;
            iArr[i10] = g1Var.f1963f;
            this.f1864c[i8] = g1Var.f1964g.ordinal();
            this.f1865d[i8] = g1Var.f1965h.ordinal();
        }
        this.f1866e = aVar.f1979f;
        this.f1867f = aVar.f1982i;
        this.f1868g = aVar.f1912s;
        this.f1869h = aVar.f1983j;
        this.f1870i = aVar.f1984k;
        this.f1871j = aVar.f1985l;
        this.f1872k = aVar.f1986m;
        this.f1873l = aVar.f1987n;
        this.f1874m = aVar.f1988o;
        this.f1875n = aVar.f1989p;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeIntArray(this.f1862a);
        parcel.writeStringList(this.f1863b);
        parcel.writeIntArray(this.f1864c);
        parcel.writeIntArray(this.f1865d);
        parcel.writeInt(this.f1866e);
        parcel.writeString(this.f1867f);
        parcel.writeInt(this.f1868g);
        parcel.writeInt(this.f1869h);
        TextUtils.writeToParcel(this.f1870i, parcel, 0);
        parcel.writeInt(this.f1871j);
        TextUtils.writeToParcel(this.f1872k, parcel, 0);
        parcel.writeStringList(this.f1873l);
        parcel.writeStringList(this.f1874m);
        parcel.writeInt(this.f1875n ? 1 : 0);
    }
}
