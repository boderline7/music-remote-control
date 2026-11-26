package androidx.recyclerview.widget;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g {

    /* renamed from: h, reason: collision with root package name */
    public static final f f2449h = new f();

    /* renamed from: a, reason: collision with root package name */
    public final a1 f2450a;

    /* renamed from: b, reason: collision with root package name */
    public final j f2451b;

    /* renamed from: c, reason: collision with root package name */
    public final Executor f2452c;

    /* renamed from: e, reason: collision with root package name */
    public List f2454e;

    /* renamed from: g, reason: collision with root package name */
    public int f2456g;

    /* renamed from: d, reason: collision with root package name */
    public final CopyOnWriteArrayList f2453d = new CopyOnWriteArrayList();

    /* renamed from: f, reason: collision with root package name */
    public List f2455f = Collections.emptyList();

    public g(b1 b1Var, j jVar) {
        this.f2450a = b1Var;
        this.f2451b = jVar;
        Executor executor = (Executor) jVar.f2498b;
        if (executor != null) {
            this.f2452c = executor;
        } else {
            this.f2452c = f2449h;
        }
    }

    public final void a(List list, Runnable runnable) {
        Iterator it = this.f2453d.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            ((y0) eVar).f2713a.onCurrentListChanged(list, this.f2455f);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void b(List list, Runnable runnable) {
        int i7 = this.f2456g + 1;
        this.f2456g = i7;
        List list2 = this.f2454e;
        if (list == list2) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        List list3 = this.f2455f;
        a1 a1Var = this.f2450a;
        if (list == null) {
            int size = list2.size();
            this.f2454e = null;
            this.f2455f = Collections.emptyList();
            a1Var.a(0, size);
            a(list3, runnable);
            return;
        }
        if (list2 != null) {
            ((Executor) this.f2451b.f2499c).execute(new d(this, list2, list, i7, runnable));
            return;
        }
        this.f2454e = list;
        this.f2455f = Collections.unmodifiableList(list);
        a1Var.b(0, list.size());
        a(list3, runnable);
    }
}
