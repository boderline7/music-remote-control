package j1;

import android.os.Bundle;
import androidx.appcompat.app.p;
import androidx.lifecycle.l;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: b, reason: collision with root package name */
    public boolean f6944b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f6945c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6946d;

    /* renamed from: e, reason: collision with root package name */
    public p f6947e;

    /* renamed from: a, reason: collision with root package name */
    public final m.g f6943a = new m.g();

    /* renamed from: f, reason: collision with root package name */
    public boolean f6948f = true;

    public final Bundle a(String str) {
        if (!this.f6946d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
        }
        Bundle bundle = this.f6945c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = this.f6945c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.f6945c;
        if (bundle4 == null || bundle4.isEmpty()) {
            this.f6945c = null;
        }
        return bundle2;
    }

    public final f b() {
        String str;
        f fVar;
        Iterator it = this.f6943a.iterator();
        do {
            m.e eVar = (m.e) it;
            if (!eVar.hasNext()) {
                return null;
            }
            Map.Entry entry = (Map.Entry) eVar.next();
            i5.a.f("components", entry);
            str = (String) entry.getKey();
            fVar = (f) entry.getValue();
        } while (!i5.a.c(str, "androidx.lifecycle.internal.SavedStateHandlesProvider"));
        return fVar;
    }

    public final void c(String str, f fVar) {
        Object obj;
        i5.a.g("key", str);
        i5.a.g("provider", fVar);
        m.g gVar = this.f6943a;
        m.c a7 = gVar.a(str);
        if (a7 != null) {
            obj = a7.f7474b;
        } else {
            m.c cVar = new m.c(str, fVar);
            gVar.f7485d++;
            m.c cVar2 = gVar.f7483b;
            if (cVar2 == null) {
                gVar.f7482a = cVar;
                gVar.f7483b = cVar;
            } else {
                cVar2.f7475c = cVar;
                cVar.f7476d = cVar2;
                gVar.f7483b = cVar;
            }
            obj = null;
        }
        if (((f) obj) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }

    public final void d() {
        if (!this.f6948f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState".toString());
        }
        p pVar = this.f6947e;
        if (pVar == null) {
            pVar = new p(this);
        }
        this.f6947e = pVar;
        try {
            l.class.getDeclaredConstructor(new Class[0]);
            p pVar2 = this.f6947e;
            if (pVar2 != null) {
                ((Set) pVar2.f842b).add(l.class.getName());
            }
        } catch (NoSuchMethodException e7) {
            throw new IllegalArgumentException("Class " + l.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e7);
        }
    }
}
