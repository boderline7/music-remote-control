package b5;

import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public final Object f2975a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2976b;

    public a(Object obj, Object obj2) {
        this.f2975a = obj;
        this.f2976b = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return i5.a.c(this.f2975a, aVar.f2975a) && i5.a.c(this.f2976b, aVar.f2976b);
    }

    public final int hashCode() {
        Object obj = this.f2975a;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f2976b;
        return hashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.f2975a + ", " + this.f2976b + ')';
    }
}
