package androidx.recyclerview.widget;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ List f2394a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ List f2395b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2396c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Runnable f2397d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ g f2398e;

    public d(g gVar, List list, List list2, int i7, Runnable runnable) {
        this.f2398e = gVar;
        this.f2394a = list;
        this.f2395b = list2;
        this.f2396c = i7;
        this.f2397d = runnable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0198, code lost:
    
        r0 = new androidx.recyclerview.widget.w();
        r0.f2699a = r9;
        r0.f2700b = r9 - r8;
        r0.f2701c = r4 - r9;
        r0.f2702d = r12;
        r0.f2703e = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01ac, code lost:
    
        r7 = r7 + 2;
        r11 = r25;
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0188, code lost:
    
        r25 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0161, code lost:
    
        r9 = r3[(r2 + r8) - 1];
        r12 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0152, code lost:
    
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01b2, code lost:
    
        r10 = r10 + 1;
        r7 = r19;
        r4 = r20;
        r9 = r21;
        r8 = r22;
        r12 = r23;
        r14 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00aa, code lost:
    
        if (r6[r20 - 1] < r6[r20 + 1]) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x012b, code lost:
    
        r20 = r4;
        r22 = r8;
        r21 = r9;
        r23 = r12;
        r24 = r14;
        r4 = false;
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0137, code lost:
    
        if (r7 > r10) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0139, code lost:
    
        r8 = r7 + r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x013d, code lost:
    
        if (r8 == (r10 + r15)) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0141, code lost:
    
        if (r8 == (r5 + r15)) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0143, code lost:
    
        r9 = r2 + r8;
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x014f, code lost:
    
        if (r3[r9 - 1] >= r3[r9 + 1]) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0154, code lost:
    
        r9 = r3[(r2 + r8) + 1] - 1;
        r12 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0168, code lost:
    
        r14 = r9 - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x016a, code lost:
    
        if (r9 <= 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x016c, code lost:
    
        if (r14 <= 0) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x016e, code lost:
    
        r25 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x017c, code lost:
    
        if (r1.f((r11 + r9) - 1, (r13 + r14) - 1) == false) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x017e, code lost:
    
        r9 = r9 - 1;
        r14 = r14 - 1;
        r11 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018a, code lost:
    
        r4 = r2 + r8;
        r3[r4] = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x018e, code lost:
    
        if (r0 != false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0190, code lost:
    
        if (r8 < r5) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0192, code lost:
    
        if (r8 > r10) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0194, code lost:
    
        r4 = r6[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0196, code lost:
    
        if (r4 < r9) goto L122;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e5 A[LOOP:3: B:20:0x00d3->B:24:0x00e5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f2 A[EDGE_INSN: B:25:0x00f2->B:26:0x00f2 BREAK  A[LOOP:3: B:20:0x00d3->B:24:0x00e5], SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.d.run():void");
    }
}
