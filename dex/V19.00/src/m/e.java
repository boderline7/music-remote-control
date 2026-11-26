package m;

import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e extends f implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public c f7480a;

    /* renamed from: b, reason: collision with root package name */
    public c f7481b;

    @Override // m.f
    public final void a(c cVar) {
        c cVar2;
        c cVar3;
        c cVar4 = null;
        if (this.f7480a == cVar && cVar == this.f7481b) {
            this.f7481b = null;
            this.f7480a = null;
        }
        c cVar5 = this.f7480a;
        if (cVar5 == cVar) {
            switch (((b) this).f7472c) {
                case 0:
                    cVar3 = cVar5.f7476d;
                    break;
                default:
                    cVar3 = cVar5.f7475c;
                    break;
            }
            this.f7480a = cVar3;
        }
        c cVar6 = this.f7481b;
        if (cVar6 == cVar) {
            c cVar7 = this.f7480a;
            if (cVar6 != cVar7 && cVar7 != null) {
                switch (((b) this).f7472c) {
                    case 0:
                        cVar2 = cVar6.f7475c;
                        break;
                    default:
                        cVar2 = cVar6.f7476d;
                        break;
                }
                cVar4 = cVar2;
            }
            this.f7481b = cVar4;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f7481b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c cVar;
        c cVar2 = this.f7481b;
        c cVar3 = this.f7480a;
        if (cVar2 != cVar3 && cVar3 != null) {
            switch (((b) this).f7472c) {
                case 0:
                    cVar = cVar2.f7475c;
                    break;
                default:
                    cVar = cVar2.f7476d;
                    break;
            }
        } else {
            cVar = null;
        }
        this.f7481b = cVar;
        return cVar2;
    }
}
