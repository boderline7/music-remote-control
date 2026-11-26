package n1;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class w extends r {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;

    /* renamed from: z, reason: collision with root package name */
    public int f7681z;

    /* renamed from: x, reason: collision with root package name */
    public ArrayList f7679x = new ArrayList();

    /* renamed from: y, reason: collision with root package name */
    public boolean f7680y = true;
    public boolean A = false;
    public int B = 0;

    @Override // n1.r
    public final void A(l6.f fVar) {
        this.f7671s = fVar;
        this.B |= 8;
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).A(fVar);
        }
    }

    @Override // n1.r
    public final void C(p3.f fVar) {
        super.C(fVar);
        this.B |= 4;
        if (this.f7679x != null) {
            for (int i7 = 0; i7 < this.f7679x.size(); i7++) {
                ((r) this.f7679x.get(i7)).C(fVar);
            }
        }
    }

    @Override // n1.r
    public final void D() {
        this.B |= 2;
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).D();
        }
    }

    @Override // n1.r
    public final void E(long j7) {
        this.f7654b = j7;
    }

    @Override // n1.r
    public final String G(String str) {
        String G = super.G(str);
        for (int i7 = 0; i7 < this.f7679x.size(); i7++) {
            StringBuilder sb = new StringBuilder();
            sb.append(G);
            sb.append("\n");
            sb.append(((r) this.f7679x.get(i7)).G(str + "  "));
            G = sb.toString();
        }
        return G;
    }

    public final void H(r rVar) {
        this.f7679x.add(rVar);
        rVar.f7661i = this;
        long j7 = this.f7655c;
        if (j7 >= 0) {
            rVar.z(j7);
        }
        if ((this.B & 1) != 0) {
            rVar.B(this.f7656d);
        }
        if ((this.B & 2) != 0) {
            rVar.D();
        }
        if ((this.B & 4) != 0) {
            rVar.C(this.f7672t);
        }
        if ((this.B & 8) != 0) {
            rVar.A(this.f7671s);
        }
    }

    @Override // n1.r
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public final void z(long j7) {
        ArrayList arrayList;
        this.f7655c = j7;
        if (j7 < 0 || (arrayList = this.f7679x) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).z(j7);
        }
    }

    @Override // n1.r
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public final void B(TimeInterpolator timeInterpolator) {
        this.B |= 1;
        ArrayList arrayList = this.f7679x;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((r) this.f7679x.get(i7)).B(timeInterpolator);
            }
        }
        this.f7656d = timeInterpolator;
    }

    public final void K(int i7) {
        if (i7 == 0) {
            this.f7680y = true;
        } else {
            if (i7 != 1) {
                throw new AndroidRuntimeException(a.a.d("Invalid parameter for TransitionSet ordering: ", i7));
            }
            this.f7680y = false;
        }
    }

    @Override // n1.r
    public final void a(q qVar) {
        super.a(qVar);
    }

    @Override // n1.r
    public final void b(View view) {
        for (int i7 = 0; i7 < this.f7679x.size(); i7++) {
            ((r) this.f7679x.get(i7)).b(view);
        }
        this.f7658f.add(view);
    }

    @Override // n1.r
    public final void d(y yVar) {
        if (s(yVar.f7686b)) {
            Iterator it = this.f7679x.iterator();
            while (it.hasNext()) {
                r rVar = (r) it.next();
                if (rVar.s(yVar.f7686b)) {
                    rVar.d(yVar);
                    yVar.f7687c.add(rVar);
                }
            }
        }
    }

    @Override // n1.r
    public final void f(y yVar) {
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).f(yVar);
        }
    }

    @Override // n1.r
    public final void g(y yVar) {
        if (s(yVar.f7686b)) {
            Iterator it = this.f7679x.iterator();
            while (it.hasNext()) {
                r rVar = (r) it.next();
                if (rVar.s(yVar.f7686b)) {
                    rVar.g(yVar);
                    yVar.f7687c.add(rVar);
                }
            }
        }
    }

    @Override // n1.r
    /* renamed from: j */
    public final r clone() {
        w wVar = (w) super.clone();
        wVar.f7679x = new ArrayList();
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            r clone = ((r) this.f7679x.get(i7)).clone();
            wVar.f7679x.add(clone);
            clone.f7661i = wVar;
        }
        return wVar;
    }

    @Override // n1.r
    public final void l(ViewGroup viewGroup, j.h hVar, j.h hVar2, ArrayList arrayList, ArrayList arrayList2) {
        long j7 = this.f7654b;
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            r rVar = (r) this.f7679x.get(i7);
            if (j7 > 0 && (this.f7680y || i7 == 0)) {
                long j8 = rVar.f7654b;
                if (j8 > 0) {
                    rVar.E(j8 + j7);
                } else {
                    rVar.E(j7);
                }
            }
            rVar.l(viewGroup, hVar, hVar2, arrayList, arrayList2);
        }
    }

    @Override // n1.r
    public final void u(View view) {
        super.u(view);
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).u(view);
        }
    }

    @Override // n1.r
    public final void v(q qVar) {
        super.v(qVar);
    }

    @Override // n1.r
    public final void w(View view) {
        for (int i7 = 0; i7 < this.f7679x.size(); i7++) {
            ((r) this.f7679x.get(i7)).w(view);
        }
        this.f7658f.remove(view);
    }

    @Override // n1.r
    public final void x(ViewGroup viewGroup) {
        super.x(viewGroup);
        int size = this.f7679x.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) this.f7679x.get(i7)).x(viewGroup);
        }
    }

    @Override // n1.r
    public final void y() {
        if (this.f7679x.isEmpty()) {
            F();
            m();
            return;
        }
        v vVar = new v();
        vVar.f7678a = this;
        Iterator it = this.f7679x.iterator();
        while (it.hasNext()) {
            ((r) it.next()).a(vVar);
        }
        this.f7681z = this.f7679x.size();
        if (this.f7680y) {
            Iterator it2 = this.f7679x.iterator();
            while (it2.hasNext()) {
                ((r) it2.next()).y();
            }
            return;
        }
        for (int i7 = 1; i7 < this.f7679x.size(); i7++) {
            ((r) this.f7679x.get(i7 - 1)).a(new g(2, this, (r) this.f7679x.get(i7)));
        }
        r rVar = (r) this.f7679x.get(0);
        if (rVar != null) {
            rVar.y();
        }
    }
}
