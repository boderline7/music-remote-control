package n1;

import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import p0.d1;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class u {

    /* renamed from: a, reason: collision with root package name */
    public static final a f7675a = new a();

    /* renamed from: b, reason: collision with root package name */
    public static final ThreadLocal f7676b = new ThreadLocal();

    /* renamed from: c, reason: collision with root package name */
    public static final ArrayList f7677c = new ArrayList();

    public static void a(ViewGroup viewGroup, r rVar) {
        ArrayList arrayList = f7677c;
        if (arrayList.contains(viewGroup)) {
            return;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (o0.c(viewGroup)) {
            arrayList.add(viewGroup);
            if (rVar == null) {
                rVar = f7675a;
            }
            r clone = rVar.clone();
            ArrayList arrayList2 = (ArrayList) b().getOrDefault(viewGroup, null);
            if (arrayList2 != null && arrayList2.size() > 0) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((r) it.next()).u(viewGroup);
                }
            }
            if (clone != null) {
                clone.h(viewGroup, true);
            }
            a.a.u(viewGroup.getTag(m.transition_current_scene));
            viewGroup.setTag(m.transition_current_scene, null);
            if (clone != null) {
                t tVar = new t();
                tVar.f7673a = clone;
                tVar.f7674b = viewGroup;
                viewGroup.addOnAttachStateChangeListener(tVar);
                viewGroup.getViewTreeObserver().addOnPreDrawListener(tVar);
            }
        }
    }

    public static q.b b() {
        q.b bVar;
        ThreadLocal threadLocal = f7676b;
        WeakReference weakReference = (WeakReference) threadLocal.get();
        if (weakReference != null && (bVar = (q.b) weakReference.get()) != null) {
            return bVar;
        }
        q.b bVar2 = new q.b();
        threadLocal.set(new WeakReference(bVar2));
        return bVar2;
    }
}
