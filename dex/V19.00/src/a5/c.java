package a5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f316a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f317b = new ArrayList();

    public c(Map map) {
        String str = (String) map.get("cookie");
        if (str != null) {
            for (String str2 : str.split(";")) {
                String[] split = str2.trim().split("=");
                if (split.length == 2) {
                    this.f316a.put(split[0], split[1]);
                }
            }
        }
    }

    public final void a() {
        Iterator it = this.f317b.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f316a.keySet().iterator();
    }
}
