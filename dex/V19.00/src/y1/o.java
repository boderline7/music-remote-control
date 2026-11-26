package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class o extends p {

    /* renamed from: a, reason: collision with root package name */
    public int f10101a = -1;

    public final int e() {
        int i7 = this.f10101a;
        if (i7 >= 0) {
            return i7;
        }
        throw new RuntimeException("index not yet set");
    }

    public final String f() {
        return "[" + Integer.toHexString(this.f10101a) + ']';
    }

    public final void g(int i7) {
        if (this.f10101a != -1) {
            throw new RuntimeException("index already set");
        }
        this.f10101a = i7;
    }
}
