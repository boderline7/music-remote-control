package u;

import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import v.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends t {
    public boolean A0;
    public final s.d B0;
    public int C0;
    public int D0;
    public int E0;
    public int F0;
    public c[] G0;
    public c[] H0;
    public int I0;
    public boolean J0;
    public boolean K0;
    public WeakReference L0;
    public WeakReference M0;
    public WeakReference N0;
    public WeakReference O0;
    public final HashSet P0;
    public final v.b Q0;

    /* renamed from: w0, reason: collision with root package name */
    public final v.c f9175w0;

    /* renamed from: x0, reason: collision with root package name */
    public final v.f f9176x0;

    /* renamed from: y0, reason: collision with root package name */
    public int f9177y0;

    /* renamed from: z0, reason: collision with root package name */
    public v.r f9178z0;

    public j() {
        this.f9216v0 = new ArrayList();
        this.f9175w0 = new v.c(this);
        v.f fVar = new v.f();
        fVar.f9331b = true;
        fVar.f9332c = true;
        fVar.f9334e = new ArrayList();
        new ArrayList();
        fVar.f9335f = null;
        fVar.f9336g = new v.b();
        fVar.f9337h = new ArrayList();
        fVar.f9330a = this;
        fVar.f9333d = this;
        this.f9176x0 = fVar;
        this.f9178z0 = null;
        this.A0 = false;
        this.B0 = new s.d();
        this.E0 = 0;
        this.F0 = 0;
        this.G0 = new c[4];
        this.H0 = new c[4];
        this.I0 = q.OPTIMIZATION_STANDARD;
        this.J0 = false;
        this.K0 = false;
        this.L0 = null;
        this.M0 = null;
        this.N0 = null;
        this.O0 = null;
        this.P0 = new HashSet();
        this.Q0 = new v.b();
    }

    public static void X(i iVar, v.r rVar, v.b bVar) {
        int i7;
        int i8;
        if (rVar == null) {
            return;
        }
        if (iVar.f9145i0 == 8 || (iVar instanceof n) || (iVar instanceof a)) {
            bVar.f9319e = 0;
            bVar.f9320f = 0;
            return;
        }
        h[] hVarArr = iVar.U;
        bVar.f9315a = hVarArr[0];
        bVar.f9316b = hVarArr[1];
        bVar.f9317c = iVar.s();
        bVar.f9318d = iVar.m();
        bVar.f9323i = false;
        bVar.f9324j = 0;
        h hVar = bVar.f9315a;
        h hVar2 = h.MATCH_CONSTRAINT;
        boolean z6 = hVar == hVar2;
        boolean z7 = bVar.f9316b == hVar2;
        boolean z8 = z6 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        boolean z9 = z7 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        if (z6 && iVar.v(0) && iVar.f9162r == 0 && !z8) {
            bVar.f9315a = h.WRAP_CONTENT;
            if (z7 && iVar.f9164s == 0) {
                bVar.f9315a = h.FIXED;
            }
            z6 = false;
        }
        if (z7 && iVar.v(1) && iVar.f9164s == 0 && !z9) {
            bVar.f9316b = h.WRAP_CONTENT;
            if (z6 && iVar.f9162r == 0) {
                bVar.f9316b = h.FIXED;
            }
            z7 = false;
        }
        if (iVar.C()) {
            bVar.f9315a = h.FIXED;
            z6 = false;
        }
        if (iVar.D()) {
            bVar.f9316b = h.FIXED;
            z7 = false;
        }
        int[] iArr = iVar.f9166t;
        if (z8) {
            if (iArr[0] == 4) {
                bVar.f9315a = h.FIXED;
            } else if (!z7) {
                h hVar3 = bVar.f9316b;
                h hVar4 = h.FIXED;
                if (hVar3 == hVar4) {
                    i8 = bVar.f9318d;
                } else {
                    bVar.f9315a = h.WRAP_CONTENT;
                    rVar.b(iVar, bVar);
                    i8 = bVar.f9320f;
                }
                bVar.f9315a = hVar4;
                bVar.f9317c = (int) (iVar.Y * i8);
            }
        }
        if (z9) {
            if (iArr[1] == 4) {
                bVar.f9316b = h.FIXED;
            } else if (!z6) {
                h hVar5 = bVar.f9315a;
                h hVar6 = h.FIXED;
                if (hVar5 == hVar6) {
                    i7 = bVar.f9317c;
                } else {
                    bVar.f9316b = h.WRAP_CONTENT;
                    rVar.b(iVar, bVar);
                    i7 = bVar.f9319e;
                }
                bVar.f9316b = hVar6;
                if (iVar.Z == -1) {
                    bVar.f9318d = (int) (i7 / iVar.Y);
                } else {
                    bVar.f9318d = (int) (iVar.Y * i7);
                }
            }
        }
        rVar.b(iVar, bVar);
        iVar.Q(bVar.f9319e);
        iVar.N(bVar.f9320f);
        iVar.E = bVar.f9322h;
        iVar.K(bVar.f9321g);
        bVar.f9324j = 0;
    }

    @Override // u.t, u.i
    public final void E() {
        this.B0.t();
        this.C0 = 0;
        this.D0 = 0;
        super.E();
    }

    @Override // u.i
    public final void R(boolean z6, boolean z7) {
        super.R(z6, z7);
        int size = this.f9216v0.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((i) this.f9216v0.get(i7)).R(z6, z7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:206:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0690 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x06cf  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0812  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x082c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x08a9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x08c6  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x091a  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x091b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x07ee  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x092a  */
    /* JADX WARN: Removed duplicated region for block: B:589:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x0624 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:610:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x0654  */
    /* JADX WARN: Removed duplicated region for block: B:624:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x064e  */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v13 */
    @Override // u.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void T() {
        /*
            Method dump skipped, instructions count: 2360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u.j.T():void");
    }

    public final void U(int i7, i iVar) {
        if (i7 == 0) {
            int i8 = this.E0 + 1;
            c[] cVarArr = this.H0;
            if (i8 >= cVarArr.length) {
                this.H0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
            }
            c[] cVarArr2 = this.H0;
            int i9 = this.E0;
            cVarArr2[i9] = new c(iVar, 0, this.A0);
            this.E0 = i9 + 1;
            return;
        }
        if (i7 == 1) {
            int i10 = this.F0 + 1;
            c[] cVarArr3 = this.G0;
            if (i10 >= cVarArr3.length) {
                this.G0 = (c[]) Arrays.copyOf(cVarArr3, cVarArr3.length * 2);
            }
            c[] cVarArr4 = this.G0;
            int i11 = this.F0;
            cVarArr4[i11] = new c(iVar, 1, this.A0);
            this.F0 = i11 + 1;
        }
    }

    public final void V(s.d dVar) {
        boolean Y = Y(64);
        c(dVar, Y);
        int size = this.f9216v0.size();
        boolean z6 = false;
        for (int i7 = 0; i7 < size; i7++) {
            i iVar = (i) this.f9216v0.get(i7);
            boolean[] zArr = iVar.T;
            zArr[0] = false;
            zArr[1] = false;
            if (iVar instanceof a) {
                z6 = true;
            }
        }
        if (z6) {
            for (int i8 = 0; i8 < size; i8++) {
                i iVar2 = (i) this.f9216v0.get(i8);
                if (iVar2 instanceof a) {
                    a aVar = (a) iVar2;
                    for (int i9 = 0; i9 < aVar.f9211w0; i9++) {
                        i iVar3 = aVar.f9210v0[i9];
                        if (aVar.f9095y0 || iVar3.d()) {
                            int i10 = aVar.f9094x0;
                            if (i10 == 0 || i10 == 1) {
                                iVar3.T[0] = true;
                            } else if (i10 == 2 || i10 == 3) {
                                iVar3.T[1] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = this.P0;
        hashSet.clear();
        for (int i11 = 0; i11 < size; i11++) {
            i iVar4 = (i) this.f9216v0.get(i11);
            iVar4.getClass();
            boolean z7 = iVar4 instanceof s;
            if (z7 || (iVar4 instanceof n)) {
                if (z7) {
                    hashSet.add(iVar4);
                } else {
                    iVar4.c(dVar, Y);
                }
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                s sVar = (s) ((i) it.next());
                for (int i12 = 0; i12 < sVar.f9211w0; i12++) {
                    if (hashSet.contains(sVar.f9210v0[i12])) {
                        sVar.c(dVar, Y);
                        hashSet.remove(sVar);
                        break;
                    }
                }
            }
            if (size2 == hashSet.size()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((i) it2.next()).c(dVar, Y);
                }
                hashSet.clear();
            }
        }
        if (s.d.f8610p) {
            HashSet hashSet2 = new HashSet();
            for (int i13 = 0; i13 < size; i13++) {
                i iVar5 = (i) this.f9216v0.get(i13);
                iVar5.getClass();
                if (!(iVar5 instanceof s) && !(iVar5 instanceof n)) {
                    hashSet2.add(iVar5);
                }
            }
            b(this, dVar, hashSet2, this.U[0] == h.WRAP_CONTENT ? 0 : 1, false);
            Iterator it3 = hashSet2.iterator();
            while (it3.hasNext()) {
                i iVar6 = (i) it3.next();
                q.a(this, dVar, iVar6);
                iVar6.c(dVar, Y);
            }
        } else {
            for (int i14 = 0; i14 < size; i14++) {
                i iVar7 = (i) this.f9216v0.get(i14);
                if (iVar7 instanceof j) {
                    h[] hVarArr = iVar7.U;
                    h hVar = hVarArr[0];
                    h hVar2 = hVarArr[1];
                    h hVar3 = h.WRAP_CONTENT;
                    if (hVar == hVar3) {
                        iVar7.O(h.FIXED);
                    }
                    if (hVar2 == hVar3) {
                        iVar7.P(h.FIXED);
                    }
                    iVar7.c(dVar, Y);
                    if (hVar == hVar3) {
                        iVar7.O(hVar);
                    }
                    if (hVar2 == hVar3) {
                        iVar7.P(hVar2);
                    }
                } else {
                    q.a(this, dVar, iVar7);
                    if (!(iVar7 instanceof s) && !(iVar7 instanceof n)) {
                        iVar7.c(dVar, Y);
                    }
                }
            }
        }
        if (this.E0 > 0) {
            b.a(this, dVar, null, 0);
        }
        if (this.F0 > 0) {
            b.a(this, dVar, null, 1);
        }
    }

    public final boolean W(int i7, boolean z6) {
        boolean z7;
        h hVar;
        boolean z8 = true;
        boolean z9 = z6 & true;
        v.f fVar = this.f9176x0;
        j jVar = fVar.f9330a;
        h l7 = jVar.l(0);
        h l8 = jVar.l(1);
        int t6 = jVar.t();
        int u6 = jVar.u();
        ArrayList arrayList = fVar.f9334e;
        if (z9 && (l7 == (hVar = h.WRAP_CONTENT) || l8 == hVar)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                v vVar = (v) it.next();
                if (vVar.f9381f == i7 && !vVar.k()) {
                    z9 = false;
                    break;
                }
            }
            if (i7 == 0) {
                if (z9 && l7 == h.WRAP_CONTENT) {
                    jVar.O(h.FIXED);
                    jVar.Q(fVar.d(jVar, 0));
                    jVar.f9134d.f9380e.d(jVar.s());
                }
            } else if (z9 && l8 == h.WRAP_CONTENT) {
                jVar.P(h.FIXED);
                jVar.N(fVar.d(jVar, 1));
                jVar.f9136e.f9380e.d(jVar.m());
            }
        }
        if (i7 == 0) {
            h hVar2 = jVar.U[0];
            if (hVar2 == h.FIXED || hVar2 == h.MATCH_PARENT) {
                int s6 = jVar.s() + t6;
                jVar.f9134d.f9384i.d(s6);
                jVar.f9134d.f9380e.d(s6 - t6);
                z7 = true;
            }
            z7 = false;
        } else {
            h hVar3 = jVar.U[1];
            if (hVar3 == h.FIXED || hVar3 == h.MATCH_PARENT) {
                int m6 = jVar.m() + u6;
                jVar.f9136e.f9384i.d(m6);
                jVar.f9136e.f9380e.d(m6 - u6);
                z7 = true;
            }
            z7 = false;
        }
        fVar.g();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            v vVar2 = (v) it2.next();
            if (vVar2.f9381f == i7 && (vVar2.f9377b != jVar || vVar2.f9382g)) {
                vVar2.e();
            }
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            v vVar3 = (v) it3.next();
            if (vVar3.f9381f == i7 && (z7 || vVar3.f9377b != jVar)) {
                if (!vVar3.f9383h.f9348j || !vVar3.f9384i.f9348j || (!(vVar3 instanceof v.d) && !vVar3.f9380e.f9348j)) {
                    z8 = false;
                    break;
                }
            }
        }
        jVar.O(l7);
        jVar.P(l8);
        return z8;
    }

    public final boolean Y(int i7) {
        return (this.I0 & i7) == i7;
    }

    @Override // u.i
    public final void p(StringBuilder sb) {
        sb.append(this.f9146j + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.W);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.X);
        sb.append("\n");
        Iterator it = this.f9216v0.iterator();
        while (it.hasNext()) {
            ((i) it.next()).p(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
