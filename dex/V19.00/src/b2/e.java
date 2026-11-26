package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends m {
    public static final e VALUE_FALSE = new e(0);
    public static final e VALUE_TRUE = new e(1);

    @Override // c2.d
    public final c2.c b() {
        return c2.c.BOOLEAN;
    }

    @Override // e2.i
    public final String d() {
        return this.f2934a == 0 ? "false" : "true";
    }

    @Override // b2.a
    public final String f() {
        return "boolean";
    }

    public final String toString() {
        return this.f2934a == 0 ? "boolean{false}" : "boolean{true}";
    }
}
