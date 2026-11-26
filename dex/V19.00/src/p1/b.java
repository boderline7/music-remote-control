package p1;

import android.os.Parcel;
import android.util.SparseIntArray;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends a {

    /* renamed from: d, reason: collision with root package name */
    public final SparseIntArray f8215d;

    /* renamed from: e, reason: collision with root package name */
    public final Parcel f8216e;

    /* renamed from: f, reason: collision with root package name */
    public final int f8217f;

    /* renamed from: g, reason: collision with root package name */
    public final int f8218g;

    /* renamed from: h, reason: collision with root package name */
    public final String f8219h;

    /* renamed from: i, reason: collision with root package name */
    public int f8220i;

    /* renamed from: j, reason: collision with root package name */
    public int f8221j;

    /* renamed from: k, reason: collision with root package name */
    public int f8222k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), h0.FRAGMENT_ENCODE_SET, new q.b(), new q.b(), new q.b());
    }

    public b(Parcel parcel, int i7, int i8, String str, q.b bVar, q.b bVar2, q.b bVar3) {
        super(bVar, bVar2, bVar3);
        this.f8215d = new SparseIntArray();
        this.f8220i = -1;
        this.f8222k = -1;
        this.f8216e = parcel;
        this.f8217f = i7;
        this.f8218g = i8;
        this.f8221j = i7;
        this.f8219h = str;
    }

    @Override // p1.a
    public final b a() {
        Parcel parcel = this.f8216e;
        int dataPosition = parcel.dataPosition();
        int i7 = this.f8221j;
        if (i7 == this.f8217f) {
            i7 = this.f8218g;
        }
        return new b(parcel, dataPosition, i7, a.a.i(new StringBuilder(), this.f8219h, "  "), this.f8212a, this.f8213b, this.f8214c);
    }

    @Override // p1.a
    public final boolean e(int i7) {
        while (this.f8221j < this.f8218g) {
            int i8 = this.f8222k;
            if (i8 == i7) {
                return true;
            }
            if (String.valueOf(i8).compareTo(String.valueOf(i7)) > 0) {
                return false;
            }
            int i9 = this.f8221j;
            Parcel parcel = this.f8216e;
            parcel.setDataPosition(i9);
            int readInt = parcel.readInt();
            this.f8222k = parcel.readInt();
            this.f8221j += readInt;
        }
        return this.f8222k == i7;
    }

    @Override // p1.a
    public final void i(int i7) {
        int i8 = this.f8220i;
        SparseIntArray sparseIntArray = this.f8215d;
        Parcel parcel = this.f8216e;
        if (i8 >= 0) {
            int i9 = sparseIntArray.get(i8);
            int dataPosition = parcel.dataPosition();
            parcel.setDataPosition(i9);
            parcel.writeInt(dataPosition - i9);
            parcel.setDataPosition(dataPosition);
        }
        this.f8220i = i7;
        sparseIntArray.put(i7, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i7);
    }
}
