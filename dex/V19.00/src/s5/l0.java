package s5;

import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l0 {
    public static final k0 Companion = new k0();

    /* renamed from: c, reason: collision with root package name */
    public static final Pattern f8810c = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* renamed from: d, reason: collision with root package name */
    public static final Pattern f8811d = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: a, reason: collision with root package name */
    public final String f8812a;

    /* renamed from: b, reason: collision with root package name */
    public final String[] f8813b;

    public l0(String str, String[] strArr) {
        this.f8812a = str;
        this.f8813b = strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.nio.charset.Charset a(java.nio.charset.Charset r6) {
        /*
            r5 = this;
            java.lang.String[] r0 = r5.f8813b
            int r1 = r0.length
            int r1 = r1 + (-1)
            r2 = 0
            r3 = 2
            int r1 = n1.z.q0(r2, r1, r3)
            if (r1 < 0) goto L21
        Ld:
            r3 = r0[r2]
            java.lang.String r4 = "charset"
            boolean r3 = o5.k.C(r3, r4)
            if (r3 == 0) goto L1c
            int r2 = r2 + 1
            r0 = r0[r2]
            goto L22
        L1c:
            if (r2 == r1) goto L21
            int r2 = r2 + 2
            goto Ld
        L21:
            r0 = 0
        L22:
            if (r0 != 0) goto L25
            return r6
        L25:
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r0)     // Catch: java.lang.IllegalArgumentException -> L29
        L29:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: s5.l0.a(java.nio.charset.Charset):java.nio.charset.Charset");
    }

    public final boolean equals(Object obj) {
        return (obj instanceof l0) && i5.a.c(((l0) obj).f8812a, this.f8812a);
    }

    public final int hashCode() {
        return this.f8812a.hashCode();
    }

    public final String toString() {
        return this.f8812a;
    }
}
