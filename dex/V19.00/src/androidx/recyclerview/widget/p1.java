package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p1 implements u2 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2597a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r1 f2598b;

    public /* synthetic */ p1(r1 r1Var, int i7) {
        this.f2597a = i7;
        this.f2598b = r1Var;
    }

    public final int a(View view) {
        int i7 = this.f2597a;
        r1 r1Var = this.f2598b;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.C(view) + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.z(view) + ((ViewGroup.MarginLayoutParams) s1Var2).bottomMargin;
        }
    }

    public final int b(View view) {
        int i7 = this.f2597a;
        r1 r1Var = this.f2598b;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.B(view) - ((ViewGroup.MarginLayoutParams) s1Var).leftMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.D(view) - ((ViewGroup.MarginLayoutParams) s1Var2).topMargin;
        }
    }
}
