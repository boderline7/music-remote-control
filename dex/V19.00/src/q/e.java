package q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements Cloneable {

    /* renamed from: e, reason: collision with root package name */
    public static final Object f8384e = new Object();

    /* renamed from: a, reason: collision with root package name */
    public boolean f8385a = false;

    /* renamed from: b, reason: collision with root package name */
    public long[] f8386b;

    /* renamed from: c, reason: collision with root package name */
    public Object[] f8387c;

    /* renamed from: d, reason: collision with root package name */
    public int f8388d;

    public e() {
        int i7;
        int i8 = 4;
        while (true) {
            i7 = 80;
            if (i8 >= 32) {
                break;
            }
            int i9 = (1 << i8) - 12;
            if (80 <= i9) {
                i7 = i9;
                break;
            }
            i8++;
        }
        int i10 = i7 / 8;
        this.f8386b = new long[i10];
        this.f8387c = new Object[i10];
    }

    public final void a() {
        int i7 = this.f8388d;
        Object[] objArr = this.f8387c;
        for (int i8 = 0; i8 < i7; i8++) {
            objArr[i8] = null;
        }
        this.f8388d = 0;
        this.f8385a = false;
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final e clone() {
        try {
            e eVar = (e) super.clone();
            eVar.f8386b = (long[]) this.f8386b.clone();
            eVar.f8387c = (Object[]) this.f8387c.clone();
            return eVar;
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }

    public final void c() {
        int i7 = this.f8388d;
        long[] jArr = this.f8386b;
        Object[] objArr = this.f8387c;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            Object obj = objArr[i9];
            if (obj != f8384e) {
                if (i9 != i8) {
                    jArr[i8] = jArr[i9];
                    objArr[i8] = obj;
                    objArr[i9] = null;
                }
                i8++;
            }
        }
        this.f8385a = false;
        this.f8388d = i8;
    }

    public final Object d(long j7, Long l7) {
        Object obj;
        int b7 = d.b(this.f8386b, this.f8388d, j7);
        return (b7 < 0 || (obj = this.f8387c[b7]) == f8384e) ? l7 : obj;
    }

    public final long e(int i7) {
        if (this.f8385a) {
            c();
        }
        return this.f8386b[i7];
    }

    public final void f(long j7, Object obj) {
        int b7 = d.b(this.f8386b, this.f8388d, j7);
        if (b7 >= 0) {
            this.f8387c[b7] = obj;
            return;
        }
        int i7 = ~b7;
        int i8 = this.f8388d;
        if (i7 < i8) {
            Object[] objArr = this.f8387c;
            if (objArr[i7] == f8384e) {
                this.f8386b[i7] = j7;
                objArr[i7] = obj;
                return;
            }
        }
        if (this.f8385a && i8 >= this.f8386b.length) {
            c();
            i7 = ~d.b(this.f8386b, this.f8388d, j7);
        }
        int i9 = this.f8388d;
        if (i9 >= this.f8386b.length) {
            int i10 = (i9 + 1) * 8;
            int i11 = 4;
            while (true) {
                if (i11 >= 32) {
                    break;
                }
                int i12 = (1 << i11) - 12;
                if (i10 <= i12) {
                    i10 = i12;
                    break;
                }
                i11++;
            }
            int i13 = i10 / 8;
            long[] jArr = new long[i13];
            Object[] objArr2 = new Object[i13];
            long[] jArr2 = this.f8386b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f8387c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f8386b = jArr;
            this.f8387c = objArr2;
        }
        int i14 = this.f8388d - i7;
        if (i14 != 0) {
            long[] jArr3 = this.f8386b;
            int i15 = i7 + 1;
            System.arraycopy(jArr3, i7, jArr3, i15, i14);
            Object[] objArr4 = this.f8387c;
            System.arraycopy(objArr4, i7, objArr4, i15, this.f8388d - i7);
        }
        this.f8386b[i7] = j7;
        this.f8387c[i7] = obj;
        this.f8388d++;
    }

    public final void g(long j7) {
        int b7 = d.b(this.f8386b, this.f8388d, j7);
        if (b7 >= 0) {
            Object[] objArr = this.f8387c;
            Object obj = objArr[b7];
            Object obj2 = f8384e;
            if (obj != obj2) {
                objArr[b7] = obj2;
                this.f8385a = true;
            }
        }
    }

    public final int h() {
        if (this.f8385a) {
            c();
        }
        return this.f8388d;
    }

    public final Object i(int i7) {
        if (this.f8385a) {
            c();
        }
        return this.f8387c[i7];
    }

    public final String toString() {
        if (h() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8388d * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8388d; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(e(i7));
            sb.append('=');
            Object i8 = i(i7);
            if (i8 != this) {
                sb.append(i8);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
