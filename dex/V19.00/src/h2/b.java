package h2;

import java.util.Iterator;
import k2.g;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f6703a;

    public b(d dVar) {
        this.f6703a = dVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        d dVar = this.f6703a;
        dVar.f6716j = dVar.D();
        return dVar.f6716j != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        d dVar = this.f6703a;
        g gVar = dVar.f6716j;
        return gVar != null ? gVar : dVar.D();
    }
}
