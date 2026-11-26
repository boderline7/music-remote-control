package r;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f8493d;

    /* renamed from: e, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f8494e;

    /* renamed from: f, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f8495f;

    /* renamed from: g, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f8496g;

    /* renamed from: h, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f8497h;

    public d(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f8493d = atomicReferenceFieldUpdater;
        this.f8494e = atomicReferenceFieldUpdater2;
        this.f8495f = atomicReferenceFieldUpdater3;
        this.f8496g = atomicReferenceFieldUpdater4;
        this.f8497h = atomicReferenceFieldUpdater5;
    }

    @Override // l6.f
    public final boolean j(f fVar, c cVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        c cVar2 = c.f8491b;
        do {
            atomicReferenceFieldUpdater = this.f8496g;
            if (atomicReferenceFieldUpdater.compareAndSet(fVar, cVar, cVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(fVar) == cVar);
        return false;
    }

    @Override // l6.f
    public final boolean k(f fVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f8497h;
            if (atomicReferenceFieldUpdater.compareAndSet(fVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(fVar) == obj);
        return false;
    }

    @Override // l6.f
    public final boolean l(f fVar, e eVar, e eVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f8495f;
            if (atomicReferenceFieldUpdater.compareAndSet(fVar, eVar, eVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(fVar) == eVar);
        return false;
    }

    @Override // l6.f
    public final void m0(e eVar, e eVar2) {
        this.f8494e.lazySet(eVar, eVar2);
    }

    @Override // l6.f
    public final void n0(e eVar, Thread thread) {
        this.f8493d.lazySet(eVar, thread);
    }
}
