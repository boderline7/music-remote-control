package androidx.fragment.app;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a1 extends androidx.lifecycle.e1 {

    /* renamed from: g, reason: collision with root package name */
    public static final z0 f1914g = new z0();

    /* renamed from: d, reason: collision with root package name */
    public final boolean f1918d;

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f1915a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1916b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f1917c = new HashMap();

    /* renamed from: e, reason: collision with root package name */
    public boolean f1919e = false;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1920f = false;

    public a1(boolean z6) {
        this.f1918d = z6;
    }

    public final void b(z zVar) {
        if (this.f1920f) {
            Log.isLoggable("FragmentManager", 2);
            return;
        }
        HashMap hashMap = this.f1915a;
        if (hashMap.containsKey(zVar.mWho)) {
            return;
        }
        hashMap.put(zVar.mWho, zVar);
        if (Log.isLoggable("FragmentManager", 2)) {
            zVar.toString();
        }
    }

    public final void c(z zVar) {
        if (this.f1920f) {
            Log.isLoggable("FragmentManager", 2);
        } else {
            if (this.f1915a.remove(zVar.mWho) == null || !Log.isLoggable("FragmentManager", 2)) {
                return;
            }
            zVar.toString();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a1.class != obj.getClass()) {
            return false;
        }
        a1 a1Var = (a1) obj;
        return this.f1915a.equals(a1Var.f1915a) && this.f1916b.equals(a1Var.f1916b) && this.f1917c.equals(a1Var.f1917c);
    }

    public final int hashCode() {
        return this.f1917c.hashCode() + ((this.f1916b.hashCode() + (this.f1915a.hashCode() * 31)) * 31);
    }

    @Override // androidx.lifecycle.e1
    public final void onCleared() {
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        this.f1919e = true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f1915a.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f1916b.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f1917c.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
