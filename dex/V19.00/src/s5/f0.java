package s5;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f0 {
    public static final e0 Companion = new e0();
    public static final String INVALID_HOST = "Invalid URL host";

    /* renamed from: a, reason: collision with root package name */
    public String f8783a;

    /* renamed from: d, reason: collision with root package name */
    public String f8786d;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f8788f;

    /* renamed from: g, reason: collision with root package name */
    public ArrayList f8789g;

    /* renamed from: h, reason: collision with root package name */
    public String f8790h;

    /* renamed from: b, reason: collision with root package name */
    public String f8784b = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: c, reason: collision with root package name */
    public String f8785c = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: e, reason: collision with root package name */
    public int f8787e = -1;

    public f0() {
        ArrayList arrayList = new ArrayList();
        this.f8788f = arrayList;
        arrayList.add(h0.FRAGMENT_ENCODE_SET);
    }

    public final h0 a() {
        int b7;
        ArrayList arrayList;
        String str = this.f8783a;
        if (str == null) {
            throw new IllegalStateException("scheme == null");
        }
        g0 g0Var = h0.Companion;
        String d7 = g0.d(g0Var, this.f8784b, 0, 0, false, 7);
        String d8 = g0.d(g0Var, this.f8785c, 0, 0, false, 7);
        String str2 = this.f8786d;
        if (str2 == null) {
            throw new IllegalStateException("host == null");
        }
        int i7 = this.f8787e;
        if (i7 != -1) {
            b7 = i7;
        } else {
            String str3 = this.f8783a;
            i5.a.d(str3);
            g0Var.getClass();
            b7 = g0.b(str3);
        }
        ArrayList arrayList2 = this.f8788f;
        ArrayList arrayList3 = new ArrayList(c5.l.x1(arrayList2));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(g0.d(h0.Companion, (String) it.next(), 0, 0, false, 7));
        }
        ArrayList<String> arrayList4 = this.f8789g;
        if (arrayList4 != null) {
            arrayList = new ArrayList(c5.l.x1(arrayList4));
            for (String str4 : arrayList4) {
                arrayList.add(str4 != null ? g0.d(h0.Companion, str4, 0, 0, true, 3) : null);
            }
        } else {
            arrayList = null;
        }
        String str5 = this.f8790h;
        return new h0(str, d7, d8, str2, b7, arrayList3, arrayList, str5 != null ? g0.d(h0.Companion, str5, 0, 0, false, 7) : null, toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:148:0x0291, code lost:
    
        if (r10 < 65536) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x007b, code lost:
    
        if (r6 == ':') goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:123:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x024a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0153  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(s5.h0 r30, java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 1124
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s5.f0.b(s5.h0, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x008a, code lost:
    
        if (r1 != s5.g0.b(r3)) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s5.f0.toString():java.lang.String");
    }
}
