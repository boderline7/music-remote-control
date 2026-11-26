package d2;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final Class f6265a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f6266b;

    /* renamed from: c, reason: collision with root package name */
    public final ClassLoader f6267c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f6268d = false;

    public b(Class cls, ArrayList arrayList, ClassLoader classLoader) {
        this.f6265a = cls;
        this.f6266b = new ArrayList(arrayList);
        this.f6267c = classLoader;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f6265a == bVar.f6265a && this.f6266b.equals(bVar.f6266b) && this.f6267c == bVar.f6267c && this.f6268d == bVar.f6268d;
    }

    public final int hashCode() {
        return this.f6267c.hashCode() + this.f6266b.hashCode() + this.f6265a.hashCode() + (this.f6268d ? 1 : 0);
    }
}
