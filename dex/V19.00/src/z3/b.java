package z3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final a f10294a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f10295b;

    public b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f10294a = aVar;
        int length = iArr.length;
        int i7 = 1;
        if (length <= 1 || iArr[0] != 0) {
            this.f10295b = iArr;
            return;
        }
        while (i7 < length && iArr[i7] == 0) {
            i7++;
        }
        if (i7 == length) {
            this.f10295b = new int[]{0};
            return;
        }
        int i8 = length - i7;
        int[] iArr2 = new int[i8];
        this.f10295b = iArr2;
        System.arraycopy(iArr, i7, iArr2, 0, i8);
    }

    public final b a(b bVar) {
        a aVar = bVar.f10294a;
        a aVar2 = this.f10294a;
        if (!aVar2.equals(aVar)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (c()) {
            return bVar;
        }
        if (bVar.c()) {
            return this;
        }
        int[] iArr = this.f10295b;
        int length = iArr.length;
        int[] iArr2 = bVar.f10295b;
        if (length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length2 = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length2);
        for (int i7 = length2; i7 < iArr.length; i7++) {
            iArr3[i7] = iArr2[i7 - length2] ^ iArr[i7];
        }
        return new b(aVar2, iArr3);
    }

    public final int b() {
        return this.f10295b.length - 1;
    }

    public final boolean c() {
        return this.f10295b[0] == 0;
    }

    public final String toString() {
        if (c()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int b7 = b(); b7 >= 0; b7--) {
            int[] iArr = this.f10295b;
            int i7 = iArr[(iArr.length - 1) - b7];
            if (i7 != 0) {
                if (i7 < 0) {
                    if (b7 == b()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    i7 = -i7;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b7 == 0 || i7 != 1) {
                    a aVar = this.f10294a;
                    if (i7 == 0) {
                        aVar.getClass();
                        throw new IllegalArgumentException();
                    }
                    int i8 = aVar.f10289b[i7];
                    if (i8 == 0) {
                        sb.append('1');
                    } else if (i8 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(i8);
                    }
                }
                if (b7 != 0) {
                    if (b7 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b7);
                    }
                }
            }
        }
        return sb.toString();
    }
}
