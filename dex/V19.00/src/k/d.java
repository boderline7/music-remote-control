package k;

import android.content.Context;
import android.view.MenuItem;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {

    /* renamed from: a, reason: collision with root package name */
    public Object f7060a;

    /* renamed from: b, reason: collision with root package name */
    public Object f7061b;

    /* renamed from: c, reason: collision with root package name */
    public Object f7062c;

    public d(int i7) {
        this.f7061b = new float[i7 * 2];
        this.f7062c = new int[i7];
    }

    public d(Context context) {
        this.f7060a = context;
    }

    public static boolean m(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static boolean r(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public abstract void c();

    public abstract void d();

    public abstract Object e(int i7, int i8);

    public abstract q.b f();

    public abstract int g();

    public abstract int h(Object obj);

    public abstract int i(Object obj);

    public abstract void j(Object obj, Object obj2);

    public abstract void k(int i7);

    public abstract Object l(int i7, Object obj);

    public final MenuItem n(MenuItem menuItem) {
        if (!(menuItem instanceof k0.b)) {
            return menuItem;
        }
        k0.b bVar = (k0.b) menuItem;
        if (((q.k) this.f7061b) == null) {
            this.f7061b = new q.k();
        }
        MenuItem menuItem2 = (MenuItem) ((q.k) this.f7061b).getOrDefault(bVar, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        w wVar = new w((Context) this.f7060a, bVar);
        ((q.k) this.f7061b).put(bVar, wVar);
        return wVar;
    }

    public abstract void o();

    public abstract void p(l3.c cVar);

    public abstract void q();

    public abstract void s();

    public final Object[] t(int i7, Object[] objArr) {
        int g7 = g();
        if (objArr.length < g7) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), g7);
        }
        for (int i8 = 0; i8 < g7; i8++) {
            objArr[i8] = e(i8, i7);
        }
        if (objArr.length > g7) {
            objArr[g7] = null;
        }
        return objArr;
    }

    public abstract void u();
}
