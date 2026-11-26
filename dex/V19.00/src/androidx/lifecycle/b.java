package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f2147a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final Map f2148b;

    public b(HashMap hashMap) {
        this.f2148b = hashMap;
        for (Map.Entry entry : hashMap.entrySet()) {
            q qVar = (q) entry.getValue();
            List list = (List) this.f2147a.get(qVar);
            if (list == null) {
                list = new ArrayList();
                this.f2147a.put(qVar, list);
            }
            list.add((c) entry.getKey());
        }
    }

    public static void a(List list, x xVar, q qVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                c cVar = (c) list.get(size);
                cVar.getClass();
                try {
                    int i7 = cVar.f2152a;
                    Method method = cVar.f2153b;
                    if (i7 == 0) {
                        method.invoke(obj, new Object[0]);
                    } else if (i7 == 1) {
                        method.invoke(obj, xVar);
                    } else if (i7 == 2) {
                        method.invoke(obj, xVar, qVar);
                    }
                } catch (IllegalAccessException e7) {
                    throw new RuntimeException(e7);
                } catch (InvocationTargetException e8) {
                    throw new RuntimeException("Failed to call observer method", e8.getCause());
                }
            }
        }
    }
}
