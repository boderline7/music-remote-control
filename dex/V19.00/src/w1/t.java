package w1;

import com.android.dex.DexException;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    public final v1.a f9439a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9440b;

    /* renamed from: c, reason: collision with root package name */
    public ArrayList f9441c;

    /* renamed from: d, reason: collision with root package name */
    public int f9442d = -1;

    /* renamed from: e, reason: collision with root package name */
    public int f9443e;

    /* renamed from: f, reason: collision with root package name */
    public final int f9444f;

    public t(v1.a aVar, int i7, int i8, int i9) {
        this.f9439a = aVar;
        this.f9440b = i8;
        this.f9441c = new ArrayList(i7);
        this.f9444f = i9;
    }

    public final void a(h hVar) {
        this.f9441c.add(hVar);
        hVar.f9428c.getClass();
        if (hVar instanceof r) {
            ((r) hVar).getClass();
            throw null;
        }
        if (hVar instanceof s) {
            ((s) hVar).getClass();
        }
    }

    public final void b() {
        int size = this.f9441c.size();
        int i7 = this.f9440b + this.f9442d + this.f9443e;
        int i8 = i7 - this.f9444f;
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(i7, 1);
        for (int i9 = 0; i9 < i7; i9++) {
            if (i9 >= i8) {
                nVar.d(i9, i9 + 1);
            } else {
                nVar.d(i9, i9);
            }
        }
        for (int i10 = 0; i10 < size; i10++) {
            h hVar = (h) this.f9441c.get(i10);
            if (!(hVar instanceof e)) {
                this.f9441c.set(i10, hVar.i(nVar));
            }
        }
        this.f9443e++;
    }

    public final void c() {
        int size = this.f9441c.size();
        for (int i7 = 0; i7 < size; i7++) {
            h hVar = (h) this.f9441c.get(i7);
            if (!(hVar instanceof e)) {
                this.f9441c.set(i7, hVar.k(1));
            }
        }
        this.f9442d++;
    }

    public final j d(h hVar) {
        j e7 = e(hVar.l(hVar.f9429d.k(hVar.f9427b.f9435e, null)), hVar.f9427b);
        if (e7 != null) {
            return e7;
        }
        throw new DexException("No expanded opcode for " + hVar, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x002a, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final w1.j e(w1.h r2, w1.j r3) {
        /*
            r1 = this;
        L0:
            if (r3 == 0) goto L2a
            w1.n r0 = r3.f9434d
            boolean r0 = r0.i(r2)
            if (r0 == 0) goto L10
            v1.a r2 = r1.f9439a
            r2.getClass()
            goto L2a
        L10:
            w1.j[] r0 = w1.k.f9436a
            r0 = -1
            int r3 = r3.f9433c
            if (r3 != r0) goto L19
            r3 = 0
            goto L0
        L19:
            int r3 = r3 + 1
            w1.j[] r0 = w1.k.f9436a     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L22
            r3 = r0[r3]     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L22
            if (r3 == 0) goto L22
            goto L0
        L22:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "bogus opcode"
            r2.<init>(r3)
            throw r2
        L2a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: w1.t.e(w1.h, w1.j):w1.j");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean f(j[] jVarArr) {
        int i7 = this.f9442d;
        if (i7 < 0) {
            i7 = 0;
        }
        boolean z6 = false;
        while (true) {
            int size = this.f9441c.size();
            int i8 = this.f9442d;
            for (int i9 = 0; i9 < size; i9++) {
                h hVar = (h) this.f9441c.get(i9);
                j jVar = jVarArr[i9];
                j e7 = e(hVar, jVar);
                if (e7 == null) {
                    BitSet f7 = d(hVar).f9434d.f(hVar);
                    boolean z7 = hVar.f9427b.f9435e;
                    a2.n nVar = hVar.f9429d;
                    int length = nVar.f6354b.length;
                    int e8 = (z7 == 0 || f7.get(0)) ? 0 : ((a2.m) nVar.f(0)).e();
                    int i10 = 0;
                    for (int i11 = z7; i11 < length; i11++) {
                        if (!f7.get(i11)) {
                            i10 += ((a2.m) nVar.f(i11)).e();
                        }
                    }
                    int max = Math.max(i10, e8);
                    if (max > i8) {
                        i8 = max;
                    }
                } else if (jVar == e7) {
                }
                jVarArr[i9] = e7;
            }
            if (i7 >= i8) {
                this.f9442d = i7;
                return z6;
            }
            int i12 = i8 - i7;
            int size2 = this.f9441c.size();
            for (int i13 = 0; i13 < size2; i13++) {
                h hVar2 = (h) this.f9441c.get(i13);
                if (!(hVar2 instanceof e)) {
                    this.f9441c.set(i13, hVar2.k(i12));
                }
            }
            z6 = true;
            i7 = i8;
        }
    }
}
