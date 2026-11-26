package u1;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public final q[] f9305a;

    /* renamed from: b, reason: collision with root package name */
    public final c2.b f9306b;

    public r(q[] qVarArr) {
        this.f9305a = (q[]) qVarArr.clone();
        this.f9306b = new c2.b(qVarArr.length);
        for (int i7 = 0; i7 < qVarArr.length; i7++) {
            this.f9306b.g(i7, qVarArr[i7].f9303b);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof r) && Arrays.equals(((r) obj).f9305a, this.f9305a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f9305a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i7 = 0;
        while (true) {
            q[] qVarArr = this.f9305a;
            if (i7 >= qVarArr.length) {
                return sb.toString();
            }
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(qVarArr[i7]);
            i7++;
        }
    }
}
