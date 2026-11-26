package w2;

import android.view.View;
import java.util.Iterator;
import java.util.List;
import p0.l2;
import p0.q1;
import p0.y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends q1 {

    /* renamed from: c, reason: collision with root package name */
    public final View f9463c;

    /* renamed from: d, reason: collision with root package name */
    public int f9464d;

    /* renamed from: e, reason: collision with root package name */
    public int f9465e;

    /* renamed from: f, reason: collision with root package name */
    public final int[] f9466f = new int[2];

    public d(View view) {
        this.f9463c = view;
    }

    @Override // p0.q1
    public final l2 a(l2 l2Var, List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if ((((y1) it.next()).f8207a.c() & 8) != 0) {
                this.f9463c.setTranslationY(r2.a.c(this.f9465e, r0.f8207a.b(), 0));
                break;
            }
        }
        return l2Var;
    }
}
