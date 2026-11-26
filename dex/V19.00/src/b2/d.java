package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d extends q {

    /* renamed from: c, reason: collision with root package name */
    public final c2.a f2931c;

    /* renamed from: d, reason: collision with root package name */
    public c2.a f2932d;

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0024, code lost:
    
        if (r3.equals("invokeExact") == false) goto L141;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public d(b2.v r3, b2.s r4) {
        /*
            Method dump skipped, instructions count: 720
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b2.d.<init>(b2.v, b2.s):void");
    }

    @Override // c2.d
    public final c2.c b() {
        return this.f2931c.f3036b;
    }

    @Override // b2.a
    public final int e(a aVar) {
        q qVar = (q) aVar;
        int compareTo = this.f2936a.compareTo(qVar.f2936a);
        if (compareTo == 0) {
            compareTo = this.f2937b.f2938a.compareTo(qVar.f2937b.f2938a);
        }
        return compareTo != 0 ? compareTo : this.f2931c.compareTo(((d) aVar).f2931c);
    }

    public final int g(boolean z6) {
        c2.a aVar = this.f2931c;
        if (!z6) {
            if (this.f2932d == null) {
                c2.c cVar = this.f2936a.f2943a;
                aVar.getClass();
                String str = "(" + cVar.f3040a + aVar.f3035a.substring(1);
                Object[] objArr = aVar.f3037c.f6354b;
                int length = objArr.length;
                c2.b bVar = new c2.b(length + 1);
                bVar.g(0, cVar);
                int i7 = 0;
                while (i7 < length) {
                    int i8 = i7 + 1;
                    bVar.g(i8, objArr[i7]);
                    i7 = i8;
                }
                bVar.f6364a = false;
                c2.a aVar2 = new c2.a(str, aVar.f3036b, bVar);
                c2.a aVar3 = (c2.a) c2.a.f3034e.putIfAbsent(str, aVar2);
                if (aVar3 != null) {
                    aVar2 = aVar3;
                }
                this.f2932d = aVar2;
            }
            aVar = this.f2932d;
        }
        c2.b bVar2 = aVar.f3037c;
        int length2 = bVar2.f6354b.length;
        int i9 = 0;
        for (int i10 = 0; i10 < length2; i10++) {
            i9 += ((c2.c) bVar2.f(i10)).e();
        }
        return i9;
    }
}
