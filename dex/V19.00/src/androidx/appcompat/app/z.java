package androidx.appcompat.app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f887d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f888e;

    public /* synthetic */ z(int i7, Object obj) {
        this.f887d = i7;
        this.f888e = obj;
    }

    @Override // p0.m1
    public final void a() {
        int i7 = this.f887d;
        Object obj = this.f888e;
        switch (i7) {
            case 0:
                x xVar = (x) obj;
                xVar.f880b.f789v.setAlpha(1.0f);
                j0 j0Var = xVar.f880b;
                j0Var.f792y.d(null);
                j0Var.f792y = null;
                break;
            case 1:
                j0 j0Var2 = (j0) obj;
                j0Var2.f789v.setAlpha(1.0f);
                j0Var2.f792y.d(null);
                j0Var2.f792y = null;
                break;
            default:
                a0 a0Var = (a0) obj;
                a0Var.f672b.f789v.setVisibility(8);
                j0 j0Var3 = a0Var.f672b;
                PopupWindow popupWindow = j0Var3.f790w;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (j0Var3.f789v.getParent() instanceof View) {
                    View view = (View) j0Var3.f789v.getParent();
                    int i8 = d1.OVER_SCROLL_ALWAYS;
                    p0.p0.c(view);
                }
                j0Var3.f789v.e();
                j0Var3.f792y.d(null);
                j0Var3.f792y = null;
                ViewGroup viewGroup = j0Var3.A;
                int i9 = d1.OVER_SCROLL_ALWAYS;
                p0.p0.c(viewGroup);
                break;
        }
    }

    @Override // l6.f, p0.m1
    public final void c() {
        int i7 = this.f887d;
        Object obj = this.f888e;
        switch (i7) {
            case 0:
                ((x) obj).f880b.f789v.setVisibility(0);
                break;
            case 1:
                j0 j0Var = (j0) obj;
                j0Var.f789v.setVisibility(0);
                if (j0Var.f789v.getParent() instanceof View) {
                    View view = (View) j0Var.f789v.getParent();
                    int i8 = d1.OVER_SCROLL_ALWAYS;
                    p0.p0.c(view);
                    break;
                }
                break;
        }
    }
}
