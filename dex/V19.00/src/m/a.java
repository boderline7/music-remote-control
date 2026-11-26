package m;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends g {

    /* renamed from: e, reason: collision with root package name */
    public final HashMap f7471e = new HashMap();

    @Override // m.g
    public final c a(Object obj) {
        return (c) this.f7471e.get(obj);
    }

    @Override // m.g
    public final Object b(Object obj) {
        Object b7 = super.b(obj);
        this.f7471e.remove(obj);
        return b7;
    }

    public final Object c(Object obj, Object obj2) {
        c a7 = a(obj);
        if (a7 != null) {
            return a7.f7474b;
        }
        HashMap hashMap = this.f7471e;
        c cVar = new c(obj, obj2);
        this.f7485d++;
        c cVar2 = this.f7483b;
        if (cVar2 == null) {
            this.f7482a = cVar;
            this.f7483b = cVar;
        } else {
            cVar2.f7475c = cVar;
            cVar.f7476d = cVar2;
            this.f7483b = cVar;
        }
        hashMap.put(obj, cVar);
        return null;
    }
}
