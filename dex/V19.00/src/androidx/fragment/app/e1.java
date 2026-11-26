package androidx.fragment.app;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e1 {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1940a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1941b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public a1 f1942c;

    public final void a(z zVar) {
        if (this.f1940a.contains(zVar)) {
            throw new IllegalStateException("Fragment already added: " + zVar);
        }
        synchronized (this.f1940a) {
            this.f1940a.add(zVar);
        }
        zVar.mAdded = true;
    }

    public final z b(String str) {
        d1 d1Var = (d1) this.f1941b.get(str);
        if (d1Var != null) {
            return d1Var.f1933c;
        }
        return null;
    }

    public final z c(String str) {
        z findFragmentByWho;
        for (d1 d1Var : this.f1941b.values()) {
            if (d1Var != null && (findFragmentByWho = d1Var.f1933c.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public final ArrayList d() {
        ArrayList arrayList = new ArrayList();
        for (d1 d1Var : this.f1941b.values()) {
            if (d1Var != null) {
                arrayList.add(d1Var);
            }
        }
        return arrayList;
    }

    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        for (d1 d1Var : this.f1941b.values()) {
            if (d1Var != null) {
                arrayList.add(d1Var.f1933c);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public final List f() {
        ArrayList arrayList;
        if (this.f1940a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1940a) {
            arrayList = new ArrayList(this.f1940a);
        }
        return arrayList;
    }

    public final void g(d1 d1Var) {
        z zVar = d1Var.f1933c;
        String str = zVar.mWho;
        HashMap hashMap = this.f1941b;
        if (hashMap.get(str) != null) {
            return;
        }
        hashMap.put(zVar.mWho, d1Var);
        if (zVar.mRetainInstanceChangedWhileDetached) {
            if (zVar.mRetainInstance) {
                this.f1942c.b(zVar);
            } else {
                this.f1942c.c(zVar);
            }
            zVar.mRetainInstanceChangedWhileDetached = false;
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            zVar.toString();
        }
    }

    public final void h(d1 d1Var) {
        z zVar = d1Var.f1933c;
        if (zVar.mRetainInstance) {
            this.f1942c.c(zVar);
        }
        if (((d1) this.f1941b.put(zVar.mWho, null)) != null && Log.isLoggable("FragmentManager", 2)) {
            zVar.toString();
        }
    }
}
