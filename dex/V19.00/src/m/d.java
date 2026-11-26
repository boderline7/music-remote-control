package m;

import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends f implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public c f7477a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f7478b = true;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g f7479c;

    public d(g gVar) {
        this.f7479c = gVar;
    }

    @Override // m.f
    public final void a(c cVar) {
        c cVar2 = this.f7477a;
        if (cVar == cVar2) {
            c cVar3 = cVar2.f7476d;
            this.f7477a = cVar3;
            this.f7478b = cVar3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f7478b) {
            return this.f7479c.f7482a != null;
        }
        c cVar = this.f7477a;
        return (cVar == null || cVar.f7475c == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f7478b) {
            this.f7478b = false;
            this.f7477a = this.f7479c.f7482a;
        } else {
            c cVar = this.f7477a;
            this.f7477a = cVar != null ? cVar.f7475c : null;
        }
        return this.f7477a;
    }
}
