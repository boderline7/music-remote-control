package androidx.appcompat.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1478a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e2 f1479b;

    public /* synthetic */ x1(e2 e2Var, int i7) {
        this.f1478a = i7;
        this.f1479b = e2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1478a;
        e2 e2Var = this.f1479b;
        switch (i7) {
            case 1:
                s1 s1Var = e2Var.f1214c;
                if (s1Var != null) {
                    s1Var.setListSelectionHidden(true);
                    s1Var.requestLayout();
                    break;
                }
                break;
            default:
                s1 s1Var2 = e2Var.f1214c;
                if (s1Var2 != null) {
                    int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                    if (p0.o0.b(s1Var2) && e2Var.f1214c.getCount() > e2Var.f1214c.getChildCount() && e2Var.f1214c.getChildCount() <= e2Var.f1224m) {
                        e2Var.f1237z.setInputMethodMode(2);
                        e2Var.g();
                        break;
                    }
                }
                break;
        }
    }
}
