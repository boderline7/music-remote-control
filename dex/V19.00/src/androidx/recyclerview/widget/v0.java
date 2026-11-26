package androidx.recyclerview.widget;

import android.view.View;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v0 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2683a;

    /* renamed from: b, reason: collision with root package name */
    public int f2684b;

    /* renamed from: c, reason: collision with root package name */
    public int f2685c;

    /* renamed from: d, reason: collision with root package name */
    public int f2686d;

    /* renamed from: e, reason: collision with root package name */
    public int f2687e;

    /* renamed from: f, reason: collision with root package name */
    public int f2688f;

    /* renamed from: g, reason: collision with root package name */
    public int f2689g;

    /* renamed from: h, reason: collision with root package name */
    public int f2690h;

    /* renamed from: i, reason: collision with root package name */
    public int f2691i;

    /* renamed from: j, reason: collision with root package name */
    public int f2692j;

    /* renamed from: k, reason: collision with root package name */
    public List f2693k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2694l;

    public final void a(View view) {
        int layoutPosition;
        int size = this.f2693k.size();
        View view2 = null;
        int i7 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i8 = 0; i8 < size; i8++) {
            View view3 = ((j2) this.f2693k.get(i8)).itemView;
            s1 s1Var = (s1) view3.getLayoutParams();
            if (view3 != view && !s1Var.f2652a.isRemoved() && (layoutPosition = (s1Var.f2652a.getLayoutPosition() - this.f2686d) * this.f2687e) >= 0 && layoutPosition < i7) {
                view2 = view3;
                if (layoutPosition == 0) {
                    break;
                } else {
                    i7 = layoutPosition;
                }
            }
        }
        if (view2 == null) {
            this.f2686d = -1;
        } else {
            this.f2686d = ((s1) view2.getLayoutParams()).f2652a.getLayoutPosition();
        }
    }

    public final View b(z1 z1Var) {
        List list = this.f2693k;
        if (list == null) {
            View view = z1Var.i(this.f2686d, Long.MAX_VALUE).itemView;
            this.f2686d += this.f2687e;
            return view;
        }
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view2 = ((j2) this.f2693k.get(i7)).itemView;
            s1 s1Var = (s1) view2.getLayoutParams();
            if (!s1Var.f2652a.isRemoved() && this.f2686d == s1Var.f2652a.getLayoutPosition()) {
                a(view2);
                return view2;
            }
        }
        return null;
    }
}
