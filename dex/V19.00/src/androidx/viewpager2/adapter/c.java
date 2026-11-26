package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import androidx.lifecycle.x;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Handler f2856a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f2857b;

    public c(Handler handler, b bVar) {
        this.f2856a = handler;
        this.f2857b = bVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        if (qVar == q.ON_DESTROY) {
            this.f2856a.removeCallbacks(this.f2857b);
            xVar.getLifecycle().b(this);
        }
    }
}
