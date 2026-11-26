package a4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public int f267a;

    /* renamed from: b, reason: collision with root package name */
    public int f268b;

    /* renamed from: c, reason: collision with root package name */
    public int f269c;

    /* renamed from: d, reason: collision with root package name */
    public String f270d;

    /* renamed from: e, reason: collision with root package name */
    public Enum f271e;

    /* renamed from: f, reason: collision with root package name */
    public Object f272f;

    /* renamed from: g, reason: collision with root package name */
    public Object f273g;

    /* renamed from: h, reason: collision with root package name */
    public Comparable f274h;

    /* renamed from: i, reason: collision with root package name */
    public Object f275i;

    public final int a() {
        return ((StringBuilder) this.f274h).length();
    }

    public final char b() {
        return this.f270d.charAt(this.f267a);
    }

    public final boolean c() {
        return this.f267a < this.f270d.length() - this.f269c;
    }

    public final void d(int i7) {
        j jVar = (j) this.f275i;
        if (jVar == null || i7 > jVar.f297b) {
            k kVar = (k) this.f271e;
            a.a.u(this.f272f);
            a.a.u(this.f273g);
            this.f275i = j.e(i7, kVar);
        }
    }

    public final void e(char c7) {
        ((StringBuilder) this.f274h).append(c7);
    }
}
