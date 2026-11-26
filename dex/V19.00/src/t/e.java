package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends f {

    /* renamed from: d, reason: collision with root package name */
    public final double f8953d;

    /* renamed from: e, reason: collision with root package name */
    public final double f8954e;

    /* renamed from: f, reason: collision with root package name */
    public final double f8955f;

    /* renamed from: g, reason: collision with root package name */
    public final double f8956g;

    public e(String str) {
        this.f8959a = str;
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(44, indexOf);
        this.f8953d = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
        int i7 = indexOf2 + 1;
        int indexOf3 = str.indexOf(44, i7);
        this.f8954e = Double.parseDouble(str.substring(i7, indexOf3).trim());
        int i8 = indexOf3 + 1;
        int indexOf4 = str.indexOf(44, i8);
        this.f8955f = Double.parseDouble(str.substring(i8, indexOf4).trim());
        int i9 = indexOf4 + 1;
        this.f8956g = Double.parseDouble(str.substring(i9, str.indexOf(41, i9)).trim());
    }

    @Override // t.f
    public final double a(double d7) {
        if (d7 <= 0.0d) {
            return 0.0d;
        }
        if (d7 >= 1.0d) {
            return 1.0d;
        }
        double d8 = 0.5d;
        double d9 = 0.5d;
        while (d8 > 0.01d) {
            d8 *= 0.5d;
            d9 = d(d9) < d7 ? d9 + d8 : d9 - d8;
        }
        double d10 = d9 - d8;
        double d11 = d(d10);
        double d12 = d9 + d8;
        double d13 = d(d12);
        double e7 = e(d10);
        return (((d7 - d11) * (e(d12) - e7)) / (d13 - d11)) + e7;
    }

    @Override // t.f
    public final double b(double d7) {
        double d8 = 0.5d;
        double d9 = 0.5d;
        while (d8 > 1.0E-4d) {
            d8 *= 0.5d;
            d9 = d(d9) < d7 ? d9 + d8 : d9 - d8;
        }
        double d10 = d9 - d8;
        double d11 = d9 + d8;
        return (e(d11) - e(d10)) / (d(d11) - d(d10));
    }

    public final double d(double d7) {
        double d8 = 1.0d - d7;
        double d9 = 3.0d * d8;
        double d10 = d8 * d9 * d7;
        double d11 = d9 * d7 * d7;
        return (this.f8955f * d11) + (this.f8953d * d10) + (d7 * d7 * d7);
    }

    public final double e(double d7) {
        double d8 = 1.0d - d7;
        double d9 = 3.0d * d8;
        double d10 = d8 * d9 * d7;
        double d11 = d9 * d7 * d7;
        return (this.f8956g * d11) + (this.f8954e * d10) + (d7 * d7 * d7);
    }
}
