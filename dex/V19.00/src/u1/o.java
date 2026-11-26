package u1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    public final h1.b f9292a;

    /* renamed from: b, reason: collision with root package name */
    public final q f9293b;

    /* renamed from: c, reason: collision with root package name */
    public int f9294c = -1;

    /* renamed from: d, reason: collision with root package name */
    public a2.m f9295d;

    public o(h1.b bVar, q qVar) {
        this.f9292a = bVar;
        this.f9293b = qVar;
    }

    public final a2.m a() {
        if (this.f9295d == null) {
            this.f9292a.g();
            if (this.f9295d == null) {
                throw new AssertionError();
            }
        }
        return this.f9295d;
    }

    public final String toString() {
        return a2.m.PREFIX + this.f9294c + "(" + this.f9293b + ")";
    }
}
