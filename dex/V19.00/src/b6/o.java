package b6;

import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends s {
    public static final n Companion = new n();

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f2999c;

    /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
    
        if (r2.intValue() < 9) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0069 A[SYNTHETIC] */
    static {
        /*
            b6.n r0 = new b6.n
            r0.<init>()
            b6.o.Companion = r0
            java.lang.String r0 = "java.specification.version"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L77
            r4 = 10
            i5.a.h(r4)
            int r5 = r0.length()
            if (r5 != 0) goto L1f
            goto L77
        L1f:
            char r6 = r0.charAt(r3)
            r7 = 48
            int r7 = i5.a.i(r6, r7)
            r8 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r7 >= 0) goto L41
            if (r5 != r1) goto L31
            goto L77
        L31:
            r7 = 45
            if (r6 != r7) goto L3a
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r1
        L38:
            r7 = r6
            goto L43
        L3a:
            r7 = 43
            if (r6 != r7) goto L77
            r6 = r1
            r7 = r3
            goto L43
        L41:
            r6 = r3
            goto L38
        L43:
            r9 = -59652323(0xfffffffffc71c71d, float:-5.0215282E36)
            r10 = r3
            r11 = r9
        L48:
            if (r6 >= r5) goto L69
            char r12 = r0.charAt(r6)
            int r12 = java.lang.Character.digit(r12, r4)
            if (r12 >= 0) goto L55
            goto L77
        L55:
            if (r10 >= r11) goto L5e
            if (r11 != r9) goto L77
            int r11 = r8 / 10
            if (r10 >= r11) goto L5e
            goto L77
        L5e:
            int r10 = r10 * 10
            int r13 = r8 + r12
            if (r10 >= r13) goto L65
            goto L77
        L65:
            int r10 = r10 - r12
            int r6 = r6 + 1
            goto L48
        L69:
            if (r7 == 0) goto L71
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)
        L6f:
            r2 = r0
            goto L77
        L71:
            int r0 = -r10
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L6f
        L77:
            if (r2 == 0) goto L84
            int r0 = r2.intValue()
            r2 = 9
            if (r0 < r2) goto L82
            goto L8d
        L82:
            r1 = r3
            goto L8d
        L84:
            java.lang.Class<javax.net.ssl.SSLSocket> r0 = javax.net.ssl.SSLSocket.class
            java.lang.String r2 = "getApplicationProtocol"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.NoSuchMethodException -> L82
            r0.getMethod(r2, r4)     // Catch: java.lang.NoSuchMethodException -> L82
        L8d:
            b6.o.f2999c = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b6.o.<clinit>():void");
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        s.Companion.getClass();
        sSLParameters.setApplicationProtocols((String[]) r.a(list).toArray(new String[0]));
        sSLSocket.setSSLParameters(sSLParameters);
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        String applicationProtocol;
        try {
            applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null) {
                return null;
            }
            if (i5.a.c(applicationProtocol, h0.FRAGMENT_ENCODE_SET)) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
