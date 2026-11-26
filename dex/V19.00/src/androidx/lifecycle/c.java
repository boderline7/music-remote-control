package androidx.lifecycle;

import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final int f2152a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f2153b;

    public c(Method method, int i7) {
        this.f2152a = i7;
        this.f2153b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2152a == cVar.f2152a && this.f2153b.getName().equals(cVar.f2153b.getName());
    }

    public final int hashCode() {
        return this.f2153b.getName().hashCode() + (this.f2152a * 31);
    }
}
