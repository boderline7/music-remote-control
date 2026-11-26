package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m2 extends w1 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2544a = false;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f1 f2545b;

    public m2(f1 f1Var) {
        this.f2545b = f1Var;
    }

    @Override // androidx.recyclerview.widget.w1
    public final void a(int i7, RecyclerView recyclerView) {
        if (i7 == 0 && this.f2544a) {
            this.f2544a = false;
            this.f2545b.h();
        }
    }

    @Override // androidx.recyclerview.widget.w1
    public final void b(RecyclerView recyclerView, int i7, int i8) {
        if (i7 == 0 && i8 == 0) {
            return;
        }
        this.f2544a = true;
    }
}
