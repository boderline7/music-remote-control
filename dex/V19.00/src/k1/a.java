package k1;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import androidx.startup.StartupException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: d, reason: collision with root package name */
    public static volatile a f7204d;

    /* renamed from: e, reason: collision with root package name */
    public static final Object f7205e = new Object();

    /* renamed from: c, reason: collision with root package name */
    public final Context f7208c;

    /* renamed from: b, reason: collision with root package name */
    public final HashSet f7207b = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f7206a = new HashMap();

    public a(Context context) {
        this.f7208c = context.getApplicationContext();
    }

    public static a c(Context context) {
        if (f7204d == null) {
            synchronized (f7205e) {
                try {
                    if (f7204d == null) {
                        f7204d = new a(context);
                    }
                } finally {
                }
            }
        }
        return f7204d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.f7208c.getString(c.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    hashSet = this.f7207b;
                    if (!hasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (b.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e7) {
                throw new StartupException(e7);
            }
        }
    }

    public final Object b(Class cls, HashSet hashSet) {
        Object obj;
        if (z.E0()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
        }
        HashMap hashMap = this.f7206a;
        if (hashMap.containsKey(cls)) {
            obj = hashMap.get(cls);
        } else {
            hashSet.add(cls);
            try {
                b bVar = (b) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class> a7 = bVar.a();
                if (!a7.isEmpty()) {
                    for (Class cls2 : a7) {
                        if (!hashMap.containsKey(cls2)) {
                            b(cls2, hashSet);
                        }
                    }
                }
                obj = bVar.b(this.f7208c);
                hashSet.remove(cls);
                hashMap.put(cls, obj);
            } catch (Throwable th2) {
                throw new StartupException(th2);
            }
        }
        Trace.endSection();
        return obj;
    }
}
