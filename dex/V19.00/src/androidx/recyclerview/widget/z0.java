package androidx.recyclerview.widget;

import java.util.List;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class z0 extends h1 {
    final g mDiffer;
    private final e mListener;

    public z0(t tVar) {
        y0 y0Var = new y0(this);
        this.mListener = y0Var;
        b1 b1Var = new b1(this);
        c cVar = new c();
        if (cVar.f2390a == null) {
            synchronized (c.f2388b) {
                try {
                    if (c.f2389c == null) {
                        c.f2389c = Executors.newFixedThreadPool(2);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            cVar.f2390a = c.f2389c;
        }
        g gVar = new g(b1Var, new j(cVar.f2390a, tVar));
        this.mDiffer = gVar;
        gVar.f2453d.add(y0Var);
    }

    public List<Object> getCurrentList() {
        return this.mDiffer.f2455f;
    }

    public Object getItem(int i7) {
        return this.mDiffer.f2455f.get(i7);
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.mDiffer.f2455f.size();
    }

    public void onCurrentListChanged(List<Object> list, List<Object> list2) {
    }

    public void submitList(List<Object> list) {
        this.mDiffer.b(list, null);
    }

    public void submitList(List<Object> list, Runnable runnable) {
        this.mDiffer.b(list, runnable);
    }
}
