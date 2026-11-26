package s5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum q0 {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final p0 Companion = new p0();

    /* renamed from: a, reason: collision with root package name */
    public final String f8872a;

    q0(String str) {
        this.f8872a = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f8872a;
    }
}
