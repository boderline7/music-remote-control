package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t2 {

    /* renamed from: a, reason: collision with root package name */
    public int f2667a;

    /* renamed from: b, reason: collision with root package name */
    public int f2668b;

    /* renamed from: c, reason: collision with root package name */
    public int f2669c;

    /* renamed from: d, reason: collision with root package name */
    public int f2670d;

    /* renamed from: e, reason: collision with root package name */
    public int f2671e;

    public final boolean a() {
        int i7 = this.f2667a;
        int i8 = 2;
        if ((i7 & 7) != 0) {
            int i9 = this.f2670d;
            int i10 = this.f2668b;
            if (((i9 > i10 ? 1 : i9 == i10 ? 2 : 4) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & z1.c.INVOKE_DIRECT) != 0) {
            int i11 = this.f2670d;
            int i12 = this.f2669c;
            if ((((i11 > i12 ? 1 : i11 == i12 ? 2 : 4) << 4) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & 1792) != 0) {
            int i13 = this.f2671e;
            int i14 = this.f2668b;
            if ((((i13 > i14 ? 1 : i13 == i14 ? 2 : 4) << 8) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & 28672) != 0) {
            int i15 = this.f2671e;
            int i16 = this.f2669c;
            if (i15 > i16) {
                i8 = 1;
            } else if (i15 != i16) {
                i8 = 4;
            }
            if ((i7 & (i8 << 12)) == 0) {
                return false;
            }
        }
        return true;
    }
}
