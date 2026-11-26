package e2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends h {
    public static final f EMPTY;

    /* renamed from: b, reason: collision with root package name */
    public int[] f6361b;

    /* renamed from: c, reason: collision with root package name */
    public int f6362c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6363d;

    static {
        f fVar = new f(0);
        EMPTY = fVar;
        fVar.f6364a = false;
    }

    public f(int i7) {
        super(true);
        try {
            this.f6361b = new int[i7];
            this.f6362c = 0;
            this.f6363d = true;
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f6363d != fVar.f6363d || this.f6362c != fVar.f6362c) {
            return false;
        }
        for (int i7 = 0; i7 < this.f6362c; i7++) {
            if (this.f6361b[i7] != fVar.f6361b[i7]) {
                return false;
            }
        }
        return true;
    }

    public final void f(int i7) {
        e();
        int i8 = this.f6362c;
        int[] iArr = this.f6361b;
        if (i8 == iArr.length) {
            int[] iArr2 = new int[((i8 * 3) / 2) + 10];
            System.arraycopy(iArr, 0, iArr2, 0, i8);
            this.f6361b = iArr2;
        }
        int[] iArr3 = this.f6361b;
        int i9 = this.f6362c;
        int i10 = i9 + 1;
        this.f6362c = i10;
        iArr3[i9] = i7;
        if (!this.f6363d || i10 <= 1) {
            return;
        }
        this.f6363d = i7 >= iArr3[i9 + (-1)];
    }

    public final int g(int i7) {
        if (i7 >= this.f6362c) {
            throw new IndexOutOfBoundsException("n >= size()");
        }
        try {
            return this.f6361b[i7];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IndexOutOfBoundsException("n < 0");
        }
    }

    public final void h(int i7, int i8) {
        e();
        if (i7 >= this.f6362c) {
            throw new IndexOutOfBoundsException("n >= size()");
        }
        try {
            this.f6361b[i7] = i8;
            this.f6363d = false;
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i7 < 0) {
                throw new IllegalArgumentException("n < 0");
            }
        }
    }

    public final int hashCode() {
        int i7 = 0;
        for (int i8 = 0; i8 < this.f6362c; i8++) {
            i7 = (i7 * 31) + this.f6361b[i8];
        }
        return i7;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f6362c * 5) + 10);
        sb.append('{');
        for (int i7 = 0; i7 < this.f6362c; i7++) {
            if (i7 != 0) {
                sb.append(", ");
            }
            sb.append(this.f6361b[i7]);
        }
        sb.append('}');
        return sb.toString();
    }
}
