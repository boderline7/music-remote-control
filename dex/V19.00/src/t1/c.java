package t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {
    public static final int MAX_BUFFER_BYTES = 2048;

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0012, code lost:
    
        r0.append("\r\n");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.ByteArrayInputStream r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = -1
            r2 = r1
        L7:
            int r2 = r5.read()     // Catch: java.lang.Exception -> L20
            if (r2 == r1) goto L2d
            char r3 = (char) r2     // Catch: java.lang.Exception -> L20
            r4 = 10
            if (r3 != r4) goto L18
            java.lang.String r5 = "\r\n"
            r0.append(r5)     // Catch: java.lang.Exception -> L20
            goto L2d
        L18:
            r4 = 13
            if (r3 == r4) goto L7
            r0.append(r3)     // Catch: java.lang.Exception -> L20
            goto L7
        L20:
            int r5 = r0.length()
            if (r5 != 0) goto L2d
            if (r2 != r1) goto L2a
            r5 = 0
            return r5
        L2a:
            java.lang.String r5 = ""
            return r5
        L2d:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.c.a(java.io.ByteArrayInputStream):java.lang.String");
    }
}
