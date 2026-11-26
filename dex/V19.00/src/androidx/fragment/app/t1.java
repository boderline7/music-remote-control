package androidx.fragment.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2056a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ v1 f2057b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ y1 f2058c;

    public /* synthetic */ t1(y1 y1Var, v1 v1Var, int i7) {
        this.f2056a = i7;
        this.f2058c = y1Var;
        this.f2057b = v1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f2056a;
        v1 v1Var = this.f2057b;
        y1 y1Var = this.f2058c;
        switch (i7) {
            case 0:
                if (y1Var.f2130b.contains(v1Var)) {
                    v1Var.f2070a.a(v1Var.f2072c.mView);
                    break;
                }
                break;
            default:
                y1Var.f2130b.remove(v1Var);
                y1Var.f2131c.remove(v1Var);
                break;
        }
    }
}
