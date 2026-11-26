package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m0 implements View.OnAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2014a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d1 f2015b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2016c;

    public m0(d1 d1Var, View view) {
        this.f2015b = d1Var;
        this.f2016c = view;
    }

    public m0(n0 n0Var, d1 d1Var) {
        this.f2016c = n0Var;
        this.f2015b = d1Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        int i7 = this.f2014a;
        Object obj = this.f2016c;
        switch (i7) {
            case 0:
                d1 d1Var = this.f2015b;
                z zVar = d1Var.f1933c;
                d1Var.j();
                y1.g((ViewGroup) zVar.mView.getParent(), ((n0) obj).f2025a).e();
                break;
            default:
                View view2 = (View) obj;
                view2.removeOnAttachStateChangeListener(this);
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                p0.p0.c(view2);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
