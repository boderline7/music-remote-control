package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d {

    /* renamed from: c, reason: collision with root package name */
    public static final d f2159c = new d();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f2160a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f2161b = new HashMap();

    public static void c(HashMap hashMap, c cVar, q qVar, Class cls) {
        q qVar2 = (q) hashMap.get(cVar);
        if (qVar2 == null || qVar == qVar2) {
            if (qVar2 == null) {
                hashMap.put(cVar, qVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + cVar.f2153b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + qVar2 + ", new value " + qVar);
    }

    public final b a(Class cls, Method[] methodArr) {
        int i7;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            hashMap.putAll(b(superclass).f2148b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : b(cls2).f2148b.entrySet()) {
                c(hashMap, (c) entry.getKey(), (q) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e7) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e7);
            }
        }
        boolean z6 = false;
        for (Method method : methodArr) {
            i0 i0Var = (i0) method.getAnnotation(i0.class);
            if (i0Var != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i7 = 0;
                } else {
                    if (!x.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i7 = 1;
                }
                q value = i0Var.value();
                if (parameterTypes.length > 1) {
                    if (!q.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (value != q.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i7 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                c(hashMap, new c(method, i7), value, cls);
                z6 = true;
            }
        }
        b bVar = new b(hashMap);
        this.f2160a.put(cls, bVar);
        this.f2161b.put(cls, Boolean.valueOf(z6));
        return bVar;
    }

    public final b b(Class cls) {
        b bVar = (b) this.f2160a.get(cls);
        return bVar != null ? bVar : a(cls, null);
    }
}
