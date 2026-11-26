package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends e2.h {
    public static final o EMPTY = new o(0);

    /* renamed from: b, reason: collision with root package name */
    public final m[] f239b;

    /* renamed from: c, reason: collision with root package name */
    public int f240c;

    public o(int i7) {
        super(i7 != 0);
        this.f239b = new m[i7];
        this.f240c = 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        m[] mVarArr = oVar.f239b;
        m[] mVarArr2 = this.f239b;
        int length = mVarArr2.length;
        if (length != mVarArr.length || h() != oVar.h()) {
            return false;
        }
        for (int i7 = 0; i7 < length; i7++) {
            m mVar = mVarArr2[i7];
            Object obj2 = mVarArr[i7];
            if (mVar != obj2 && (mVar == null || !mVar.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    public final m f(int i7) {
        try {
            return this.f239b[i7];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus reg");
        }
    }

    public final void g(m mVar) {
        int i7;
        m mVar2;
        m[] mVarArr = this.f239b;
        e();
        if (mVar == null) {
            throw new NullPointerException("spec == null");
        }
        this.f240c = -1;
        try {
            int i8 = mVar.f237a;
            mVarArr[i8] = mVar;
            if (i8 > 0 && (mVar2 = mVarArr[i8 - 1]) != null && mVar2.e() == 2) {
                mVarArr[i7] = null;
            }
            if (mVar.e() == 2) {
                mVarArr[i8 + 1] = null;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("spec.getReg() out of range");
        }
    }

    public final int h() {
        int i7 = this.f240c;
        if (i7 >= 0) {
            return i7;
        }
        int i8 = 0;
        for (m mVar : this.f239b) {
            if (mVar != null) {
                i8++;
            }
        }
        this.f240c = i8;
        return i8;
    }

    public final int hashCode() {
        m[] mVarArr = this.f239b;
        int length = mVarArr.length;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            m mVar = mVarArr[i8];
            i7 = (i7 * 31) + (mVar == null ? 0 : mVar.hashCode());
        }
        return i7;
    }

    public final String toString() {
        m[] mVarArr = this.f239b;
        StringBuilder sb = new StringBuilder(mVarArr.length * 25);
        sb.append('{');
        boolean z6 = false;
        for (m mVar : mVarArr) {
            if (mVar != null) {
                if (z6) {
                    sb.append(", ");
                } else {
                    z6 = true;
                }
                sb.append(mVar);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
