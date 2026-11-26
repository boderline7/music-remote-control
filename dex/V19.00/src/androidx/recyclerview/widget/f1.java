package androidx.recyclerview.widget;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class f1 extends u1 {

    /* renamed from: a, reason: collision with root package name */
    public RecyclerView f2445a;

    /* renamed from: b, reason: collision with root package name */
    public final m2 f2446b = new m2(this);

    /* renamed from: c, reason: collision with root package name */
    public c1 f2447c;

    /* renamed from: d, reason: collision with root package name */
    public c1 f2448d;

    public static int c(View view, d1 d1Var) {
        return ((d1Var.c(view) / 2) + d1Var.d(view)) - ((d1Var.g() / 2) + d1Var.f());
    }

    public static View d(r1 r1Var, d1 d1Var) {
        int w6 = r1Var.w();
        View view = null;
        if (w6 == 0) {
            return null;
        }
        int g7 = (d1Var.g() / 2) + d1Var.f();
        int i7 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i8 = 0; i8 < w6; i8++) {
            View v6 = r1Var.v(i8);
            int abs = Math.abs(((d1Var.c(v6) / 2) + d1Var.d(v6)) - g7);
            if (abs < i7) {
                view = v6;
                i7 = abs;
            }
        }
        return view;
    }

    public final void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2445a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        m2 m2Var = this.f2446b;
        if (recyclerView2 != null) {
            ArrayList arrayList = recyclerView2.f2312h0;
            if (arrayList != null) {
                arrayList.remove(m2Var);
            }
            this.f2445a.setOnFlingListener(null);
        }
        this.f2445a = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.getOnFlingListener() != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            this.f2445a.h(m2Var);
            this.f2445a.setOnFlingListener(this);
            new Scroller(this.f2445a.getContext(), new DecelerateInterpolator());
            h();
        }
    }

    public final int[] b(r1 r1Var, View view) {
        int[] iArr = new int[2];
        if (r1Var.e()) {
            iArr[0] = c(view, f(r1Var));
        } else {
            iArr[0] = 0;
        }
        if (r1Var.f()) {
            iArr[1] = c(view, g(r1Var));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public View e(r1 r1Var) {
        if (r1Var.f()) {
            return d(r1Var, g(r1Var));
        }
        if (r1Var.e()) {
            return d(r1Var, f(r1Var));
        }
        return null;
    }

    public final d1 f(r1 r1Var) {
        c1 c1Var = this.f2448d;
        if (c1Var == null || c1Var.f2425a != r1Var) {
            this.f2448d = new c1(r1Var, 0);
        }
        return this.f2448d;
    }

    public final d1 g(r1 r1Var) {
        c1 c1Var = this.f2447c;
        if (c1Var == null || c1Var.f2425a != r1Var) {
            this.f2447c = new c1(r1Var, 1);
        }
        return this.f2447c;
    }

    public final void h() {
        r1 layoutManager;
        View e7;
        RecyclerView recyclerView = this.f2445a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (e7 = e(layoutManager)) == null) {
            return;
        }
        int[] b7 = b(layoutManager, e7);
        int i7 = b7[0];
        if (i7 == 0 && b7[1] == 0) {
            return;
        }
        this.f2445a.f0(i7, b7[1], false);
    }
}
