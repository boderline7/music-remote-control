package y1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i0 extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final q f10095e;

    /* renamed from: f, reason: collision with root package name */
    public final List f10096f;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public i0(y1.q r4, java.util.ArrayList r5) {
        /*
            r3 = this;
            int r0 = l(r5)
            r1 = 0
            java.lang.Object r1 = r5.get(r1)
            y1.a0 r1 = (y1.a0) r1
            int r2 = r5.size()
            int r1 = r1.c()
            int r1 = r1 * r2
            int r2 = l(r5)
            int r2 = r2 + r1
            r3.<init>(r0, r2)
            if (r4 == 0) goto L23
            r3.f10096f = r5
            r3.f10095e = r4
            return
        L23:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "itemType == null"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: y1.i0.<init>(y1.q, java.util.ArrayList):void");
    }

    public static int l(ArrayList arrayList) {
        try {
            return Math.max(4, ((a0) arrayList.get(0)).f10033a);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("items.size() == 0");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("items == null");
        }
    }

    @Override // y1.p
    public final void a(f fVar) {
        Iterator it = this.f10096f.iterator();
        while (it.hasNext()) {
            ((a0) it.next()).a(fVar);
        }
    }

    @Override // y1.p
    public final q b() {
        return this.f10095e;
    }

    @Override // y1.a0
    public final void i(c0 c0Var, int i7) {
        int i8 = i7 + this.f10033a;
        int i9 = -1;
        boolean z6 = true;
        int i10 = -1;
        for (a0 a0Var : this.f10096f) {
            int c7 = a0Var.c();
            int i11 = a0Var.f10033a;
            if (z6) {
                z6 = false;
                i9 = c7;
                i10 = i11;
            } else {
                if (c7 != i9) {
                    throw new UnsupportedOperationException("item size mismatch");
                }
                if (i11 != i10) {
                    throw new UnsupportedOperationException("item alignment mismatch");
                }
            }
            i8 = a0Var.h(c0Var, i8) + c7;
        }
    }

    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        List list = this.f10096f;
        int size = list.size();
        if (cVar.d()) {
            cVar.b(0, g() + " " + this.f10095e.f10105c);
            cVar.b(4, "  size: ".concat(n1.z.m1(size)));
        }
        cVar.j(size);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((a0) it.next()).d(fVar, cVar);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(i0.class.getName());
        sb.append(this.f10096f);
        return sb.toString();
    }
}
