package androidx.lifecycle;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o1 {

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f2200a = new LinkedHashMap();

    public final void a() {
        LinkedHashMap linkedHashMap = this.f2200a;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((e1) it.next()).clear();
        }
        linkedHashMap.clear();
    }
}
