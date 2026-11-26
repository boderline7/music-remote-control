package v;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends v {

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f9328k;

    /* renamed from: l, reason: collision with root package name */
    public int f9329l;

    public d(int i7, u.i iVar) {
        super(iVar);
        u.i iVar2;
        this.f9328k = new ArrayList();
        this.f9381f = i7;
        u.i iVar3 = this.f9377b;
        u.i o6 = iVar3.o(i7);
        while (true) {
            iVar2 = iVar3;
            iVar3 = o6;
            if (iVar3 == null) {
                break;
            } else {
                o6 = iVar3.o(this.f9381f);
            }
        }
        this.f9377b = iVar2;
        int i8 = this.f9381f;
        Object obj = i8 == 0 ? iVar2.f9134d : i8 == 1 ? iVar2.f9136e : null;
        ArrayList arrayList = this.f9328k;
        arrayList.add(obj);
        u.i n6 = iVar2.n(this.f9381f);
        while (n6 != null) {
            int i9 = this.f9381f;
            arrayList.add(i9 == 0 ? n6.f9134d : i9 == 1 ? n6.f9136e : null);
            n6 = n6.n(this.f9381f);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            v vVar = (v) it.next();
            int i10 = this.f9381f;
            if (i10 == 0) {
                vVar.f9377b.f9130b = this;
            } else if (i10 == 1) {
                vVar.f9377b.f9132c = this;
            }
        }
        if (this.f9381f == 0 && ((u.j) this.f9377b.V).A0 && arrayList.size() > 1) {
            this.f9377b = ((v) arrayList.get(arrayList.size() - 1)).f9377b;
        }
        this.f9329l = this.f9381f == 0 ? this.f9377b.f9153m0 : this.f9377b.f9155n0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:291:0x03ae, code lost:
    
        r2 = r2 - r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00db  */
    @Override // v.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(v.e r27) {
        /*
            Method dump skipped, instructions count: 973
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v.d.a(v.e):void");
    }

    @Override // v.v
    public final void d() {
        ArrayList arrayList = this.f9328k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((v) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        u.i iVar = ((v) arrayList.get(0)).f9377b;
        u.i iVar2 = ((v) arrayList.get(size - 1)).f9377b;
        int i7 = this.f9381f;
        h hVar = this.f9384i;
        h hVar2 = this.f9383h;
        if (i7 == 0) {
            u.f fVar = iVar.J;
            u.f fVar2 = iVar2.L;
            h i8 = v.i(fVar, 0);
            int e7 = fVar.e();
            u.i m6 = m();
            if (m6 != null) {
                e7 = m6.J.e();
            }
            if (i8 != null) {
                v.b(hVar2, i8, e7);
            }
            h i9 = v.i(fVar2, 0);
            int e8 = fVar2.e();
            u.i n6 = n();
            if (n6 != null) {
                e8 = n6.L.e();
            }
            if (i9 != null) {
                v.b(hVar, i9, -e8);
            }
        } else {
            u.f fVar3 = iVar.K;
            u.f fVar4 = iVar2.M;
            h i10 = v.i(fVar3, 1);
            int e9 = fVar3.e();
            u.i m7 = m();
            if (m7 != null) {
                e9 = m7.K.e();
            }
            if (i10 != null) {
                v.b(hVar2, i10, e9);
            }
            h i11 = v.i(fVar4, 1);
            int e10 = fVar4.e();
            u.i n7 = n();
            if (n7 != null) {
                e10 = n7.M.e();
            }
            if (i11 != null) {
                v.b(hVar, i11, -e10);
            }
        }
        hVar2.f9339a = this;
        hVar.f9339a = this;
    }

    @Override // v.v
    public final void e() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f9328k;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((v) arrayList.get(i7)).e();
            i7++;
        }
    }

    @Override // v.v
    public final void f() {
        this.f9378c = null;
        Iterator it = this.f9328k.iterator();
        while (it.hasNext()) {
            ((v) it.next()).f();
        }
    }

    @Override // v.v
    public final long j() {
        ArrayList arrayList = this.f9328k;
        int size = arrayList.size();
        long j7 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            j7 = r5.f9384i.f9344f + ((v) arrayList.get(i7)).j() + j7 + r5.f9383h.f9344f;
        }
        return j7;
    }

    @Override // v.v
    public final boolean k() {
        ArrayList arrayList = this.f9328k;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!((v) arrayList.get(i7)).k()) {
                return false;
            }
        }
        return true;
    }

    public final u.i m() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f9328k;
            if (i7 >= arrayList.size()) {
                return null;
            }
            u.i iVar = ((v) arrayList.get(i7)).f9377b;
            if (iVar.f9145i0 != 8) {
                return iVar;
            }
            i7++;
        }
    }

    public final u.i n() {
        ArrayList arrayList = this.f9328k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            u.i iVar = ((v) arrayList.get(size)).f9377b;
            if (iVar.f9145i0 != 8) {
                return iVar;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f9381f == 0 ? "horizontal : " : "vertical : ");
        Iterator it = this.f9328k.iterator();
        while (it.hasNext()) {
            v vVar = (v) it.next();
            sb.append("<");
            sb.append(vVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}
