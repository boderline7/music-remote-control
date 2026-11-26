package n2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public long f7736a;

    /* renamed from: b, reason: collision with root package name */
    public long f7737b;

    /* renamed from: c, reason: collision with root package name */
    public long f7738c;

    public final void a(long j7) {
        this.f7737b = j7 & 4294967295L;
    }

    public final void b(long j7) {
        this.f7736a = j7 & 4294967295L;
    }

    public final String toString() {
        return "SubRange[\n  lowCount=" + this.f7736a + "\n  highCount=" + this.f7737b + "\n  scale=" + this.f7738c + "]";
    }
}
