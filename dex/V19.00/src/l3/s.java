package l3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends d {

    /* renamed from: g, reason: collision with root package name */
    public int f7418g;

    /* renamed from: h, reason: collision with root package name */
    public int f7419h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f7420i;

    @Override // l3.d
    public final void a() {
        if (this.f7418g == 0) {
            if (this.f7344b > 0) {
                throw new IllegalArgumentException("Rounded corners are not supported in contiguous indeterminate animation.");
            }
            if (this.f7345c.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }
}
