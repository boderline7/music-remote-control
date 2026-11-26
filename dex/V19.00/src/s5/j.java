package s5;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {
    public static final h Companion = new h();
    public static final j DEFAULT;

    /* renamed from: a, reason: collision with root package name */
    public final Set f8806a;

    /* renamed from: b, reason: collision with root package name */
    public final f6.e f8807b;

    static {
        Set set;
        List list = (List) new v2.a(18).f9388b;
        i5.a.g("<this>", list);
        int size = list.size();
        if (size == 0) {
            set = c5.t.INSTANCE;
        } else if (size != 1) {
            set = new LinkedHashSet(n1.z.I0(list.size()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                set.add(it.next());
            }
        } else {
            set = Collections.singleton(list.get(0));
            i5.a.f("singleton(element)", set);
        }
        DEFAULT = new j(set, null);
    }

    public j(Set set, f6.e eVar) {
        i5.a.g("pins", set);
        this.f8806a = set;
        this.f8807b = eVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (i5.a.c(jVar.f8806a, this.f8806a) && i5.a.c(jVar.f8807b, this.f8807b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f8806a.hashCode() + 1517) * 41;
        f6.e eVar = this.f8807b;
        return hashCode + (eVar != null ? eVar.hashCode() : 0);
    }
}
