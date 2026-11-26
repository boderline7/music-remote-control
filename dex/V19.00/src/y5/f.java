package y5;

import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    public static final g6.l f10214a;

    /* renamed from: b, reason: collision with root package name */
    public static final g6.l f10215b;

    static {
        g6.l.Companion.getClass();
        f10214a = g6.k.b("\"\\");
        f10215b = g6.k.b("\t ,=");
    }

    public static final boolean a(x0 x0Var) {
        if (i5.a.c((String) x0Var.f8913a.f1483c, "HEAD")) {
            return false;
        }
        int i7 = x0Var.f8916d;
        return (((i7 >= 100 && i7 < 200) || i7 == 204 || i7 == 304) && t5.b.j(x0Var) == -1 && !o5.k.C("chunked", x0.A(x0Var, "Transfer-Encoding"))) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
    
        r5 = a.a.j(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
    
        if (r6 < 0) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004b, code lost:
    
        if (r6 == 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        if (r6 == 1) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
    
        r8 = 0;
        r7 = "=".charAt(0);
        r9 = new char[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
    
        if (r8 >= r6) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
    
        r9[r8] = r7;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
    
        r6 = new java.lang.String(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        r6 = "=".toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        r6 = s5.h0.FRAGMENT_ENCODE_SET;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009f, code lost:
    
        throw new java.lang.IllegalArgumentException(("Count 'n' must be non-negative, but was " + r6 + '.').toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0146, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0146, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void b(g6.h r18, java.util.ArrayList r19) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.f.b(g6.h, java.util.ArrayList):void");
    }

    public static final String c(g6.h hVar) {
        long E = hVar.E(f10215b);
        if (E == -1) {
            E = hVar.f6573b;
        }
        if (E != 0) {
            return hVar.H(E, o5.a.UTF_8);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x01f0, code lost:
    
        if (r3.f8062a.matcher(r0).matches() == false) goto L108;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void d(s5.t r34, s5.h0 r35, s5.d0 r36) {
        /*
            Method dump skipped, instructions count: 614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.f.d(s5.t, s5.h0, s5.d0):void");
    }

    public static final boolean e(g6.h hVar) {
        boolean z6 = false;
        while (!hVar.t()) {
            byte C = hVar.C(0L);
            if (C == 44) {
                hVar.y();
                z6 = true;
            } else {
                if (C != 32 && C != 9) {
                    break;
                }
                hVar.y();
            }
        }
        return z6;
    }
}
