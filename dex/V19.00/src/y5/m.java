package y5;

import s5.q0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {
    public static final l Companion = new l();
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_MISDIRECTED_REQUEST = 421;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_TEMP_REDIRECT = 307;

    /* renamed from: a, reason: collision with root package name */
    public final q0 f10226a;

    /* renamed from: b, reason: collision with root package name */
    public final int f10227b;

    /* renamed from: c, reason: collision with root package name */
    public final String f10228c;

    public m(q0 q0Var, int i7, String str) {
        i5.a.g("protocol", q0Var);
        this.f10226a = q0Var;
        this.f10227b = i7;
        this.f10228c = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f10226a == q0.HTTP_1_0) {
            sb.append("HTTP/1.0");
        } else {
            sb.append("HTTP/1.1");
        }
        sb.append(' ');
        sb.append(this.f10227b);
        sb.append(' ');
        sb.append(this.f10228c);
        String sb2 = sb.toString();
        i5.a.f("StringBuilder().apply(builderAction).toString()", sb2);
        return sb2;
    }
}
