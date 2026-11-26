package w1;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends e2.d implements Comparable {
    public static final b EMPTY = new b(0);

    @Override // e2.d, e2.i
    public final String d() {
        return j(h0.FRAGMENT_ENCODE_SET, h0.FRAGMENT_ENCODE_SET);
    }

    @Override // java.lang.Comparable
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final int compareTo(b bVar) {
        if (this == bVar) {
            return 0;
        }
        int length = this.f6354b.length;
        int length2 = bVar.f6354b.length;
        int min = Math.min(length, length2);
        for (int i7 = 0; i7 < min; i7++) {
            int compareTo = ((a) f(i7)).compareTo((a) bVar.f(i7));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (length < length2) {
            return -1;
        }
        return length > length2 ? 1 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String j(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 100
            r0.<init>(r1)
            java.lang.Object[] r1 = r5.f6354b
            int r1 = r1.length
            r0.append(r6)
            r0.append(r7)
            java.lang.String r7 = "catch "
            r0.append(r7)
            r7 = 0
        L16:
            if (r7 >= r1) goto L73
            java.lang.Object r2 = r5.f(r7)
            w1.a r2 = (w1.a) r2
            if (r7 == 0) goto L2d
            java.lang.String r3 = ",\n"
            r0.append(r3)
            r0.append(r6)
            java.lang.String r3 = "  "
            r0.append(r3)
        L2d:
            int r3 = r1 + (-1)
            if (r7 != r3) goto L4f
            java.lang.Object[] r3 = r5.f6354b
            int r3 = r3.length
            if (r3 != 0) goto L37
            goto L4f
        L37:
            int r3 = r3 + (-1)
            java.lang.Object r3 = r5.f(r3)
            w1.a r3 = (w1.a) r3
            b2.v r4 = b2.v.OBJECT
            b2.v r3 = r3.f9413a
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L4f
            java.lang.String r3 = "<any>"
            r0.append(r3)
            goto L5a
        L4f:
            b2.v r3 = r2.f9413a
            c2.c r3 = r3.f2943a
            java.lang.String r3 = r3.d()
            r0.append(r3)
        L5a:
            java.lang.String r3 = " -> "
            r0.append(r3)
            int r2 = r2.f9414b
            char r3 = (char) r2
            if (r2 != r3) goto L69
            java.lang.String r2 = n1.z.l1(r2)
            goto L6d
        L69:
            java.lang.String r2 = n1.z.m1(r2)
        L6d:
            r0.append(r2)
            int r7 = r7 + 1
            goto L16
        L73:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: w1.b.j(java.lang.String, java.lang.String):java.lang.String");
    }
}
