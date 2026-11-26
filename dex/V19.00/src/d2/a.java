package d2;

import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final String f6261a;

    /* renamed from: b, reason: collision with root package name */
    public final Class[] f6262b;

    /* renamed from: c, reason: collision with root package name */
    public final Class f6263c;

    /* renamed from: d, reason: collision with root package name */
    public final Method f6264d;

    public a(Method method) {
        this.f6264d = method;
        this.f6261a = method.getName();
        this.f6262b = method.getParameterTypes();
        this.f6263c = method.getReturnType();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f6261a.equals(aVar.f6261a) && this.f6263c.equals(aVar.f6263c) && Arrays.equals(this.f6262b, aVar.f6262b);
    }

    public final int hashCode() {
        int hashCode = this.f6261a.hashCode() + 544;
        int hashCode2 = this.f6263c.hashCode() + (hashCode * 31) + hashCode;
        return (hashCode2 * 31) + Arrays.hashCode(this.f6262b) + hashCode2;
    }
}
