package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    public final a2.q f9446a;

    /* renamed from: c, reason: collision with root package name */
    public final androidx.appcompat.app.g f9448c;

    /* renamed from: d, reason: collision with root package name */
    public final i6.i f9449d;

    /* renamed from: e, reason: collision with root package name */
    public final j.h f9450e;

    /* renamed from: f, reason: collision with root package name */
    public final int f9451f;

    /* renamed from: h, reason: collision with root package name */
    public final int f9453h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f9454i;

    /* renamed from: b, reason: collision with root package name */
    public final int f9447b = 1;

    /* renamed from: g, reason: collision with root package name */
    public int[] f9452g = null;

    public w(a2.q qVar, int i7, v1.a aVar) {
        this.f9446a = qVar;
        this.f9448c = new androidx.appcompat.app.g(qVar);
        this.f9453h = i7;
        boolean[] zArr = {true};
        a2.c cVar = (a2.c) qVar.f249b;
        if (cVar.f226d == -1) {
            p3.f fVar = new p3.f();
            fVar.f8238a = 0;
            int length = cVar.f6354b.length;
            for (int i8 = 0; i8 < length; i8++) {
                a2.h hVar = ((a2.b) cVar.f(i8)).f222b;
                int length2 = hVar.f6354b.length;
                for (int i9 = 0; i9 < length2; i9++) {
                    ((a2.g) hVar.f(i9)).a(fVar);
                }
            }
            cVar.f226d = fVar.f8238a;
        }
        a1.b bVar = new a1.b(zArr, cVar.f226d, i7);
        int length3 = cVar.f6354b.length;
        for (int i10 = 0; i10 < length3; i10++) {
            a2.h hVar2 = ((a2.b) cVar.f(i10)).f222b;
            int length4 = hVar2.f6354b.length;
            for (int i11 = 0; i11 < length4; i11++) {
                ((a2.g) hVar2.f(i11)).a(bVar);
            }
        }
        this.f9454i = zArr[0];
        Object[] objArr = cVar.f6354b;
        int length5 = objArr.length * 3;
        int i12 = 0;
        for (Object obj : objArr) {
            a2.b bVar2 = (a2.b) obj;
            if (bVar2 != null) {
                i12 += bVar2.f222b.f6354b.length;
            }
        }
        int i13 = length5 + i12;
        if (cVar.f226d == -1) {
            p3.f fVar2 = new p3.f();
            fVar2.f8238a = 0;
            int length6 = cVar.f6354b.length;
            for (int i14 = 0; i14 < length6; i14++) {
                a2.h hVar3 = ((a2.b) cVar.f(i14)).f222b;
                int length7 = hVar3.f6354b.length;
                for (int i15 = 0; i15 < length7; i15++) {
                    ((a2.g) hVar3.f(i15)).a(fVar2);
                }
            }
            cVar.f226d = fVar2.f8238a;
        }
        int i16 = cVar.f226d + (this.f9454i ? 0 : this.f9453h);
        this.f9451f = i16;
        i6.i iVar = new i6.i(aVar, i13, length5, i16, i7);
        this.f9449d = iVar;
        this.f9450e = new j.h(this, iVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0038 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static a2.n a(a2.g r6, a2.m r7) {
        /*
            a2.n r0 = r6.f231d
            a2.p r6 = r6.f228a
            int r6 = r6.f241a
            r1 = 0
            r2 = 1
            r3 = 14
            if (r6 == r3) goto L14
            r3 = 16
            if (r6 == r3) goto L14
            switch(r6) {
                case 20: goto L14;
                case 21: goto L14;
                case 22: goto L14;
                default: goto L13;
            }
        L13:
            goto L36
        L14:
            java.lang.Object[] r6 = r0.f6354b
            int r6 = r6.length
            r3 = 2
            if (r6 != r3) goto L36
            int r6 = r7.f237a
            java.lang.Object r3 = r0.f(r2)
            a2.m r3 = (a2.m) r3
            int r3 = r3.f237a
            if (r6 != r3) goto L36
            java.lang.Object r6 = r0.f(r2)
            a2.m r6 = (a2.m) r6
            java.lang.Object r0 = r0.f(r1)
            a2.m r0 = (a2.m) r0
            a2.n r0 = a2.n.j(r6, r0)
        L36:
            if (r7 != 0) goto L39
            return r0
        L39:
            java.lang.Object[] r6 = r0.f6354b
            int r6 = r6.length
            a2.n r3 = new a2.n
            int r4 = r6 + 1
            r3.<init>(r4)
            r4 = r1
        L44:
            if (r4 >= r6) goto L51
            int r5 = r4 + 1
            java.lang.Object r4 = r0.f(r4)
            r3.g(r5, r4)
            r4 = r5
            goto L44
        L51:
            r3.g(r1, r7)
            boolean r6 = r0.f6364a
            r6 = r6 ^ r2
            if (r6 == 0) goto L5b
            r3.f6364a = r1
        L5b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: w1.w.a(a2.g, a2.m):a2.n");
    }
}
