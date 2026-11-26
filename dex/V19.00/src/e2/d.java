package e2;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d extends h implements i {

    /* renamed from: b, reason: collision with root package name */
    public final Object[] f6354b;

    public d(int i7) {
        super(i7 != 0);
        try {
            this.f6354b = new Object[i7];
        } catch (NegativeArraySizeException unused) {
            throw new IllegalArgumentException("size < 0");
        }
    }

    public c2.c a(int i7) {
        return (c2.c) f(i7);
    }

    @Override // e2.i
    public String d() {
        String name = getClass().getName();
        return h(name.substring(name.lastIndexOf(46) + 1) + '{', "}", true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.f6354b, ((d) obj).f6354b);
    }

    public final Object f(int i7) {
        try {
            Object obj = this.f6354b[i7];
            if (obj != null) {
                return obj;
            }
            throw new NullPointerException("unset: " + i7);
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i7 < 0) {
                throw new IndexOutOfBoundsException("n < 0");
            }
            throw new IndexOutOfBoundsException("n >= size()");
        }
    }

    public final void g(int i7, Object obj) {
        e();
        try {
            this.f6354b[i7] = obj;
        } catch (ArrayIndexOutOfBoundsException unused) {
            if (i7 >= 0) {
                throw new IndexOutOfBoundsException("n >= size()");
            }
            throw new IndexOutOfBoundsException("n < 0");
        }
    }

    public final String h(String str, String str2, boolean z6) {
        Object[] objArr = this.f6354b;
        int length = objArr.length;
        StringBuilder sb = new StringBuilder((length * 10) + 10);
        if (str != null) {
            sb.append(str);
        }
        for (int i7 = 0; i7 < length; i7++) {
            if (i7 != 0) {
                sb.append(", ");
            }
            if (z6) {
                sb.append(((i) objArr[i7]).d());
            } else {
                sb.append(objArr[i7]);
            }
        }
        if (str2 != null) {
            sb.append(str2);
        }
        return sb.toString();
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f6354b);
    }

    public final String toString() {
        String name = getClass().getName();
        return h(name.substring(name.lastIndexOf(46) + 1) + '{', "}", false);
    }
}
