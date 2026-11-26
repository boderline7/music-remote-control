package i5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements d {

    /* renamed from: a, reason: collision with root package name */
    public final Class f6811a;

    public i(Class cls) {
        a.g("jClass", cls);
        this.f6811a = cls;
    }

    @Override // i5.d
    public final Class a() {
        return this.f6811a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            if (a.c(this.f6811a, ((i) obj).f6811a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f6811a.hashCode();
    }

    public final String toString() {
        return this.f6811a.toString() + " (Kotlin reflection is not available)";
    }
}
