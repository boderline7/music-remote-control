package l5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends b {
    public static final d Companion = new d();

    /* renamed from: d, reason: collision with root package name */
    public static final e f7457d = new e(1, 0, 1);

    @Override // l5.b
    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            if (!isEmpty() || !((e) obj).isEmpty()) {
                e eVar = (e) obj;
                if (this.f7450a == eVar.f7450a) {
                    if (this.f7451b == eVar.f7451b) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // l5.b
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f7450a * 31) + this.f7451b;
    }

    @Override // l5.b
    public final boolean isEmpty() {
        return this.f7450a > this.f7451b;
    }

    @Override // l5.b
    public final String toString() {
        return this.f7450a + ".." + this.f7451b;
    }
}
