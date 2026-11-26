package e1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends c {
    public d(c cVar) {
        i5.a.g("initialExtras", cVar);
        this.f6345a.putAll(cVar.f6345a);
    }

    public final Object a(b bVar) {
        i5.a.g("key", bVar);
        return this.f6345a.get(bVar);
    }

    public final void b(b bVar, Object obj) {
        i5.a.g("key", bVar);
        this.f6345a.put(bVar, obj);
    }
}
