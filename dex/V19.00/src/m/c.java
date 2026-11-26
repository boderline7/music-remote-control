package m;

import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Map.Entry {

    /* renamed from: a, reason: collision with root package name */
    public final Object f7473a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7474b;

    /* renamed from: c, reason: collision with root package name */
    public c f7475c;

    /* renamed from: d, reason: collision with root package name */
    public c f7476d;

    public c(Object obj, Object obj2) {
        this.f7473a = obj;
        this.f7474b = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f7473a.equals(cVar.f7473a) && this.f7474b.equals(cVar.f7474b);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f7473a;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f7474b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f7473a.hashCode() ^ this.f7474b.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f7473a + "=" + this.f7474b;
    }
}
