package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d0 extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final b2.u f10064e;

    public d0(b2.u uVar) {
        super(1, n1.z.o1(uVar.f2940a.length()) + uVar.f2941b.f199c + 1);
        this.f10064e = uVar;
    }

    @Override // y1.p
    public final void a(f fVar) {
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_STRING_DATA_ITEM;
    }

    @Override // y1.a0
    public final int e(a0 a0Var) {
        return this.f10064e.compareTo(((d0) a0Var).f10064e);
    }

    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        b2.u uVar = this.f10064e;
        a1.b bVar = uVar.f2941b;
        int length = uVar.f2940a.length();
        if (cVar.d()) {
            cVar.b(n1.z.o1(length), "utf16_size: ".concat(n1.z.m1(length)));
            cVar.b(bVar.f199c + 1, uVar.g());
        }
        cVar.m(length);
        int i7 = bVar.f199c;
        int i8 = cVar.f6349c;
        int i9 = i7 + i8;
        if (cVar.f6347a) {
            cVar.f(i9);
        } else if (i9 > cVar.f6348b.length) {
            e2.c.g();
            throw null;
        }
        byte[] bArr = cVar.f6348b;
        int length2 = bArr.length - i8;
        int i10 = bVar.f199c;
        if (length2 < i10) {
            throw new IndexOutOfBoundsException("(out.length - offset) < size()");
        }
        System.arraycopy((byte[]) bVar.f200d, bVar.f198b, bArr, i8, i10);
        cVar.f6349c = i9;
        cVar.i(0);
    }
}
