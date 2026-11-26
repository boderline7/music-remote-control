package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b1 implements a1, k1 {

    /* renamed from: a, reason: collision with root package name */
    public final Object f2385a;

    public b1(d dVar) {
        this.f2385a = dVar;
    }

    public /* synthetic */ b1(Object obj) {
        this.f2385a = obj;
    }

    @Override // androidx.recyclerview.widget.a1
    public final void a(int i7, int i8) {
        ((h1) this.f2385a).notifyItemRangeRemoved(i7, i8);
    }

    @Override // androidx.recyclerview.widget.a1
    public final void b(int i7, int i8) {
        ((h1) this.f2385a).notifyItemRangeInserted(i7, i8);
    }

    @Override // androidx.recyclerview.widget.a1
    public final void c(int i7, int i8) {
        ((h1) this.f2385a).notifyItemMoved(i7, i8);
    }

    @Override // androidx.recyclerview.widget.a1
    public final void d(int i7, int i8, Object obj) {
        ((h1) this.f2385a).notifyItemRangeChanged(i7, i8, obj);
    }

    public final boolean e(int i7, int i8) {
        Object obj = this.f2385a;
        Object obj2 = ((d) obj).f2394a.get(i7);
        Object obj3 = ((d) obj).f2395b.get(i8);
        if (obj2 != null && obj3 != null) {
            return ((t) ((d) obj).f2398e.f2451b.f2500d).areContentsTheSame(obj2, obj3);
        }
        if (obj2 == null && obj3 == null) {
            return true;
        }
        throw new AssertionError();
    }

    public final boolean f(int i7, int i8) {
        Object obj = this.f2385a;
        Object obj2 = ((d) obj).f2394a.get(i7);
        Object obj3 = ((d) obj).f2395b.get(i8);
        return (obj2 == null || obj3 == null) ? obj2 == null && obj3 == null : ((t) ((d) obj).f2398e.f2451b.f2500d).areItemsTheSame(obj2, obj3);
    }

    public final Object g(int i7, int i8) {
        Object obj = this.f2385a;
        Object obj2 = ((d) obj).f2394a.get(i7);
        Object obj3 = ((d) obj).f2395b.get(i8);
        if (obj2 == null || obj3 == null) {
            throw new AssertionError();
        }
        return ((t) ((d) obj).f2398e.f2451b.f2500d).getChangePayload(obj2, obj3);
    }
}
